package team.qiruan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import team.qiruan.service.UserBindService;

/**
 * 绑定到根目录的Controller
 */
@Controller
public class MainController {
    @Autowired
    private UserBindService userBindService;

    @GetMapping("/")
    String index(){
        return "index";
    }

    @GetMapping("/bindvali/{item}/{valicode}")
    String bindValidate(@PathVariable String item,@PathVariable String valicode,Model model){
        if(userBindService.validateUserBind(item, valicode)){
            model.addAttribute("text", "邮箱绑定成功！");
        }else{
            model.addAttribute("text", "邮箱绑定失败。");
        }
        return "user/validate";
    }
}