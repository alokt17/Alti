package com.example.demo.util;

import com.example.demo.entity.Player;

import java.util.List;

public class PlayerResponse {
    List<Player> players;
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
