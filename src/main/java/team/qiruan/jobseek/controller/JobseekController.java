package team.qiruan.jobseek.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import team.qiruan.domain.Result;
import team.qiruan.domain.User;
import team.qiruan.jobseek.dao.Jobseek;
// import lombok.extern.slf4j.Slf4j;
import team.qiruan.jobseek.dao.JobseekDao;
import team.qiruan.service.FileService;
import team.qiruan.service.UserService;

/**
 * JobseekController
 */
@Controller
// @Slf4j

@RequestMapping("/jobseek")
public class JobseekController {
    @Autowired
    JobseekDao jobseekDao;
    @Autowired
    private FileService fileService;
    @Autowired
    UserService userService;

    @GetMapping("/getall")
    String getAll(Model model) {
        model.addAttribute("allJobseekers", jobseekDao.getAllJobseeks_Less());

        return "jobseek/alljobseekers";
    }

    @GetMapping("/one/{id}")
    String getOne(@PathVariable Integer id, Model model) {
        model.addAttribute("model", jobseekDao.search(id));
        return "jobseek/onejobseeker";
    }

    @GetMapping("/addone")
    String addOne(Model model) {

        return "jobseek/addone";
    }

    @PostMapping("/add")
    @ResponseBody
    Result addOneback(Principal principal, Jobseek jobseek) {
        if(principal.getName()==null)
        {
            return new Result(1, "未登陆");
        }
         
        User user = userService.getUserByName( principal.getName());
        System.out.println(jobseek.toString());
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        jobseek.setTime(dateFormat.format(date));
        jobseek.setUid(user.getId());
        // vcr = vcr.replace("/file/down/", "");
        jobseek.setVcr(jobseek.getVcr().replace("/file/down/", ""));
        fileService.updateFileRelationShip("vcr_" + principal.getName(), Arrays.asList(jobseek.getVcr()));
        jobseek.setCheck(0);

       
        // Jobseek(id=null, time=y, uid=y, school=12311, year=3, major=软件工程, phone=null, qq=2948927433, mail=1312,1312, sub1=null, sub2=null, sub3=null, money=12312, introduce=<p>sdfasfaf</p>, check=null, vcr=/file/down/0000RacSJBp1kj9uI.jpeg)
        return new Result(0, "success",  jobseekDao.insert(jobseek));
    }

    @GetMapping("/my")
    String getAllOne(Principal principal, Model model)
    {
        if(principal.getName()==null)
        {
            return "404";
        }
         
        model.addAttribute("allJobseeks", jobseekDao.getAllOneJobseeks(userService.getUserByName( principal.getName()).getId()));
        return "jobseek/my";
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    Result deleteOne(@PathVariable Integer id) {
        
        return new Result(0, "message", jobseekDao.delete(id));
    }
}