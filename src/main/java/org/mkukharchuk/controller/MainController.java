package org.mkukharchuk.controller;

import org.mkukharchuk.dao.ImageDAO;
import org.mkukharchuk.model.Image;
import org.mkukharchuk.service.ImageCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;


@Controller
public class MainController {

    @Autowired
    private ImageCreatorService service;

    @Autowired
    private ImageDAO imageDAO;

    @GetMapping("/")
    public String index(Model model){
        service.writeImage();
        Image lastImage = imageDAO.getLastImage();
        String path = new File("").getAbsolutePath() + "/" + "images" + "/" + lastImage.getFileName();
        model.addAttribute("newest",lastImage.getFileName());
        return "/index";
    }

}
