package com.stone.app.core.jpa.strategy;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerationStrategy;

import java.util.UUID;

/**
 * @author rose
 * @date 2022/11/8 10:47
 */
public class IdGenerationStrategy implements UUIDGenerationStrategy {

    @Override
    public int getGeneratedVersion() {
        return 1;
    }

    @Override
    public UUID generateUUID(SharedSessionContractImplementor sharedSessionContractImplementor) {
        return UUID.fromString(
                UUID.randomUUID().toString());
    }
}
