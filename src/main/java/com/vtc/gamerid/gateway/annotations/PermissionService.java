package com.vtc.gamerid.gateway.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by phucnguyen on 03/04/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionService {
    String functionName() default "";
}
