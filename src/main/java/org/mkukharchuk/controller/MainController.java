package org.mkukharchuk.controller;

import org.mkukharchuk.dao.impl.ImageDAOImpl;
import org.mkukharchuk.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @Autowired
    private ImageDAOImpl imageDAOImpl;

    @GetMapping("/")
    public String index(Model model){
        Image lastImage = imageDAOImpl.getLastImage();
        model.addAttribute("newest",lastImage.getFileName());
        return "index";
    }

}
