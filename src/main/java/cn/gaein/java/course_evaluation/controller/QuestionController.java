package cn.gaein.java.course_evaluation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import cn.gaein.java.course_evaluation.dto.QuestionDto;
import cn.gaein.java.course_evaluation.entity.Evaluation;
import cn.gaein.java.course_evaluation.entity.Question;
import cn.gaein.java.course_evaluation.param.QuestionParam;
import cn.gaein.java.course_evaluation.repository.EvaluationRepository;
import cn.gaein.java.course_evaluation.repository.QuestionRepository;
import cn.gaein.java.course_evaluation.responseHelper.Response;

import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionRepository repository;
    private final EvaluationRepository evaluationRepository;

    private final Response questionNotFoundResponse = Response.notFound("Question not found");
    private final Response evaluationNoFoundResponse = Response.notFound("Evaluation not found");

    @Autowired
    public QuestionController(QuestionRepository repository, EvaluationRepository evaluationRepository) {
        this.repository = repository;
        this.evaluationRepository = evaluationRepository;
    }

    @GetMapping("")
    public Response getAllQuestion() {
        var questions = repository.findAll();

        return Response.success(
            StreamSupport.stream(questions.spliterator(), false)
                .map(QuestionDto::new)
        );
    }

    @PostMapping("")
    public Response createQuestion(@RequestBody QuestionParam param) {
        // check if evaluation exists
        var evaluationId = param.getEvaluationId();
        var evaluation = evaluationRepository.findById(evaluationId);

        if (evaluation == null) {
            return evaluationNoFoundResponse;
        }

        // save question
        var question = new Question();
        question.setContent(param.getContent());
        question.setScore(param.getScore());
        question.setEvaluation(evaluation);

        repository.save(question);
        return Response.success(new QuestionDto(question));
    }

    @GetMapping("/{id}")
    public Response getQuestionById(@PathVariable("id") long id) {
        Question question = repository.findById(id);
        if (question == null) {
            return questionNotFoundResponse;
        }
        return Response.success(new QuestionDto(question));
    }

    @PutMapping("/{id}")
    public Response updateQuestion(@PathVariable("id") long id, @RequestBody QuestionParam param) {
        // check if question exists
        var question = repository.findById(id);
        if (question == null) {
            return questionNotFoundResponse;
        }
        // check if evaluation exists
        var evaluationId = param.getEvaluationId();
        var evaluation = evaluationRepository.findById(evaluationId);
        if (evaluation == null) {
            return evaluationNoFoundResponse;
        }

        // update question
        question.setContent(param.getContent());
        question.setScore(param.getScore());
        question.setEvaluation(evaluation);
        repository.save(question);
        return Response.success(new QuestionDto(question));
    }

    @DeleteMapping("/{id}")
    public Response deleteQuestion(@PathVariable("id") long id) {
        var question = repository.findById(id);

        if (question == null) {
            return questionNotFoundResponse;
        }

        repository.delete(question);
        return Response.success();
    }

    @GetMapping("/evaluation")
    public Response getAllQuestionByEvaluationId(@RequestParam long evaluationId) {
        var questions = repository.findByEvaluationId(evaluationId);

        return Response.success(
            StreamSupport.stream(questions.spliterator(), false)
                .map(QuestionDto::new)
        );
    }
}
