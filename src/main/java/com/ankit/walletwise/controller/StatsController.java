package com.ankit.walletwise.controller;

import com.ankit.walletwise.service.StatsService;
import com.ankit.walletwise.util.AuthenticationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    private final AuthenticationUtils authUtils;

    public StatsController(StatsService statsService,AuthenticationUtils authUtils) {
        this.statsService = statsService;
        this.authUtils=authUtils;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getUserStats(@AuthenticationPrincipal UserDetails userDetails) {
        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        Map<String, Object> stats = statsService.getUserStats(userId);
        return ResponseEntity.ok(stats);
    }


}