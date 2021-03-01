package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import com.vtc.gamerid.gateway.common.Constants;

/**
 * Created by phucnguyen on 28/04/2017.
 */
@Entity
@Table(name = "tblFunction")
public class TblFunction {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "functionName")
    private String functionName;

    @Column(name = "status")
    private String status = Constants.STATUS_ACTIVE;

    public TblFunction() {
    }

    public TblFunction(String functionName, String status) {
        this.functionName = functionName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
