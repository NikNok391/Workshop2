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
 * @author marce
 */
public class BoatRepository {
    private List<Boat> boats = new ArrayList<Boat>();
    
    
    public BoatRepository(){
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
