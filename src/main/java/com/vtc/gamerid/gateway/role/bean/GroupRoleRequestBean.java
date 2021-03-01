package com.vtc.gamerid.gateway.role.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 25/04/2017.
 */
@Getter
@Setter
@NoArgsConstructor
public class GroupRoleRequestBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -4731304967447801733L;

    private Long              id;

    private String            name;

    private int               point            = -1;

    private String            status;

    private List<Long>        functionList     = new ArrayList<>();

}
