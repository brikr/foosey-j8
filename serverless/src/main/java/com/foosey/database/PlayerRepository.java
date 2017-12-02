package com.foosey.database;

import com.foosey.data.Player;
import org.apache.log4j.Logger;

import java.sql.*;

public class PlayerRepository {
    private static final Logger LOG = Logger.getLogger(PlayerRepository.class);
    private Connection conn;

    public PlayerRepository() {
        this.conn = DatabaseUtils.getConnection();
    }

    public Player getByid(int playerId) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT PlayerId, LeagueID, DisplayName, Admin, Active, Elo, GamesPlayed, GamesWon FROM Player " +
                "WHERE PlayerID = ?"
            );
            stmt.setInt(1, playerId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Player(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getBoolean(4),
                    rs.getBoolean(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8));
            }
        } catch (SQLException e) {
            LOG.error(e.toString());
        }

        return null;
    }

    /*
     * Adds player to database and returns their new id
     */
    public int addPlayer(int leagueId, String displayName, boolean admin, boolean active) {
        int playerId = -1;

        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Player " +
                "VALUES (NULL, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)",
                Statement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, leagueId);
            stmt.setString(2, displayName);
            stmt.setBoolean(3, admin);
            stmt.setBoolean(4, active);

            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) playerId = rs.getInt(1);
        } catch (SQLException e) {
            LOG.error(e.toString());
        }

        return playerId;
    }

    public boolean updatePlayer(int id, Player player) {
        boolean successful = false;

        try {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Player SET " +
                "DisplayName = ?," +
                "Admin = ?," +
                "Active = ?," +
                "Elo = ?," +
                "GamesPlayed = ?," +
                "GamesWon = ? " +
                "WHERE PlayerID = ?"
            );
            stmt.setString(1, player.getDisplayName());
            stmt.setBoolean(2, player.isAdmin());
            stmt.setBoolean(3, player.isActive());
            stmt.setInt(4, player.getElo());
            stmt.setInt(5, player.getGamesPlayed());
            stmt.setInt(6, player.getGamesWon());
            stmt.setInt(7, id);

            successful = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            LOG.warn(e.toString());
        }

        return successful;
    }

    public boolean removePlayer(int id) {
        boolean successful = false;

        try {
            PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM Player " +
                "WHERE PlayerID = ?"
            );
            stmt.setInt(1, id);

            successful = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            LOG.warn(e.toString());
        }

        return successful;
    }
}
