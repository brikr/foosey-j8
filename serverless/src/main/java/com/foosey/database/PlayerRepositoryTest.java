package com.foosey.database;

import com.foosey.data.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerRepositoryTest {
    //TODO: this doesn't really mess with database except that it increments the next key that will be added to the player table
    // we should setup a test database with identical schema
    @Test
    public void addGetUpdateRemovePlayer() {
        PlayerRepository playerRepository = new PlayerRepository();

        int id = playerRepository.addPlayer(0, "testUser", false, true   );
        assertNotEquals(-1, id);

        Player player = playerRepository.getByid(id);
        assertNotNull(player);

        Player updatedPlayer = new Player(player, "renamedUser", null, null, null, null, null);
        boolean successful = playerRepository.updatePlayer(id, updatedPlayer);
        assertTrue(successful);

        successful = playerRepository.removePlayer(id);
        assertTrue(successful);
    }
}
