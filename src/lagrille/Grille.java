package lagrille;

import lescases.*;

public class Grille {

    private Case[][] tableau;
    private int XMAX;
    private int YMAX;

    public Grille() {
    }

    public Case[][] getTableau() {
        return tableau;
    }

    public void setTableau(Case[][] tableau) {
        this.tableau = tableau;
    }

    public int getXMAX() {
        return XMAX;
    }

    public void setXMAX(int xMAX) {
        XMAX = xMAX;
    }

    public int getYMAX() {
        return YMAX;
    }

    public void setYMAX(int yMAX) {
        YMAX = yMAX;
    }
    
}