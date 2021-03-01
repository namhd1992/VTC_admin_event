package com.vtc.gamerid.gateway.common.dao.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.vtc.gamerid.gateway.common.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by phucnguyen on 24/04/2017.
 */
@Entity
@Table(name = "tblGroupRole")
@Setter
@Getter
@NoArgsConstructor
public class TblGroupRole {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int point=1;

    private String status = Constants.STATUS_ACTIVE;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "tblGroupRole_Function", joinColumns = @JoinColumn(name = "groupRoleId"), inverseJoinColumns = @JoinColumn(name = "functionId"))
    private List<TblFunction> functionList;

    public TblGroupRole(String name, int point, String status) {
        this.name = name;
        this.point = point;
        this.status = status;
    }

}
