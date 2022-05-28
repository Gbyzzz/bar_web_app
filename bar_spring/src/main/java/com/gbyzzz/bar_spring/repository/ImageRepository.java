package com.gbyzzz.bar_spring.repository;

import com.gbyzzz.bar_spring.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
