package com.stone.app.core.jpa.service;

import com.stone.app.core.jpa.mode.Domain;
import com.stone.app.core.jpa.repositories.DomainRepository;
import org.springframework.data.domain.Example;

import java.util.Optional;

/**
 * @author rose
 * @date 2022-11-19 9:32
 */
public interface DomainService<T extends Domain, Repository extends DomainRepository<T>> {
    Repository getRepository();

    default T findOne(Example<T> example) {
        Optional<T> one = getRepository().findOne(example);
        // Optional<T> one = getRepository().findOne(predicate);
        return one.isEmpty() ? null : one.get();
    }

}
