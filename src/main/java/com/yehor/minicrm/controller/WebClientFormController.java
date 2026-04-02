package com.yehor.minicrm.controller;

import com.yehor.minicrm.dto.ClientRequestDto;
import com.yehor.minicrm.entity.ClientStatus;
import com.yehor.minicrm.service.ClientService;
import com.yehor.minicrm.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class WebClientFormController {

    private final ClientService clientService;
    private final ManagerService managerService;

    public WebClientFormController(ClientService clientService, ManagerService managerService) {
        this.clientService = clientService;
        this.managerService = managerService;
    }

    @GetMapping("/clients-page/new")
    public String showCreateClientForm(Model model) {
        model.addAttribute("client", new ClientRequestDto());
        model.addAttribute("statuses", ClientStatus.values());
        model.addAttribute("managers", managerService.getAllManagers());
        return "client-form";
    }

    @PostMapping("/clients-page")
    public String createClientFromForm(@Valid @ModelAttribute("client") ClientRequestDto clientDto,
                                       BindingResult bindingResult,
                                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ClientStatus.values());
            model.addAttribute("managers", managerService.getAllManagers());
            return "client-form";
        }

        clientService.createClient(clientDto);
        return "redirect:/clients-page";
    }
}