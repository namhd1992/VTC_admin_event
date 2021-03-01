package com.vtc.gamerid.gateway.luckyspin.facade._interface;

import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.luckyspin.bean.SpinItemBeanRequest;
import com.vtc.gamerid.gateway.luckyspin.bean.SpinItemSqlRequest;

/**
 * Created by phucnguyen on 03/07/2017.
 */
public interface AdminSpinItemFacade {
    public BaseDataResponse getItemByPublisher(SpinItemSqlRequest spinItemSqlRequest);

    public BaseDataResponse createItem(SpinItemBeanRequest spinItemBeanRequest);

    public BaseDataResponse updateItem(SpinItemBeanRequest spinItemBeanRequest);

    public BaseDataResponse deleteItem(Long itemSpinId);
}
