package lescases;

import entitesvivantes.Personnage;

public class Rocher extends Case {

    public Rocher() {
        setEstSoumisALaGravite(true);
    }

    public Rocher(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setEstSoumisALaGravite(true);
    }

    @Override
    public void mettrePersoSurCase(Personnage p) {
        System.out.println("Cette case est de type" + 
        getClass().getSimpleName() +", le personnage "  + 
        p.getClass().getSimpleName() + " ne peut pas être ici !");    }
}