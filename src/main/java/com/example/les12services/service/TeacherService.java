package com.example.les12services.service;

import com.example.les12services.dto.TeacherDto;
import com.example.les12services.exception.RecordNotFoundException;
import com.example.les12services.model.Teacher;
import com.example.les12services.repository.TeacherRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository repos;

    // constructor injection (instead of @Autowired)
    public TeacherService(TeacherRepository r) {
        this.repos = r;
    }

    public Long createTeacher(TeacherDto teacherDto) {
        Teacher newTeacher = new Teacher();

        // map dto to entity
        newTeacher.setFirstName(teacherDto.firstName);
        newTeacher.setLastName(teacherDto.lastName);
        newTeacher.setDob(teacherDto.dob);

        Teacher savedTeacher = repos.save(newTeacher);
        return savedTeacher.getId();
    }

    public TeacherDto getTeacher(long id) {

        if (id < 0) {
            throw new IndexOutOfBoundsException("You should use a positive number instead a negative number");
        } else if (!repos.existsById(id)) {
            throw new RecordNotFoundException("No teacher found with this ID");
        } else {
            Optional<Teacher> requestedTeacher = repos.findById(id);

            //map entity to dto

            TeacherDto requestedTeacherDto = new TeacherDto();

            requestedTeacherDto.firstName = requestedTeacher.get().getFirstName();
            requestedTeacherDto.lastName = requestedTeacher.get().getLastName();
            requestedTeacherDto.dob = requestedTeacher.get().getDob();

            return requestedTeacherDto;
        }
    }

        public Iterable<TeacherDto> getAllTeachers () {

            Iterable<Teacher> requestedTeachersList = repos.findAll();

            List<TeacherDto> requestedTeachersDtoList = new ArrayList<>();

            for (Teacher teacher :
                    requestedTeachersList) {
                TeacherDto requestedTeacherDto = new TeacherDto();

                requestedTeacherDto.firstName = teacher.getFirstName();
                requestedTeacherDto.lastName = teacher.getLastName();
                requestedTeacherDto.dob = teacher.getDob();

                requestedTeachersDtoList.add(requestedTeacherDto);

            }

            return requestedTeachersDtoList;

        }

}
