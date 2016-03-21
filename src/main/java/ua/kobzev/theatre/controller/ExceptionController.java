package ua.kobzev.theatre.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by kkobziev on 3/19/16.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String catchException(Model model){
        model.addAttribute("textError", "Ups! Something happen. Please try again");

        return "error";
    }
}
