package com.foosey.data;

public class Player {
    private final int playerId;
    private final int leagueId;
    private final String displayName;
    private final boolean admin;
    private final boolean active;
    private final int elo;
    private final int gamesPlayed;
    private final int gamesWon;

    /*
     * Base constructor
     */
    public Player(int playerId, int leagueId, String displayName, boolean admin, boolean active, int elo, int gamesPlayed, int gamesWon) {
        this.playerId = playerId;
        this.leagueId = leagueId;
        this.displayName = displayName;
        this.admin = admin;
        this.active = active;
        this.elo = elo;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
    }

    /*
     * Overwrite constructor. Useful when updating a player
     * This will copy the player object passed in, but overwrite any values with non-null parameters provided
     * Note that playerId and leagueId cannot be overwritten
     */
    public Player(Player player, String displayName, Boolean admin, Boolean active, Integer elo, Integer gamesPlayed, Integer gamesWon) {
        this.playerId = player.getPlayerId();
        this.leagueId = player.getLeagueId();
        this.displayName = displayName == null ? player.getDisplayName() : displayName;
        this.admin = admin == null ? player.isAdmin() : admin;
        this.active = active == null ? player.isActive() : active;
        this.elo = elo == null ? player.getElo() : elo;
        this.gamesPlayed = gamesPlayed == null ? player.getGamesPlayed() : gamesPlayed;
        this.gamesWon = gamesWon == null ? player.getGamesWon() : gamesWon;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isActive() {
        return active;
    }

    public int getElo() {
        return elo;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }
}
