package com.foosey.database;

import com.serverless.ApiGatewayResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SchemaInitializerTest {
    @Test
    public void testInitializeSchema() {
        ApiGatewayResponse response = new SchemaInitializer().handleRequest(null, null);
        assertEquals(200, response.getStatusCode());
    }
}
