package com.db.migration.service.impl;

import com.db.migration.common.StudentMap;
import com.db.migration.entity.Student;
import com.db.migration.repository.StudentRepository;
import com.db.migration.response.StudentResponse;
import com.db.migration.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    @Override
    public StudentResponse create(Student student) {
        return StudentMap
                .mapStudentResponse(this.studentRepository.save(student));
    }

    @Override
    public StudentResponse get(Integer id) {
        final Optional<Student> student = this.studentRepository.findById(id);
        if(student.isPresent())
            return StudentMap
                    .mapStudentResponse(student.get());
        return new StudentResponse();
    }

    @Override
    public void delete(Integer id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public StudentResponse update(Integer id, Student student) {
        final Optional<Student> optionalStudent = this.studentRepository.findById(id);
        if (optionalStudent.isPresent())
            return StudentMap
                    .mapStudentResponse(this.studentRepository.save(student));
        return new StudentResponse();
    }
}
