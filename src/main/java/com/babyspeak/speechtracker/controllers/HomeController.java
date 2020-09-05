package com.babyspeak.speechtracker.controllers;

import com.babyspeak.speechtracker.models.*;
import com.babyspeak.speechtracker.models.data.*;
import com.babyspeak.speechtracker.models.dto.RegisterFormDTO;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
    public class HomeController {

    @Autowired
    private AllWordsRepository allWordsRepository;

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

    @Autowired
    AuthenticationController authenticationController;


//    private Words wordy = new Words();
//
//    private String[] stuff = wordy.getWordList();



    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model, RegisterFormDTO registerFormDTO) {

        model.addAttribute("allWords", allWordsRepository.findAll());
        model.addAttribute(new TrackerList());
//        HttpSession session = request.getSession();
//        User user = authenticationController.getUserFromSession(session);
//        System.out.println(user.getId());
        return "index";
    }

    @RequestMapping("results/index")
    public String resultsIndex(Model model) {

        model.addAttribute("allWords", allWordsRepository.findAll());
        model.addAttribute(new TrackerList());

        return "/results/index";
    }

    @RequestMapping("/results/submit")
    public String processAddTrackerForm(Model model) {

        model.addAttribute("totalData", snapshotWordProgressRepository.findAll());
        model.addAttribute("letter", "allWords");
        return "results/submit";
    }

    @PostMapping("/results/index")
    public String processAddTrackerForm(HttpServletRequest request, @ModelAttribute @Valid TrackerList newTracker,
                                        Errors errors, Model model, @RequestParam Map<String,String> allQueryParams) {



        if (errors.hasErrors()) {
            model.addAttribute("allWords", allWordsRepository.findAll());
            return "submit";
        }

        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();

        double totalAnswers = 0;
        double totalYesAnswer = 0;

        HttpSession session = request.getSession();
        Optional<User> user = Optional.ofNullable(authenticationController.getUserFromSession(session));
        User optionalUser = user.get();

        for (Map.Entry<String,String> entry : allQueryParams.entrySet()){
            SnapshotWordProgress snap = new SnapshotWordProgress();
            Integer tempId = Integer.parseInt(entry.getKey());
            snap.setCorrect(entry.getValue());
            Optional<AllWords> tempWord = allWordsRepository.findById(tempId);
            AllWords allword = tempWord.get();
            snap.setWord(allword.getWord());
            snap.setName(allword.getName());
            snap.setYear(year);
            snap.setMonth(month);
            snap.setDay(day);
            snapshotWordProgressRepository.save(snap);
            totalAnswers ++;
            if (entry.getValue().equals("yes")){
                totalYesAnswer ++;
            }
        }
        model.addAttribute("letter", "allWords");
        model.addAttribute("totalData", snapshotWordProgressRepository.findAll());
        model.addAttribute("allData", allQueryParams);
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

    @GetMapping("/test/resultByLetter/{letter}")
    public String handlerResults(Model model, @PathVariable(name = "letter") String letter) {
        model.addAttribute("totalData", snapshotWordProgressRepository.findAll());
        model.addAttribute("letter", letter);
        return "/test/resultByLetter";
    }

    @PostMapping("/test/{letter}")
    public String processByLetter(HttpServletRequest request, @ModelAttribute @Valid TrackerList newTracker,
                                  Errors errors, Model model, @PathVariable("letter") String letter, @RequestParam Map<String,String> allQueryParams) {

        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();

        double totalAnswers = 0;
        double totalYesAnswer = 0;

        HttpSession session = request.getSession();
        Optional<User> user = Optional.ofNullable(authenticationController.getUserFromSession(session));
        User optionalUser = user.get();
        //System.out.println(optionalUser.getId());

        for (Map.Entry<String,String> entry : allQueryParams.entrySet()){
            SnapshotWordProgress snap = new SnapshotWordProgress();
            Integer tempId = Integer.parseInt(entry.getKey());
            snap.setCorrect(entry.getValue());
            Optional<AllWords> tempWord = allWordsRepository.findById(tempId);
            AllWords allword = tempWord.get();
            snap.setWord(allword.getWord());
            snap.setName(allword.getName());
            snap.setYear(year);
            snap.setMonth(month);
            snap.setDay(day);
            snap.setUserid(optionalUser.getId());
            snapshotWordProgressRepository.save(snap);
            totalAnswers ++;
            if (entry.getValue().equals("yes")){
                totalYesAnswer ++;
            }
        }


        model.addAttribute("totalData", snapshotWordProgressRepository.findAll());
        model.addAttribute("letter", letter);
        model.addAttribute("zak", "Total correct: " + totalYesAnswer);
        model.addAttribute("sam", "Total attempted: " + totalAnswers);
        model.addAttribute("bob", "Percent Correct = " + Math.round((totalYesAnswer/totalAnswers) * 100) + "%");
        return "test/resultByLetter";
    }





}

