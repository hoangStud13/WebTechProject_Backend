package com.htwberlin.popularflightservice.repository;

import com.htwberlin.popularflightservice.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Long> {
}
