/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.Giftcode;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 26, 2020
 */
@Repository
public interface GiftcodeRepository extends JpaRepository<Giftcode, Long> {
    
    List<Giftcode> findByEventTypeAndMainEventIdAndSubEventId(String eventType, Long mainEventId, Long subEventId);
    
    int countByEventTypeAndMainEventIdAndSubEventId(String eventType, Long mainEventId, Long subEventId);
    
    int countByEventTypeAndMainEventIdAndSubEventIdAndUserInfoNotNull(String eventType, 
                                                               Long mainEventId, 
                                                               Long subEventId);

}
