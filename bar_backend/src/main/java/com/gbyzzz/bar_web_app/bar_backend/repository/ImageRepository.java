package com.gbyzzz.bar_web_app.bar_backend.repository;

import com.gbyzzz.bar_web_app.bar_backend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
