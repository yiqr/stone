package com.stone.app.core.jpa.repositories;

import com.stone.app.core.jpa.mode.Domain;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author rose
 * @date 2022/11/8 11:04
 */
@NoRepositoryBean
public interface DomainRepository<T extends Domain> extends ModelRepository<T, String>
        // , QuerydslPredicateExecutor<T>
{
}
