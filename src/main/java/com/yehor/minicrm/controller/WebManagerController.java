package com.yehor.minicrm.controller;

import com.yehor.minicrm.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebManagerController {

    private final ManagerService managerService;

    public WebManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/managers-page")
    public String managersPage(Model model) {
        model.addAttribute("managers", managerService.getAllManagers());
        return "managers";
    }
}