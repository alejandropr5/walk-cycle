/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarduino;

/**
 *
 * @author alejandro
 */
public class Data {

    private double aRod;
    private String fase;

    public Data(double aRod, String fase) {
        this.aRod = aRod;
        this.fase = fase;
    }

    public double getaRod() {
        return aRod;
    }

    public String getFase() {
        return fase;
    }

    public void setaRod(double aRod) {
        this.aRod = aRod;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    @Override
    public String toString() {
        return "<html>Ángulo de la rodilla: <B>" + aRod + "°</B> "
                + "\nFase de marcha: <B>" + fase + "</B></html>";
        
    }
}
