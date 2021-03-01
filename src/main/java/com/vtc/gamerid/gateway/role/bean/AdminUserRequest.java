package com.vtc.gamerid.gateway.role.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.request.BeanRequest;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import java.io.Serializable;

/**
 * Created by phucnguyen on 15/09/2017.
 */
public class AdminUserRequest implements Serializable, BeanRequest{
    private int limit = 10;
    private int offset = 0;
    private String searchValue = "";
    private String roleName = null;

    @Override
    public ValidateBean validate() {
        if(this.searchValue != null && this.searchValue.length() > 1
            && !RegularExpression.validateStripXss(this.searchValue))
            return new ValidateBean(false, "Search value invalid");
        if(this.roleName != null && !RegularExpression.validateStripXss(this.roleName))
            return new ValidateBean(false, "Role invalid");
        return new ValidateBean(true, Constants.SUCCESS);
    }

    public AdminUserRequest() {
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }


}
