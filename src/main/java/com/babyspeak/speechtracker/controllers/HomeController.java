package com.babyspeak.speechtracker.controllers;

import com.babyspeak.speechtracker.models.*;
import com.babyspeak.speechtracker.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
    private HwordsInitialRepository hwordsInitialRepository;

    @Autowired
    private MwordsInitialRepository mwordsInitialRepository;

    @Autowired
    private MwordsFinalRepository mwordsFinalRepository;

    @Autowired
    private NwordsInitialRepository nwordsInitialRepository;

    @Autowired
    private NwordsFinalRepository nwordsFinalRepository;

    @Autowired
    private PwordsInitialRepository pwordsInitialRepository;

    @Autowired
    private PwordsFinalRepository pwordsFinalRepository;

    @Autowired
    private WwordsInitialRepository wwordsInitialRepository;

    @Autowired
    private SnapshotWordProgressRepository snapshotWordProgressRepository;


//    private Words wordy = new Words();
//
//    private String[] stuff = wordy.getWordList();

    @RequestMapping("/")
    public String index(Model model) {


        model.addAttribute("bStart", bwordsInitialRepository.findAll());
        model.addAttribute("bEnd", bwordsFinalRepository.findAll());
        model.addAttribute("fStart", fwordsInitialRepository.findAll());
        model.addAttribute("fEnd", fwordsFinalRepository.findAll());
        model.addAttribute("hStart", hwordsInitialRepository.findAll());
        model.addAttribute("mStart", mwordsInitialRepository.findAll());
        model.addAttribute("mEnd", mwordsFinalRepository.findAll());
        model.addAttribute("nStart", nwordsInitialRepository.findAll());
        model.addAttribute("nEnd", nwordsFinalRepository.findAll());
        model.addAttribute("pStart", pwordsInitialRepository.findAll());
        model.addAttribute("pEnd", pwordsFinalRepository.findAll());
        model.addAttribute("wStart", wwordsInitialRepository.findAll());
        model.addAttribute(new TrackerList());

        return "index";
    }

    @RequestMapping("results/index")
    public String resultsIndex(Model model) {


        model.addAttribute("bStart", bwordsInitialRepository.findAll());
        model.addAttribute("bEnd", bwordsFinalRepository.findAll());
        model.addAttribute("fStart", fwordsInitialRepository.findAll());
        model.addAttribute("fEnd", fwordsFinalRepository.findAll());
        model.addAttribute("hStart", hwordsInitialRepository.findAll());
        model.addAttribute("mStart", mwordsInitialRepository.findAll());
        model.addAttribute("mEnd", mwordsFinalRepository.findAll());
        model.addAttribute("nStart", nwordsInitialRepository.findAll());
        model.addAttribute("nEnd", nwordsFinalRepository.findAll());
        model.addAttribute("pStart", pwordsInitialRepository.findAll());
        model.addAttribute("pEnd", pwordsFinalRepository.findAll());
        model.addAttribute("wStart", wwordsInitialRepository.findAll());
        model.addAttribute(new TrackerList());

        return "/results/index";
    }

    @RequestMapping("/results/submit")
    public String processAddTrackerForm(Model model) {

        model.addAttribute("totalData", snapshotWordProgressRepository.findAll());

        return "results/submit";
    }

    @PostMapping("/results/index")
    public String processAddTrackerForm(@ModelAttribute @Valid TrackerList newTracker,
                                        Errors errors, Model model, @RequestParam Map<String,String> allQueryParams) {



        if (errors.hasErrors()) {
//            model.addAttribute("bStart", bwordsInitialRepository.findAll());
//            model.addAttribute("bEnd", bwordsFinalRepository.findAll());
//            model.addAttribute("fStart", fwordsInitialRepository.findAll());
//            model.addAttribute("fEnd", fwordsFinalRepository.findAll());
//            model.addAttribute("hStart", hwordsInitialRepository.findAll());
//            model.addAttribute("mStart", mwordsInitialRepository.findAll());
//            model.addAttribute("mEnd", mwordsFinalRepository.findAll());
//            model.addAttribute("nStart", nwordsInitialRepository.findAll());
//            model.addAttribute("nEnd", nwordsFinalRepository.findAll());
//            model.addAttribute("pStart", pwordsInitialRepository.findAll());
//            model.addAttribute("pEnd", pwordsFinalRepository.findAll());
//            model.addAttribute("wStart", wwordsInitialRepository.findAll());
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
        return "results/submit";
    }

    @GetMapping("/test/{letter}")
    public String handler(Model model, @PathVariable(name = "letter") String letter) {
        if (letter.equals("starts_with_b")){
            model.addAttribute("wordList", bwordsInitialRepository.findAll());
            return "test/byLetter";
        } else if (letter.equals("ends_with_b")){
            model.addAttribute("wordList", bwordsFinalRepository.findAll());
            return "test/byLetter";
        } else if (letter.equals("starts_with_f")){
            model.addAttribute("wordList", fwordsInitialRepository.findAll());
            return "test/byLetter";
        } else if (letter.equals("ends_with_f")){
            model.addAttribute("wordList", fwordsFinalRepository.findAll());
            return "test/byLetter";
        } else if (letter.equals("starts_with_h")){
            model.addAttribute("wordList", hwordsInitialRepository.findAll());
            return "test/byLetter";
        } else if (letter.equals("starts_with_m")){
            model.addAttribute("wordList", mwordsInitialRepository.findAll());
            return "test/byLetter";
        } else if (letter.equals("ends_with_m")){
            model.addAttribute("wordList", mwordsFinalRepository.findAll());
            return "test/byLetter";
        } else if (letter.equals("starts_with_n")){
            model.addAttribute("wordList", nwordsInitialRepository.findAll());
            return "test/byLetter";
        } else if (letter.equals("ends_with_n")){
            model.addAttribute("wordList", nwordsFinalRepository.findAll());
            return "test/byLetter";
        } else if (letter.equals("starts_with_p")){
            model.addAttribute("wordList", pwordsInitialRepository.findAll());
            return "test/byLetter";
        } else if (letter.equals("ends_with_p")){
            model.addAttribute("wordList", pwordsFinalRepository.findAll());
            return "test/byLetter";
        } else if (letter.equals("starts_with_w")){
            model.addAttribute("wordList", wwordsInitialRepository.findAll());
            return "test/byLetter";
        }
        return "results/index";
    }






}

