/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Member;
import Model.Boat;
import Controller.MemberService;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author niklaswittenbrink
 */
public class MemberView {

    private MemberService ms = new MemberService();
    Scanner reader = new Scanner(System.in);
    
    public static void main(String[] args) {
        MemberView mv = new MemberView();
        mv.userInterface();
    }

    public void userInterface() {
        Scanner menu = new Scanner(System.in);
        String m = "E";
        do {
            System.out.println("\n=======BoatClub 96=======");
            System.out.println("What do you want to do \n[C]reate new member \n[R]etrieve the memberlist \n[U]pdate a Member \n[D]elete a Member \nor [E]xit?");
            System.out.println("=========================");
            m = menu.nextLine();
            switch (m.toLowerCase()) {
                case "c":
                    this.createMember();
                    break;
                case "r":
                    this.list();
                    break;
                case "u":
                    this.updateMember();
                    break;
                case "d":
                    this.removeMember();
                    break;
                default:
                    break;
            }
        } while (!m.equals("E"));
        ms.saveSessionBoatData();
        ms.saveSessionMemberData();
        menu.close();
    }

    private void createMember() {
        System.out.println("Please enter new name");
        String name = reader.nextLine();
        ms.addMember(name);
        System.out.println("Adding a boat: Length?");
        String length = reader.nextLine();
        System.out.println("Type of the Boat?\n[1]Sailboat\n[2]Motorsailer\n[3]Kayak\n[0]Other");
        String type = reader.nextLine();
        ms.addBoat(name, length, type);
        System.out.println("New Member: " + name + "\n");
    }

    private void list() {
        System.out.println("[C]ompact list or [D]etailed?");
        String listStyle = reader.nextLine();
        if(listStyle.equals("C") || listStyle.equals("c")){
            System.out.println("Name \tID \tNumber of boats:");
            for (int i = 0; i < ms.getRepoMemberNum(); i++) {
                printMember(ms.getRepoMember(i));
            }
        } else if(listStyle.equals("D") || listStyle.equals("d")){
            System.out.println("Name \tID \tNumber of boats:");
            for (int i = 0; i <  ms.getRepoMemberNum(); i++) {
                printMember(ms.getRepoMember(i));
                if (ms.getRepoMember(i).getNumberBoats() > 0){
                System.out.println("\tNum \tLength\tType");
                    for (int j = 0; j < ms.getRepoMember(i).getNumberBoats(); j++) {
                        printBoat(ms.getRepoMemberBoat(ms.getRepoMember(i).getName(),j));
                    }
                }    
            }
        }
    }

    private void removeMember() {
        System.out.println("Please enter a member's name to remove");
        String name = reader.nextLine();
        System.out.println("Remove " + name + "? [Y/n]");
        String confirm = reader.nextLine();
        if (confirm.equals("Y") || confirm.equals("y")) {
            if (ms.removeMember(name)) {
                System.out.println(name + " sucsessfully removed!");
            } else {
                System.out.println("Member " + name + " not found");
            }
        } else {
            System.out.println("Removing abort.");
        }
    }

    private void updateMember() {
        System.out.println("Please enter the member's name to update");
        String name = reader.nextLine();
        if (ms.findMember(name) != -1) {
            System.out.println("Updating [P]ersonal data or [B]oats data?");
            String change = reader.nextLine();
            if (change.equals("P") || change.equals("p")) {
                System.out.println("What is to update? The [N]ame or the [I]d-number?");
                String reason = reader.nextLine();
                System.out.println("Enter new value");
                String update = reader.nextLine();
                if (ms.updateMember(name, reason, update)) {
                    System.out.println(name + " sucsessfully updated");
                } else {
                    System.out.println("Member " + name + " not found or unable to update!");
                }
            } else if (change.equals("B") || change.equals("b")) {
                System.out.println("[U]pdating or [A]dding a boat?");
                String boatsUpdate = reader.nextLine();
                if (boatsUpdate.equals("U") || boatsUpdate.equals("u")) {
                    System.out.println("Which boat [num] should be updated?");
                    System.out.println("Num \tLength\tType");
                    for (int i = 0; i < ms.getRepoMemberByName(name).getNumberBoats(); i++) {
                        printBoat (ms.getRepoMemberBoat(name,i));
                    }
                    String num = reader.nextLine();
                    System.out.println("What is to update? The [L]ength or the [T]ype?");
                    String reason = reader.nextLine();
                    System.out.println("Enter new value. [TYPE: 1= Sailboat, 2= Motorboat, 3= Kayak, other]");
                    String update = reader.nextLine();
                    if (ms.updateMemberBoat(name, Integer.parseInt(num), reason, update)) {
                        System.out.println(name + "'s boat sucsessfully updated");
                    } else {
                        System.out.println("Member " + name + " not found or unable to update!");
                    }
                } else if (boatsUpdate.equals("A") || boatsUpdate.equals("a")) {
                    System.out.println("Adding a boat: Length?");
                    String length = reader.nextLine();
                    System.out.println("Type of the Boat?\n[1]Sailboat\n[2]Motorsailer\n[3]Kayak\n[0]Other");
                    String type = reader.nextLine();
                    ms.addBoat(name, length, type);
                }
            }
        } else {
            System.out.println("Member " + name + " not found!");
        }
    }
    
    private void printMember (Member member){
        System.out.println(member.getName() + "\t" + member.getId() + "\t" + member.getNumberBoats());
    }
    
    private void printBoat (Boat boat){
        System.out.println("\t" + boat.getNum() + "\t" + boat.getLength() + "\t" + boat.getType());
    }
}
