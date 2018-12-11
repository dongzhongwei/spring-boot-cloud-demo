package com.ddw.demo.beans;


import com.ddw.demo.domain.Name;
import com.ddw.demo.domain.User;
import org.apache.commons.beanutils.ConversionException;

import java.beans.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

public class BeansDemo {

    public static void main(String[] args) throws Exception {

//        User user = User.builder().build();
        User user = new User();

        //commons-beanutils操作
//        PropertyUtils.setProperty(user,"id",1);
//        BeanUtils.setProperty(user,"id","2");
//        DateConverter converter = new DateConverter( null );
//        converter.setPattern("yyyy-MM-dd");
//        ConvertUtils.register(converter, Date.class);
//        BeanUtils.setProperty(user,"birthday","2018-04-20");

        //jdk操作
        final BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);

        //bean描述符
        final BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        System.out.println(beanDescriptor);
        System.out.println("----------------------------------------------------------");
        //method描述符
        final MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        Arrays.stream(methodDescriptors).forEach(System.out::println);
        System.out.println("----------------------------------------------------------");
        //property描述符
        final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(propertyDescriptor -> {
            String propertyName =  propertyDescriptor.getName();
            if ("id".equals(propertyName)){
                try {
                    String idValue = "2";
                    //获取类型
                    final Class<?> propertyType = propertyDescriptor.getPropertyType();
                    final String typeName = propertyType.getTypeName();
                    final Class<?> targetClass = primitiveToWrapper(propertyType);
                    System.out.printf("propertyName:%s,propertyType:%s,typeName\n",propertyName,propertyType,typeName);
                    propertyDescriptor.getWriteMethod().invoke(user, toNumber(propertyType, targetClass,idValue));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(user);
    }


    private static <T> Class<T> primitiveToWrapper(final Class<T> type) {
        if (type == null || !type.isPrimitive()) {
            return type;
        }

        if (type == Integer.TYPE) {
            return (Class<T>) Integer.class;
        } else if (type == Double.TYPE) {
            return (Class<T>) Double.class;
        } else if (type == Long.TYPE) {
            return (Class<T>) Long.class;
        } else if (type == Boolean.TYPE) {
            return (Class<T>) Boolean.class;
        } else if (type == Float.TYPE) {
            return (Class<T>) Float.class;
        } else if (type == Short.TYPE) {
            return (Class<T>) Short.class;
        } else if (type == Byte.TYPE) {
            return (Class<T>) Byte.class;
        } else if (type == Character.TYPE) {
            return (Class<T>) Character.class;
        } else {
            return type;
        }
    }

    private static Number toNumber(final Class<?> sourceType, final Class<?> targetType, final String value) {

        // Byte
        if (targetType.equals(Byte.class)) {
            return new Byte(value);
        }

        // Short
        if (targetType.equals(Short.class)) {
            return new Short(value);
        }

        // Integer
        if (targetType.equals(Integer.class)) {
            return new Integer(value);
        }

        // Long
        if (targetType.equals(Long.class)) {
            return new Long(value);
        }

        // Float
        if (targetType.equals(Float.class)) {
            return new Float(value);
        }

        // Double
        if (targetType.equals(Double.class)) {
            return new Double(value);
        }

        // BigDecimal
        if (targetType.equals(BigDecimal.class)) {
            return new BigDecimal(value);
        }

        // BigInteger
        if (targetType.equals(BigInteger.class)) {
            return new BigInteger(value);
        }

        throw new ConversionException("");
    }
}
