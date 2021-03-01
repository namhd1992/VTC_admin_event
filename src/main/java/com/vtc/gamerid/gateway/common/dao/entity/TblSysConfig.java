package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 15/03/2017.
 */
@Entity
@Table(name = "tblSysConfig")
@Setter
@Getter
@NoArgsConstructor
public class TblSysConfig {
    @Id
    @GeneratedValue
    private Long   id;

    private String keyName;

    private String value;

    private String status;

    public TblSysConfig(String keyName, String value, String status) {
        this.keyName = keyName;
        this.value = value;
        this.status = status;
    }

}
