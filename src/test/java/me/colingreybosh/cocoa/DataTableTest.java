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
     * size()
     *   returns 0, 1, >1
     * 
     * isEmpty()
     *   returns true, false
     * 
     * containsKey(Object key)
     *   returns true, false
     * 
     * containsValue(Object value)
     *   returns true, false
     * 
     * get(Object key)
     *   key is in table, key isn't in table
     * 
     * put(Object key, Object value)
     *   key was already in table, key wasn't already in table
     * 
     * remove(Object key)
     *   key was in table, key wasn't in table
     * 
     * putAll(Map<? extends String, ? extends String> m)
     *   m is empty, m is nonempty
     *   m contains new keys, m has same keys as table
     * 
     * clear()
     *   table was empty, table wasn't empty
     * 
     * keySet()
     *   table is empty, table isn't empty
     * 
     * values()
     *   table is empty, table isn't empty
     * 
     * entrySet()
     *   table is empty, table isn't empty
     * 
     * iterator()
     *   table.size() == 0, == 1, > 1
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
