/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.luckyspin.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.luckyspin.facade._interface.AdminReportSpinFacade;

/**
 * Created by phucnguyen on 14/07/2017.
 */
@RestController
public class AdminSpinReportController extends AbstractController<AdminReportSpinFacade> {

    @GetMapping("/admin/spin-result-report")
    public ResponseEntity<?> getResultReport(@RequestParam(value = "lucky_spin_id") long luckySpinId,
                                             long startDate,
                                             long endDate){
        return response(toResult(service.resultReportSpin(luckySpinId, startDate, endDate)));
    }
}

