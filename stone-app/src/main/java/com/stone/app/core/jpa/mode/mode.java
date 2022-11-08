package com.stone.app.core.jpa.mode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author rose
 * @date 2022/11/8 10:27
 */
@Data
public class mode<ID extends Serializable> implements Serializable {

    @Id
    @JsonProperty("id")
    @GeneratedValue(generator = "systemUuidGenStrategy")
    @GenericGenerator(name = "systemUuidGenStrategy", strategy = "uuid2",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy",
                            value = "com.stone.starter.jpa.core.strategy.IdGenerationStrategy"
                    )
            }
    )
    @Column(name = "id")
    private ID id;

    @JsonProperty("createdAt")
    @Column(name = "created_at")
    private Date createdAt;

    @JsonProperty("updatedAt")
    @Column(name = "updated_at")
    private Date updatedAt;
}
