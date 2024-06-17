package com.socialnetwork.chat_service.controller;

import io.getstream.chat.java.exceptions.StreamException;
import io.getstream.chat.java.models.User;
import io.getstream.chat.java.services.framework.Client;
import io.getstream.chat.java.services.framework.DefaultClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/chat")
@RequiredArgsConstructor
public class ChatController {

    @Autowired
    private final Client client;

    @GetMapping("/genusertoken/{userId}")
    public String genUserToken(@PathVariable("userId") String userId) throws StreamException {
        var user = User.UserRequestObject.builder().id(userId).role("admin").build();
        User.upsert().user(user).request();
        return User.createToken(userId, null, null);
    }


}
