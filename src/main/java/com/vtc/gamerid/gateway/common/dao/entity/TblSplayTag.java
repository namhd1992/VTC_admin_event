package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 03/04/2018.
 */
@Entity
@Table(name = "tblSplayTag")
@Getter
@Setter
@NoArgsConstructor
public class TblSplayTag {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String typeName;

    private String backgroundColor;

    public TblSplayTag(String name) {
        this.name = name;
    }

    public TblSplayTag(String name, String typeName) {
        this.name = name;
        this.typeName = typeName;
    }
}
