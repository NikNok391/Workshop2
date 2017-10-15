/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Model.Boat;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author niklaswittenbrink
 */
public class Member {
    static private int idCount = 0;
    private int id;
    private String name;
    private List<Boat> boats = new ArrayList<Boat>();
    
    public Member(String name) {
        id = idCount;
        idCount++;
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public static int getIdCount() {
        return idCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public int getNumberBoats() {
        return boats.size();
    }

    public void addBoat(Boat newBoat) {
        boats.add(newBoat);
    }   
    
    public void removeBoat (Boat newBoat){
        boats.remove(newBoat);
    }

    public List<Boat> getBoats() {
        return boats;
    }

}
