package com.tmitri.splattask.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Дмитрий on 15.10.2016.
 */
//Модель банковского счета
@Entity
@Table(name="account")
public class Account {
    private Integer id;
    private Long value;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name="id")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "value", nullable = false, columnDefinition = "int default 100")
    public Long getValue() {
        return this.value;
    }

    public void setValue(Long value) {
        this.value = value;
    }


}
