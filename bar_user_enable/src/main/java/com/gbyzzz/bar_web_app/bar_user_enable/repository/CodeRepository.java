package com.gbyzzz.bar_web_app.bar_user_enable.repository;

import com.gbyzzz.bar_web_app.bar_user_enable.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CrudRepository<Message, String> {
}
