package com.vtc.gamerid.gateway.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by phucnguyen on 03/03/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLogAnnotation {
    String functionName() default "None";
}
