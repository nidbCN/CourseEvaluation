package cn.gaein.java.course_evaluation.controller;

import cn.gaein.java.course_evaluation.dto.TeacherDto;
import cn.gaein.java.course_evaluation.entity.Teacher;
import cn.gaein.java.course_evaluation.param.TeacherParam;
import cn.gaein.java.course_evaluation.repository.TeacherRepository;
import cn.gaein.java.course_evaluation.responseHelper.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherRepository repository;
    private final Response teacherNotFoundResponse = Response.notFound("Teacher not found");
    private final Response teacherIdNumberExistResponse = Response.badRequest("Teacher id number already exists");

    @Autowired
    public TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Response getAllTeacher() {
        var teachers = repository.findAll();

        return Response.success(
                StreamSupport.stream(teachers.spliterator(), false)
                        .map(TeacherDto::new)
        );
    }

    @PostMapping("")
    public Response createTeacher(@RequestBody TeacherParam param) {
        // check if idNumber exist
        if (repository.findByIdNumber(param.getIdNumber()) != null) {
            return teacherIdNumberExistResponse;
        }

        // save teacher
        var teacher = new Teacher();
        teacher.setIdNumber(param.getIdNumber());
        teacher.setName(param.getName());
        teacher.setPhone(param.getPhone());

        repository.save(teacher);
        return Response.success(new TeacherDto(teacher));
    }

    @GetMapping("/{id}")
    public Response getTeacherById(@PathVariable("id") long id) {
        var teacher = repository.findById(id);

        return teacher == null
                ? teacherNotFoundResponse
                : Response.success(new TeacherDto(teacher));
    }

    @PostMapping("/{id}")
    public Response updateTeacher(@PathVariable("id") long id, @RequestBody TeacherParam param) {
        var teacher = repository.findById(id);
        if (teacher == null) {
            return teacherNotFoundResponse;
        }

        // check if idNumber exist
        if (repository.findByIdNumber(param.getIdNumber()) != null
                && !teacher.getIdNumber().equals(param.getIdNumber())) {
            return teacherIdNumberExistResponse;
        }

        // update teacher
        teacher.setIdNumber(param.getIdNumber());
        teacher.setName(param.getName());
        teacher.setPhone(param.getPhone());

        repository.save(teacher);
        return Response.success(new TeacherDto(teacher));
    }

    @DeleteMapping("/{id}")
    public Response deleteTeacher(@PathVariable("id") long id) {
        var teacher = repository.findById(id);

        if (teacher == null) {
            return teacherNotFoundResponse;
        }

        repository.delete(teacher);
        return Response.success();
    }

    @GetMapping("/search")
    public Response searchTeacherByName(@RequestParam String name) {
        var teachers = repository.findByName(name);

        return Response.success(
                StreamSupport.stream(teachers.spliterator(), false)
                        .map(TeacherDto::new)
        );
    }
}
