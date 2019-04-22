package com.ddw.demo.custom.repository;

import com.ddw.demo.annotation.BatchUpdate;
import com.google.common.base.CaseFormat;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {

    private final JpaEntityInformation entityInformation;

    private final EntityManager entityManager;

    public MyRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityInformation = entityInformation;

        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public int deleteInBatchById(Iterable<ID> ids) {
        String tableName = getDomainClass().getAnnotation(Table.class).name();
        String idName = entityInformation.getIdAttribute().getName();
        String sql = "delete from "+ tableName + " where "+idName+" in (?1)";
        return entityManager.createNativeQuery(sql).setParameter(1, ids).executeUpdate();
    }

    private Query getQuery(String sql, Object... params){
        Query q = entityManager.createNativeQuery(sql);
        if (params != null && params.length > 0){
            for (int i = 0; i < params.length; i++) {
                q.setParameter(i+1,params[i]);
            }
        }
        return q;
    }

    public Map<String,Integer> getSelectedFieldMap(String sql){
        int selectIndex = StringUtils.indexOfIgnoreCase(sql, "select");
        int fromIndex = StringUtils.indexOfIgnoreCase(sql, "from");
        Assert.isTrue(selectIndex > -1 && fromIndex > selectIndex,"sql is error");
        String selectedField = StringUtils.remove(sql.substring(selectIndex+6, fromIndex),StringUtils.SPACE);
        String[] selectedFields = selectedField.split(",");
        Map<String,Integer> selectedFieldMap = new HashMap<String,Integer>();
        for (int i = 0,l = selectedFields.length; i < l; i++) {
            String filed = selectedFields[i].replace("_", "");
            if (StringUtils.containsIgnoreCase(filed,"as")){
                filed = filed.substring(StringUtils.indexOfIgnoreCase(filed,"as")+2);
            }
            selectedFieldMap.put(filed.toLowerCase(), i);
        }
        return selectedFieldMap;
    }

    private <S> S getObject(Class<S> clazz, Map<String,Integer> selectedFieldMap, boolean isArray, Object obj){
        try {
            S s = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0, l = fields.length; i < l; i++) {
                String filedName =  fields[i].getName().toLowerCase();
                if (selectedFieldMap.get(filedName) != null){
                    PropertyUtils.setProperty(s, filedName,
                            isArray ? ((Object[])obj)[selectedFieldMap.get(filedName)] : obj);
                }
            }
            return s;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public <S> S findByNativeSql(Class<S> clazz, String sql, Object... params) {
        Query q = getQuery(sql, params);
        Map<String,Integer> selectedFieldMap = getSelectedFieldMap(sql);
        boolean isArray = false;
        Object obj = q.getSingleResult();
        if (obj instanceof Object[]){
            isArray = true;
        }
        return getObject(clazz, selectedFieldMap, isArray, obj);
    }

    @Override
    public <S> List<S> findAllByNativeSql(Class<S> clazz, String sql, Object... params) {
        Query q = getQuery(sql, params);
        Map<String,Integer> selectedFieldMap = getSelectedFieldMap(sql);
        List<S> result = new ArrayList<S>();
        boolean isArray = false;
        List<Object> resultList = q.getResultList();
        for (Object obj : resultList) {
            if (obj instanceof Object[]){
                isArray = true;
            }
            S s = getObject(clazz, selectedFieldMap, isArray, obj);
            result.add(s);
        }
        return result;
    }

    @Override
    @Transactional
    public <S extends T> int saveInBatch(Iterable<S> entities) {
        if (entities == null) {
            return 0;
        }
        String tableName = getDomainClass().getAnnotation(Table.class).name();
        Field[] declaredFields = getDomainClass().getDeclaredFields();
        StringBuffer sql = new StringBuffer("insert into "+tableName+"(");
        for (Field field : declaredFields) {
            if (!field.getName().equals(entityInformation.getIdAttribute().getName())) {
                String columnName;
                if (field.getAnnotation(Column.class) != null){
                    columnName = field.getAnnotation(Column.class).name();
                } else {
                    columnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,field.getName());
                }
                sql.append(columnName + ",");
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(") values ");
        for (S entity : entities) {
            sql.append("(");
            for (Field field : declaredFields) {
                if (!field.getName().equals(entityInformation.getIdAttribute().getName())) {
                    try {
                        Object value = PropertyUtils.getProperty(entity, field.getName());
                        if (value == null){
                            sql.append("null,");
                        } else {
                            sql.append("'" + value + "',");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append("),");
        }
        return entityManager.createNativeQuery(sql.substring(0, sql.length() - 1)).executeUpdate();
    }


    @Override
    @Transactional
    public <S extends T> int updateInBatchById(Iterable<S> entities) {
        if (entities == null) {
            return 0;
        }
        String idName = entityInformation.getIdAttribute().getName();
        //判断是否所有的id都设置
        for (S entity : entities) {
            Object value = new Object();
            try {
                value = PropertyUtils.getProperty(entity, idName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Assert.isTrue(ObjectUtils.isEmpty(value), "id must not be null!");
        }

        Class<T> domainClass = getDomainClass();
        String tableName = domainClass.getAnnotation(Table.class).name();
        StringBuffer sql = new StringBuffer("insert into "+tableName+"(");
        StringBuffer updateValue =  new StringBuffer();
        Field[] declaredFields = domainClass.getDeclaredFields();
        List<String> updateFiledList = new LinkedList<>();

        for (Field field : declaredFields) {
            if (field.getName().equals(idName) || (field.getAnnotation(BatchUpdate.class) != null
                    && field.getAnnotation(BatchUpdate.class).value())){
                String columnName;
                if (field.getAnnotation(Column.class) != null){
                    columnName = field.getAnnotation(Column.class).name();
                } else {
                    columnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,field.getName());
                }
                sql.append(columnName + ",");
                updateFiledList.add(field.getName());
                if (!field.getName().equals(idName)){
                    updateValue.append(columnName+"=values("+columnName+"),");
                }
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(") values ");
        for (S entity : entities) {
            sql.append("(");
            for (String updateFiled : updateFiledList) {
                try {
                    Object value = PropertyUtils.getProperty(entity, updateFiled);
                    if (value == null){
                        sql.append("null,");
                    } else {
                        sql.append("'" + value + "',");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append("),");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" on duplicate key update ");
        sql.append(updateValue);
        return entityManager.createNativeQuery(sql.substring(0, sql.length() - 1)).executeUpdate();
    }

//    @Override
//    public List<T> findAll(Iterable<ID> ids) {
//        if (ids == null || !ids.iterator().hasNext()) {
//            return Collections.emptyList();
//        }
//        String tableName = getDomainClass().getAnnotation(Table.class).name();
//        String idName = entityInformation.getIdAttribute().getName();
//        String sql = "select * from "+ tableName + " where "+idName+" in (?1)";
//        return entityManager.createNativeQuery(sql, getDomainClass()).setParameter(1,ids).getResultList();
//    }
}
