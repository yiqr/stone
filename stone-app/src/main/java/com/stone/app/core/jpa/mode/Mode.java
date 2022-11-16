package com.stone.app.core.jpa.mode;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author rose
 * @date 2022/11/8 10:27
 */
@Getter
@Setter
@MappedSuperclass
public class Mode<K extends Serializable> implements Serializable {


    protected static final int ID_LENGTH = 40;
    protected static final int REMARK_LENGTH = 200;
    protected static final int ACTIVE_STATUS_LENGTH = 2;
    protected static final int AMOUNT_LENGTH = 40;
    protected static final int NAME_LENGTH = 120;
    protected static final int BOOLEAN_LENGTH = 1;


    @Id
    @GeneratedValue(generator = "customGenerationId")
    @GenericGenerator(name = "customGenerationId", strategy = "com.stone.app.core.jpa.strategy.IdGenerationStrategy",
            parameters = {
                    @Parameter(
                            name = "idPrefix",
                            value = ""
                    )
            }
    )
    @Column(name = "id", length = 70)
    private K k;
}
