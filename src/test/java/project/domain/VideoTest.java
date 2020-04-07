package project.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author chenhuiz
 */
public class VideoTest {

    private Video video;

    public VideoTest() {
        video = new Video(3, "didn't i", "One Republic", "New single song", "www.youtube.com", false);
    }

    @Test
    public void getIdTest() {
        assertEquals(3, video.getId());
    }

    @Test
    public void setAuthorTest() {
        video.setAuthor("OneRepublic");
        assertEquals("OneRepublic", video.getAuthor());
    }

    @Test
    public void setTitleTest() {
        video.setTitle("Didn't I");
        assertEquals("Didn't I", video.getTitle());
    }

    @Test
    public void setDescriptionTest() {
        video.setDescription("New single song for the new album");
        assertEquals("New single song for the new album", video.getDescription());
    }

    @Test
    public void setUrlTest() {
        video.setUrl("www.one.com");
        assertEquals("www.one.com", video.getUrl());
    }

    @Test
    public void setCheckedTest() {
        video.setChecked(!this.video.getChecked());
        assertEquals(true, video.getChecked());
    }

    @Test
    public void videoToStringTest() {

        String s = "\nTitle: didn't i"
                + "\nAuthor: One Republic"
                + "\nDescription: New single song"
                + "\nUrl: www.youtube.com"
                + "\nChecked: " + false
                + "\nType: Video";
        assertEquals(s, video.toString());
    }

}