package ua.kobzev.theatre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by kkobziev on 3/20/16.
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = "/uploadfile", method = RequestMethod.GET)
    public String uploadFilePage(){
        return "upload";
    }

    @RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
    public String uploadMultipleFileHandler(@RequestParam("name") String[] names,
                                     @RequestParam("file") MultipartFile[] files) {

        if (files.length != names.length)
            throw new IllegalArgumentException();

        String message = "";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String name = names[i];
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                message = message + "You successfully uploaded file=" + name
                        + "<br />";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        }
        return "index";
    }
}
