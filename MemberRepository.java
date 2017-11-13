/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author niklaswittenbrink
 */
public class MemberRepository {
    private List<Member> members = new ArrayList<Member>();
    private List<Boat> boats = new ArrayList<Boat>();

    public MemberRepository() {
    }  
    
    public void addMember(Member newMemb){
       members.add(newMemb);
    }
    
    public void removeMember(int id){
        members.remove(id);
    }

    public List<Member> getMembers() {
        return members;
    } 
    
    public int getMemberNum (){
        return members.size();
    }
   
    public void addBoat(Boat newBoat){
        boats.add(newBoat);
    }
    
    public void removeBoat(int num){
        boats.remove(num);
    }

    public List<Boat> getBoats() {
        return boats;
    }
}
