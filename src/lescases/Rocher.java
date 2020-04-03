package lescases;

import entitesvivantes.Personnage;

public class Rocher extends Case {

    private boolean enMouvement;

    public Rocher() {
        setEstSoumisALaGravite(true);
        setEnMouvement(false);
    }

    public Rocher(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setEstSoumisALaGravite(true);
        setEnMouvement(false);
    }

    @Override
    public void mettrePersoSurCase(Personnage p) {
        System.out.println("Cette case est de type" + 
        getClass().getSimpleName() +", le personnage "  + 
        p.getClass().getSimpleName() + " ne peut pas être ici !");    }

    public boolean isEnMouvement() {
        return enMouvement;
    }

    public void setEnMouvement(boolean enMouvement) {
        this.enMouvement = enMouvement;
    }

}