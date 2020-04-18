package project.domain;

import java.sql.Timestamp;

/**
 *
 * @author chenhuiz
 */
public class Tip {
    private final long id;
    private String title;
    private String author;
    private String description;
    private String url;
    private boolean checked;
    private Timestamp checkedtime;
    
    public Tip(long id, String title, String author, String description, String url, boolean checked, Timestamp checkedtime) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.url = url;
        this.checked = checked;
        this.checkedtime = checkedtime;
    }
    
    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public boolean getChecked() {
        return checked;
    }
    
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Timestamp getCheckedtime() {
        return checkedtime;
    }

    public void setCheckedtime(Timestamp checkedtime) {
        this.checkedtime = checkedtime;
    }

    public int compareTo(Tip otherTip) {
        int titleDiff = this.title.compareToIgnoreCase(otherTip.title);
        return titleDiff;
    }
    
    @Override
    public String toString() {
        return "\nTitle: " + title + 
                "\nAuthor: " + author +
                "\nDescription: " + description +
                "\nUrl: " + url +
                "\nChecked: " + checked;
    }
}
