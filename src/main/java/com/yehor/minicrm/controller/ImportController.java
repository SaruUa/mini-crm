package com.yehor.minicrm.controller;

import com.yehor.minicrm.service.ImportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/import")
public class ImportController {

    private final ImportService importService;

    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping("/clients/{managerId}")
    public ResponseEntity<String> importClients(@PathVariable Long managerId) {
        try {
            String result = importService.importClientsFromApi(managerId);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}