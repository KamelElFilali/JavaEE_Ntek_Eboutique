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
public class ImageProduit {
    private int idImageProduit;
    private int idProduit;
    private String imageProduit;

    public ImageProduit()
    {
        
    }
    
    public ImageProduit(int idImageProduit, int idProduit, String imageProduit) {
        this.idImageProduit = idImageProduit;
        this.idProduit = idProduit;
        this.imageProduit = imageProduit;
    }

     public ImageProduit(int idImageProduit, int idProduit) {
        this.idImageProduit = idImageProduit;
        this.idProduit = idProduit;
    }
     
    public int getIdImageProduit() {
        return idImageProduit;
    }

    public void setIdImageProduit(int idImageProduit) {
        this.idImageProduit = idImageProduit;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(String imageProduit) {
        this.imageProduit = imageProduit;
    }

    
}