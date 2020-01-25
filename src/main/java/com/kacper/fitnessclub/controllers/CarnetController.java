package com.kacper.fitnessclub.controllers;

import com.kacper.fitnessclub.models.CarnetPurchaseHistory;
import com.kacper.fitnessclub.repositories.CarnetPurchaseHistoryRepository;
import com.kacper.fitnessclub.services.CarnetService;
import com.kacper.fitnessclub.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarnetController {
    @Autowired
    UserServices userServices;
    @Autowired
    CarnetService carnetService;
    @Autowired
    CarnetPurchaseHistoryRepository carnetPurchaseHistoryRepository;

    @GetMapping(value = "/carnet")
    public String carnets(Model model){
        model.addAttribute("carnetList", carnetService.getCartens());
        return "carnet";
    }

    @PostMapping(value = "/carnet", params = {"id", "username"})
    public String setCarnet(Model model, Integer id, String username){
        model.addAttribute("carnetId", id);
        int pom = userServices.getUser(username);
        int karnet = userServices.checkCarnet(pom);
        if(karnet == 0) {
            model.addAttribute("username", userServices.getUser(username));
            return "payment";
        }else{
            return "redirect:carnet?maszJuzKarnet";
        }
    }

    @PostMapping(value = "/paided", params = {"carnetId", "username"})
    public String paided(Model model, Integer carnetId, Integer username){
        CarnetPurchaseHistory carnetPurchaseHistory = new CarnetPurchaseHistory();
        carnetPurchaseHistory.setCarnet(carnetService.getCartner(carnetId));
        carnetPurchaseHistory.setUser(userServices.getUser(username));
        carnetPurchaseHistory.setPaid(true);
        carnetPurchaseHistoryRepository.save(carnetPurchaseHistory);
        model.addAttribute("carnet", carnetService.getCartner(carnetId).getName());
        model.addAttribute("user", userServices.getUser(username).getUsername());
        return "paymentSuccess";
    }

}
