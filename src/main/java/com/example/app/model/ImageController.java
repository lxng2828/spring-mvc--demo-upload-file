package com.example.app.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class ImageController {

    @GetMapping("/")
    public String showForm() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String handleUpload(@RequestParam("image") MultipartFile image, Model model) {
        String fileName = image.getOriginalFilename();
        File uploadDir = new File("C:\\Users\\PETER\\Desktop\\uploads");
        if (!uploadDir.exists()) uploadDir.mkdirs();

        try {
            File dest = new File(uploadDir, fileName);
            image.transferTo(dest);
            model.addAttribute("imagePath", "/uploads/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "upload-result";
    }
}
