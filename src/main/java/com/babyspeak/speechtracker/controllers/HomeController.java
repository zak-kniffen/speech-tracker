package com.babyspeak.speechtracker.controllers;

import com.babyspeak.speechtracker.models.*;
import com.babyspeak.speechtracker.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
    public class HomeController {

    @Autowired
    private BwordsInitialRepository bwordsInitialRepository;

    @Autowired
    private BwordsFinalRepository bwordsFinalRepository;

    @Autowired
    private FwordsInitialRepository fwordsInitialRepository;

    @Autowired
    private FwordsFinalRepository fwordsFinalRepository;

    @Autowired
    private SnapshotWordProgressRepository snapshotWordProgressRepository;


//    private Words wordy = new Words();
//
//    private String[] stuff = wordy.getWordList();

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("stuff", bwordsInitialRepository.findAll());
        model.addAttribute("stuffy", bwordsFinalRepository.findAll());
        model.addAttribute("stuffy2", fwordsInitialRepository.findAll());
        model.addAttribute("stuffy3", fwordsFinalRepository.findAll());
        model.addAttribute(new TrackerList());

        return "index";
    }

    @PostMapping("/")
    public String processAddTrackerForm(@ModelAttribute @Valid TrackerList newTracker,
                                        Errors errors, Model model, @RequestParam Map<String,String> allQueryParams) {



        if (errors.hasErrors()) {
//            model.addAttribute("stuff", bwordsInitialRepository.findAll());
//            model.addAttribute("stuffy", bwordsFinalRepository.findAll());
//            model.addAttribute("stuffy2", fwordsInitialRepository.findAll());
//            model.addAttribute("stuffy3", fwordsFinalRepository.findAll());
            return "submit";
        }

        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();

        double totalAnswers = 0;
        double totalYesAnswer = 0;

        for (Map.Entry<String,String> entry : allQueryParams.entrySet()){
            SnapshotWordProgress snap = new SnapshotWordProgress();
            snap.setCorrect(entry.getValue());
            snap.setWord(entry.getKey());
            snap.setYear(year);
            snap.setMonth(month);
            snap.setDay(day);
            snapshotWordProgressRepository.save(snap);
            totalAnswers ++;
            if (entry.getValue().equals("yes")){
                totalYesAnswer ++;
            }
        }
        model.addAttribute("totalData", snapshotWordProgressRepository.findAll());

        model.addAttribute("zak", "Total correct: " + totalYesAnswer);
        model.addAttribute("sam", "Total attempted: " + totalAnswers);
        model.addAttribute("bob", "Percent Correct = " + Math.round((totalYesAnswer/totalAnswers) * 100) + "%");
        return "submit";
    }






}

