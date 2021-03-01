/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblImage;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Mar 21, 2019
 */
@Repository
public interface ImageRepository extends JpaRepository<TblImage, Long> {
    
    public TblImage findByUrlImage(String urlImage);

}
