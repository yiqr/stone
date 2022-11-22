package com.stone.app.core.jpa.service;

import com.querydsl.core.types.Predicate;
import com.stone.app.core.jpa.mode.Domain;
import com.stone.app.core.jpa.repositories.DomainRepository;

import java.util.Optional;

/**
 * @author rose
 * @date 2022-11-19 9:32
 */
public interface DomainService<T extends Domain, Repository extends DomainRepository<T>> {
    Repository getRepository();

    default T findOne(Predicate predicate) {
        Optional<T> one = getRepository().findOne(predicate);
        return one.isEmpty() ? null : one.get();
    }

}
