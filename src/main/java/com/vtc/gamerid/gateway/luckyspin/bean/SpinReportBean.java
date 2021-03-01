package com.vtc.gamerid.gateway.luckyspin.bean;

import java.io.Serializable;

import com.vtc.gamerid.gateway.common.ultils.VNCharacterUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 14/07/2017.
 */
@Setter
@Getter
@NoArgsConstructor
public class SpinReportBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8119740320606961102L;

    private Long              spinId;

    private Long              userId;

    private int               limit            = 10;

    private int               offset           = 0;

    private String            searchValue      = null;

    private Long              createBy;

    public String sqlReport(){
        StringBuffer hsql = new StringBuffer();
        if(this.spinId > 0 )
            hsql.append(" and spinEvent.id = "+this.spinId);
        if(this.userId > 0)
            hsql.append(" and userInfo.id = "+this.userId);
        if(this.searchValue != null){
            hsql.append(" and (item.name like '%"+this.searchValue+"%' " +
                    "or item.keyName like '%"+
                    VNCharacterUtils.removeAccent(this.searchValue)+"%' " +
                    "or userInfo.fullName like '%+"+this.searchValue+"%') ");
        }
        if(this.createBy > 0)
            hsql.append(" and createBy.id = "+this.createBy);
        return  hsql.toString();
    }
}
