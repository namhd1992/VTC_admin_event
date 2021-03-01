package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.UserInfo;

/**
 * Created by phucnguyen on 15/03/2017.
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    public UserInfo findByEmail(String email);
    
//    @Query(value = "select a from UserInfo a " +
//            "left join a.userGameRID b " +
//            "left join a.userVTC c " +
//            "left join a.groupRole groupRole where (a.email like ?1 " +
//            "or a.fullName like ?2 " +
//            "or b.username like ?3 " +
//            "or c.username like ?4) " + 
//            " and (groupRole.name = ?5 or ?5 is null)")
//    public List<UserInfo> getAllUserInfo(String email, String fullName, String userNameGamerId, String userName, String groupRoleName);
    
    @Query(value = "select count(a.id) from UserInfo a " +
//            "left join a.userGameRID b " +
            "left join a.userVTC c where (a.status = ?1 or ?1 is null) " +
            "and (a.email like ?2 " +
            "or a.fullName like ?3 " +
//            "or b.username like ?4 " +
            "or c.username like ?4) ")
    public int countUserInfo(String status, String email, String fullName, String userName);
    
    List<UserInfo> findByGroupRoleId(Long groupRoleId);
    
    @Query(value = "from UserInfo u inner join u.userVTC uv where uv.scoinId = ?")
    UserInfo findByUserVTCScoinId(Long scoinId);

}
