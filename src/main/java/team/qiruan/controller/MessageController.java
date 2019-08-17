package team.qiruan.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.qiruan.domain.Message;
import team.qiruan.domain.Result;
import team.qiruan.service.MessageService;
import team.qiruan.utils.JsoupUtil;

/**
 * 留言相关
 */
@Controller
@RequestMapping("/message/{belong}")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    String showMessage(@PathVariable String belong,Model model){
        List<Message> messages=messageService.getAllMessage(belong);
        model.addAttribute("messages", messages);
        model.addAttribute("count", messages.size());
        model.addAttribute("belong", belong);
        return "message/show";
    }

    @PostMapping
    @ResponseBody
    Result addMessage(@PathVariable String belong,@RequestParam String content,Model model,Principal principal){
        content=JsoupUtil.clean(content);//防止xss
        String username="";
        if(principal!=null){
            username=principal.getName();
        }
        messageService.addMessage(belong, username, content);
        return new Result();
    }
}