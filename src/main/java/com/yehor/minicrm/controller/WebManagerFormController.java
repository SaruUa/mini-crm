package com.yehor.minicrm.controller;

import com.yehor.minicrm.dto.ManagerRequestDto;
import com.yehor.minicrm.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebManagerFormController {

    private final ManagerService managerService;

    public WebManagerFormController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/managers-page/new")
    public String showCreateManagerForm(Model model) {
        model.addAttribute("manager", new ManagerRequestDto());
        return "manager-form";
    }

    @PostMapping("/managers-page")
    public String createManagerFromForm(@Valid @ModelAttribute("manager") ManagerRequestDto managerDto,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "manager-form";
        }

        managerService.createManager(managerDto);
        return "redirect:/managers-page";
    }
}