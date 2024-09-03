package com.muse.home.legacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


public class HapjuController {
    private final HapjuService hapjuService;
    @Autowired public HapjuController(HapjuService hapjuService){
        this.hapjuService = hapjuService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("allHapjuList",hapjuService.getAllHapjuList());
        return "home";
    }

    @GetMapping("/hapju")
    public String hapju(@RequestParam("date") String date, Model model){
        model.addAttribute("date", date);
        model.addAttribute("hapjuList", hapjuService.getHapjuList(date));
        return "hapju";
    }

    @GetMapping("/hapju/new")
    public String createHapju(){
        return "create_hapju";
    }

    @PostMapping("/hapju/new")
    public String hapjuForm(HapjuForm hapjuForm){
        hapjuService.createHapju(hapjuForm);
        return "redirect:/";
    }

}
