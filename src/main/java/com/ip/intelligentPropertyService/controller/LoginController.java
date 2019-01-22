package com.ip.intelligentPropertyService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "information_center";
    }

    @RequestMapping("/goVillageInfo")
    public String goVillageInfo() {
        return "child/village_info";
    }

    @RequestMapping("/goBulletinBar")
    public String bulletin_bar() {
        return "child/bulletin_bar";
    }

    @RequestMapping("/goContactUs")
    public String goContactUs() {
        return "child/contact_us";
    }

    @RequestMapping("/goQuestion")
    public String goQuestion() {
        return "child/question";
    }

    @RequestMapping("/goQuestionResult")
    public String goQuestionResult() {
        return "child/questionResult";
    }

    @RequestMapping("/submit")
    public String submit() {
        return "child/test";
    }

    @RequestMapping("/pdf")
    public String getpdf() {
        return "showpdf";
    }
}
