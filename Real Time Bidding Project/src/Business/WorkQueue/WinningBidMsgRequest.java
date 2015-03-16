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
public class WinningBidMsgRequest extends WorkRequest{
    String adImage;
    float bidAmount;

    public String getAdImage() {
        return adImage;
    }

    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

    public float getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(float bidAmount) {
        this.bidAmount = bidAmount;
    }
    
    
}
