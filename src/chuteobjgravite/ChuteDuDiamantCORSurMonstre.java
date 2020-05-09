package chuteobjgravite;

import entitesvivantes.Luciole;
import entitesvivantes.Monstre;
import entitesvivantes.Papillon;
import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Acier;
import lescases.Diamant;
import lescases.Sortie;
import lescases.Vide;
import modele.exceptions.BoulderMortException;
import toutlessongs.Musique;

public class ChuteDuDiamantCORSurMonstre extends ChuteObjGraviteCOR {

    public ChuteDuDiamantCORSurMonstre(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerObjGraviteVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1).estOccupee() && grille.getCaseDuTab(cs,ls+1).getEstIci() instanceof Monstre)) {
           // System.out.println("la case target n'est pas un monstre");
            return false;
        }

        grille.getCaseDuTab(cs,ls + 1).getEstIci().setVie(grille.getCaseDuTab(cs, ls+1).getEstIci().getVie()-1);
        if(grille.getCaseDuTab(cs,ls + 1).getEstIci().getVie() <= 0) {

            if(grille.getCaseDuTab(cs,ls + 1).getEstIci() instanceof Luciole) {
                Musique.initBruitage(Musique.EXPLOSION);
                for(int i = -1; i <= 1; i++) {
                    for(int j = -1; j <= 1; j++) {
                        
                        if(ls + 1 + j >= 0 && ls + 1 + j < grille.getYMAX()) {
                            if(cs + i >= 0 && cs + 1 < grille.getXMAX()) {
                                if(!(grille.getCaseDuTab(cs + i, ls + 1 + j) instanceof Acier ||
                                    grille.getCaseDuTab(cs + i, ls + 1 + j) instanceof Sortie)) {
                                    if(!(grille.getCaseDuTab(cs + i, ls + 1 + j).estOccupee() && 
                                    grille.getCaseDuTab(cs + i, ls + 1 + j).getEstIci() instanceof Rockford)) {
                                        grille.setCaseDuTab(cs + i, ls+1+j,new Vide(cs+i, ls+1+j));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            else if(grille.getCaseDuTab(cs,ls + 1).getEstIci() instanceof Papillon) {
                Musique.initBruitage(Musique.EXPLOSION);
                for(int i = -1; i <= 1; i++) {
                    for(int j = -1; j <= 1; j++) {
                        
                        if(ls + 1 + j >= 0 && ls + 1 + j < grille.getYMAX()) {
                            if(cs + i >= 0 && cs + 1 < grille.getXMAX()) {
                                if(!(grille.getCaseDuTab(cs + i, ls + 1 + j) instanceof Acier ||
                                    grille.getCaseDuTab(cs + i, ls + 1 + j) instanceof Sortie)) {
                                    if(!(grille.getCaseDuTab(cs + i, ls + 1 + j).estOccupee() && 
                                    grille.getCaseDuTab(cs + i, ls + 1 + j).getEstIci() instanceof Rockford)) {
                                        grille.setCaseDuTab(cs + i, ls+1+j,new Diamant(cs+i, ls+1+j));
                                    }
                                    else if(grille.getCaseDuTab(cs + i, ls + 1 + j).getEstIci() instanceof Rockford) {
                                        Rockford temp = (Rockford)(grille.getCaseDuTab(cs + i, ls + 1 + j).getEstIci());
                                        temp.setCompteurDiamant(temp.getCompteurDiamant()+1);
                                    }
                                    
                                }
                            }
                        }
                    }
                }
            }

        }
        grille.verifVieAll();
        return true;
    }

}