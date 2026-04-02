package com.yehor.minicrm.controller;

import com.yehor.minicrm.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebClientController {

    private final ClientService clientService;

    public WebClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients-page")
    public String clientsPage(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients";
    }
}