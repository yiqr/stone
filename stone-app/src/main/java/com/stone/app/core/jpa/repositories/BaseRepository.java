package com.stone.app.core.jpa.repositories;

import com.stone.app.core.jpa.mode.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author rose
 * @date 2022/11/8 11:04
 */
@NoRepositoryBean
public interface BaseRepository<T extends Domain, ID extends Serializable> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {
}
