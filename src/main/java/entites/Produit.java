/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.ArrayList;

/**
 *
 * @author narib
 */
public class Produit {
    private int idProduit;
    private int idCategorie;
    private int idMarque;
    private int codeProduit;
    private String nomProduit;
    private double prixProduit;
    private int stockProduit;
    private ArrayList<ImageProduit> imageProduit;

    public Produit()
    {
        imageProduit = new ArrayList<>();
    }
    
    public Produit(int idCategorie, int idMarque, int codeProduit, String nomProduit, double prixProduit, int stockProduit) {
        this.idCategorie = idCategorie;
        this.idMarque = idMarque;
        this.codeProduit = codeProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.stockProduit = stockProduit;
        imageProduit = new ArrayList<>();
    }
    
    public Produit(int idCategorie, int idMarque, int codeProduit, String nomProduit, double prixProduit, ArrayList<ImageProduit> imageProduit) {
        this.idCategorie = idCategorie;
        this.idMarque = idMarque;
        this.codeProduit = codeProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.imageProduit = imageProduit;
    }
    
    public Produit(int idProduit, int idCategorie, int idMarque, int codeProduit, String nomProduit, double prixProduit, ArrayList<ImageProduit> imageProduit) {
        this.idProduit = idProduit;
        this.idCategorie = idCategorie;
        this.idMarque = idMarque;
        this.codeProduit = codeProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.imageProduit = imageProduit;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public int getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(int codeProduit) {
        this.codeProduit = codeProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }

    public int getStockProduit() {
        return stockProduit;
    }

    public void setStockProduit(int stockProduit) {
        this.stockProduit = stockProduit;
    }

    public ArrayList<ImageProduit> getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(ArrayList<ImageProduit> imageProduit) {
        this.imageProduit = imageProduit;
    }
    
    
    
}