package me.colingreybosh.cocoa;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for the CocoaBot ADT.
 * 
 * @author Colin Greybosh
 *
 */
public class CocoaBotTest {
    
    @Test
    public void testAssertionsEnabled() {
        assertThrows(AssertionError.class, () -> { assert false; },
                "make sure assertions are enabled with VM argument '-ea'");
    }
}
