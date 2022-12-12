package cn.gaein.java.course_evaluation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpSession;

import cn.gaein.java.course_evaluation.dto.StudentDto;
import cn.gaein.java.course_evaluation.entity.Student;
import cn.gaein.java.course_evaluation.param.StudentParam;
import cn.gaein.java.course_evaluation.param.StudentRegisterParam;
import cn.gaein.java.course_evaluation.repository.StudentRepository;
import cn.gaein.java.course_evaluation.responseHelper.Response;
import cn.gaein.java.course_evaluation.utils.HashUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository repository;

    private final Response studentNotFoundResponse = Response.notFound("Student not found");
    private final Response studentIdNumberExistResponse = Response.badRequest("Student id number already exists");

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Response getAllStudent() {
        var students = repository.findAll();

        return Response.success(
            StreamSupport.stream(students.spliterator(), false)
                .map(StudentDto::new)
        );
    }

    @PostMapping("")
    public Response createStudent(@RequestBody StudentParam param) {
        // check if idNumber exist
        if (repository.findByIdNumber(param.getIdNumber()) != null) {
            return studentIdNumberExistResponse;
        }

        // save student
        var student = new Student();
        student.setIdNumber(param.getIdNumber());
        student.setName(param.getName());
        student.setPhone(param.getPhone());
        student.setSex(param.getSex());
        student.setEmail(param.getEmail());
        student.setPassword(HashUtils.md5(param.getPassword()));
        student.setAge(param.getAge());

        repository.save(student);
        return Response.success(new StudentDto(student));
    }

    @GetMapping("/{id}")
    public Response getStudentById(@PathVariable("id") long id) {
        var student = repository.findById(id);

        return student == null
            ? studentNotFoundResponse
            : Response.success(new StudentDto(student));
    }

    @PutMapping("/{id}")
    public Response updateStudent(@PathVariable("id") long id, @RequestBody StudentParam param) {
        var student = repository.findById(id);
        if (student == null) {
            return studentNotFoundResponse;
        }
        // check if idNumber exist
        if (repository.findByIdNumber(param.getIdNumber()) != null
            && !student.getIdNumber().equals(param.getIdNumber())) {
            return studentIdNumberExistResponse;
        }

        // update student
        student.setIdNumber(param.getIdNumber());
        student.setName(param.getName());
        student.setPhone(param.getPhone());
        student.setSex(param.getSex());
        student.setEmail(param.getEmail());
        student.setPassword(HashUtils.md5(param.getPassword()));
        student.setAge(param.getAge());

        repository.save(student);
        return Response.success(new StudentDto(student));
    }

    @DeleteMapping("/{id}")
    public Response deleteStudent(@PathVariable("id") long id) {
        var student = repository.findById(id);

        if (student == null) {
            return studentNotFoundResponse;
        }

        repository.delete(student);
        return Response.success();
    }

    @GetMapping("/search")
    public Response searchStudentByName(@RequestParam String name) {
        var students = repository.findByName(name);

        return Response.success(
            StreamSupport.stream(students.spliterator(), false)
                .map(StudentDto::new)
        );
    }

    @GetMapping("login")
    public Response login(@RequestParam String idNumber, @RequestParam String password) {
        var student = repository.findByIdNumber(idNumber);

        if (student == null) {
            return studentNotFoundResponse;
        }

        if (!student.getPassword().equals(HashUtils.md5(password))) {
            return Response.badRequest("Password is incorrect");
        }

        return Response.success(new StudentDto(student));
    }

    @PostMapping("/register")
    public Response registerStudent(HttpSession session, @RequestBody StudentRegisterParam param) {
        // check verify code
        var verifyCode = (String) session.getAttribute("verifyCode");

        if (verifyCode == null || !verifyCode.equals(param.getVerifyCode())) {
            return Response.badRequest("Verify code is not correct");
        }
        // check if idNumber exist

        if (repository.findByIdNumber(param.getIdNumber()) != null) {
            return studentIdNumberExistResponse;
        }

        // save student
        var student = new Student();
        student.setIdNumber(param.getIdNumber());
        student.setName(param.getName());
        student.setPhone(param.getPhone());
        student.setSex(param.getSex());
        student.setEmail(param.getEmail());
        student.setPassword(HashUtils.md5(param.getPassword()));
        student.setAge(param.getAge());

        repository.save(student);
        return Response.success(new StudentDto(student));
    }
}
