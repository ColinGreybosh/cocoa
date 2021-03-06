package me.colingreybosh.cocoa;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private final BufferedWriter writer;
    private final Map<String, String> table;
    private final List<String> lines;
    
    /*
     * Abstraction Function
     *   AF(path, writer, table, lines) = a DataTable that modifies the file containing `lines`
     *                                    located at `path` using `reader` and `writer` such 
     *                                    that `table` represents the data contained within 
     *                                    that file at any point in time 
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
        lines = Files.newBufferedReader(path, CHARSET).lines().collect(Collectors.toList());
        table = toMap();
        checkRep();
    }
    
    private void checkRep() {
        assert writer != null;
        for (String line : lines) {
            assert line != null;
        }
        assert table != null;
    }
    
    /**
     * Generates the contents of a DataTable file equivalent to the current table instance.
     * The lines are sorted in lexicographic order, and lines will always be "\n" terminated.
     * 
     * @return The contents of a DataTable file equivalent to the current table instance.
     */
    protected String toFileContents() {
        String result = "";
        final List<Map.Entry<String, String>> entries = new ArrayList<>(table.entrySet());
        entries.sort((o1, o2) -> o1.getKey().compareTo(o2.getKey()));
        for (Map.Entry<String, String> entry : entries) {
            result += entry.getKey() + " " + entry.getValue() + "\n";
        }
        return result;
    }
    
    /**
     * Generates a map equivalent to the contents of a DataTable file.
     * 
     * @return A map equivalent to the contents of a DataTable file.
     */
    protected Map<String, String> toMap() {
        final Map<String, String> result = new HashMap<>();
        lines.stream()
             .map(line -> line.split(" "))
             .map(DataTable::toEntry)
             .forEach(result::putAll);
        return result;
    }
    
    protected static Map<String, String> toEntry(String[] line) {
        final Map<String, String> result = new HashMap<>();
        result.put(line[0], line[1]);
        return result;
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
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
