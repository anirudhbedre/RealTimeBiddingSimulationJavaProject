/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Role;

import Business.Ecosystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;

/**
 *
 * @author anirudhbedre
 */
public abstract class Role {
    
     public enum RoleType{
        Admin("Admin"),
        Publisher("Publisher"),
        AdvertisingManager("AdvertisingManager"),
        Consumer("Consumer"),
        AdExchangeManager("AdExchange Manager");
        
        private String value;
        private RoleType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public abstract JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization,Enterprise enterprise ,Network network,Ecosystem business);

    @Override
    public String toString() {
        String roleName=this.getClass().getName();
        return roleName;
    }
    
}
