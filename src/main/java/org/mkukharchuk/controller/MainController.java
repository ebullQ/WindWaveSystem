package org.mkukharchuk.controller;

import org.mkukharchuk.dao.ImageDAO;
import org.mkukharchuk.model.Image;
import org.mkukharchuk.service.ImageCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @Autowired
    public ImageCreatorService service;

    @Autowired
    public ImageDAO imageDAO;

    @GetMapping("/")
    public String index(Model model){
        service.writeImage();
        Image lastImage = imageDAO.getLastImage();
        model.addAttribute("newest",lastImage.getFileName());
        return "/index";
    }

}
