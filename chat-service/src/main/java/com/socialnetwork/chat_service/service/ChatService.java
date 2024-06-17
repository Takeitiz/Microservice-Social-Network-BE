//package com.socialnetwork.chat_service.service;
//
//import io.getstream.chat.java.models.User;
//import io.getstream.chat.java.services.framework.Client;
//
//public class ChatService {
//
//    private Client streamClient;
//    private String apiKey = "your_api_key";
//    private String apiSecret = "your_api_secret";
//
////    public ChatService() {
////        this.streamClient = Client.getInstance()
////                .apiKey(apiKey)
////                .apiSecret(apiSecret)
////                .build();
////    }
////
////    public String generateToken(String userId) {
////        try {
////            return User.createToken(apiKey, apiSecret, userId);
////        } catch (Exception e) {
////            e.printStackTrace();
////            return null;
////        }
////    }
//}