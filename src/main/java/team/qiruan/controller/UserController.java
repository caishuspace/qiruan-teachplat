package team.qiruan.controller;

import java.util.Arrays;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import team.qiruan.domain.EditUserInfo;
import team.qiruan.domain.Result;
import team.qiruan.domain.User;
import team.qiruan.domain.UserInfo;
import team.qiruan.service.EMailService;
import team.qiruan.service.FileService;
import team.qiruan.service.UserBindService;
import team.qiruan.service.UserInfoService;
import team.qiruan.service.UserService;
import team.qiruan.utils.ConversionUtil;
import team.qiruan.utils.JsoupUtil;

/**
 * 用户中心相关代码 注意！因为使用切片拦截检查用户是否为当前登录用户， username必须为方法的第一个参数
 * 
 * @author 刘海鑫
 */

@Controller
@Slf4j
@RequestMapping("/user/{username}")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserBindService userBindService;
    @Autowired
    private EMailService emailService;

    /**
     * 展示用户信息
     * 
     * @param username
     * @param model
     * @return
     */
    @GetMapping
    String info(@PathVariable String username, Model model) {
        UserInfo userInfo = userInfoService.getUserInfoByName(username);
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        model.addAttribute("userinfo", userInfo);
        return "user/info";
    }

    /**
     * 更新用户信息的接口
     * 
     * @param username
     * @param userInfo
     * @param model
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    Result updateInfo(@PathVariable String username, @Valid EditUserInfo userInfo) {
        userInfo.setIntroduce(JsoupUtil.clean(userInfo.getIntroduce()));// 防止xss
        if (userInfoService.updateUserInfo(username, userInfo)) {
            return new Result(0, "修改成功。");
        }
        return new Result(3, "修改失败，发生未知错误。");
    }

    /**
     * 修改头像
     * 
     * @param username
     * @param avatar
     * @return
     */
    @PostMapping("/avatar")
    @ResponseBody
    Result updateAvatar(@PathVariable String username, @RequestParam String avatar) {
        log.trace("{}修改头像为{}", username, avatar);
        avatar = avatar.replace("/file/down/", "");
        if (userInfoService.updateAvatar(username, avatar)) {
            fileService.updateFileRelationShip("avatar_" + username, Arrays.asList(avatar));
            return new Result(0, "头像修改成功！", avatar);
        }
        return new Result(8, "头像保存失败。");
    }

    /**
     * 用户安全中心
     * 
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/security")
    String security(@PathVariable String username, Model model) {
        UserInfo userInfo = userInfoService.getUserInfoByName(username);
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        model.addAttribute("userinfo", userInfo);
        model.addAttribute("userbind", userBindService.getUserBindByName(username));
        return "user/security";
    }

    /**
     * 修改密码
     * @param username
     * @param model
     * @param oldPassword
     * @param newPassWord
     * @param newPassWord_2
     * @return
     */
    @PostMapping("/changePWD")
    String changePassword(@PathVariable String username, Model model, @RequestParam String oldPassword,
            @RequestParam String newPassWord, @RequestParam String newPassWord_2) {
        log.trace("用户{}尝试修改密码。",username);
        UserInfo userInfo = userInfoService.getUserInfoByName(username);
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        User user = userService.getUserByName(username);
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            model.addAttribute("text", "密码修改失败！旧密码不正确。");
        } else if (!newPassWord.equals(newPassWord_2)) {
            model.addAttribute("text", "密码修改失败！两次输入新密码不相同。");
        } else {
            if (userService.changePWD(username, passwordEncoder.encode(newPassWord))) {
                model.addAttribute("text", "密码修改成功。");
            } else {
                model.addAttribute("text", "密码修改失败，未知原因。");
            }
        }
        model.addAttribute("userinfo", userInfo);
        return "user/result";
    }

    @PostMapping("/bind/email")
    @ResponseBody
    Result changeBindEmail(@PathVariable String username,@RequestParam String email){
        Random ran=new Random();
        String valicode=ConversionUtil.encode(Math.abs(ran.nextLong()), 11);
        if(userBindService.addUserBindEmail(username, email,valicode)){
            emailService.sendEmailValiCode(username, email, valicode);
            return new Result(0,"绑定邮箱已经修改，请尽快完成验证。");
        }
        return new Result(5,"绑定邮箱修改失败，请重试。");
    }
}