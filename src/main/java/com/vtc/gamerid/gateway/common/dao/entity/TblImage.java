/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Mar 21, 2019
 */
@Entity
@Table(name="tblImage")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblImage {
    
    @Id
    @GeneratedValue
    private Long   id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date   createOn;

    private String nameImage;

    private String urlImage;

}
