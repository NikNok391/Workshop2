/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author marce
 */
public class Boat {
    private int length;
    private enum Type {
        Sailboat, Motorsailer, Kayak, Other;
    }
    private int typeNum;
    static private int numCount = 0;
    private int num;
    
    public Boat (){
        num = numCount;
        numCount++;
    }
    
    public Boat(int length, String type) {
        num = numCount;
        numCount++;
        this.length = length;
        typeNum = typeConvers(type);
    }
    
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getType() {
        return Type.values()[typeNum].name();
    }

    public void setType(String type) {
        typeNum = typeConvers(type);
    }

    public int getNum() {
        return num;
    } 

    public int getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(int typeNum) {
        this.typeNum = typeNum;
    }

    public static int getNumCount() {
        return numCount;
    }

    public static void setNumCount(int numCount) {
        Boat.numCount = numCount;
    }

    public void setNum(int num) {
        this.num = num;
    }
        
    private int typeConvers (String type){
        int conType;
        switch(type){
            case "1":   conType = 0;
                        break;
            case "2":   conType = 1;
                        break;
            case "3":   conType = 2;
                        break;
            default :   conType = 3;
                        break;            
        }
        return conType;
    }
}
