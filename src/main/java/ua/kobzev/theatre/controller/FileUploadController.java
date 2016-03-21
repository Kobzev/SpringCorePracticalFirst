package ua.kobzev.theatre.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.service.EventService;
import ua.kobzev.theatre.service.UserService;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by kkobziev on 3/20/16.
 */
@Controller
public class FileUploadController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/uploadfile", method = RequestMethod.GET)
    public String uploadFilePage(){
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files){

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                parseJSON(file);
            }
        }

        return "index";
    }

    private void parseJSON(MultipartFile multipartFile){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(multipartFile.getInputStream());

            //Users
            User[] users = mapper.treeToValue(rootNode.get("users"), User[].class);
            Arrays.asList(users).stream().forEach(user -> userService.register(user));

            //Events
            Event[] events = mapper.treeToValue(rootNode.get("events"), Event[].class);
            Arrays.asList(events).stream().forEach(event -> eventService.create(event));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
