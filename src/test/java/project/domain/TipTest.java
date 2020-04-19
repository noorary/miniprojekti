package project.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author chenhuiz
 */
public class TipTest {

    private Tip tip;

    public TipTest() {
        tip = new Tip(3, "didn't i", "One Republic", "New single song", "www.youtube.com", false, null);
    }

    @Test
    public void getIdTest() {
        assertEquals(3, tip.getId());
    }

    @Test
    public void setAuthorTest() {
        tip.setAuthor("OneRepublic");
        assertEquals("OneRepublic", tip.getAuthor());
    }

    @Test
    public void setTitleTest() {
        tip.setTitle("Didn't I");
        assertEquals("Didn't I", tip.getTitle());
    }

    @Test
    public void setDescriptionTest() {
        tip.setDescription("New single song for the new album");
        assertEquals("New single song for the new album", tip.getDescription());
    }

    @Test
    public void setUrlTest() {
        tip.setUrl("www.one.com");
        assertEquals("www.one.com", tip.getUrl());
    }

    @Test
    public void setCheckedTest() {
        tip.setChecked(!this.tip.getChecked());
        assertEquals(true, tip.getChecked());
    }

    @Test
    public void videoToStringTest() {

        String s = "\nTitle: didn't i"
                + "\nAuthor: One Republic"
                + "\nDescription: New single song"
                + "\nUrl: www.youtube.com"
                + "\nChecked: " + false;
        
        assertEquals(s, tip.toString());
    }

}