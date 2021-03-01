package com.vtc.gamerid.gateway.common.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class BaseDataResponse implements Serializable{
    private String statusCode;
    private String onlyMessage;
    private Map<String, Object> data;
    private List<?> dataArr;
    private Object dataObj;
    private int totalRecords = 0;
    private int _code;

    public BaseDataResponse(String statusCode, String onlyMessage, List<?> dataArr) {
        this.statusCode = statusCode;
        this.onlyMessage = onlyMessage;
        this.dataArr = dataArr;
        this.totalRecords = 0;
    }

    public BaseDataResponse(String statusCode, String onlyMessage, List<?> dataArr, int totalRecords) {
        this.statusCode = statusCode;
        this.onlyMessage = onlyMessage;
        this.dataArr = dataArr;
        this.totalRecords = totalRecords;
    }

    public BaseDataResponse(String statusCode, String onlyMessage, Map<String, Object> data) {
        this.statusCode = statusCode;
        this.onlyMessage = onlyMessage;
        this.data = data;
        this.totalRecords = 0;
    }

    public BaseDataResponse(String statusCode, String onlyMessage, List<?> dataArr, Object dataObj) {
        this.statusCode = statusCode;
        this.onlyMessage = onlyMessage;
        this.dataArr = dataArr;
        this.dataObj = dataObj;
    }

    public BaseDataResponse(String statusCode, String onlyMessage) {
        this.statusCode = statusCode;
        this.onlyMessage = onlyMessage;
        this.totalRecords = 0;
        this.dataArr = new ArrayList<>();
    }

    public BaseDataResponse(String statusCode, String onlyMessage, int _code) {
        this.statusCode = statusCode;
        this.onlyMessage = onlyMessage;
        this.totalRecords = 0;
        this._code = _code;
    }

    public BaseDataResponse(String statusCode, String onlyMessage, Object dataObj) {
        this.statusCode = statusCode;
        this.onlyMessage = onlyMessage;
        this.dataObj = dataObj;
    }

    public BaseDataResponse(String statusCode, String onlyMessage, Object dataObj, int totalRecords) {
        this.statusCode = statusCode;
        this.onlyMessage = onlyMessage;
        this.dataObj = dataObj;
        this.totalRecords = totalRecords;
    }

    public BaseDataResponse(String statusCode, String onlyMessage, List<?> dataArr, Object dataObj, int totalRecords) {
        this.statusCode = statusCode;
        this.onlyMessage = onlyMessage;
        this.dataArr = dataArr;
        this.dataObj = dataObj;
        this.totalRecords = totalRecords;
    }

//    @Override
//    public String toString() {
//        return "BaseDataResponse{" +
//                "statusCode='" + statusCode + '\'' +
//                ", onlyMessage='" + onlyMessage + '\'' +
//                ", data=" + data +
//                ", dataArr=" + dataArr +
//                '}';
//    }
}
