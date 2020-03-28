package entitesvivantes;

import lescases.Vide;

public class Monstre extends Personnage {

    public Monstre(){
        setVie(1);
        addCasesTraversables(Vide.class.getSimpleName());
    }

}