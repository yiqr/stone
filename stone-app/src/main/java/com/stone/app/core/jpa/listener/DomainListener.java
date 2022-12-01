package com.stone.app.core.jpa.listener;

import com.stone.app.core.jpa.mode.Domain;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author rose
 * @date 2022/11/8 15:47
 */
public class DomainListener {
    @PrePersist
    public void persist(final Domain entity) {
        if (Objects.isNull(entity.getCreatedAt())) {
            entity.setCreatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void update(final Domain entity) {
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
