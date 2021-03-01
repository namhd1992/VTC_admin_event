package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblTags;

/**
 * Created by phucnguyen on 21/03/2017.
 */
@Repository
public interface TagsRepository extends JpaRepository<TblTags, Long> {

}
