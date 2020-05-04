package chuteobjgravite;

import entitesvivantes.Luciole;
import entitesvivantes.Monstre;
import entitesvivantes.Papillon;
import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Acier;
import lescases.Diamant;
import lescases.Rocher;
import modele.exceptions.BoulderMortException;

public class ChuteDuRocherCORSurMonstre extends ChuteObjGraviteCOR {

    public ChuteDuRocherCORSurMonstre(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerObjGraviteVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)) {
            //System.out.println("la case source n'est pas un rocher");
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1).estOccupee() && grille.getCaseDuTab(cs,ls+1).getEstIci() instanceof Monstre)) {
            //System.out.println("la case target n'est pas un monstre");
            return false;
        }

        grille.getCaseDuTab(cs,ls + 1).getEstIci().setVie(grille.getCaseDuTab(cs, ls+1).getEstIci().getVie()-1);
        if(grille.getCaseDuTab(cs,ls + 1).getEstIci().getVie() <= 0) {
            if(grille.getCaseDuTab(cs,ls + 1).getEstIci() instanceof Luciole) {
                grille.setCaseDuTab(cs, ls+1,new Diamant(cs,ls+1));
            }

            else if(grille.getCaseDuTab(cs,ls + 1).getEstIci() instanceof Papillon) {
                for(int i = -1; i <= 1; i++) {
                    for(int j = -1; j <= 1; j++) {
                        
                        if(ls + 1 + j >= 0 && ls + 1 + j < grille.getYMAX()) {
                            if(cs + i >= 0 && cs + 1 < grille.getXMAX()) {
                                if(!(grille.getCaseDuTab(cs + i, ls + 1 + j) instanceof Acier)) {
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
        //System.out.println("le rocher a tape un monstre :\ntableau "+cs+" "+(ls+1)+" = "+grille.getCaseDuTab(cs,ls-1));
        return true;
    }

}