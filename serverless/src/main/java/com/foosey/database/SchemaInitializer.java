package com.foosey.database;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.ApiGatewayResponse;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

/*
 * Initializes the foosey database schema
 * This handler has no API endpoint, instead it should be invoked via serverless:
 *  sls invoke -f initializeSchema
 */
public class SchemaInitializer implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = Logger.getLogger(SchemaInitializer.class);

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> stringObjectMap, Context context) {
        Connection con = DatabaseUtils.getConnection();

        int statusCode = 200;
        String message = "Schema initialized.";

        try {
            con.setAutoCommit(false);

            // Create Game table
            con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS" +
                            "Game(" +
                            "GameID INTEGER NOT NULL," +
                            "PlayerID INTEGER NOT NULL," +
                            "LeagueID INTEGER NOT NULL," +
                            "Score INTEGER NOT NULL," +
                            "Timestamp DATETIME NOT NULL," +
                            "PRIMARY KEY (GameID, PlayerID))"
            ).execute();

            // Create Player table
            con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS" +
                            "Player(" +
                            "PlayerID INTEGER PRIMARY KEY," +
                            "LeagueID INTEGER NOT NULL," +
                            "DisplayName VARCHAR(100) NOT NULL," +
                            "Admin TINYINY(1) DEFAULT 0 NOT NULL," +
                            "Active TINYINT(1) DEFAULT 1 NOT NULL," +
                            "Elo INTEGER DEFAULT 1200 NOT NULL," +
                            "GamesPlayed INTEGER DEFAULT 0 NOT NULL," +
                            "GamesWon INTEGER DEFAULT 0 NOT NULL," +
                            "UNIQUE(DisplayName))"
            ).execute();

            // Create League table
            con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS" +
                            "League(" +
                            "LeagueID INTEGER PRIMARY KEY," +
                            "LeagueCode VARCHAR(16) UNIQUE," +
                            "LeagueName VARCHAR(200))"
            ).execute();

            // Create EloHistory table
            con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS" +
                            "EloHistory(" +
                            "GameID INTEGER NOT NULL," +
                            "PlayerID INTEGER NOT NULL," +
                            "LeagueID INTEGER NOT NULL," +
                            "Elo INTEGER NOT NULL," +
                            "PRIMARY KEY(GameID, PlayerID))"
            ).execute();

            con.commit();
        } catch (SQLException e) {
            LOG.warn(e.toString());
            statusCode = 500;
            message = e.toString();
        }

        return ApiGatewayResponse.builder()
                .setStatusCode(statusCode)
                .setObjectBody(new SchemaInitializerResponse(message))
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build();
    }

    class SchemaInitializerResponse {
        private final String message;

        public SchemaInitializerResponse(String message) {
            this.message = message;
        }
    }
}
