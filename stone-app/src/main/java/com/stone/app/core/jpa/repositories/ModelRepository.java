package com.stone.app.core.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author rose
 * @date 2022-11-19 9:46
 */
@NoRepositoryBean
public interface ModelRepository<T, K extends Serializable> extends JpaRepository<T, K> {
}
