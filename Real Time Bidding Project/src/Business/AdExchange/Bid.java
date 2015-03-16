/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.AdExchange;

/**
 *
 * @author anirudhbedre
 */
public class Bid {
    int auctionId;
    float bidAmount;
    int bidByAdvertiserId;
    String adImage;

    public String getAdImage() {
        return adImage;
    }

    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public float getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(float bidAmount) {
        this.bidAmount = bidAmount;
    }

    public int getBidByAdvertiserId() {
        return bidByAdvertiserId;
    }

    public void setBidByAdvertiserId(int bidByAdvertiserId) {
        this.bidByAdvertiserId = bidByAdvertiserId;
    }

  

    @Override
    public String toString() {
       return String.valueOf(bidByAdvertiserId) ;
    }
    
    
    
}
