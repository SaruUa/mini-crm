package com.yehor.minicrm.controller;

import com.yehor.minicrm.service.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebImportController {

    private final ImportService importService;

    public WebImportController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping("/import-clients")
    public String importClientsFromApi() {
        importService.importClientsFromApi(1L);
        return "redirect:/clients-page";
    }
}