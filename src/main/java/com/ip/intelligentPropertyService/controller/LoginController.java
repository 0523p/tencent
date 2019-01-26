package com.ip.intelligentPropertyService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/goAnotherPage")
    public String goAnotherPage(HttpServletRequest request) {
        String page = request.getParameter("page");
        return "child/" + page;
    }

    @RequestMapping("/submit")
    public String submit() {
        return "child/test";
    }

    @RequestMapping("/showPdf")
    public String getpdf(HttpServletRequest request, Model model) {
        String fileId = request.getParameter("fileId");
        model.addAttribute("fileId", fileId);
        return "showPdf";
    }
}
