package com.bootcamp.aula_spring_data.controllers;

import com.bootcamp.aula_spring_data.dtos.CourseDto;
import com.bootcamp.aula_spring_data.entities.Course;
import com.bootcamp.aula_spring_data.exceptions.EntityNotFoundException;
import com.bootcamp.aula_spring_data.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.courseService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        Optional<Course> optCourse = this.courseService.buscarPorId(id);
        if (  optCourse.isEmpty() ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar curso");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optCourse.get());
    }

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody CourseDto courseDto){
        Course course = new Course();
        course.setName(courseDto.name());
        course.setDescription(courseDto.description());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.courseService.salvar(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            this.courseService.apagarPorId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Curso apagado com sucesso");
        } catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso nao encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        try {
            Course updatedCourse = this.courseService.atualizar(id, courseDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedCourse);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
        }
    }

    /*
        criar o metodo de atualizar curso, podendo alterar nome ou descricao
        @PutMapping
        localhost:8080/courses/1
        -> 1) Consultar se o curso existe?
        -> 2) Se existe, atualizar a informação
        -> 3) se nao existe lançar excecao que nao existe
        -> 4) se salvou com sucesso retorna o course atualizado
     */


}
