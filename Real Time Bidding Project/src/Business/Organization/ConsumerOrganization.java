/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.Role.ConsumerRole;
import Business.Role.PublisherManagerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author anirudhbedre
 */
public class ConsumerOrganization extends Organization{
     public ConsumerOrganization() {
        super(Organization.Type.Consumer.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new ConsumerRole());
        return roles;
    } 
}
