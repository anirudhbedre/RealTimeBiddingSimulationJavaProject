/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Publisher;

import Business.UserAccount.UserAccount;
import java.util.ArrayList;

/**
 *
 * @author anirudhbedre
 */
public class UserPotentialDirectory {
      private ArrayList<UserPotential> userPotentialList;

    public UserPotentialDirectory() {
        userPotentialList = new ArrayList<>();
    }

    public ArrayList<UserPotential> getUserPotentialList() {
        return userPotentialList;
    }

    public void setUserPotentialList(ArrayList<UserPotential> userPotentialList) {
        this.userPotentialList = userPotentialList;
    }
    
    public UserPotential createUserPotential(UserAccount userAccount){
      UserPotential userPotential = new UserPotential();
      userPotential.setUserAccount(userAccount);
      userPotentialList.add(userPotential);
      return userPotential;  
    }
    
    public UserPotential checkIfUserPotentialExists(UserAccount userAccount)
    {
        for(UserPotential userPotential:userPotentialList)
        {
            if(userPotential.getUserAccount()==userAccount)
            {
                return userPotential;
            }
            
        }
        return null;
    }
    
}
