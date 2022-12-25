package cn.gaein.java.course_evaluation.controller;

import cn.gaein.java.course_evaluation.dto.StudentDto;
import cn.gaein.java.course_evaluation.entity.Student;
import cn.gaein.java.course_evaluation.param.LoginParam;
import cn.gaein.java.course_evaluation.param.StudentParam;
import cn.gaein.java.course_evaluation.param.StudentRegisterParam;
import cn.gaein.java.course_evaluation.repository.StudentRepository;
import cn.gaein.java.course_evaluation.responseHelper.Response;
import cn.gaein.java.course_evaluation.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.stream.StreamSupport;

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

    @PostMapping("/session")
    public Response login(@RequestBody LoginParam param) {
        var student = repository.findByIdNumber(param.getAccount());

        if (student == null) {
            return studentNotFoundResponse;
        }

        if (!student.getPassword().equals(HashUtils.md5(param.getPassword()))) {
            return Response.badRequest("Password is incorrect");
        }

        return Response.success(new StudentDto(student));
    }

    @PostMapping("/student/register")
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
