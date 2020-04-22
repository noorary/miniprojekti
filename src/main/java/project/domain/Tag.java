package project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chenhuiz
 */
public class Tag {

    private int id;
    private String name;
    private List<TipTag> tips;

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
        this.tips = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nId: " + this.id +
               "\nName: " + this.name;
    }
}
