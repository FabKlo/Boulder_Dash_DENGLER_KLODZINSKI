package chuterocher;

import java.util.ArrayList;
import java.util.Random;

import lagrille.Grille;
import lescases.Rocher;
import lescases.Vide;
import modele.exceptions.BoulderMortException;

public class ChuteDuRocherCORSurRocher extends ChuteDuRocherCOR {

    public ChuteDuRocherCORSurRocher (ChuteDuRocherCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Rocher)) {
            System.out.println("la case target n'est pas un rocher");
            return false;
        }
        
        ArrayList<Integer> oui = new ArrayList<Integer>();
        oui.add(-1);
        oui.add(1);
        Random r = new Random();
        int temp = r.nextInt((1 - 0) + 1) + 0;
        temp = oui.get(temp);

        //int temp = 1;

        if(cs + temp < 0 || cs + temp >= grille.getXMAX() || !(grille.getCaseDuTab(cs + temp, ls) instanceof Vide)) {
            System.out.println("cs + temp = " + cs+temp);
            temp *= -1;
        }

        if(cs + temp < 0 || cs + temp >= grille.getXMAX() || !(grille.getCaseDuTab(cs + temp, ls) instanceof Vide)) {
            System.out.println("cs + temp = " + cs+temp);
            return true;
        }

        if(!((Rocher)(grille.getCaseDuTab(cs,ls))).isEnMouvement()) {

            if(grille.getCaseDuTab(cs + temp, ls) instanceof Vide &&
            grille.getCaseDuTab(cs + temp, ls + 1) instanceof Vide) {

                System.out.println("cs : "+(cs)+" l : "+(ls));
                System.out.println("ct : "+(cs+temp)+" l : "+(ls));
                if(!(grille.getCaseDuTab(cs + temp, ls).estOccupee()) && !(grille.getCaseDuTab(cs + temp, ls + 1).estOccupee())) {

                    grille.setCaseDuTab(cs + temp, ls, grille.getCaseDuTab(cs,ls));
                    ((Rocher)(grille.getCaseDuTab(cs + temp,ls))).setEnMouvement(true);
                    grille.setCaseDuTab(cs,ls, new Vide(cs, ls));
                    return true;
                } else
                    return true;

            } else
                return true;

        } else {
            if(grille.getCaseDuTab(cs + temp, ls) instanceof Vide &&
            grille.getCaseDuTab(cs + temp, ls + 1) instanceof Vide) {

                if(!(grille.getCaseDuTab(cs + temp, ls).estOccupee())) {
                    grille.setCaseDuTab(cs + temp, ls, grille.getCaseDuTab(cs,ls));
                    grille.setCaseDuTab(cs,ls, new Vide(cs, ls));
                    return true;
                }
                else {
                    ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false); 
                    return true;
                }
            } else {
                ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false); 
                return true;
            }
        }


    }

}