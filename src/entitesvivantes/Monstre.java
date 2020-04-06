package entitesvivantes;

import lescases.Vide;

public class Monstre extends Personnage {

    /**
     * Création d'un monstre avec vie = 1
     */
    public Monstre(){
        setVie(1);
        addCasesTraversables(Vide.class.getSimpleName());
    }

}