/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Advertising;

import java.util.ArrayList;

/**
 *
 * @author anirudhbedre
 */
public class TagParameterConfigsList {
    private ArrayList<TagParameterConfigs> tagParameterConfigsList;

    public TagParameterConfigsList() {
    this.tagParameterConfigsList=new ArrayList<>();
    initializeTagParameterCongfigs();
    }

    
    public ArrayList<TagParameterConfigs> getTagParameterConfigsList() {
        return tagParameterConfigsList;
    }

    public void setTagParameterConfigsList(ArrayList<TagParameterConfigs> tagParameterConfigsList) {
        this.tagParameterConfigsList = tagParameterConfigsList;
    }
    
      public void createTagParameterConfigs(String tagName,int priceOnNumberOfVisits){
        
        TagParameterConfigs tagParameterConfigs=new TagParameterConfigs(tagName);
        tagParameterConfigs.setPriceOnNumberOfVisits(priceOnNumberOfVisits);
        tagParameterConfigsList.add(tagParameterConfigs);
        
    }
    
    
    public void createOrUpdateTagParameterConfigs(String tagName, int priceOnNumberOfVisits){
        for(TagParameterConfigs tagParameterConfigs:this.tagParameterConfigsList)
        {
            if(tagParameterConfigs.getTagName().equals(tagName)){
                tagParameterConfigs.setPriceOnNumberOfVisits(priceOnNumberOfVisits);
                return;
            }
        }
       TagParameterConfigs tagParameterConfigs=new TagParameterConfigs(tagName);
        tagParameterConfigs.setPriceOnNumberOfVisits(priceOnNumberOfVisits);
        tagParameterConfigsList.add(tagParameterConfigs);
    }
    public void initializeTagParameterCongfigs() {
        createTagParameterConfigs("Music", 0);
        createTagParameterConfigs("Books", 0);
        createTagParameterConfigs("Mobilephones",0);
        createTagParameterConfigs("Ipod", 0);
        createTagParameterConfigs("Clothes", 0);
        createTagParameterConfigs("Movies", 0);
        createTagParameterConfigs("TVshows", 0);
        createTagParameterConfigs("Modems", 0);
        createTagParameterConfigs("Gadgets", 0);
        createTagParameterConfigs("Sports", 0);
    }
    
}
