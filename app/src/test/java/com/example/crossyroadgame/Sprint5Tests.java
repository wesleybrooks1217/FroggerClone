package com.example.crossyroadgame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Sprint5Tests {
    private Log log1;
    private Log log2;

    private Player player;

    @Before
    public void buildUp() {
        log1 = new Log( new int[]{5,5,5}, 5, R.drawable.longlog, "left", 2, 5);
        log2 = new Log(new int[]{5,5,5}, 8, R.drawable.shortlog, "right", 4, 6);
        player = new Player("Bob", 10, 11, 5, 5, new int[]{5,5});
    }

    @Test
    public void differentBitMaps() {
        int idLog1 = log1.getBitMap();
        int idLog2 = log2.getBitMap();
        boolean same = idLog1 == idLog2;
        assertFalse(same);
    }

    @Test
    public void differentLengths() {
        int length1 = log1.getLength();
        int length2 = log2.getLength();
        boolean same = length1 == length2;
        assertFalse(same);
    }

    @Test
    public void differentDirections() {
        String direction1 = log1.getDirection();
        String direction2 = log2.getDirection();
        boolean same = direction2.equals(direction1);
        assertFalse(same);
    }
    @Test
    public void differentSpeed() {
        int speed1 = log1.getSpeed();
        int speed2 = log2.getSpeed();
        boolean same = speed1 == speed2;
        assertFalse(same);
    }
    @After
    public void teardown() {
        log1 = null;
        log2 = null;
        player = null;
    }

    @Test
    public void differentPlacement() {
        int place1 = log1.getRowsFromBottom();
        int place2 = log2.getRowsFromBottom();
        boolean same = place1 == place2;
        assertFalse(same);
    }
    @Test
    public void playerHasNotWon() {
        for (int i = 0; i < 5; i++) {
            player.moveUp();
        }
        assertFalse(player.getWin());
    }

    @Test

    public void playerHasWon() {
        for (int i = 0; i < 12; i++) {
            player.moveUp();
        }
        assertTrue(player.getWin());
    }

    @Test
    public void correctEndTextLoss() {
        for (int i = 0; i < 5; i++) {
            player.moveUp();
        }
        String txt = player.getEndText();
        boolean correct = txt.equals("Game Over");
        assertTrue(correct);
    }



    public void notOnLog() {
        player.logCollision(log2);
        assertFalse(player.getOnLog());
    }
    @Test
    public void onLog() {
        player.logCollision(log1);
        assertTrue(player.getOnLog());
    }


    @Test
    public void correctEndTextWin() {
        for (int i = 0; i < 12; i++) {
            player.moveUp();
        }
        String txt = player.getEndText();
        boolean correct = txt.equals("Congrats! You win");
        assertTrue(correct);
    }
    @Test
    public void endScoreCorrect() {
        double highestPrevious = 0;
        double last = 0;
        while(player.getY() != 0) {
            player.moveUp();
            if (player.getY() == 0) {
                last = player.getScore();
            } else {
                highestPrevious = player.getScore();
            }
        }
        assertTrue(last > highestPrevious);
    }


}
