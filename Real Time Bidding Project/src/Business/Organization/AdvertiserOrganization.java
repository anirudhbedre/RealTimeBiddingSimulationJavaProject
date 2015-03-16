/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.Advertising.Ad;

import Business.Advertising.DSP;
import Business.Advertising.TagParameterConfigsList;
import Business.Enterprise.Enterprise;
import Business.Publisher.UserPotential;
import Business.Role.AdvertisingManager;
import Business.Role.ConsumerRole;
import Business.Role.Role;
import Business.WorkQueue.AuctionBiddingRequest;
import Business.WorkQueue.BidRequest;
import Business.WorkQueue.WorkRequest;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author anirudhbedre
 */
public class AdvertiserOrganization extends Organization {
    private DSP dsp;
    private Ad ad;
    private float advertisingBudget;
    
       public AdvertiserOrganization() {
        super(Organization.Type.Advertiser.getValue());
        dsp=new DSP();
        ad=new Ad();
        advertisingBudget=0;
    }

  
       

    public float getAdvertisingBudget() {
        return advertisingBudget;
    }

    public void setAdvertisingBudget(float advertisingBudget) {
        this.advertisingBudget = advertisingBudget;
    }
    public void reduceBidAmountFromBudget(float bidAmount) {
        this.advertisingBudget = advertisingBudget-bidAmount;
    }

    public DSP getDsp() {
        return dsp;
    }

    public void setDsp(DSP dsp) {
        this.dsp = dsp;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

  
  public void replyWithBid(AdvertiserOrganization advertiserOrganization, Enterprise enterprise)
  {
          
      // ----- check the current auction avent invite
      
       AuctionBiddingRequest auctionBiddingRequest = null;//=null;
       for(WorkRequest request: advertiserOrganization.getWorkQueue().getWorkRequestList()){
          //JOptionPane.showMessageDialog(null, "Found:"+String.valueOf(o.getOrganizationID()), "Warning", JOptionPane.WARNING_MESSAGE);
           if(request instanceof AuctionBiddingRequest && request.getStatus().equals("Sent"))
            auctionBiddingRequest=(AuctionBiddingRequest) request;
        }

       int basePriceForAd=auctionBiddingRequest.getBasePrice();
       
        // ---- prepare the bid        
        if(auctionBiddingRequest!=null){
            //-- prepare bid details    
            int auctionId=auctionBiddingRequest.getAuctionEvent().getAuctionID();
            UserPotential userPotential=auctionBiddingRequest.getUserPotential();
            
            float bidAmount=advertiserOrganization.getDsp().getBiddingAmount(userPotential);
            
            // ackknowledge auctionBiddingRequest
            auctionBiddingRequest.setStatus("Received");
            
            // -- create bid request
            String message="Bid from Advertiser "+String.valueOf(advertiserOrganization.getOrganizationID());
            BidRequest bidRequest=new BidRequest();
            bidRequest.setMessage(message);
            float advertisingBudget=advertiserOrganization.getAdvertisingBudget();
            
            if(advertisingBudget>basePriceForAd)
            {
            bidRequest.setBidAmount(bidAmount);
          
            }
            else
            bidRequest.setBidAmount(0);
            
            bidRequest.setAdvertiserId(advertiserOrganization.getOrganizationID());
            bidRequest.setAuctionEventId(auctionId);
            bidRequest.setStatus("Sent");
            
            
                bidRequest.setAdImage(ad.getAdImage()); /// put image path. how will you get it? from ad inventory?
            
            System.out.println("Preparing bid. Adding the ad :"+bidRequest.getAdImage());
            String bidDetails=message+" auctionID:"+String.valueOf(auctionId)+"  Bid Amount:"+String.valueOf(dsp.getBiddingAmount(userPotential));
            System.out.println(bidDetails);

            
            // -- sending the bid 
            AdExchangeOrganization adExchangeOrganization=null;
            for(Organization o:enterprise.getOrganizationDirectory().getOrganizationList()){         
                if(o instanceof AdExchangeOrganization){
                    adExchangeOrganization=(AdExchangeOrganization) o;
                    System.out.println( " adexchange currently point to: O points to"+String.valueOf(o.getOrganizationID())+
                    "adExchangeOrganization points to : "+
                    String.valueOf(adExchangeOrganization.getOrganizationID()));
                // break;
                }
                else{
                 // System.out.println( "not an instance of AdExchange, so continuing loop");
                // break;
                continue;
                }
                
                if(adExchangeOrganization!=null){
                    
                    adExchangeOrganization.getWorkQueue().getWorkRequestList().add(bidRequest); // attach req to target wrkQ
                    System.out.println("going  to reply to adExchange with bid ");
                    
                }
                else
                System.out.println("adx org pointer in replywithbid was null");
                
            }  
        }
        else
        System.out.println("auctionbiddingreq was null");
          
  }
       
       
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new AdvertisingManager());
        return roles;
    } 
}
