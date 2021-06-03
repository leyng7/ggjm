package me.ryeong.ggjm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartyController {

    @GetMapping("/parties")
    public String list() {
        return "parties/list";
    }

}
