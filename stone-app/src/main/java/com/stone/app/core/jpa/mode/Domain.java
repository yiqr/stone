package com.stone.app.core.jpa.mode;

import com.stone.app.core.jpa.listener.DomainListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * @author rose
 * @date 2022/11/8 15:45
 */
@Getter
@Setter
@MappedSuperclass
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = {DomainListener.class})
public abstract class Domain extends mode<String> {
}
