package team.qiruan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team.qiruan.jobseek.dao.Jobseek_less;
import team.qiruan.service.SimpleSearchService;

/**
 * SimpleSearchController
 */
@Controller
@RequestMapping("/search")
public class SimpleSearchController {
    @Autowired
    private SimpleSearchService simpleSearchService;

    @GetMapping
    String search(@RequestParam String word,Model model){
        List<Jobseek_less> res=simpleSearchService.searchJobSeek(word);
        model.addAttribute("word", word);
        model.addAttribute("result", res);
        return "search/search";
    }
}