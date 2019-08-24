package team.qiruan.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.qiruan.domain.LetterDetail;
import team.qiruan.domain.Result;
import team.qiruan.service.LetterService;
import team.qiruan.utils.JsoupUtil;

/**
 * 站内信
 */
@Controller
@RequestMapping("/letter")
public class LetterController {
    @Autowired
    private LetterService letterService;

    @GetMapping("/send")
    String send(Principal principal,@RequestParam(required = false) String to, Model model) {
        model.addAttribute("to", to);
        return "letter/send";
    }

    @PostMapping("/send")
    String send(@RequestParam String to,@RequestParam String title , @RequestParam String content, Principal principal, Model model) {
        if (principal == null) {
            model.addAttribute("text", "发送失败，用户未登录！");
        } else {
            content=JsoupUtil.clean(content);
            if (letterService.sendLetter(principal.getName(),to,title, content)) {
                model.addAttribute("text", "发送成功！");
            } else {
                model.addAttribute("text", "发送失败。");
            }
        }
        return "letter/sent";
    }

    @GetMapping("/mailbox")
    String mailbox(Principal principal, Model model, @RequestParam(required = false, defaultValue = "all") String box) {
        model.addAttribute("box", box);
        switch (box) {
        case "send":
            model.addAttribute("mails", letterService.getAllSentLetters(principal.getName()));
            break;
        case "unread":
            model.addAttribute("mails", letterService.getUnreadLetters(principal.getName()));
            break;
        default:
            model.addAttribute("mails", letterService.getAllLetters(principal.getName()));
            break;
        }
        return "letter/mailbox";
    }
    @GetMapping("/letter/{id}")
    @ResponseBody
    LetterDetail getLetter(@PathVariable Integer id,Principal principal,@RequestParam(required = false,defaultValue = "false") Boolean open){
        if(open){
            letterService.read(principal.getName(), id);
        }
        return letterService.getLetterById(principal.getName(), id);
    }

    @PostMapping("/del")
    @ResponseBody
    Result del(@RequestParam Integer id,Principal principal){
        Boolean flag=letterService.del(principal.getName(), id);
        if(flag){
            return new Result(0,"删除站内信成功。");
        }
        return new Result(5,"删除失败");
    }
}