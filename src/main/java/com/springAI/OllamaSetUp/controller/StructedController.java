package com.springAI.OllamaSetUp.controller;

import com.springAI.OllamaSetUp.model.Countries;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClientBuilder;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StructedController {
    private final ChatClient chatClient;
    public StructedController(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }
    @GetMapping("/chat-bean")
    public ResponseEntity<Countries> chatBean(@RequestParam("message") String message){
        Countries countries = chatClient.prompt().user(message).call().entity(Countries.class);
        return ResponseEntity.ok(countries);
    }
    @GetMapping("/chat-list")
    public ResponseEntity<List<String>> chatList(@RequestParam("message") String message){
        List<String> countries = chatClient.prompt().user(message).call()
                .entity(new ListOutputConverter());
        return ResponseEntity.ok(countries);
    }
    @GetMapping("/chat-map")
    public ResponseEntity<Map<String,Object>> chatMap(@RequestParam("message") String message){
        Map<String,Object> countryCities = chatClient.prompt().user(message).call().entity(new MapOutputConverter());
        return ResponseEntity.ok(countryCities);
    }
    @GetMapping("/chat-bean-list")
    public ResponseEntity<List<Countries>> chatBeanList(@RequestParam("message") String message){
        List<Countries>countries=chatClient.prompt().user(message).call().entity(new ParameterizedTypeReference<List<Countries>>() {
        });
        return ResponseEntity.ok(countries);


    }
}