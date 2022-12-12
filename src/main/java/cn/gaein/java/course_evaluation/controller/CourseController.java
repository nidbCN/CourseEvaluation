package cn.gaein.java.course_evaluation.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

import cn.gaein.java.course_evaluation.dto.CourseDto;
import cn.gaein.java.course_evaluation.dto.StudentDto;
import cn.gaein.java.course_evaluation.entity.Course;
import cn.gaein.java.course_evaluation.entity.Student;
import cn.gaein.java.course_evaluation.entity.Teacher;
import cn.gaein.java.course_evaluation.param.CourseParam;
import cn.gaein.java.course_evaluation.repository.CourseRepository;
import cn.gaein.java.course_evaluation.repository.StudentRepository;
import cn.gaein.java.course_evaluation.repository.TeacherRepository;
import cn.gaein.java.course_evaluation.responseHelper.Response;

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
@RequestMapping("/course")
public class CourseController {

    private final CourseRepository repository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    private final Response courseNotFoundResponse = Response.notFound("Course not found");
    private final Response teacherNotFoundResponse = Response.notFound("Teacher not found");
    private final Response studentNotFoundResponse = Response.notFound("Student not found");

    @Autowired
    public CourseController(CourseRepository repository, TeacherRepository teacherRepository,
                            StudentRepository studentRepository) {
        this.repository = repository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/search")
    public Response searchCourse(String title) {
        Iterable<Course> courses = repository.findByTitle(title);
        List<CourseDto> dtos = new ArrayList<>();
        for (Course course : courses) {
            dtos.add(new CourseDto(course));
        }
        if (dtos.isEmpty()) {
            return courseNotFoundResponse;
        }
        return Response.success(dtos);
    }

    public Response getCourseList(@RequestParam(required = false) String title) {
        Iterable<Course> courses;

        if (title == null) {
            courses = repository.findAll();
        } else {
            courses = repository.findByTitle(title);
        }

        return Response.success(
            StreamSupport.stream(courses.spliterator(), false)
                .map(CourseDto::new)
        );
    }

    @PostMapping("")
    public Response createCourse(@RequestBody CourseParam param) {
        // check if teacher exists
        var teacherId = param.getTeacherId();
        var teacher = teacherRepository.findById(teacherId);
        if (!teacher.isPresent()) {
            return teacherNotFoundResponse;
        }

        // save course
        Course course = new Course();
        course.setTitle(param.getTitle());
        course.setDescription(param.getDescription());
        course.setTeacher(teacher.get());
        repository.save(course);
        return Response.success(new CourseDto(course));
    }

    @GetMapping("/{id}")
    public Response getCourseById(@PathVariable("id") long id) {
        var course = repository.findById(id);
        return course == null
            ? courseNotFoundResponse
            : Response.success(new CourseDto(course));
    }

    public Response updateCourse(@PathVariable("id") long id, @RequestBody CourseParam param) {
        var course = repository.findById(id);

        if (course == null) {
            return courseNotFoundResponse;
        }

        // check if teacher exists
        long teacherId = param.getTeacherId();
        var teacher = teacherRepository.findById(teacherId);
        if (teacher == null) {
            return teacherNotFoundResponse;
        }

        // update course
        course.setTitle(param.getTitle());
        course.setDescription(param.getDescription());
        course.setTeacher(teacher);
        repository.save(course);
        return Response.success(new CourseDto(course));
    }

    @DeleteMapping("/{id}")
    public Response deleteCourse(@PathVariable("id") long id) {
        var course = repository.findById(id);

        if (course == null) {
            return courseNotFoundResponse;
        }

        repository.delete(course);
        return Response.success();
    }

    public Response test(long id) {
        var course = repository.findById(id);

        return course == null
            ? courseNotFoundResponse
            : Response.success();
    }

    @GetMapping("/{id}/students")
    public Response getStudentsByCourseId(@PathVariable("id") long id) {
        var course = repository.findById(id);

        return course == null
            ? courseNotFoundResponse
            : Response.success(
            course.students.stream().map(StudentDto::new)
        );
    }


    @PostMapping("/{id}/student")
    public Response insertCourseStudent(@PathVariable("id") long id, @RequestParam long studentId) {
        var course = repository.findById(id);
        if (course == null) {
            return teacherNotFoundResponse;
        }
        var student = studentRepository.findById(studentId);
        if (student == null) {
            return studentNotFoundResponse;
        }
        // check if student is already in course
        if (course.getStudents().contains(student)) {
            return Response.noContent("Student is already in course");
        }

        course.students.add(student);
        repository.save(course);

        return Response.success(new CourseDto(course));
    }

    @DeleteMapping("/{id}/student")
    public Response deleteCourseStudent(@PathVariable("id") long id, @RequestParam long studentId) {
        var course = repository.findById(id);
        if (course == null) {
            return teacherNotFoundResponse;
        }
        var student = studentRepository.findById(studentId);
        if (student == null) {
            return studentNotFoundResponse;
        }
        course.students.remove(student);
        repository.save(course);
        return Response.success(new CourseDto(course));
    }

    @GetMapping("/teacher")
    public Response getAllCourseByTeacherId(@RequestParam long teacherId) {
        var courses = repository.findByTeacherId(teacherId);

        return Response.success(
            StreamSupport.stream(courses.spliterator(), false)
                .map(CourseDto::new)
        );
    }

    @GetMapping("/student")
    public Response getAllCourseByStudentId(@RequestParam long studentId) {
        var courses = repository.findByStudentId(studentId);

        return Response.success(
            courses.stream().map(CourseDto::new)
        );
    }
}
