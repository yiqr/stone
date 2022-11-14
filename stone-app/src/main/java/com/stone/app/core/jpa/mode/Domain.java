package com.stone.app.core.jpa.mode;

import com.stone.app.core.jpa.listener.DomainListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author rose
 * @date 2022/11/8 15:45
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = {DomainListener.class})
public abstract class Domain extends mode<String> {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = ID_LENGTH)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = ID_LENGTH)
    private String updatedBy;

    @Column(name = "remark", length = REMARK_LENGTH)
    protected String remark;
}
