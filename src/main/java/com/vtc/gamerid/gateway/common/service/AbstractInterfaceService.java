/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.service;

import java.io.Serializable;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 16, 2020
 */
public interface AbstractInterfaceService<T, ID extends Serializable>  {
    
    T get(ID id);

}
