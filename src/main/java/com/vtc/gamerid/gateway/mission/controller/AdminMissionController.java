package com.vtc.gamerid.gateway.mission.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.mission.bean.MissionBeanRequest;
import com.vtc.gamerid.gateway.mission.bean.MissionFilter;
import com.vtc.gamerid.gateway.mission.service.AdminMissionService;

/**
 * Created by phucnguyen on 21/11/2017.
 */
@RestController
@RequestMapping("/admin/mission")
public class AdminMissionController extends AbstractController<AdminMissionService> {

    @GetMapping()
    public ResponseEntity<?> getMission(MissionFilter dataRequest) {
        return response(toResult(service.getMission(dataRequest), service.countAllRecordMission()));
    };

    @PostMapping()
    public ResponseEntity<?> createMission(@RequestBody MissionBeanRequest dataRequest) {
        return response(toResult(service.createMission(dataRequest)));
    };

    @PutMapping()
    public ResponseEntity<?> updateMission(@RequestBody MissionBeanRequest dataRequest) {
        return response(toResult(service.updateMission(dataRequest)));
    };

    @DeleteMapping()
    public ResponseEntity<?> deleteMission(@RequestParam Long missionId) {
        return response(toResult(service.deleteMission(missionId)));
    };
}
