package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 07/08/2017.
 */
@Entity
@Table(name = "tblAdbSetting")
@Setter
@Getter
@NoArgsConstructor
public class TblAdbSetting {
    @Id
    @GeneratedValue
    private Long   id;

    private String name;

    private String value;

    public TblAdbSetting(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
