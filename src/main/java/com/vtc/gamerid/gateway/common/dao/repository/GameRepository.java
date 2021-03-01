package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.Game;

/**
 * Created by phucnguyen on 05/12/2017.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "from Game where "
            + " status <> ?1 "
            + " and (keyName like %?2% or ?2 is null) "
//            + " and (scoinGameId = ?3 or ?3 < 1 )"
            + " and (createBy = ?3 or ?3 is null )"
            + " order by status, updateOn desc ")
    public List<Game> getSplayGame(String status, String keyName, Long createBy);

    @Query(value = "select count(id) from Game where "
            + " status <> ?1 "
            + " and (keyName like ?2 or ?2 is null) "
//            + " and (scoinGameId = ?3 or ?3 < 1 )"
            + " and (createBy = ?3 or ?3 is null )"
            + " order by status, updateOn desc ")
    public int countSplayGame(String status, String keyName, Long createBy);

    public Game findByIdAndStatusNot(Long gameId, String Status);

//    public void updatePostionSplayGame(AdminSplayGamePositionBean dataRequest);

    
    public Game findByScoinGameIdAndStatusNot(Long gameId, String Status);
}
