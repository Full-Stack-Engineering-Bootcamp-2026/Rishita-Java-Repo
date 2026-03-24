package com.cdac.servicelayer.repository;



import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {

    public List<String> getMessages() {
        return List.of(
            "Do or die",
            "You can do it!",
            "Karma is real!"
        );
    }
}
