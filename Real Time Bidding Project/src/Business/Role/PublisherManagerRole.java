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
import Business.Organization.PublisherOrganization;
import Business.UserAccount.UserAccount;
import UserInterface.PublisherRole.PublisherWorkAreaJPanel;
import javax.swing.JPanel;

/**
 *
 * @author anirudhbedre
 */
public class PublisherManagerRole extends Role{
     @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization,Enterprise enterprise,Network network, Ecosystem business) {
        return  
               new PublisherWorkAreaJPanel(userProcessContainer, business,(PublisherOrganization) organization);
    }
}
