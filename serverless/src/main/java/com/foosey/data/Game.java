package com.foosey.data;

import java.util.Map;

public class Game {
    private final int gameId;
    private final int leagueId;
    private final long timestamp;
    private final Map<Player, Integer> scores;

    public Game(int gameId, int leagueId, long timestamp, Map<Player, Integer> scores) {
        this.gameId = gameId;
        this.leagueId = leagueId;
        this.timestamp = timestamp;
        this.scores = scores;
    }

    public int getGameId() {
        return gameId;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Map<Player, Integer> getScores() {
        return scores;
    }
}
