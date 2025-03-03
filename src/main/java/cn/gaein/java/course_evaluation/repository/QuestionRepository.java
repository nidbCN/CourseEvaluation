package cn.gaein.java.course_evaluation.repository;

import cn.gaein.java.course_evaluation.entity.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    Question findById(long id);

    Iterable<Question> findByEvaluationId(long id);
}
