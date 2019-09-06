package team.qiruan.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team.qiruan.domain.UserInfo;
import team.qiruan.service.UserInfoService;


@Controller
@RequestMapping("/cooperate")
public class WorkController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/show")
    String info(Principal principal, Model model) {
        UserInfo userInfo = userInfoService.getUserInfoByName(principal.getName());
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        System.out.println(userInfo);
        model.addAttribute("userinfo", userInfo);
        return "cooperate/show";
    }
    
    @GetMapping("/views")
    String getViews(Principal principal, Model model) {
        UserInfo userInfo = userInfoService.getUserInfoByName(principal.getName());
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        System.out.println(userInfo);
        model.addAttribute("userinfo", userInfo);
        return "cooperate/views";
    }

}