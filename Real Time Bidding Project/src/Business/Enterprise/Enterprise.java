/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Enterprise;

import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;

/**
 *
 * @author anirudhbedre
 */
public abstract class Enterprise extends Organization {
        private EnterpriseType enterpriseType;
    private OrganizationDirectory organizationDirectory;

    public Enterprise(String name,EnterpriseType type) {
        super(name);
        this.enterpriseType = type;
        organizationDirectory = new OrganizationDirectory();
    }
    
    public enum EnterpriseType{
//        
        Consumer("Consumer"),
        InternetAdvertisingPlatform("Internet Advertising Platform");
        
        private String value;
        
        private EnterpriseType(String value)
        {
            this.value = value;
        }
        public String getValue()
        {
            return value;
        }
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }

    public void setOrganizationDirectory(OrganizationDirectory organizationDirectory) {
        this.organizationDirectory = organizationDirectory;
    }

    @Override
    public String toString() {
        return enterpriseType.getValue();
    }
    
}
