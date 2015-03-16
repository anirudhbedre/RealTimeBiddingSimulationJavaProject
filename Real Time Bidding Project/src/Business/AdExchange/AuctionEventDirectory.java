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
public class AuctionEventDirectory {
      private ArrayList<AuctionEvent> auctionEventList;

    public AuctionEventDirectory() {
        auctionEventList = new ArrayList<>();
    }

    public ArrayList<AuctionEvent> getAuctionEventList() {
        return auctionEventList;
    }
    
    public AuctionEvent createAuctionEvent(){
        AuctionEvent auctionEvent = null;
        auctionEvent = new AuctionEvent();
        auctionEventList.add(auctionEvent);
        return auctionEvent;
    }
}
