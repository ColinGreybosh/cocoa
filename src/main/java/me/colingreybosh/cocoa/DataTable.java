package me.colingreybosh.cocoa;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Mutable ADT representing a simple mapping of data stored in a file.
 * 
 * Files are formatted according to the following grammar, that requires a file
 * to include newline terminated rows of two non-empty strings of non-whitespace, non-newline
 * characters separated by a single space:
 * 
 * FILE ::= ROW*
 * ROW ::= KEY " " VALUE NEWLINE
 * KEY ::= VALUE
 * VALUE ::= [\S]+
 * NEWLINE ::= "\n" | "\r" "\n"?
 * 
 * Completely empty files are valid tables as well.
 * 
 * @author Colin Greybosh
 *
 */
public class DataTable implements Map<String, String>, Iterable<DataTable.Row>, AutoCloseable {
    
    /**
     * An immutable ADT representing a row within a 2-column table.
     * 
     * @author Colin Greybosh
     * 
     */
    public static class Row {
        private final String key;
        private final String value;
        
        /*
         * Abstraction Function:
         *   AF(key, value) = a table entry mapping `key` -> `value`
         *   
         * Representation Invariant:
         *   true
         *   
         * Safety from representation exposure:
         *   all fields are private and final
         *   all fields are immutable types
         */
        
        /**
         * Create a new table entry.
         * 
         * @param key The key in the first column.
         * @param value The value in the second column.
         */
        public Row(String key, String value) {
            this.key = key;
            this.value = value;
        }
        
        /**
         * Get this row's key.
         * 
         * @return This row's key.
         */
        public String getKey() {
            return key;
        }
        
        /**
         * Get this row's value.
         * 
         * @return This row's value.
         */
        public String getValue() {
            return value;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object that) {
            return that instanceof Row && sameValue((Row) that);
        }
        
        /**
         * Indicates whether this Row is equal to another Row.
         * 
         * @param that
         * @return {@code true} if this object is observationally 
         */
        public boolean sameValue(Row that) {
            return getKey().equals(that.getKey()) 
                    && getValue().equals(that.getValue());
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return Objects.hash(getKey(), getValue());
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "(" + getKey() + " -> " + getValue() + ")";
        }
    }
    
    /**
     * Create a table, generating a new empty table at {@code pathToFile} if one
     * does not already exist or parsing existing information.
     * 
     * Only a single instance of a table may exist for every unique file.
     * 
     * @param pathToFile The path to the file containing the table information.
     */
    public DataTable(String pathToFile) {
        throw new RuntimeException("unimplemented");
    }
    
    @Override
    public void close() throws Exception {
        throw new RuntimeException("unimplemented");
    }
    
    /**
     * Get the size of the table, in number of rows.
     * 
     * @return The size of the table, in number of rows.
     */
    public int size() {
        throw new RuntimeException("unimplemented");
    }
    
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public boolean containsValue(Object value) {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public String get(Object key) {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public String put(String key, String value) {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public String remove(Object key) {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public void clear() {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public Set<String> keySet() {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public Collection<String> values() {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        throw new RuntimeException("unimplemented");
    }
    
    @Override
    public Iterator<Row> iterator() {
        throw new RuntimeException("unimplemented");
    }
    
    @Override
    public boolean equals(Object that) {
        return that instanceof DataTable && sameValue((DataTable) that);
    }
    
    /**
     * Compares the current value of this table to {@code that}.
     *  
     * @param that Another table to compare equality to. 
     * @return {@code true} if this table and {@code that} are observationally equal.
     */
    public boolean sameValue(DataTable that) {
        throw new RuntimeException("unimplemented");
    }
    
    @Override
    public int hashCode() {
        throw new RuntimeException("unimplemented");
    }
    
    @Override
    public String toString() {
        throw new RuntimeException("unimplemented");
    }
}
