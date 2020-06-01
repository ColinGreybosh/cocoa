package me.colingreybosh.cocoa;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
public class DataTable implements AutoCloseable {
    
    private static final Charset CHARSET = Charset.forName("UTF-8");
    private final Path path;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final Map<String, String> table;
    
    /*
     * Abstraction Function
     *   AF(path, reader, writer, table) = a DataTable that modifies the file located at `path` using
     *                                     `reader` and `writer` such that `table` represents the
     *                                     data contained within that file at any point in time 
     *   
     * Representation Invariant
     *   true
     * 
     * Safety from representation exposure
     *   all fields are private and final
     *   table is returned within an unmodifiable wrapper
     */
    
    /**
     * Create a table, generating a new empty table at {@code pathToFile} if one
     * does not already exist or parsing existing information.
     * 
     * Only a single instance of a table may exist for every unique file.
     * 
     * @param pathToFile The path to the file containing the table information.
     * @throws IOException If the path is invalid or opening the file fails.
     */
    public DataTable(String pathToFile) throws IOException {
        path = Paths.get(pathToFile);
        writer = Files.newBufferedWriter(path, CHARSET, CREATE, APPEND);
        reader = Files.newBufferedReader(path, CHARSET);
        table = toMap();
        checkRep();
    }
    
    private void checkRep() {
        assert writer != null;
        assert reader != null;
        assert table != null;
    }
    
    /**
     * Generates the contents of a DataTable file equivalent to the current table instance.
     * 
     * @return The contents of a DataTable file equivalent to the current table instance.
     */
    private String toFileContents() {
        String result = "";
        for (Map.Entry<String, String> row : table.entrySet()) {
            result += row.getKey() + " " + row.getValue() + "\n";
        }
        return result;
    }
    
    /**
     * Generates a map equivalent to the contents of a DataTable file.
     * 
     * @return A map equivalent to the contents of a DataTable file.
     */
    private Map<String, String> toMap() {
        final Map<String, String> result = new HashMap<>();
        reader.lines()
             .map(line -> line.split(" "))
             .map(DataTable::toEntry)
             .forEach(result::putAll);
        return result;
    }
    
    private static Map<String, String> toEntry(String[] line) {
        final Map<String, String> result = new HashMap<>();
        result.put(line[0], line[1]);
        return result;
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        reader.close();
        writer.close();
    }
    
    /**
     * Get the size of the table, in number of rows.
     * 
     * @return The size of the table, in number of rows.
     */
    public int size() {
        return table.size();
    }
    
    /**
     * Put this key->value pair into the table, adding a new line
     * if one does not exist and modifying the existing line otherwise.
     * 
     * @param key The key to add to the table.
     * @param value The value to add to the table.
     */
    public void put(String key, String value) {
        throw new RuntimeException("unimplemented");
    }

    /**
     * Get the table.
     * 
     * @return The table.
     */
    public Map<String, String> getTable() {
        return Collections.unmodifiableMap(table);
    }

    /**
     * {@inheritDoc}
     */
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
        return getTable().equals(that.getTable());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(table);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "(Table " + path + ")";
    }
}
