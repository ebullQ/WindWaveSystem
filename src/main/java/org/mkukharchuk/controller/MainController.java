package org.mkukharchuk.controller;

import org.mkukharchuk.dao.impl.ImageDAOImpl;
import org.mkukharchuk.model.Image;
import org.mkukharchuk.service.ImageEncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@Controller
public class MainController {

    @Autowired
    private ImageDAOImpl imageDAOImpl;

    @Autowired
    private ImageEncoderService encoderService;

    private final String path = new File("").getAbsolutePath() + "/" + "direction" + "/";

    @GetMapping("/")
    public String index(Model model){
        Image lastImage = imageDAOImpl.getLastImage();
        model.addAttribute("newest",lastImage);
        model.addAttribute("windDirection", encoderService.encoder(path + lastImage.getWind().getDirectionImageName()));
        return "index";
    }

}
