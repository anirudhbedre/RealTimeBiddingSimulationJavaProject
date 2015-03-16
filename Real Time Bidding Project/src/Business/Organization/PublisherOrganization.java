/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.Enterprise.Enterprise;
import Business.Publisher.SSP;
import Business.Publisher.Tag;
import Business.Publisher.TagList;
import Business.Publisher.UserPotential;
import Business.Publisher.UserPotentialDirectory;
import Business.Role.PublisherManagerRole;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.NotifyAdExchangeWorkRequest;
import Business.WorkQueue.NotifyPublisherWorkRequest;
import Business.WorkQueue.WinningBidMsgRequest;
import Business.WorkQueue.WorkRequest;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author anirudhbedre
 */
public class PublisherOrganization extends Organization{
    private SSP ssp;
    private float account;
    private UserPotentialDirectory userPotentialDirectory;
      public PublisherOrganization() {
        super(Organization.Type.Publisher.getValue());
        ssp=new SSP();
        account=0;
        userPotentialDirectory=new UserPotentialDirectory();
    }

    public UserPotentialDirectory getUserPotentialDirectory() {
        return userPotentialDirectory;
    }

    public void setUserPotentialDirectory(UserPotentialDirectory userPotentialDirectory) {
        this.userPotentialDirectory = userPotentialDirectory;
    }

    public float getAccount() {
        return account;
    }

    public void addToAccount(float account) {
        this.account = this.account +account;
    }
      
      
    public SSP getSsp() {
        return ssp;
    }

    public void setSsp(SSP ssp) {
        this.ssp = ssp;
    }
    
    public String notifyAdExchange(Enterprise enterprise,PublisherOrganization publisherOrganization){
        
        // -- Publisher see workqueue for notification 
        NotifyPublisherWorkRequest notificationRequest=null;
        for(WorkRequest request: publisherOrganization.getWorkQueue().getWorkRequestList()){
            if(request instanceof NotifyPublisherWorkRequest && request.getStatus().equals("Sent"))
                notificationRequest=(NotifyPublisherWorkRequest)request;
        }
        
        // ------retrieve consumer details from useraccount details here:
         UserAccount consumerAccount=notificationRequest.getSender();
         TagList consumerTagList=notificationRequest.getTagList();
        

        // check if he is already exists in userPotential directory, if not: create, else: return exisiting one and carry forward 
         
        UserPotential userPotential=publisherOrganization.getUserPotentialDirectory().checkIfUserPotentialExists(consumerAccount);
        if (userPotential==null){
            userPotential=publisherOrganization.getUserPotentialDirectory().createUserPotential(consumerAccount);
            
        }
        // assign tags to user potential
      //  if(consumerTagList!=null){
        for(Tag tag:consumerTagList.getTagList()){
            userPotential.getTagList().createOrUpdateTags(tag.getTagName());
        }
       // }
                
         
     //   consumerUserAccount.
        
        // -------acknowledge NotifyPublisher request
        notificationRequest.setStatus("Received");
        
       
        
        //publisherOrganization.getWorkQueue().getWorkRequestList().remove(notificationRequest);
        
        // ------- publisher has to send details to adExchange
        
        String message2="Publisher wants an auction with this userProfile";
        NotifyAdExchangeWorkRequest notifyAdExchangeWorkRequest=new NotifyAdExchangeWorkRequest();
        notifyAdExchangeWorkRequest.setMessage(message2);
        notifyAdExchangeWorkRequest.setUserPotential(userPotential);
        notifyAdExchangeWorkRequest.setStatus("Sent");
        notifyAdExchangeWorkRequest.setBasePrice(publisherOrganization.getSsp().getBasePrice());
        AdExchangeOrganization adExchangeOrganization=null;
        
        for(Organization o:enterprise.getOrganizationDirectory().getOrganizationList()){
            if(o instanceof AdExchangeOrganization){
                adExchangeOrganization=(AdExchangeOrganization) o;
                break; // this is ok as long as there is only one adExchange
            }
        }
        
        if(adExchangeOrganization!=null){
            adExchangeOrganization.getWorkQueue().getWorkRequestList().add(notifyAdExchangeWorkRequest); // attach req to target wrkQ
            System.out.println("Sent user profile to AdExchange");
            adExchangeOrganization.startAuctionEvent(adExchangeOrganization,enterprise,publisherOrganization);
            
            WinningBidMsgRequest winningBidMsgRequest=null;//=new WinningBidMsgRequest();
            for(WorkRequest request : publisherOrganization.getWorkQueue().getWorkRequestList()){
                if(request instanceof WinningBidMsgRequest && request.getStatus().equals("Sent"))
                {
                   winningBidMsgRequest=(WinningBidMsgRequest)request;
                   break;
                }
            }
            float bidAmount=winningBidMsgRequest.getBidAmount();
            publisherOrganization.addToAccount(bidAmount);
            String adImage=winningBidMsgRequest.getAdImage();
            winningBidMsgRequest.setStatus("Received");
            System.out.println("Winning BidAmount was"+bidAmount);
            System.out.println("Publisher's account now has"+publisherOrganization.getAccount());
            System.out.println("Winning Ad image was"+adImage);

            return adImage;
            
        }
        else{
            System.out.println("AdExchange org pointer was null");
            JOptionPane.showMessageDialog(null, "AdExchange org pointer was null", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
       
    }
      
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new PublisherManagerRole());
        return roles;
    }
}
