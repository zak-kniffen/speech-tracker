package com.babyspeak.speechtracker.controllers;

import com.babyspeak.speechtracker.models.Words;
import com.babyspeak.speechtracker.models.hWordsInitial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
    public class HomeController {

    private hWordsInitial wordy = new hWordsInitial();

    private String[] stuff = wordy.getWordList();

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("stuff", stuff);

        return "index";
    }







}

