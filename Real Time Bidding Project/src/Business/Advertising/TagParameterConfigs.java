/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Advertising;

/**
 *
 * @author anirudhbedre
 */
public class TagParameterConfigs {
    private String tagName;
    private int priceOnNumberOfVisits;

    public TagParameterConfigs(String tagName) {
        this.tagName = tagName;
    }

    
    
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getPriceOnNumberOfVisits() {
        return priceOnNumberOfVisits;
    }

    public void setPriceOnNumberOfVisits(int priceOnNumberOfVisits) {
        this.priceOnNumberOfVisits = priceOnNumberOfVisits;
    }

    @Override
    public String toString() {
        return tagName ;
    }
    
    
}
