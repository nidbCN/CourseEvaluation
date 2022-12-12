package cn.gaein.java.course_evaluation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import cn.gaein.java.course_evaluation.dto.PraiseDto;
import cn.gaein.java.course_evaluation.entity.Evaluation;
import cn.gaein.java.course_evaluation.entity.Praise;
import cn.gaein.java.course_evaluation.param.PraiseParam;
import cn.gaein.java.course_evaluation.repository.EvaluationRepository;
import cn.gaein.java.course_evaluation.repository.PraiseRepository;
import cn.gaein.java.course_evaluation.responseHelper.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/praise")
public class PraiseController {

    private final PraiseRepository repository;
    private final EvaluationRepository evaluationRepository;

    private final Response praiseNotFoundResponse = Response.notFound("Praise not found");
    private final Response evaluationNotFoundResponse = Response.notFound("Evaluation not found");
    private final Response evaluationHasBeenPraised = Response.badRequest("Evaluation has already been praised");

    @Autowired
    public PraiseController(PraiseRepository repository, EvaluationRepository evaluationRepository) {
        this.repository = repository;
        this.evaluationRepository = evaluationRepository;
    }

    @GetMapping("")
    public Response getAllPraise() {
        var praises = repository.findAll();

        return Response.success(
            StreamSupport.stream(praises.spliterator(), false)
                .map(PraiseDto::new)
        );
    }

    @PostMapping("")
    public Response createPraise(@RequestBody PraiseParam param) {
        // check if evaluation exists
        var evaluationId = param.getEvaluationId();
        var evaluation = evaluationRepository.findById(evaluationId);
        if (evaluation == null) {
            return evaluationNotFoundResponse;
        }
        // check if evaluation has already been praised
        var check = repository.findByEvaluationId(evaluationId);
        if (check != null) {
            return evaluationHasBeenPraised;
        }
        // save praise
        var praise = new Praise();
        praise.setContent(param.getContent());
        praise.setEvaluation(evaluation);
        repository.save(praise);
        return Response.success(new PraiseDto(praise));
    }

    @GetMapping("/{id}")
    public Response getPraiseById(@PathVariable("id") long id) {
        var praise = repository.findById(id);

        return praise == null
            ? praiseNotFoundResponse
            : Response.success(new PraiseDto(praise));
    }

    @PutMapping("/{id}")
    public Response updatePraise(@PathVariable("id") long id, @RequestBody PraiseParam param) {
        // check if praise exists
        var praise = repository.findById(id);
        if (praise == null) {
            return praiseNotFoundResponse;
        }

        // check if evaluation exists
        var evaluationId = param.getEvaluationId();
        var evaluation = evaluationRepository.findById(evaluationId);

        if (evaluation == null) {
            return evaluationNotFoundResponse;
        }

        // check if evaluation has already been praised
        var check = repository.findByEvaluationId(evaluationId);
        if (check != null) {
            return evaluationHasBeenPraised;
        }
        // update praise

        praise.setContent(param.getContent());
        praise.setEvaluation(evaluation);
        repository.save(praise);

        return Response.success(new PraiseDto(praise));
    }

    @DeleteMapping("/{id}")
    public Response deletePraise(@PathVariable("id") long id) {
        var praise = repository.findById(id);

        if (praise == null) {
            return praiseNotFoundResponse;
        }

        repository.delete(praise);
        return Response.success();
    }

    @GetMapping("/evaluation")
    public Response getPraiseByEvaluationId(@RequestParam long evaluationId) {
        var praise = repository.findByEvaluationId(evaluationId);

        return praise == null
            ? praiseNotFoundResponse
            : Response.success(new PraiseDto(praise));
    }
}
