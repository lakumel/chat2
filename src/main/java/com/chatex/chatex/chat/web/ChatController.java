package com.chatex.chatex.chat.web;

import com.chatex.chatex.chat.entity.ChatMessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate template;   //특정 broker로 메세지 전달

    public ChatController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message){
        message.setMessage(message.getWriter() + " 님이 채팅방에 참여햐였습니다.");
        template.convertAndSend("/sub/chat/room" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message){
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(),message);
    }




}
