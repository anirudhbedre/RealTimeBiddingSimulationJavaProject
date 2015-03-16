/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.AdExchange.AuctionEvent;
import Business.AdExchange.AuctionEventDirectory;
import Business.AdExchange.Bid;
import Business.Enterprise.Enterprise;
import Business.Publisher.UserPotential;
import Business.Role.AdExchangeManagerRole;
import Business.Role.AdvertisingManager;
import Business.Role.Role;
import Business.WorkQueue.AuctionBiddingRequest;
import Business.WorkQueue.BidRequest;
import Business.WorkQueue.NotifyAdExchangeWorkRequest;
import Business.WorkQueue.WinningBidMsgRequest;
import Business.WorkQueue.WorkRequest;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author anirudhbedre
 */
public class AdExchangeOrganization extends Organization {
    
    private AuctionEventDirectory auctionEventDirectory;
    
    public AdExchangeOrganization() {
        super(Organization.Type.AdExchange.getValue());
        auctionEventDirectory=new AuctionEventDirectory();
        
        
    }

    public AuctionEventDirectory getAuctionEventDirectory() {
        return auctionEventDirectory;
    }

    public void setAuctionEventDirectory(AuctionEventDirectory auctionEventDirectory) {
        this.auctionEventDirectory = auctionEventDirectory;
    }
    
    public void startAuctionEvent(AdExchangeOrganization adExchangeOrganization,Enterprise enterprise, PublisherOrganization publisherOrganization){
          

        // --------- Ad Exchange see workqueue for notification ----- work out the first part(notification handling and deleting)
        
        NotifyAdExchangeWorkRequest startAuctionRequest=null;
        for(WorkRequest request: adExchangeOrganization.getWorkQueue().getWorkRequestList()){
            if(request instanceof NotifyAdExchangeWorkRequest && request.getStatus().equals("Sent"))
                startAuctionRequest=(NotifyAdExchangeWorkRequest)request;
        }
        
        // ------retrieve publisher adspace & consumer details from startAuctionRequest details here:
        int basePrice=startAuctionRequest.getBasePrice();
        UserPotential userPotential=startAuctionRequest.getUserPotential();
        // ..............
        
        // -------flush/acknowledge NotifyPublisher request
        startAuctionRequest.setStatus("Received");
        
        
        
        // -------------- Ad Exchange creates new Auction event
        AuctionEvent auctionEvent=adExchangeOrganization.auctionEventDirectory.createAuctionEvent();
        
        
       // --- Creating and Broadcasting AuctionBiddingRequests to all the Advertisers
//        String message2=" Auction Bidding Request";
//        AuctionBiddingRequest auctionBiddingRequest=new AuctionBiddingRequest();
//        auctionBiddingRequest.setMessage(message2);
//        auctionBiddingRequest.setAuctionEvent(auctionEvent);
//        auctionBiddingRequest.setStatus("Sent");
        
        
        
        AdvertiserOrganization advertiserOrganization=null;
        for(Organization o:enterprise.getOrganizationDirectory().getOrganizationList()){
            if(o instanceof AdvertiserOrganization){
                 System.out.println("Found:"+String.valueOf(o.getOrganizationID()));   
                
                advertiserOrganization=(AdvertiserOrganization) o;

                  String message=" Auction Bidding Request";
                  AuctionBiddingRequest auctionBiddingRequest=new AuctionBiddingRequest();
                  auctionBiddingRequest.setMessage(message);
                  auctionBiddingRequest.setUserPotential(userPotential);
                  auctionBiddingRequest.setAuctionEvent(auctionEvent);
                  auctionBiddingRequest.setStatus("Sent");
                  auctionBiddingRequest.setBasePrice(basePrice);
                  advertiserOrganization.getWorkQueue().getWorkRequestList().add(auctionBiddingRequest); // attach req to target wrkQ
                  System.out.println("going  to send to advertiser ");   
                  advertiserOrganization.replyWithBid(advertiserOrganization, enterprise);

                
            }
        }

        
     ///----- check all received bids
     
        int bidAmount;
        BidRequest bidRequest=null;
        System.out.println( "Entered BidRequest for-loop");
        for(WorkRequest request: adExchangeOrganization.getWorkQueue().getWorkRequestList()){
             //System.out.println( "Entered BidRequest for-loop");
            if(request instanceof BidRequest){
                bidRequest=(BidRequest) request;
                if(bidRequest.getAuctionEventId()==auctionEvent.getAuctionID()){
                    Bid bid=auctionEvent.createBid();
                    bid.setAuctionId(bidRequest.getAuctionEventId());
                    bid.setBidAmount(bidRequest.getBidAmount());
                    bid.setAdImage(bidRequest.getAdImage());
                    bid.setBidByAdvertiserId(bidRequest.getAdvertiserId());
                    System.out.println("Added bid by"+bidRequest.getMessage());
                    
                }
            }
            //else
              //  System.out.println("not an instance of BidRequest");
                
        } 
        System.out.println("Recieved all bids");
        
            
     //// --- calculate winner of bid --- incorporate this function in auction event?
        float maxBid=0;
        int auctionWinner;
        Bid highestBidder=null;
        String winningAd=null;
        for(Bid bid:auctionEvent.getBidList()){
            if(bid.getBidAmount()>=maxBid){
                highestBidder=bid;
                maxBid=bid.getBidAmount();
                System.out.println("Ad in loop : " +bid.getAdImage());
            }
        }
           
        auctionWinner=highestBidder.getBidByAdvertiserId();
        winningAd=highestBidder.getAdImage() ;
         System.out.println("Winner of the bid is "+highestBidder.getBidByAdvertiserId());
               
        // storing in auction event
         auctionEvent.setWinningBidAmount(maxBid);
         auctionEvent.setWinnerOfAuction(auctionWinner);
         auctionEvent.setWinningAD(winningAd);
        
        
         AdvertiserOrganization adOrganization=null;
        for(Organization o:enterprise.getOrganizationDirectory().getOrganizationList()){
            if(o instanceof AdvertiserOrganization){
                adOrganization=(AdvertiserOrganization)o;
                if(adOrganization.getOrganizationID()==auctionWinner)
                    adOrganization.reduceBidAmountFromBudget(auctionEvent.getWinningBidAmount());
            }
           
        }
        
        
        
        
           
         
         
         // Send winning ad and details to publisher

           String message="Sending winning ad to publisher"+String.valueOf(publisherOrganization.getOrganizationID());
           System.out.println( message+"Winning ad is"+winningAd);
           WinningBidMsgRequest winningBidMsgRequest=new WinningBidMsgRequest();
           winningBidMsgRequest.setMessage(message);
           winningBidMsgRequest.setBidAmount(maxBid);
           winningBidMsgRequest.setAdImage(highestBidder.getAdImage());
           winningBidMsgRequest.setStatus("Sent");
           publisherOrganization.getWorkQueue().getWorkRequestList().add(winningBidMsgRequest);
           System.out.println("Sent winning ad to publisher");

            
         
         
         
   
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new AdExchangeManagerRole());
        return roles;
    } 
}
