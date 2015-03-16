/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Advertising;

import Business.Publisher.Tag;
import Business.Publisher.UserPotential;

/**
 *
 * @author anirudhbedre
 */
public class DSP {
    private float baseBiddingAmount;
    private TagParameterConfigsList tagParameterConfigsList;

    public DSP() {
        this.baseBiddingAmount=0;
        this.tagParameterConfigsList = new TagParameterConfigsList();
    }

    public TagParameterConfigsList getTagParameterConfigsList() {
        return tagParameterConfigsList;
    }

    public void setTagParameterConfigsList(TagParameterConfigsList tagParameterConfigsList) {
        this.tagParameterConfigsList = tagParameterConfigsList;
    }
    
    
    public float getBiddingAmount(UserPotential userPotential) {
        float extraBidAmount=0;
        for(Tag tag:userPotential.getTagList().getTagList()){
           for(TagParameterConfigs tagParameterConfigs:tagParameterConfigsList.getTagParameterConfigsList()){
               if(tag.getTagName().equals(tagParameterConfigs.getTagName())){
                   extraBidAmount=extraBidAmount+ ((tag.getNumberOfVisits()*tagParameterConfigs.getPriceOnNumberOfVisits()*baseBiddingAmount)/100);
               }
           } 
        }
        
        
        
        return (baseBiddingAmount+extraBidAmount);
               
    }
    public float getBaseBiddingAmount() {
        
        return baseBiddingAmount;
    }
    public void setBaseBiddingAmount(float baseBiddingAmount) {
        this.baseBiddingAmount = baseBiddingAmount;
    }
    
    
}
