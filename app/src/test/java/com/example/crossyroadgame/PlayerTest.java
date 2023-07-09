package com.example.crossyroadgame;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
    Player player1 = new Player("bob", 100, 7, 7, 1, new int[]{10,10,10, 10, 10});
    Player player3 = new Player("bob", 100, 10, 10, 3, new int[]{10,10,10, 10, 10});
    Player player5 = new Player("bob", 100, 10, 10, 5, new int[]{10,10,10, 10, 10});

    @Test
    public void getLives() {
        int lives = player1.getLives();
        assertEquals(lives, 1);
        lives = player3.getLives();
        assertEquals(lives, 3);
         lives = player5.getLives();
        assertEquals(lives, 5);
    }

    @Test
    public void setX() {
        player5.setX(1);
        assertEquals(1, player5.getX());
    }

    @Test
    public void setY() {
        player5.setY(1);
        assertEquals(1, player5.getY());
    }

    @Test
    public void findDifficulty() {
    String difficulty1 = player1.findDifficulty();
    assertEquals(difficulty1, "HARD");
    String difficulty3 = player3.findDifficulty();
    assertEquals(difficulty3, "MEDIUM");
    String difficulty5 = player5.findDifficulty();
    assertEquals(difficulty5, "EASY");
    }


    @Test
    public void moveRight() {
    player1.setX(0);
    player1.moveRight();
    assertEquals(1, player1.getX());
    player1.moveRight();
    assertEquals(2, player1.getX());
    player1.moveRight();
    assertEquals(3, player1.getX());
    player1.moveRight();
    assertEquals(4, player1.getX());
    player1.moveRight();
    assertEquals(5, player1.getX());
    player1.moveRight();
    assertEquals(6, player1.getX());

    //once player reaches num of cols - 1
    // they shouldn't be able to move right anymore (reached boundary)
    player1.moveRight();
    assertEquals(6, player1.getX());
    }

    @Test
    public void moveLeft() {
        player1.setX(6);
        player1.moveLeft();
        assertEquals(5, player1.getX());
        player1.moveLeft();
        assertEquals(4, player1.getX());
        player1.moveLeft();
        assertEquals(3, player1.getX());
        player1.moveLeft();
        assertEquals(2, player1.getX());
        player1.moveLeft();
        assertEquals(1, player1.getX());
        player1.moveLeft();
        assertEquals(0, player1.getX());
        //once player reaches col 0,
        // they shouldn't be able to move left anymore (reached boundary)
        player1.moveLeft();
        assertEquals(0, player1.getX());
    }

    @Test
    public void moveUp() {
        //y axis is inverted
        player1.setY(6);
        player1.moveUp();
        assertEquals(5, player1.getY());
        player1.moveUp();
        assertEquals(4, player1.getY());
        player1.moveUp();
        assertEquals(3, player1.getY());
        player1.moveUp();
        assertEquals(2, player1.getY());
        player1.moveUp();
        assertEquals(1, player1.getY());
        player1.moveUp();
        assertEquals(0, player1.getY());
        //once player reaches row 0,
        // they shouldn't be able to move up anymore (reached boundary)
        player1.moveUp();
        assertEquals(0, player1.getY());
    }

    @Test
    public void moveDown() {
        //y axis is inverted
        player1.setY(0);
        player1.moveDown();
        assertEquals(1, player1.getY());
        player1.moveDown();
        assertEquals(2, player1.getY());
        player1.moveDown();
        assertEquals(3, player1.getY());
        player1.moveDown();
        assertEquals(4, player1.getY());
        player1.moveDown();
        assertEquals(5, player1.getY());
        player1.moveDown();
        assertEquals(6, player1.getY());
        //once player reaches num of rows - 1
        // they shouldn't be able to move down anymore (reached boundary)
        player1.moveDown();
        assertEquals(6, player1.getY());
    }

    @Test
    public void initialScore() {
        assertEquals(player1.getScore(), 0);
    }

    @Test
    public void firstMoveScore() {
        player1.setY(10);
        player1.moveUp();
        assertEquals(10, player1.getScore());
    }

    @Test
    public void secondMoveScore() {
        player1.setY(10);
        player1.moveUp();
        player1.moveUp();
        assertEquals(30, player1.getScore());
    }
    @Test
    public void thirdMoveScore() {
        player1.setY(10);
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        assertEquals(45, player1.getScore());
    }
    
    @Test
    public void fourthMoveScore() {
        player1.setY(10);
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        assertEquals(95, player1.getScore());
    }
    @Test
    public void fifthMoveScore() {
        player1.setY(10);
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        assertEquals(105, player1.getScore());
    }
    @Test
    public void sixthMoveScore() {
        player1.setY(10);
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        assertEquals(115, player1.getScore());
    }
    @Test
    public void seventhMoveScore() {
        player1.setY(10);
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        player1.moveUp();
        assertEquals(125, player1.getScore());
    }

    public void moveLeftScore() {
        Player player33 = new Player("bob", 100, 10, 10, 3, new int[]{10,10,10, 10, 10});
        player33.setY(10);
        player33.moveLeft();
        assertEquals(0, player33.getScore());
        player33.moveUp();
        assertEquals(10, player33.getScore());
        player33.moveLeft();
        assertEquals(10, player33.getScore());
        player33.moveLeft();
        assertEquals(10, player33.getScore());
        player33.moveUp();
        assertEquals(30, player33.getScore());
        player33.moveLeft();
        assertEquals(30, player33.getScore());
        player33.moveLeft();
        assertEquals(30, player33.getScore());
    }

    @Test
    public void moveRightScore() {
        Player player33 = new Player("bob", 100, 10, 10, 3, new int[]{10,10,10, 10, 10});
        player33.setY(10);
        player33.moveRight();
        assertEquals(0, player33.getScore());
        player33.moveUp();
        assertEquals(10, player33.getScore());
        player33.moveRight();
        assertEquals(10, player33.getScore());
        player33.moveRight();
        assertEquals(10, player33.getScore());
        player33.moveUp();
        assertEquals(30, player33.getScore());
        player33.moveRight();
        assertEquals(30, player33.getScore());
        player33.moveRight();
        assertEquals(30, player33.getScore());
    }


    @Test
    public void backwardsScore() {
        Player player33 = new Player("bob", 100, 10, 10, 3, new int[]{10,10,10, 10, 10});
        player33.setY(9);
        player33.moveUp();
        assertEquals(20, player33.getScore());
        player33.moveDown();
        assertEquals(20, player33.getScore());
        player33.moveUp();
        assertEquals(20, player33.getScore());
        player33.moveUp();
        assertEquals(35, player33.getScore());
        player33.moveDown();
        assertEquals(35, player33.getScore());
        player33.moveDown();
        assertEquals(35, player33.getScore());
        player33.moveDown();
        assertEquals(35, player33.getScore());

    }


    @Test
    public void repeatedForwardsScore() {
        Player player33 = new Player("bob", 100, 10, 10, 3, new int[]{10,10,10, 10, 10});
        player33.setY(9);
        player33.moveUp();
        assertEquals(20, player33.getScore());
        player33.moveDown();
        assertEquals(20, player33.getScore());
        player33.moveUp();
        assertEquals(20, player33.getScore());
        player33.moveUp();
        assertEquals(35, player33.getScore());
        player33.moveDown();
        assertEquals(35, player33.getScore());
        player33.moveUp();
        assertEquals(35, player33.getScore());
        player33.moveUp();
        assertEquals(85, player33.getScore());
        player33.moveDown();
        assertEquals(85, player33.getScore());
        player33.moveUp();
        assertEquals(85, player33.getScore());
    }

    @Test
    public void differentScoresPerObstacle() {
        Player player33 = new Player("bob", 100, 10, 10, 3, new int[]{10,10,10, 10, 10});
        int score = 0;
        player33.setY(9);
        player33.moveUp();
        int scoreDifference1 = player33.getScore() - score;
        score = player33.getScore();
        player33.moveUp();

        int scoreDifference2 = player33.getScore() - score;
        score = player33.getScore();
        player33.moveUp();

        int scoreDifference3 = player33.getScore() - score;
        score = player33.getScore();

        assertNotEquals(scoreDifference1, scoreDifference2);
        assertNotEquals(scoreDifference3, scoreDifference2);
        assertNotEquals(scoreDifference1, scoreDifference3);

    }

    @Test
    public void collisionLostLife() {
        Player p1 = new Player("bob", 100, 10, 10, 3, new int[]{10,10,10, 10, 10});
        int lives = p1.getLives();
        p1.loseLife();
        assertEquals(lives - 1, p1.getLives());
        assertNotEquals(lives, p1.getLives());
    }

    @Test
    public void collisionResetScore() {
        Player p1 = new Player("bob", 100, 10, 10, 3, new int[]{10,10,10, 10, 10});
        p1.moveUp();
        int currScore = p1.getScore();
        p1.loseLife();
        assertEquals(0, p1.getScore());
        assertNotEquals(currScore, p1.getScore());
    }

    @Test
    public void correctEndTextWin() {
        Player player = new Player("bob", 100, 10, 10, 3, new int[]{10,10,10, 10, 10});
        for (int i = 0; i < 12; i++) {
            player.moveUp();
        }
        String txt = player.getEndText();
        boolean correct = txt.equals("Congrats! You win");
        assertTrue(correct);
    }
    @Test
    public void endScoreCorrect() {
        Player player = new Player("bob", 100, 10, 10, 3, new int[]{10,10,10, 10, 10});
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

