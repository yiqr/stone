package com.stone.app.core.jpa.listener;

import com.stone.app.core.jpa.mode.Domain;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;
import java.util.Objects;

/**
 * @author rose
 * @date 2022/11/8 15:47
 */
public class DomainListener {
    @PrePersist
    public void persist(final Domain entity) {
        if (Objects.isNull(entity.getCreatedAt())) {
            entity.setCreatedAt(new Date());
        }
    }

    @PreUpdate
    public void update(final Domain entity) {
        entity.setUpdatedAt(new Date());
    }
}
