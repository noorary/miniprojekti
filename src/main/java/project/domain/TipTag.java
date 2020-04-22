package project.domain;

/**
 *
 * @author chenhuiz
 */
public class TipTag {
    
    private Tip tip;
    private Tag tag;
    
    public TipTag(Tip tip, Tag tag) {
        this.tip = tip;
        this.tag = tag;
    }
    
    public void setTip(Tip tip) {
        this.tip = tip;
    }
    
    public Tip getTip() {
        return this.tip;
    }
    
    public void setTag(Tag tag) {
        this.tag = tag;
    }
    
    public Tag getTag() {
        return this.tag;
    }
    
}
