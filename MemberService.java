/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MemberRepository;
import Model.Boat;
import Model.Member;
import Model.DataHandler;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author niklaswittenbrink
 */
public class MemberService {
    private MemberRepository memberRepo;
    DataHandler register = new DataHandler();
    
    public MemberService(){
        memberRepo = new MemberRepository();
        loadSessionBoatData();
        loadSessionMemberData();
    }
    
    public void addMember(String name){
        Member newMemb = new Member(name);
        memberRepo.addMember(newMemb);
    }
    
    public Member getRepoMemberByName (String name){
        return memberRepo.getMembers().get(findMember(name));
    }
    
    public Member getRepoMember(int memberId){
        return memberRepo.getMembers().get(memberId);
    }
    
    public int getRepoMemberNum(){
        return memberRepo.getMemberNum();
    }
    
    public Boat getRepoMemberBoat (String name, int boatId){
        return memberRepo.getMembers().get(findMember(name)).getBoats().get(boatId);
    }
    
    
    public boolean removeMember (String name){
        int foundId = findMember(name);
        if (foundId != -1){
            for(int i = 0; i<memberRepo.getMembers().get(foundId).getNumberBoats();i++){
                memberRepo.removeBoat(memberRepo.getMembers().get(foundId).getBoats().get(i).getNum());
            }
            memberRepo.removeMember(foundId); 
            return true;
        } else {
            return false;
        }        
    }
    
    public int findMember (String name){
        int foundId = -1;
        for(int i=0; i<memberRepo.getMembers().size();i++){
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
                case "l": memberRepo.getBoats().get(num).setLength(Integer.parseInt(value));
                          back = true;
                          break;
                case "t": memberRepo.getBoats().get(num).setType(value);
                          back = true;
                          break; 
                default: back = false;
            }
        } else {
            back = false;
        }
        return back;
    }
    
    public void addBoat(String name, String length, String type){
        Boat newBoat = new Boat(Integer.parseInt(length),type);
        memberRepo.addBoat(newBoat);
        memberRepo.getMembers().get(findMember(name)).addBoat(memberRepo.getBoats().get(newBoat.getNum()));
    } 
    
    public void loadSessionBoatData(){
        register.createReader("boatData.txt");
        //Boat.setNumCount(register.readData());
        int boatNum = Integer.parseInt(register.readData());
        for(int i=0; i<boatNum; i++){
            memberRepo.addBoat(new Boat (Integer.parseInt(register.readData()),register.readData()));
            memberRepo.getBoats().get(i).setNum(Integer.parseInt(register.readData()));
        }
        register.closeReader();
    }
    
    public void loadSessionMemberData(){
        register.createReader("memberData.txt");
        //Boat.setNumCount(register.readData());
        int memberNum = Integer.parseInt(register.readData());
        for(int i=0; i<memberNum; i++){
            memberRepo.addMember(new Member (register.readData()));
            memberRepo.getMembers().get(i).setId(Integer.parseInt(register.readData()));
            int boatNum = Integer.parseInt(register.readData());
            if (boatNum != 0){
                for(int j=0; j<boatNum; j++){
                    memberRepo.getMembers().get(i).addBoat(memberRepo.getBoats().get(Integer.parseInt(register.readData())));
                }
            }
        }
        register.closeReader();
    }
    
    
    public void saveSessionBoatData(){
        register.createWriter("boatData.txt");
        //register.writeData(Integer.toString(Boat.getNumCount()));
        //register.writeData(System.getProperty("line.separator"));
        register.writeData(Integer.toString(memberRepo.getBoats().size()));
        register.writeData(System.getProperty("line.separator"));
        for(int i=0; i<memberRepo.getBoats().size(); i++){
            register.writeData(Integer.toString(memberRepo.getBoats().get(i).getLength()));
            register.writeData(System.getProperty("line.separator"));
            register.writeData(Integer.toString(memberRepo.getBoats().get(i).getTypeNum()+1));
            register.writeData(System.getProperty("line.separator"));
            register.writeData(Integer.toString(memberRepo.getBoats().get(i).getNum()));
            register.writeData(System.getProperty("line.separator"));
        }
        register.closeWriter();
    }
    
    public void saveSessionMemberData(){
        register.createWriter("memberData.txt");
        //register.writeData(Integer.toString(Boat.getNumCount()));
        //register.writeData(System.getProperty("line.separator"));
        register.writeData(Integer.toString(memberRepo.getMemberNum()));
        register.writeData(System.getProperty("line.separator"));
        for(int i=0; i<memberRepo.getMemberNum(); i++){
            register.writeData(memberRepo.getMembers().get(i).getName());
            register.writeData(System.getProperty("line.separator"));
            register.writeData(Integer.toString(memberRepo.getMembers().get(i).getId()));
            register.writeData(System.getProperty("line.separator"));
            register.writeData(Integer.toString(memberRepo.getMembers().get(i).getNumberBoats()));
            register.writeData(System.getProperty("line.separator"));
            for (int j=0; j<memberRepo.getMembers().get(i).getNumberBoats(); j++){
                register.writeData(Integer.toString(memberRepo.getMembers().get(i).getBoats().get(j).getNum()));
                register.writeData(System.getProperty("line.separator"));
            }
            
        }
        register.closeWriter();
    }
    
}