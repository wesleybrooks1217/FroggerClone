package com.example.crossyroadgame;
import static org.junit.Assert.*;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LeafObjectTests {

    private LeafObject mainLeaf;



    @Before
    public void setUp() {
        mainLeaf = new LeafObject(5, 2, 2, 4, 0, 8, 9);

    }

    @After
    public void tearDown() {
        mainLeaf = null;
    }

    @Test
    public void testRowsFromBottom() {
        assertEquals(5, mainLeaf.getRowsFromBottom());
    }

    @Test
    public void testRows() {
        assertEquals(2, mainLeaf.getRows());
    }

    @Test
    public void testCols() {
        assertEquals(2, mainLeaf.getCols());
    }
  
    @Test
    public void testTileSize() {
        assertEquals(4, mainLeaf.getTileSize());
    }
  
    @Test
    public void testLength() {
        assertEquals(8, mainLeaf.getLength());
    }
  
    @Test
    public void testXPixels() {
        assertEquals(9, mainLeaf.getXPixels());
    }
    /*
    @Test
    public void nullPlayerCollision() {
        assertFalse(GameMap.safeOnRiver(null, new LeafObject[]{mainLeaf}));
    }
    @Test
    public void nullLeafArrayCollision() {
        assertFalse(GameMap.safeOnRiver(new Player("H", 1,1,1,1,new int[]{1,1,1,1,1}), null));
    }

     */

}

