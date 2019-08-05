package team.qiruan.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import team.qiruan.domain.RegUser;
import team.qiruan.domain.Result;
import team.qiruan.domain.User;
import team.qiruan.service.UserService;


@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;    
    @Autowired
    private UserService userService;
    
    private RequestCache requestCache=new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    @RequestMapping("/require")
    @ResponseBody
    Result require(HttpServletRequest request,HttpServletResponse response) throws IOException{
        SavedRequest savedRequest = requestCache.getRequest(request,
                response);
        if(savedRequest!=null){
            String targetUrl=savedRequest.getRedirectUrl();
            if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")){
                redirectStrategy.sendRedirect(request, response, "page-login");
            }
        }
        return new Result(1,"用户未登录。");
    }

    @PostMapping("/reg")
    @ResponseBody
    Result reg(@Valid RegUser regUser){
        if(userService.getUserByName(regUser.getName())!=null){
            return new Result(2,"注册失败，用户名已经存在！");
        }
        return null;
    }

    @GetMapping(value="/page-login")
    public String loginPage() {
        return "auth/page-login";
    }
    
    @GetMapping("/page-reg")
    public String regPage(){
        return "auth/page-reg";
    }
}