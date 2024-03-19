package com.database;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {
    @Test
    void getAccessLevel() {
        BackEndLogin databaseConnection = new BackEndLogin();
        ResponseEntity<String> responseData = databaseConnection.Login("loren","loren");
        String result = databaseConnection.getAccessLevel(responseData);
        assertEquals("Admin",result);
    }

    @Test
    void getAccessLevelShouldReturnDriver(){

    }

    @Test
    void lorennShouldReturnfalse() {
        BackEndLogin databaseConnection = new BackEndLogin();
        ResponseEntity<String> responseData = databaseConnection.Login("loren","loren");
        boolean result = databaseConnection.checkResponseData("lorenn","loren",responseData);
        assertFalse(result);
    }


    @Test
    void checkResponseData() {
        BackEndLogin databaseConnection = new BackEndLogin();
        ResponseEntity<String> responseData = databaseConnection.Login("loren","loren");
        boolean result = databaseConnection.checkResponseData("loren","loren",responseData);
        assertTrue(result);
    }

    @Test
    void testCheckResponseData() {
        BackEndLogin databaseConnection = new BackEndLogin();
        ResponseEntity<String> responseData = databaseConnection.Login("loren","loren");
        boolean result = databaseConnection.checkResponseData("loren","loren",responseData);
        assertTrue(result);
    }

    @Test
    void login() {
        BackEndLogin databaseConnection = new BackEndLogin();
        ResponseEntity<String> responseData = databaseConnection.Login("loren","loren");
        assertNotNull(responseData);

    }
}