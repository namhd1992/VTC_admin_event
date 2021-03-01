package com.vtc.gamerid.gateway.adbSetting.bean;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 29/08/2017.
 */
@SuppressWarnings("serial")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdbSettingRequest implements Serializable{
    
    private List<AdbSettingBean> dataArr;

}
