package com.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/hello-world")
    public String hello(){
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Title</title>
                </head>
                <body>
                <h1>Hola desde API REST</h1>
                </body>
                </html>
                """;
    }
    @GetMapping("/bye")
    public String bye(){
        return "Hasta luego";
    }
}
