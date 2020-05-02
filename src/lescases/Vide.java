package lescases;


import entitesvivantes.Luciole;
import entitesvivantes.Monstre;
import entitesvivantes.Papillon;
import entitesvivantes.Personnage;
import entitesvivantes.Rockford;

public class Vide extends Case {

    public Vide() {
        setEstSoumisALaGravite(false);
    }

    public Vide(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setEstSoumisALaGravite(false);
    }

    @Override
    public void mettrePersoSurCase(Personnage p) {
        if(!this.estOccupee()) {
            if(p.getCasesTraversables().contains(this.getClass().getSimpleName())) {
                p.setPositionX(this.getPositionX());
                p.setPositionY(this.getPositionY());
                this.setEstIci(p);
                /*System.out.println("Le personnage " + p.getClass().getSimpleName() + " est positionne sur la case " +
                this.getClass().getSimpleName() + " de coordonnee : x = " + p.getPositionX() + ", y = " + p.getPositionY());*/
            }
        }
    }

    @Override
    public int caseEnInt() {
        if((this.estOccupee())) {
            if(this.getEstIci() instanceof Rockford)
                return 1;
            else if(this.getEstIci() instanceof Monstre) {
                if(this.getEstIci() instanceof Papillon)
                    return 4;
                else if(this.getEstIci() instanceof Luciole)
                    return 8;
            }
                
        }
            
        return 0;
    }
    
}