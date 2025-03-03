package cn.gaein.java.course_evaluation.repository;

import cn.gaein.java.course_evaluation.entity.Evaluation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends CrudRepository<Evaluation, Long> {
    Evaluation findById(long id);

    Evaluation findByStudentIdAndCourseId(long studentId, long courseId);

    Iterable<Evaluation> findByStudentId(long studentId);

    Iterable<Evaluation> findByCourseId(long courseId);
}
