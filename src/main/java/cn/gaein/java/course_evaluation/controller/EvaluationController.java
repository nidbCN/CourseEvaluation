package cn.gaein.java.course_evaluation.controller;

import cn.gaein.java.course_evaluation.dto.EvaluationDto;
import cn.gaein.java.course_evaluation.entity.Evaluation;
import cn.gaein.java.course_evaluation.param.EvaluationParam;
import cn.gaein.java.course_evaluation.repository.CourseRepository;
import cn.gaein.java.course_evaluation.repository.EvaluationRepository;
import cn.gaein.java.course_evaluation.repository.StudentRepository;
import cn.gaein.java.course_evaluation.responseHelper.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    private final EvaluationRepository repository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    private final Response evaluationNotFoundResponse = Response.notFound("Evaluation not found");
    private final Response studentNotFoundResponse = Response.notFound("Student not found");
    private final Response courseNotFoundResponse = Response.notFound("Course not found");
    private final Response studentNotInCourseResponse = Response.badRequest("Student not in course");
    private final Response studentAlreadyEvaluatedResponse = Response
            .badRequest("Student has already evaluated this course");

    @Autowired
    public EvaluationController(EvaluationRepository repository, StudentRepository studentRepository,
                                CourseRepository courseRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;

    }

    @GetMapping("")
    public Response getAll() {
        var evaluations = repository.findAll();

        return Response.success(
                StreamSupport.stream(evaluations.spliterator(), false)
                        .map(EvaluationDto::new)
        );
    }

    @PostMapping("")
    public Response createEvaluation(@RequestBody EvaluationParam param) {
        // check if student exist
        var studentId = param.getStudentId();
        var student = studentRepository.findById(studentId);

        if (student == null) {
            return studentNotFoundResponse;
        }

        // check if course exist
        var courseId = param.getCourseId();
        var course = courseRepository.findById(courseId);

        if (course == null) {
            return courseNotFoundResponse;
        }

        // check if student in course
        if (!course.getStudents().contains(student)) {
            return studentNotInCourseResponse;
        }

        // check if student has already evaluated this course
        if (repository.findByStudentIdAndCourseId(studentId, courseId) != null) {
            return studentAlreadyEvaluatedResponse;
        }

        // create evaluation
        var evaluation = new Evaluation();
        evaluation.setStudent(student);
        evaluation.setCourse(course);
        repository.save(evaluation);
        return Response.success(new EvaluationDto(evaluation));
    }

    @GetMapping("/{id}")
    public Response getEvaluationById(@PathVariable("id") long id) {
        var evaluation = repository.findById(id);

        if (evaluation == null) {
            return evaluationNotFoundResponse;
        }

        return Response.success(new EvaluationDto(evaluation));
    }

    @PutMapping("/{id}")
    public Response updateEvaluation(@PathVariable("id") long id, @RequestBody EvaluationParam param) {
        var evaluation = repository.findById(id);

        if (evaluation == null) {
            return evaluationNotFoundResponse;
        }
        // check if student exist

        var studentId = param.getStudentId();
        var student = studentRepository.findById(studentId);

        if (student == null) {
            return studentNotFoundResponse;
        }

        // check if course exist
        var courseId = param.getCourseId();
        var course = courseRepository.findById(courseId);

        if (course == null) {
            return courseNotFoundResponse;
        }

        // check if student in course
        if (!course.getStudents().contains(student)) {
            return studentNotInCourseResponse;
        }

        // check if student has already evaluated this course
        if (repository.findByStudentIdAndCourseId(studentId, courseId) != null) {
            return studentAlreadyEvaluatedResponse;
        }

        // update evaluation
        evaluation.setStudent(student);
        evaluation.setCourse(course);
        repository.save(evaluation);
        return Response.success(new EvaluationDto(evaluation));
    }

    @DeleteMapping("/{id}")
    public Response deleteEvaluation(@PathVariable("id") long id) {
        var evaluation = repository.findById(id);

        if (evaluation == null) {
            return evaluationNotFoundResponse;
        }

        repository.delete(evaluation);
        return Response.success();
    }

    @GetMapping("/student/{id}")
    public Response getAllEvaluationByStudentId(@PathVariable("id") long id) {
        var evaluations = repository.findByStudentId(id);

        return Response.success(StreamSupport.stream(evaluations.spliterator(), false)
                .map(EvaluationDto::new)
        );
    }

    @GetMapping("/course/{id}")
    public Response getAllEvaluationByCourseId(@PathVariable("id") long id) {
        var evaluations = repository.findByCourseId(id);

        return Response.success(StreamSupport.stream(evaluations.spliterator(), false)
                .map(EvaluationDto::new)
        );
    }
}
