package team.qiruan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 绑定到根目录的Controller
 */
@Controller
public class MainController {
    @GetMapping("/")
    String index(){
        return "index";
    }
}