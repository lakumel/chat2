package com.chatex.chatex.chat.web;



import com.chatex.chatex.chat.entity.ChatRoomDTO;
import com.chatex.chatex.chat.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/chat")
public class RoomController {

    private final ChatService chatService;

    public RoomController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){
        rttr.addFlashAttribute("roomName", chatService.createChatRoomDTO(name));

        return "redirect:/chat/room";
    }

    @GetMapping("/room")
    public void getRoom(String roomId, Model model){
        model.addAttribute("list", chatService.findAllRooms());
        ChatRoomDTO chatRoom = chatService.findRoomById(roomId);

        model.addAttribute("room", Objects.requireNonNullElseGet(chatRoom, ChatRoomDTO::new));
    }





}
