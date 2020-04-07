package project.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chenhuiz
 */
public class BookTest {
    
    private Book book;
    
    @Before
    public void setUp() {
        book = new Book(1, "Harry Porter", "J.K. Rowling", "111-333", "A miracle adventure", "www.hapo.com", false);
    }
    
    @Test
    public void bookTypeTest() {
        assertEquals("Kirja", book.getType());
    }
    
    @Test
    public void getISBNTest() {
        assertEquals("111-333", book.getISBN());
    }
    
    @Test
    public void setISBNTest() {
        this.book.setISBN("000-333");
        assertEquals("000-333", book.getISBN());
    }
    
    @Test 
    public void bookToStringTest() {
        String s = "\nTitle: Harry Porter" +
                    "\nAuthor: J.K. Rowling" +
                    "\nISBN: 111-333" +
                    "\nDescription: A miracle adventure" +
                    "\nUrl: www.hapo.com" +
                    "\nChecked: " + false +
                    "\nType: Kirja";
        
        assertEquals(s, book.toString());
    }
    
}