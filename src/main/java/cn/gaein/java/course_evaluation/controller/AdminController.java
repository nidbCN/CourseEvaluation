package cn.gaein.java.course_evaluation.controller;

import cn.gaein.java.course_evaluation.repository.AdminRepository;
import cn.gaein.java.course_evaluation.response.Response;
import cn.gaein.java.course_evaluation.utils.HashUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import cn.gaein.java.course_evaluation.dto.AdminDto;
import cn.gaein.java.course_evaluation.entity.Admin;
import cn.gaein.java.course_evaluation.param.AdminParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository repository;

    private final Response adminNotFoundResponse = Response.notFound("Admin not found");
    private final Response userNameAlreadyExistsResponse = Response.badRequest("User name already exists");

    @Autowired
    public AdminController(AdminRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Response getAllAdmins(@RequestParam String name) {
        var adminList = repository.findAll();

        return Response.success(
            StreamSupport.stream(adminList.spliterator(), false)
                .map(AdminDto::new));
    }

    @GetMapping("")
    public Response searchAdminsByName(@RequestParam String name) {
        Iterable<Admin> admins = repository.findByName(name);
        List<AdminDto> dtos = new ArrayList<>();
        for (Admin admin : admins) {
            dtos.add(new AdminDto(admin));
        }
        if (dtos.isEmpty()) {
            return adminNotFoundResponse;
        }
        return Response.success(dtos);
    }

    @PostMapping("")
    public Response createAdmin(@RequestBody AdminParam param) {
        // check if username exists
        if (repository.findByUsername(param.getUsername()) != null) {
            return userNameAlreadyExistsResponse;
        }
        // save admin
        var admin = new Admin();
        admin.setName(param.getName());
        admin.setUsername(param.getUsername());
        admin.setPassword(HashUtils.md5(param.getPassword()));
        repository.save(admin);
        return Response.success(new AdminDto(admin));
    }

    @GetMapping("/{id}")
    public Response getAdminById(@PathVariable("id") long id) {
        var admin = repository.findById(id);

        return admin == null
            ? adminNotFoundResponse
            : Response.success(new AdminDto(admin));
    }

    @PutMapping("/{id}")
    public Response updateAdminById(@PathVariable("id") long id, @RequestBody AdminParam param) {
        var admin = repository.findById(id);

        if (admin == null) {
            return adminNotFoundResponse;
        }

        // check if username exists
        if (repository.findByUsername(param.getUsername()) != null
            && !admin.getUsername().equals(param.getUsername())) {
            return userNameAlreadyExistsResponse;
        }

        // update admin
        admin.setName(param.getName());
        admin.setUsername(param.getUsername());
        admin.setPassword(HashUtils.md5(param.getPassword()));
        repository.save(admin);
        return Response.success(new AdminDto(admin));
    }

    @DeleteMapping("/{id}")
    public Response deleteAdminById(@PathVariable("id") long id) {
        var admin = repository.findById(id);

        if (admin == null) {
            return adminNotFoundResponse;
        }

        repository.delete(admin);
        return Response.success();
    }

    @GetMapping("/login")
    public Response login(@RequestParam String username, @RequestParam String password) {
        Admin admin = repository.findByUsername(username);
        if (admin == null) {
            return adminNotFoundResponse;
        }
        if (!admin.getPassword().equals(HashUtils.md5(password))) {
            return Response.badRequest("Wrong password");
        }
        return Response.success(new AdminDto(admin));
    }
}
