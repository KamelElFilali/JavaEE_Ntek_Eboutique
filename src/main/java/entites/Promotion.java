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
public class Promotion {
    private int idPromotion;
    private String codePromotion;
    private int rabaisPromotion;
    private boolean isExpire;

    public Promotion() 
    {
        
    }
    
    public Promotion(String codePromotion, int rabais_promotion) {
        this.codePromotion = codePromotion;
        this.rabaisPromotion = rabais_promotion;
        this.isExpire = false;
    }
    
    public Promotion(int idPromotion, String codePromotion, int rabais_promotion, boolean isExpire) {
        this.idPromotion = idPromotion;
        this.codePromotion = codePromotion;
        this.rabaisPromotion = rabais_promotion;
        this.isExpire = isExpire;
    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public String getCodePromotion() {
        return codePromotion;
    }

    public void setCodePromotion(String codePromotion) {
        this.codePromotion = codePromotion;
    }

    public int getRabaisPromotion() {
        return rabaisPromotion;
    }

    public void setRabaisPromotion(int rabaisPromotion) {
        this.rabaisPromotion = rabaisPromotion;
    }

    public boolean isIsExpire() {
        return isExpire;
    }

    public void setIsExpire(boolean isExpire) {
        this.isExpire = isExpire;
    }
}