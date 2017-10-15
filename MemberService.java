/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MemberRepository;
import Model.BoatRepository;
import Model.Boat;
import Model.Member;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author niklaswittenbrink
 */
public class MemberService {
    private MemberRepository memberRepo;
    private BoatRepository boatRepo;
    
    public MemberService(){
        memberRepo = new MemberRepository();
        boatRepo = new BoatRepository();
    }
    
    public void addMember(String name){
        Member newMemb = new Member(name);
        memberRepo.addMember(newMemb);
    }
    
    public List<Member> listMember(){
        return memberRepo.getMembers();
    }
    
    public List<Boat> listBoat (String name){
        return memberRepo.getMembers().get(findMember(name)).getBoats();
    }
    
    public boolean removeMember (String name){
        int foundId = findMember(name);
        if (foundId != -1){
            for(int i = 0; i<memberRepo.getMembers().get(foundId).getNumberBoats();i++){
                boatRepo.removeBoat(memberRepo.getMembers().get(foundId).getBoats().get(i).getNum());
            }
            memberRepo.removeMember(foundId); 
            return true;
        } else {
            return false;
        }        
    }
    
    public int findMember (String name){
        int foundId = -1;
        for(int i=1; i<memberRepo.getMembers().size();i++){
            if (memberRepo.getMembers().get(i).getName().equals(name)){
                foundId = memberRepo.getMembers().get(i).getId();
            }  
        }
        return foundId;
    }
    
    public boolean updateMember (String name, String reason, String update){
        int foundId = findMember(name);
        boolean back = false;
        if (foundId != -1){
            switch (reason.toLowerCase()){
                case "n": memberRepo.getMembers().get(foundId).setName(update);
                          back = true;
                          break;
                case "i": memberRepo.getMembers().get(foundId).setId(Integer.parseInt(update));
                          back = true;
                          break; 
                default: back = false;
            }
        } else {
            back = false;
        }
        return back;
    }
    
    public boolean updateMemberBoat (String name, int num, String reason, String value){
        int foundId = findMember(name);
        boolean back = false;
        if (foundId != -1){
            switch (reason.toLowerCase()){
                case "l": boatRepo.getBoats().get(num).setLength(Integer.parseInt(value));
                          back = true;
                          break;
                case "t": boatRepo.getBoats().get(num).setType(value);
                          back = true;
                          break; 
                default: back = false;
            }
        } else {
            back = false;
        }
        return back;
    }
    
    public void addBoat(String name, int length, int type){
        Boat newBoat = new Boat(length,type);
        boatRepo.addBoat(newBoat);
        memberRepo.getMembers().get(findMember(name)).addBoat(boatRepo.getBoats().get(newBoat.getNum()));
    }
    
}
