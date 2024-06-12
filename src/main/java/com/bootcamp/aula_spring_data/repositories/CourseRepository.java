package com.bootcamp.aula_spring_data.repositories;

import com.bootcamp.aula_spring_data.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
