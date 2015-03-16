/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.WorkQueue;

import Business.AdExchange.AuctionEvent;
import Business.Publisher.UserPotential;

/**
 *
 * @author anirudhbedre
 */
public class AuctionBiddingRequest extends WorkRequest {
    private AuctionEvent auctionEvent;
    private int basePrice;
    private UserPotential userPotential;

    
    
    public UserPotential getUserPotential() {
        return userPotential;
    }

    public void setUserPotential(UserPotential userPotential) {
        this.userPotential = userPotential;
    }
    
    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }
    

    public AuctionEvent getAuctionEvent() {
        return auctionEvent;
    }

    public void setAuctionEvent(AuctionEvent auctionEvent) {
        this.auctionEvent = auctionEvent;
    }
    
    
}
