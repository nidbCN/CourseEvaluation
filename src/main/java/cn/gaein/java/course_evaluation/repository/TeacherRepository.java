package cn.gaein.java.course_evaluation.repository;

import cn.gaein.java.course_evaluation.entity.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher findById(long id);

    Iterable<Teacher> findByName(String name);

    Teacher findByIdNumber(String idNumber);
}
