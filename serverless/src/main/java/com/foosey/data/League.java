package com.foosey.data;

public class League {
    private final int leagueId;
    private final String leagueCode;
    private final String leagueName;

    public League(int leagueId, String leagueCode, String leagueName) {
        this.leagueId = leagueId;
        this.leagueCode = leagueCode;
        this.leagueName = leagueName;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public String getLeagueCode() {
        return leagueCode;
    }

    public String getLeagueName() {
        return leagueName;
    }
}
