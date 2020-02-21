package com.test.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description BaseEntity
 *
 * @author aji gojali
 */
@Data @MappedSuperclass
public class BaseEntity implements Serializable {
    @Id @NotNull @Column(columnDefinition = "varchar(40)")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

}
