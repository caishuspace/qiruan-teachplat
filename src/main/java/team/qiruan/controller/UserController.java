package team.qiruan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import team.qiruan.domain.EditUserInfo;
import team.qiruan.domain.Result;
import team.qiruan.domain.UserInfo;
import team.qiruan.service.UserInfoService;
import team.qiruan.utils.JsoupUtil;

/**
 * 用户中心相关代码
 * 注意！因为使用切片拦截检查用户是否为当前登录用户，
 * username必须为方法的第一个参数
 * @author 刘海鑫
 */

@Controller
@RequestMapping("/user/{username}")
public class UserController {
    @Autowired UserInfoService userInfoService;

    /**
     * 展示用户信息
     * @param username
     * @param model
     * @return
     */
    @GetMapping
    String info(@PathVariable String username,Model model){
        UserInfo userInfo=userInfoService.getUserInfoByName(username);
        if(userInfo==null){
            userInfo=new UserInfo();
        }
        model.addAttribute("username", username);
        model.addAttribute("userinfo", userInfo);
        return "user/info";
    }

    /**
     * 更新用户信息的接口
     * @param username
     * @param userInfo
     * @param model
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    Result updateInfo(@PathVariable String username,@Valid EditUserInfo userInfo,Model model){
        userInfo.setIntroduce(JsoupUtil.clean(userInfo.getIntroduce()));//防止xss
        if(userInfoService.updateUserInfo(username, userInfo)){
            return new Result(0,"修改成功。");
        }
        return new Result(3,"修改失败，发生未知错误。");
    }
}