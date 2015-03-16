/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.WorkQueue;

/**
 *
 * @author anirudhbedre
 */
public class BidRequest extends WorkRequest{
    private int auctionEventId;
    private float bidAmount;
    private int AdvertiserId;
    String adImage;

    public int getAdvertiserId() {
        return AdvertiserId;
    }

    public void setAdvertiserId(int AdvertiserId) {
        this.AdvertiserId = AdvertiserId;
    }

    
    public String getAdImage() {
        return adImage;
    }

    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

    public int getAuctionEventId() {
        return auctionEventId;
    }

    public void setAuctionEventId(int auctionEventId) {
        this.auctionEventId = auctionEventId;
    }

    
    public float getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(float bidAmount) {
        this.bidAmount = bidAmount;
    }
    
}
