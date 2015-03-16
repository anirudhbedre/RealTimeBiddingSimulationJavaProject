/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Publisher;

/**
 *
 * @author anirudhbedre
 */
public class Tag {
    private String tagName;
    private int numberOfVisits;

    public Tag(String tagName) {
        this.numberOfVisits=0;
        this.tagName=tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void updateNumberOfVisits() {
        this.numberOfVisits = this.numberOfVisits+1;
    }
    
    
    
    
    
}
