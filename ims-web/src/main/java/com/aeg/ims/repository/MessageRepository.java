package com.aeg.ims.repository;

import com.aeg.ims.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
