package me.colingreybosh.cocoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Tests for the DataTable ADT.
 * 
 * @author Colin Greybosh
 *
 */
public class DataTableTest {

    private static final String BASE_PATH = "./src/test/java/me/colingreybosh/cocoa/tables/";
    
    @Test
    public void testAssertionsEnabled() {
        assertThrows(AssertionError.class, () -> { assert false; },
                "make sure assertions are enabled with VM argument '-ea'");
    }
    
    /*
     * Testing Strategy
     * 
     * toFileContents() and toMap()
     *   
     * 
     * size()
     *   returns 0, 1, >1
     * 
     * sameValue()
     *   returns true, returns false
     *   table is empty, table is nonempty
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
        testSize(BASE_PATH + "empty.dt", 0);
    }
    
    /*
     * subdomains covered:
     *   returns 1
     */
    @Test
    public void testSizeOne() {
        testSize(BASE_PATH + "single.dt", 1);
    }
    
    /*
     * subdomains covered:
     *   return >1
     */
    @Test
    public void testSizeGreaterThanOne() {
        testSize(BASE_PATH + "three.dt", 3);
    }
    
    // Tests on sameValue()
    
    private static void testSameValueReflexivity(final DataTable table) {
        assertEquals(true, table.sameValue(table), "Expected sameValue to be reflexive!");
    }
    
    private static void testSameValueSymmetry(
            final DataTable table0, final DataTable table1, final boolean expected) {
       assertEquals(expected, table0.sameValue(table1), "Expected sameValue to be symmetric!");
       assertEquals(expected, table1.sameValue(table0), "Expected sameValue to be symmetric!");
    }
    
    private static void testSameValueTransitivity(
            final DataTable table0, final DataTable table1, final DataTable table2, final boolean expected) {
       assertEquals(expected, 
               table0.sameValue(table1) 
               && table1.sameValue(table2) 
               && table0.sameValue(table2),
               "Expected sameValue to be transitive!");
    }
    
    /*
     * subdomains covered:
     *   returns true
     *   table is empty
     */
    @Test
    public void testSameValueEmpty() {
        final boolean expected = true;
        try (final DataTable table0 = new DataTable(BASE_PATH + "sameValueEmpty0.txt");
             final DataTable table1 = new DataTable(BASE_PATH + "sameValueEmpty1.txt")) {
            testSameValueReflexivity(table0);
            testSameValueReflexivity(table1);
            testSameValueSymmetry(table0, table1, expected);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    
    /*
     * subdomains covered:
     *   returns false
     *   table is empty
     */
    @Test
    public void testSameValueEmptyFalse() {
        final boolean expected = false;
        try (final DataTable table0 = new DataTable(BASE_PATH + "empty.dt");
                final DataTable table1 = new DataTable(BASE_PATH + "single.dt")) {
           testSameValueReflexivity(table0);
           testSameValueReflexivity(table1);
           testSameValueSymmetry(table0, table1, expected);
       } catch (Exception e) {
           fail(e.getMessage());
       }
    }
    
    /*
     * subdomains covered:
     *   returns false
     *   table is nonempty
     */
    @Test
    public void testSameValueNonEmptyFalse() {
        final boolean expected = false;
        try (final DataTable table0 = new DataTable(BASE_PATH + "three.dt");
                final DataTable table1 = new DataTable(BASE_PATH + "single.dt")) {
           testSameValueReflexivity(table0);
           testSameValueReflexivity(table1);
           testSameValueSymmetry(table0, table1, expected);
       } catch (Exception e) {
           fail(e.getMessage());
       }
    }
    
    /*
     * subdomains covered:
     *   returns true
     *   table is nonempty
     */
    @Test
    public void testSameValueNonEmptyTrue() {
        final boolean expected = true;
        try (final DataTable table0 = new DataTable(BASE_PATH + "three.dt");
                final DataTable table1 = new DataTable(BASE_PATH + "sameValueNonEmptyTrue.dt")) {
           testSameValueReflexivity(table0);
           testSameValueReflexivity(table1);
           testSameValueSymmetry(table0, table1, expected);
       } catch (Exception e) {
           fail(e.getMessage());
       }
    }
}
