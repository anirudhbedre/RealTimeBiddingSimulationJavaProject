/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.WorkQueue;

import Business.Publisher.TagList;

/**
 *
 * @author anirudhbedre
 */
public class NotifyPublisherWorkRequest extends WorkRequest{
    
    private TagList tagList;

    public TagList getTagList() {
        return tagList;
    }

    public void setTagList(TagList tagList) {
        this.tagList = tagList;
    }
    
    
}
