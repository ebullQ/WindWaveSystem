package org.mkukharchuk.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class ResourcesController {

    @GetMapping(value = "/images/{fileName}",produces = "image/png")
    @ResponseBody
    public byte[] getResources(@PathVariable String fileName) {
        String path = new File("").getAbsolutePath() + "/" + "images" + "/" + fileName;
        byte[] result = null;
        try {
            result = IOUtils.toByteArray(new FileInputStream(path));
        } catch (IOException e) {
            e.getMessage();
        }
        return result;
    }
}
