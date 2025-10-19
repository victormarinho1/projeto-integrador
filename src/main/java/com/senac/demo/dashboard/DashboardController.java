package com.senac.demo.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/total-status")
    public ResponseEntity<ContagemStatusDenunciaDTO> getTotalStatus() {
        ContagemStatusDenunciaDTO c = this.dashboardService.contagemStatusDenunciaDTO();
        return ResponseEntity.ok(c);
    }
}
