package ua.kobzev.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.service.AuditoriumService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Created by kkobziev on 3/19/16.
 */
@Controller
@RequestMapping("/auditoriums")
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHome(Model model){
        model.addAttribute("auditoriums", auditoriumService.getAuditoriums());
        return "auditoriums";
    }

    @RequestMapping(value = "/choosename", method = RequestMethod.GET)
    public String chooseName(Model model){
        Auditorium auditorium = new Auditorium();
        auditorium.setName("Pick name");
        model.addAttribute("auditorium", auditorium);
        return "auditorium/fyndbyname";
    }

    @RequestMapping(value = "/findbyname", method = RequestMethod.GET)
    public String findByName(Model model, @RequestParam("name") String name){
        List<Auditorium> auditoriumList = new ArrayList<>();
        Auditorium aud = auditoriumService.findAuditoriumByName(name);
        if (nonNull(aud)) auditoriumList.add(aud);

        model.addAttribute("auditoriums", auditoriumList);
        return "auditoriums";
    }
}
