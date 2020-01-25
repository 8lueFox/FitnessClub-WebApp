package com.kacper.fitnessclub.controllers;

import com.kacper.fitnessclub.models.User;
import com.kacper.fitnessclub.repositories.UserRepository;
import com.kacper.fitnessclub.services.EmailSenderService;
import com.kacper.fitnessclub.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.RollbackException;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegisterController {
    @Autowired
    private UserServices userServices;
    @Autowired
    private EmailSenderService emailService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "registerForm";
    }

    @PostMapping(value = "/register")
    public ModelAndView register(@Valid @ModelAttribute("user") User user, BindingResult errors, ModelAndView modelAndView){
        if(errors.hasErrors()){
            modelAndView.setViewName("registerForm");
            return modelAndView;
        }
        userServices.save(user);
        String content = String.format("<h1>Rejestracja na stronie FitnessClub</h1><br>" +
                "Cześć, <b>"+ user.getUsername() + "</b><br>" +
                "Miło nam, że to z nami chcesz zmienić siebie ;)<br>" +
                "Aby dokończyć rejestrację kliknij link poniżej<br>" +
                "<a href='http://www.localhost:9090/registerConfirm?username="+user.getUsername()+"'>Potwierdź rejestrację</a>");
        emailService.sendEmail(user.getEmail(), "Rejestracja na FitnessClub", content);
        modelAndView.addObject("username", user.getUsername());
        modelAndView.setViewName("registerSuccess");
        return modelAndView;
    }

    @GetMapping(value = "/registerConfirm", params = "username")
    public String registerConfirm(Model model, String username){
        userServices.confirmUser(username);
//        model.addAttribute("username", userRepository.findUserByUsername(username));
        return "home";
    }


//    @PostMapping(value = "/registerConfirm", params = "idUser")
//    public String registerConfirmed(Integer idUser){
//        userServices.confirmUser(idUser);
//        return "home";
//    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
        binder.registerCustomEditor(Date.class, "birthday", dateEditor);
    }
}
