package com.vtc.gamerid.gateway.profile.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 19/06/2017.
 */
@Setter
@Getter
@NoArgsConstructor
public class SearchUserBeanResponse implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -3706469834530866647L;

    private int               accountNumber;

    private int               userId;

    private String            fullName;

}
