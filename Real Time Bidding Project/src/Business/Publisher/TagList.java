/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Publisher;

import java.util.ArrayList;

/**
 *
 * @author anirudhbedre
 */
public class TagList {
    private ArrayList<Tag> tagList;

    public TagList() {
        this.tagList = new ArrayList<>();
    }
    public ArrayList<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }
    
    public void createTag(String tagName){
        
        Tag tag=new Tag(tagName);
        tagList.add(tag);
        return;
    }
    
    
    public void createOrUpdateTags(String tagName){
        for(Tag tag:tagList)
        {
            if(tag.getTagName().equals(tagName)){
                tag.updateNumberOfVisits();
                return;
            }
        }
        Tag tag=new Tag(tagName);
        tagList.add(tag);
        return;
    }
     
  
    
}
