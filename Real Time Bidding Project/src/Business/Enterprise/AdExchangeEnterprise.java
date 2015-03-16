/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author anirudhbedre
 */
public class AdExchangeEnterprise extends Enterprise{
      public AdExchangeEnterprise(String name, EnterpriseType type) {
        super(name,EnterpriseType.InternetAdvertisingPlatform);
    }
    
    

    @Override
    public ArrayList<Role> getSupportedRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
