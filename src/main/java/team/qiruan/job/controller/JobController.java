package team.qiruan.job.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
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
import team.qiruan.job.dao.Job;
// import lombok.extern.slf4j.Slf4j;
import team.qiruan.job.dao.JobDao;
import team.qiruan.service.UserService;

/**
 * JobController
 */
@Controller
// @Slf4j

@RequestMapping("/job")
public class JobController {
    @Autowired
    JobDao jobDao;
   
    @Autowired
    UserService userService;

    @GetMapping("/getall")
    String getAll(Model model) {
        model.addAttribute("allJobers", jobDao.getAllJobs_Less());

        return "job/alljobers";
    }

    @GetMapping("/one/{id}")
    String getOne(@PathVariable Integer id, Model model) {
        model.addAttribute("model", jobDao.search(id));
        return "job/onejober";
    }

    @GetMapping("/addone")
    String addOne(Model model) {

        return "job/addone";
    }

    @PostMapping("/add")
    @ResponseBody
    Result addOneback(Principal principal, Job job) {
        if(principal.getName()==null)
        {
            return new Result(1, "未登陆");
        }
         
        User user = userService.getUserByName( principal.getName());
        System.out.println(job.toString());
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        job.setTime(dateFormat.format(date));
        job.setUid(user.getId());
        job.setCheck(0);
        return new Result(0, "success",  jobDao.insert(job));
    }

    @GetMapping("/my")
    String getAllOne(Principal principal, Model model)
    {
        if(principal.getName()==null)
        {
            return "404";
        }
         
        model.addAttribute("allJobs", jobDao.getAllOneJobs(userService.getUserByName( principal.getName()).getId()));
        return "job/my";
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    Result deleteOne(@PathVariable Integer id) {
        
        return new Result(0, "message", jobDao.delete(id));
    }
}