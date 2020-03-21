package entitesvivantes;

public class Monstre extends Personnage {

    private int vie;

    public Monstre(int x, int y){
        setPositionX(x);
        setPositionY(y);
        setVie(1);
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }


}