package com.bootcamp.aula_spring_data.services;

import com.bootcamp.aula_spring_data.dtos.CourseDto;
import com.bootcamp.aula_spring_data.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> listarTodos();
    Optional<Course> buscarPorId(Long id);
    Course salvar(Course course);
    void apagarPorId(Long id);
    Course atualizar(Long id, CourseDto courseDto);
}
