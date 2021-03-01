package com.vtc.gamerid.gateway.annotations;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblAuditLog;
import com.vtc.gamerid.gateway.common.dao.entity.TblFunction;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.exception.RequestException;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;

/**
 * Created by phucnguyen on 03/03/2017.
 */
@Aspect
@Component
public class SpringAOPHandle {

    private final Logger logger = LoggerFactory.getLogger(SpringAOPHandle.class);

    @Autowired
    private AdminProfileService adminProfileService;

    /**
     * Context http request
     */
    @Autowired
    private HttpServletRequest httpServletRequest;

    //Audit log
    @Around("execution(* *(..)) && @annotation(auditLogAnnotation)")
    public Object auditLogs(ProceedingJoinPoint point, AuditLogAnnotation auditLogAnnotation) throws Throwable {
        //Get data request
        String dataRequest = null;
        try {
            Object objectRequest = point.getArgs()[0];
            dataRequest = JsonMapperUtils.toJson(objectRequest);
        } catch (Exception e) {

        }

        //Get time server handle
        Date timeRequest = new Date();
        Object objectResponse = point.proceed();
        Date timeResponse = new Date();
        long totalHandle = timeResponse.getTime() - timeRequest.getTime();

        //Get data response
        String dataResponse = "";
        try {
            dataResponse = JsonMapperUtils.toJson(objectResponse);
        } catch (Exception e) {
            logger.error(e.toString());
            dataResponse = "Data response length too max";
        }

        TblAuditLog tblAuditLog = new TblAuditLog(auditLogAnnotation.functionName(), "none",
                dataRequest, timeRequest, totalHandle, dataResponse, httpServletRequest.getRemoteAddr());

        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            try {
            } catch (Exception e) {
            }

            try {
                Map<String, Object> dataDetail = StringUtils.convertStringToMap((String)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal());

                tblAuditLog.setDeviceId((String) dataDetail.get("deviceId"));
                tblAuditLog.setDeviceType((String) dataDetail.get("deviceType"));
                tblAuditLog.setLanguage((String) dataDetail.get("language"));
                tblAuditLog.setAppVersion((String) dataDetail.get("appVersion"));
            } catch (Exception e) {
            }
        }else{
            tblAuditLog.setDeviceId("anonymous");
        }
        if(httpServletRequest.getHeader("User-Agent") != null){
            tblAuditLog.setDeviceType(httpServletRequest.getHeader("User-Agent"));
        }

        String customerId = tblAuditLog.getUserInfo() == null ? "Anonymous" :
                tblAuditLog.getUserInfo().getId()+"";
        logger.info("==> [Request] " + auditLogAnnotation.functionName() + " [" +
                customerId + "] : " + dataRequest);

        if (objectResponse instanceof BaseDataResponse) {
            try {
                BaseDataResponse baseDataResponse = (BaseDataResponse) objectResponse;
                tblAuditLog.setStatus(baseDataResponse.getStatusCode());
                tblAuditLog.setOnlyMessage(baseDataResponse.getOnlyMessage());
            } catch (Exception e) {
                logger.error(e.toString());
            }
        }

        try {

            if(tblAuditLog != null && dataResponse.length() > 2000){
                logger.info("==> [Response] " + auditLogAnnotation.functionName() + " [" +
                        customerId + "] : AuditLogsId - " + tblAuditLog.getId());
            }else{
                logger.info("==> [Response] " + auditLogAnnotation.functionName() + " [" +
                        customerId + "] : " + dataResponse);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }

        return objectResponse;
    }

    //Check permission
    @Around("execution(* *(..)) && @annotation(permissionService)")
    public Object around(ProceedingJoinPoint point, PermissionService permissionService) throws Throwable {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            UserGameRID admin = adminProfileService.getSessionUserInfo();
            if (admin == null || admin.getGroupRole() == null
                    || admin.getGroupRole().getFunctionList() == null
                    || !admin.getGroupRole().getStatus().equals(Constants.STATUS_ACTIVE)) {
                throw new RequestException(Constants.NOT_PERMISSION, Constants.STATUS_CODE_FALSE,
                        4);
            }

            for (TblFunction instance : admin.getGroupRole().getFunctionList()) {
                if (instance.getFunctionName().equals(permissionService.functionName())
                        && instance.getStatus().equals(Constants.STATUS_ACTIVE)) {
                    return point.proceed(); // Continue
                                            // function
                }
            }
            throw new RequestException(Constants.NOT_PERMISSION, Constants.STATUS_CODE_FALSE, 4);
        }
        throw new RequestException(Constants.NOT_PERMISSION, Constants.STATUS_CODE_FALSE, 4);
    }
}
