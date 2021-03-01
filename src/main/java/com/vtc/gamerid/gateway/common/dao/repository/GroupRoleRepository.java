package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblGroupRole;

/**
 * Created by phucnguyen on 25/04/2017.
 */
@Repository
public interface GroupRoleRepository extends JpaRepository<TblGroupRole, Long> {
    
    @Query(value = "from TblGroupRole")
    public List<TblGroupRole> findAllGroupRole(Pageable pageable);

    public TblGroupRole findByName(String name);

    @Query(value = "select count(id) from TblGroupRole where (status = ? or ? is null)")
    public int countGroupRole(String status);

}
