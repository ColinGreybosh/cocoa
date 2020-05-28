package me.colingreybosh.cocoa;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
     * 
     * 
     * isEmpty()
     * 
     * 
     * containsKey(Object key)
     * 
     * 
     * containsValue(Object value)
     * 
     * 
     * get(Object key)
     * 
     * 
     * put(Object key, Object value)
     * 
     * 
     * remove(Object key)
     * 
     * 
     * putAll(Map<? extends String, ? extends String> m)
     * 
     * 
     * clear()
     * 
     * 
     * keySet()
     * 
     * 
     * values()
     * 
     * 
     * entrySet()
     * 
     * 
     * iterator()
     * 
     * 
     * sameValue()
     * 
     * 
     */
    
}
