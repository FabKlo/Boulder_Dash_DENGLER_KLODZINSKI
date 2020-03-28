package entitesvivantes;

import java.util.ArrayList;

public abstract class Personnage {

    private int vie;
    private int positionX;
    private int positionY;
    private ArrayList<String> casesTraversables = new ArrayList<String>();

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

    public ArrayList<String> getCasesTraversables() {
        return casesTraversables;
    }

    public void addCasesTraversables(String casesTraversables) {
        this.casesTraversables.add(casesTraversables);
    }

    public void setCasesTraversables(ArrayList<String> casesTraversables) {
        this.casesTraversables = casesTraversables;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

}