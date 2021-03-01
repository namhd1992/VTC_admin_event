package com.vtc.gamerid.gateway.common.dto.request;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 07/03/2017.
 */
@SuppressWarnings("serial")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDataRequest implements Serializable{
    private String deviceId;
    private String deviceType;
    private String language;
    private String appVersion;
    private int limit = 5;
    private int offset = 0;
    private Map<String, Object> data;

    public BaseDataRequest(String deviceId, String deviceType, String language,
                           String appVersion, Map<String, Object> data) {
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.language = language;
        this.appVersion = appVersion;
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseDataRequest{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", language='" + language + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", data=" + data +
                '}';
    }
}
