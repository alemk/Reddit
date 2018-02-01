package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.validation.Valid;

public class MainController {
    @Autowired
    RedditRepository redditRepository;
    @RequestMapping("/")
    public String listreddit(Model model){
        model.addAttribute("reddits", redditRepository.findAll());
        return "home";
    }

    @GetMapping("/add")
    public String redditpost(Model model){
        model.addAttribute("reddit", new Reddit());
        return "addpost";
    }

    @PostMapping("/process")
    public String processpost(@Valid Reddit reddit, BindingResult result)
    {
        if (result.hasErrors()){
            return "home";
        }
        redditRepository.save(reddit);
        return "redirect:/";
    }

    @RequestMapping("detail/{id}")
    public String showreddit(@PathVariable("id") long id, Model model){
        model.addAttribute("reddit", redditRepository.findOne(id));
        return "showreddit";
    }
    @RequestMapping("update/{id}")
    public String updatereddit(@PathVariable("id") long id, Model model){
        model.addAttribute("reddit", redditRepository.findOne(id));
        return "showreddit";
    }
    @RequestMapping("delete/{id}")
    public String deletereddit(@PathVariable("id") long id){
        redditRepository.delete(id);
        return "redirect:/";
    }
}
