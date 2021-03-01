package com.vtc.gamerid.gateway.luckyspin.bean;

import java.io.Serializable;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.ultils.VNCharacterUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 23/06/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpinItemSqlRequest implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private int               limit            = 10;

    private int               offset           = 0;

    private Long              createBy;

    private long              spinId           = 0;

    private String            searchValue      = null;

    private String            status           = Constants.STATUS_ACTIVE;

    private int               isDefault        = -1;

    public String getItemSpinSql() {
        StringBuffer sql = new StringBuffer(" where 1=1 ");
        if(this.spinId > 0) sql.append(" and s.id = "+this.spinId);
        if(this.status != null) sql.append(" and s.status = '"+this.status+"'");
            else sql.append(" and s.status = '"+Constants.STATUS_ACTIVE+"'");
        if(this.isDefault > -1)
            sql.append(" and s.isDefault = "+this.isDefault);
        if(this.searchValue != null)
            sql.append(" and (item.name like '%"+this.searchValue+"%' " +
                    "or item.keyName like '%"+ VNCharacterUtils.removeAccent(this.searchValue)+"%') ");
        sql.append(" order by a.position asc");
        return sql.toString();
    }
}
