/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblTopupCardHistory;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Aug 19, 2019
 */
@Repository
public interface TopupCardHistoryRepository extends JpaRepository<TblTopupCardHistory, Long> {
    
    @Query(value = "from TblTopupCardHistory where "
            + " (paymentType = ?1 or ?1 is null) "
            + " and (luckyWheelUsed = ?2 or ?2 is null) "
            + " and paymentTime between ?3 and ?4")
    List<TblTopupCardHistory> getByPaymentTypeAndLuckyWheelUsedBetweenDate(String cardType, 
                                                                    Boolean luckyWheelUsed, 
                                                                    Date startDate, 
                                                                    Date endDate);

}
