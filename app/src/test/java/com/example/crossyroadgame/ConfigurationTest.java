package com.example.crossyroadgame;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationTest {

    @Test
    public void setDifficulty() {
    assertEquals(5, Configuration.setDifficulty("Hard"));
    assertEquals(3, Configuration.setDifficulty("Medium"));
    assertEquals(1, Configuration.setDifficulty("Easy"));
    }

    @Test
    public void isValidName() {
        assertEquals(false, Configuration.isValidName(""));
        assertEquals(false, Configuration.isValidName("      "));
        assertEquals(false, Configuration.isValidName(null));
        assertEquals(true, Configuration.isValidName("wef"));
    }
}