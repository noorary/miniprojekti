package project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chenhuiz
 */
public class Tip {

    private final int id;
    private String title;
    private String author;
    private String description;
    private String url;
    private boolean checked;

//    private List<TipTag> tags;
    private List<Tag> tags;

    public Tip(int id, String title, String author, String description, String url, boolean checked) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.url = url;
        this.checked = checked;
        this.tags = new ArrayList<>();
    }

    public List<Tag> getTags() {
        return this.tags;
    }
    
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
//    
//    public void addTag(Tag tag) {
//        this.tags.add(tag);
//    }

    public int getId() {
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

    public int compareTo(Tip otherTip) {
        int titleDiff = this.title.compareToIgnoreCase(otherTip.title);
        return titleDiff;
    }

    @Override
    public String toString() {
        return "\nTitle: " + title
                + "\nAuthor: " + author
                + "\nDescription: " + description
                + "\nUrl: " + url
                + "\nChecked: " + checked;
    }
}
