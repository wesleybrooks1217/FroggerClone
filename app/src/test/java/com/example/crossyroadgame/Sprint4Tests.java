package com.example.crossyroadgame;

import static org.junit.Assert.*;

import org.junit.Test;
public class Sprint4Tests {
    Player player5 = new Player("bob", 100, 10, 10, 5, new int[]{10,10,10, 10, 10});

    @Test
    public void resetXWhenLoseLife() {
        int resetX = player5.getX();
        player5.moveLeft();
        player5.loseLife();
        assertEquals(resetX, player5.getX());
    }
    @Test
    public void resetYWhenLoseLife() {
        int resetY = player5.getY();
        player5.moveUp();
        player5.loseLife();
        assertEquals(resetY, player5.getY());
    }
}
