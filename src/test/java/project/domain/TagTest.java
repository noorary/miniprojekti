package project.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chenhuiz
 */
public class TagTest {

    private Tag tag;

    public TagTest() {
        this.tag = new Tag(4, "hello");
    }

    @Test
    public void getIdTest() {
        assertEquals(4, tag.getId());
    }

    @Test
    public void getNameTest() {
        assertEquals("hello", tag.getName());
    }

    @Test
    public void setNameTest() {
        tag.setName("Hey");
        assertEquals("Hey", tag.getName());
    }

    @Test
    public void toStringTest() {
        String s = "\nId: 4" + "\nName: hello";
        assertEquals(s, tag.toString());
    }
}
