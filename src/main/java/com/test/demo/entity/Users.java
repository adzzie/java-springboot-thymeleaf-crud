package com.test.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Description peserta
 *
 * @author aji gojali
 */
@Data @Entity
public class Users extends BaseEntity{

    @NotNull
    @Column(columnDefinition = "varchar(100)")
    private String name;

    @NotNull @Column(columnDefinition = "varchar(100)", unique = true)
    private String email;

    @NotNull @Column(columnDefinition = "varchar(100)")
    private String password;

    @Column(columnDefinition = "varchar(2)")
    private String status;
}
