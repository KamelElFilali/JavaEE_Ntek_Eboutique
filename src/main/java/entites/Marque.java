/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author narib
 */
public class Marque {
    private int idMarque;
    private String nomMarque;
    private String imageMarque;

    public Marque()
    {
        
    }
    
    public Marque(String nomMarque, String imageMarque) {
        this.nomMarque = nomMarque;
        this.imageMarque = imageMarque;
    }
    
    public Marque(int idMarque, String nomMarque, String imageMarque) {
        this.idMarque = idMarque;
        this.nomMarque = nomMarque;
        this.imageMarque = imageMarque;
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public String getImageMarque() {
        return imageMarque;
    }

    public void setImageMarque(String imageMarque) {
        this.imageMarque = imageMarque;
    }
    
    
}
