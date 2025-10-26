package com.springAI.OllamaSetUp.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class chatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatclientBuilder){
        ChatOptions chatOptions = ChatOptions.builder().model("llama3.2:1b")
                .temperature(0.8).build();
        return chatclientBuilder.defaultOptions(chatOptions)
                .defaultAdvisors(new SimpleLoggerAdvisor())

                .defaultSystem("""
                        You are an internal IT helpdesk assistant. Your role is to assist\s
                        employees with IT-related issues such as resetting passwords,\s 
                        unlocking accounts, and answering questions related to IT policies.\s
                        If a user requests help with anything outside of these\s
                        responsibilities, respond politely and inform them that you are\s
                        only able to assist with IT support tasks within your defined scope.\s
                        """).build();
    }
}