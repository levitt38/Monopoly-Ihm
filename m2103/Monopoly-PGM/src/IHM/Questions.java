/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;
import Jeu.Observateur;
import java.util.Scanner;
/**
 *
 * @author mouhatcl
 */
public class Questions{
    private static Scanner scan = new Scanner(System.in);
    private static int tab = 0;
    
    public static void increment(){
        tab++;
    }
    
    public static void decrement(){
        tab--;
    }
    
    /* Les méthodes ci-dessous sont "protected"(utilisables classe-fille ou même package) pour empêcher leur utilisation
    depuis le controleur sont passer par la classe Ihm qui implements l'Observateur */
    protected static void affiche(String s){
        for(int i=0;i<tab;i++){
            System.out.print("  ");
        }
        System.out.println(s);
    }
    
    protected static int askNb(String s){
        int choix = 0;
        boolean valide = true;
        do{
            affiche(s);
            try{
                choix = scan.nextInt();
                valide = (choix>0) ? true : false;
            } catch(Exception e){ affiche(TextColors.RED+"Merci de rentrer un chiffre svp !"+TextColors.RESET); valide=false; scan.next();}
            
        }while(!valide);
        return choix;
    }
    
    protected static String askStr(String s){
        affiche(s); String str;
        do{
            str = scan.nextLine();
        }while(str.equals(""));
        return str;
    }
    
    protected static boolean askYN(String s){
        String choix = null;
        do{
            choix = askStr(s+" (oui/non)");
            if(! choix.equalsIgnoreCase("oui") && ! choix.equalsIgnoreCase("non")){
                affiche(TextColors.RED+"Merci de rentrer soit \"oui\" soit \"non\" !"+TextColors.RESET);
            }
        }while(!(choix.equalsIgnoreCase("oui")||choix.equalsIgnoreCase("non")));
        
        return choix.equalsIgnoreCase("oui");
    }
}
