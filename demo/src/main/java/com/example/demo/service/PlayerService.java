package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Player;
import com.example.demo.util.BookResponse;
import com.example.demo.util.PlayerResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service

public class PlayerService {

    private static final String Player_Service_URL = "http://localhost:8080/api/v1/player/AllPlayer";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PlayerService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Optional<Player> getPlayerByName(String name) {
        try {
            // Fetch response as a String
            ResponseEntity<String> response = restTemplate.getForEntity(Player_Service_URL, String.class);

            // Check if the response is successful
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // Convert the response body to BookResponse
//                PlayerResponse playerResponse = objectMapper.readValue(response.getBody(), PlayerResponse.class);
//                List<Player> players = playerResponse.getPlayers();
                List<Player> players = objectMapper.readValue(response.getBody(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Player.class));

                // Find the book by ISBN
                return players.stream()
                        .filter(player -> player.getName().equalsIgnoreCase(name))
                        .findFirst();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle the exception appropriately in production code
        }

        return Optional.empty();
    }
}

//public class PlayerService {
//
//    private static final String PLAYER_SERVICE_URL = "http://localhost:8080/api/v1/player/AllPlayer";
//    private final RestTemplate restTemplate;
//    private final ObjectMapper objectMapper;
//
//    public PlayerService() {
//        this.restTemplate = new RestTemplate();
//        this.objectMapper = new ObjectMapper();
//    }
//
//    public Optional<Player> getPlayerByName(String name) {
//        try {
//            // Fetch response as a String
//            ResponseEntity<String> response = restTemplate.getForEntity(PLAYER_SERVICE_URL, String.class);
//
//            // Check if the response is successful
//            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
//                // Convert the response body to a list of Player objects
//                List<Player> players = objectMapper.readValue(response.getBody(),
//                        objectMapper.getTypeFactory().constructCollectionType(List.class, Player.class));
//
//                // Find the player by name
//                return players.stream()
//                        .filter(player -> player.getName().equalsIgnoreCase(name))
//                        .findFirst();
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace(); // Handle the exception appropriately in production code
//        }
