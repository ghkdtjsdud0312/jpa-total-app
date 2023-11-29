package com.kh.jpatotalapp.repository;

import com.kh.jpatotalapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
