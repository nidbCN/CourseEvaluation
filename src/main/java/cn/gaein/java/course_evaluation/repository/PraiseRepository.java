package cn.gaein.java.course_evaluation.repository;

import cn.gaein.java.course_evaluation.entity.Praise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PraiseRepository extends CrudRepository<Praise, Long> {
    Praise findById(long id);

    Praise findByEvaluationId(long evaluationId);
}
