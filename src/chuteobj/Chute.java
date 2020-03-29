package chuteobj;

import lescases.Case;

public abstract class Chute {

    private Chute suivant = null;

    /**
     * Constructeur detection
     * @param m La detection
     */
    public Chute(Chute m) {
        suivant = m;
    }

    public Chute getSuivant() {
        return suivant;
    }

    public Chute setSuivant(Chute suiv) {
        suivant = suiv;
        return suiv;
    }

    public abstract String getDescription();

    public abstract double SavoirMalus(Case c);

    public double getMalus(Case c)
    {
        double malus = SavoirMalus(c);
		if (malus !=-1)
			return malus;
		else if (suivant != null)
			return suivant.SavoirMalus(c);
		else
			throw new IllegalArgumentException("Il n'existe aucune interaction ");
    }

    //public abstract Combinaison detecterCombinaison(int x, int y, Niveau lv);

    /**
     * 
     * @param x La coordonnée X du bonbon
     * @param y La coordonnée Y du bonbon 
     * @param lv La niveau du jeu 
     * @return la combinaison si il y en a une sinon passe au suivant dans la chaine de responsabilité
     * @throws CandyException si il n'y a aucune interaction possible 
     */
    /*public Combinaison interagir(int x, int y, Niveau lv) throws CandyException {
    	Combinaison combi = detecterCombinaison(x, y, lv);
		if (combi !=null)
			return combi;
		else if (suivant != null)
			return suivant.interagir(x,y,lv);
		else
			throw new CandyException("Il n'existe aucune interaction ");
	}*/
}