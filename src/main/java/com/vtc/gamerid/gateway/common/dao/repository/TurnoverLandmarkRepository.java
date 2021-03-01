/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblTurnoverLandmark;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Sep 9, 2019
 */
@Repository
public interface TurnoverLandmarkRepository extends JpaRepository<TblTurnoverLandmark, Long> {
    
    @Query(value = "from TblTurnoverLandmark where "
            + " (id = ?1 or ?1 < 1) "
            + " and (game = ?2 or ?2 is null ) "
            + " and (gameId = ?3 or ?3 < 1) "
            + " and (turnoverLandmark = ?4 or ?4 < 1) "
            + " order by turnoverLandmark asc ")
    public List<TblTurnoverLandmark> getTurnoverLandmark(long Id, String game, long GameId, long turnoverLandmark);
    
    @Query(value = "select count(id) from TblTurnoverLandmark")
    public int countAllRecordEvent();
    
    @Query(value = "from TblTurnoverLandmark where "
            + " game = ?1 "
            + " and gameId = ?2 "
            + " and itemId = ?3 "
            + " and turnoverLandmark = ?4 "
            + " and (limitType = ?5 or ?5 is null)")
    public TblTurnoverLandmark findByGameIdAndItemAndTurnoverAndType(String game, 
                                                                     long gameId,
                                                                     Long itemId, 
                                                                     long turnover,
                                                                     String type);

}
