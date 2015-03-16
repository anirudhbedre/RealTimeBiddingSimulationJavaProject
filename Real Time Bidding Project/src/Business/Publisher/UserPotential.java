/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Publisher;

import Business.UserAccount.UserAccount;

/**
 *
 * @author anirudhbedre
 */
public class UserPotential {
    private UserAccount userAccount;
    private TagList tagList;

    public UserPotential() {
    this.tagList=new TagList() ;   
    
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public TagList getTagList() {
        return tagList;
    }

    public void setTagList(TagList tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return userAccount.getUsername();
    }

    
    
    
    
}
