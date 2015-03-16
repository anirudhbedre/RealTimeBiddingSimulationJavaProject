/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.AdExchange;

import java.util.ArrayList;

/**
 *
 * @author anirudhbedre
 */
public class AuctionEvent {
    int auctionID;
    int publisherID;
    int basePrice;
    String consumerName;
    ArrayList<Bid> bidList;
    float winningBidAmount;
    int winnerOfAuction;
    String winningAD;
    private static int counter=1;

    public String getWinningAD() {
        return winningAD;
    }

    public void setWinningAD(String winningAD) {
        this.winningAD = winningAD;
    }

    
    public float getWinningBidAmount() {
        return winningBidAmount;
    }

    public void setWinningBidAmount(float winningBidAmount) {
        this.winningBidAmount = winningBidAmount;
    }

    public int getWinnerOfAuction() {
        return winnerOfAuction;
    }

    public void setWinnerOfAuction(int winnerOfAuction) {
        this.winnerOfAuction = winnerOfAuction;
    }

   

    public AuctionEvent() {
        bidList=new ArrayList<>();
        auctionID=counter;
        ++counter;
    }

    public ArrayList<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(ArrayList<Bid> bidList) {
        this.bidList = bidList;
    }
     public Bid createBid(){
        Bid bid = null;
        
        bid = new Bid();
        bidList.add(bid);
      
        return bid;
    }
    

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    @Override
    public String toString() {
        return String.valueOf(auctionID);
    }
    
}
