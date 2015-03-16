/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import java.util.ArrayList;

/**
 *
 * @author anirudhbedre
 */
public class OrganizationDirectory {
       private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList<>();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }
    
    public Organization createOrganization(Organization.Type type){
        Organization organization = null;
         if (type.getValue().equals(Organization.Type.Admin.getValue())){
            organization = new AdminOrganization();
            organizationList.add(organization);
        }
        if (type.getValue().equals(Organization.Type.Publisher.getValue())){
            organization = new PublisherOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Organization.Type.Advertiser.getValue())){
            organization = new AdvertiserOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Organization.Type.Consumer.getValue())){
            organization = new ConsumerOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Organization.Type.AdExchange.getValue())){
            organization = new AdExchangeOrganization();
            organizationList.add(organization);
        }
        return organization;
    }
}
