package com.bootcamp.aula_spring_data.services.impl;

import com.bootcamp.aula_spring_data.dtos.CourseDto;
import com.bootcamp.aula_spring_data.entities.Course;
import com.bootcamp.aula_spring_data.exceptions.EntityNotFoundException;
import com.bootcamp.aula_spring_data.repositories.CourseRepository;
import com.bootcamp.aula_spring_data.services.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> listarTodos() {
        return this.courseRepository.findAll();
    }

//    @Override
    public Optional<Course> buscarPorId(Long id) {
        return this.courseRepository.findById(id);
    }

    @Transactional
    @Override
    public Course salvar(Course course) {
        return this.courseRepository.save(course);
    }

    @Override
    public void apagarPorId(Long id) {
        Optional<Course> optCourse = buscarPorId(id);
        if ( optCourse.isEmpty() )
            throw new EntityNotFoundException(" Course com id " + id);
        this.courseRepository.delete(optCourse.get());
    }
    public Course atualizar(Long id, CourseDto courseDto) {
        Optional<Course> optCourse = buscarPorId(id);
        if (optCourse.isEmpty()) {
            throw new EntityNotFoundException("Course com id " + id);
        }
        Course course = optCourse.get();
        course.setName(courseDto.name());
        course.setDescription(courseDto.description());
        return this.courseRepository.save(course);
    }
}
