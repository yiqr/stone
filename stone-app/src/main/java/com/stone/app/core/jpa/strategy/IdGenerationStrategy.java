package com.stone.app.core.jpa.strategy;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.spi.Configurable;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author rose
 * @date 2022/11/8 10:47
 */
public class IdGenerationStrategy implements Configurable, IdentifierGenerator {

    private String idPrefix;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        if (StringUtils.isNotEmpty(idPrefix)) {
            return idPrefix + "_" + uuid;
        }
        return uuid;
    }

    @Override
    public void configure(Map configurationValues) {
        Object prefix = configurationValues.get("idPrefix");
        if (Objects.nonNull(prefix)) {
            this.idPrefix = String.valueOf(prefix);
        }
    }
}
