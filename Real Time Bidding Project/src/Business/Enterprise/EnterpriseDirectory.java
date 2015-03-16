/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Enterprise;

import java.util.ArrayList;

/**
 *
 * @author anirudhbedre
 */
public class EnterpriseDirectory {
      private ArrayList<Enterprise> enterPriseList;

    public EnterpriseDirectory() {
        enterPriseList = new ArrayList<>();
    }

    public ArrayList<Enterprise> getEnterPriseList() {
        return enterPriseList;
    }

    public void setEnterPriseList(ArrayList<Enterprise> enterPriseList) {
        this.enterPriseList = enterPriseList;
    }
    
    public Enterprise createAndAddEnterprise(String name, Enterprise.EnterpriseType type)
    {
        Enterprise enterprise = null;
        if(type == Enterprise.EnterpriseType.InternetAdvertisingPlatform)
        {
            enterprise = new AdExchangeEnterprise(name, type);
            enterPriseList.add(enterprise);
        }
        else if(type == Enterprise.EnterpriseType.Consumer)
        {
            enterprise = new ConsumerEnterprise(name, type);
            enterPriseList.add(enterprise);
        }
        return enterprise;
    }
}
