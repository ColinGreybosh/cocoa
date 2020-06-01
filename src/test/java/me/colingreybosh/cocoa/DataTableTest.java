package me.colingreybosh.cocoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Tests for the DataTable ADT.
 * 
 * @author Colin Greybosh
 *
 */
public class DataTableTest {

    @Test
    public void testAssertionsEnabled() {
        assertThrows(AssertionError.class, () -> { assert false; },
                "make sure assertions are enabled with VM argument '-ea'");
    }
    
    /*
     * Testing Strategy
     * 
     * toFile() and toMap()
     *   
     * 
     * size()
     *   returns 0, 1, >1
     * 
     * sameValue()
     *   returns true, returns false
     * 
     */
    
    // Tests on size()
    
    private static void testSize(String filePath, int expectedSize) {
        try (final DataTable table = new DataTable(filePath)) {
            assertEquals(expectedSize, table.size(), "Expected table size to be " + expectedSize);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    
    /*
     * subdomains covered:
     *   returns 0
     */
    @Test
    public void testSizeEmpty() {
        testSize("src/test/java/me/colingreybosh/cocoa/tables/empty.dt", 0);
    }
    
    /*
     * subdomains covered:
     *   returns 1
     */
    @Test
    public void testSizeOne() {
        testSize("src/test/java/me/colingreybosh/cocoa/tables/single.dt", 1);
    }
    
    /*
     * subdomains covered:
     *   return >1
     */
    @Test
    public void testSizeGreaterThanOne() {
        testSize("src/test/java/me/colingreybosh/cocoa/tables/three.dt", 3);
    }
}
