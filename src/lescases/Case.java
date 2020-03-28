 package lescases;

 import entitesvivantes.*;

public abstract class Case {

    private int positionX;
    private int positionY;

    private Personnage estIci;

    private boolean estSoumisALaGravite;

    public boolean estOccupee()  {
        if(getEstIci() == null)
            return false;
        else
            return true;
    }

    public abstract void mettrePersoSurCase(Personnage p) ;


    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return "Case [positionX=" + positionX + ", positionY=" + positionY + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Case other = (Case) obj;
        if (positionX != other.positionX)
            return false;
        if (positionY != other.positionY)
            return false;
        return true;
    }

    public Personnage getEstIci()  {
            return estIci;
  
    }

    public void setEstIci(Personnage estIci) {
        this.estIci = estIci;
    }
  

    public boolean isEstSoumisALaGravite() {
        return estSoumisALaGravite;
    }

    public void setEstSoumisALaGravite(boolean estSoumisALaGravite) {
        this.estSoumisALaGravite = estSoumisALaGravite;
    }
    

}