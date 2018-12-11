package com.ddw.demo.custom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID> {

    int deleteInBatchById(Iterable<ID> ids);

    <S extends T> int saveInBatch(Iterable<S> entities);

    <S extends T> int updateInBatchById(Iterable<S> entities);

    <S> S findByNativeSql(Class<S> clazz, String sql, Object... params);

    <S> List<S> findAllByNativeSql(Class<S> clazz, String sql, Object... params);

}
