package cn.gaein.java.course_evaluation.controller;

import cn.gaein.java.course_evaluation.responseHelper.Response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public Response index() {
        return Response.success("Hello World!");
    }
}
