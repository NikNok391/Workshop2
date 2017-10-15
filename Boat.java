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
    private String type;
    static private int numCount = 0;
    private int num;
    
    public Boat (){
        num = numCount;
        numCount++;
    }

    public Boat(int length, int type) {
        num = numCount;
        numCount++;
        this.length = length;
        switch (type){
            case 1: this.type="Sailboat";
                    break;
            case 2: this.type="Motorsailer";
                    break;
            case 3: this.type="Kayak";
                    break;
            default: this.type="Other";
                     break;
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }    
}
