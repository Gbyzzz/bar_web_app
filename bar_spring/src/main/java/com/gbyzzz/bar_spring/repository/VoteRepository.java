package com.gbyzzz.bar_spring.repository;

import com.gbyzzz.bar_spring.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anton Pinchuk
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
