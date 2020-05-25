/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;

/**
 *
 * @author narib
 */
public class Utilisateur {
    private int idUtilisateur;
    private int idStatus;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String e_mailUtilisateur;
    private String motDePassUtilisateur;
    private Date dateDeNaissanceUtilisateur;
    private int numRueUtilisateur;
    private String nomRueUtilisateur;
    private String villeUtilisateur;
    private String codePostalUtilisateur;
    private String provinceUtilisateur;
    private String paysUtilisateur;
    private String telUtilisateur;
    private boolean CheckedUtilisateur;
    private String idCheckUtilisateur;

    public Utilisateur()
    {
        
    }
    
    public Utilisateur(int idUtilisateur, int idStatus, String nomUtilisateur, String prenomUtilisateur, String e_mailUtilisateur, String motDePassUtilisateur, Date dateDeNaissanceUtilisateur, int numRueUtilisateur, String nomRueUtilisateur, String villeUtilisateur, String codePostalUtilisateur, String provinceUtilisateur, String paysUtilisateur, String telUtilisateur, boolean CheckedUtilisateur, String idCheckUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.idStatus = idStatus;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.e_mailUtilisateur = e_mailUtilisateur;
        this.motDePassUtilisateur = motDePassUtilisateur;
        this.dateDeNaissanceUtilisateur = dateDeNaissanceUtilisateur;
        this.numRueUtilisateur = numRueUtilisateur;
        this.nomRueUtilisateur = nomRueUtilisateur;
        this.villeUtilisateur = villeUtilisateur;
        this.codePostalUtilisateur = codePostalUtilisateur;
        this.provinceUtilisateur = provinceUtilisateur;
        this.paysUtilisateur = paysUtilisateur;
        this.telUtilisateur = telUtilisateur;
        this.CheckedUtilisateur = CheckedUtilisateur;
        this.idCheckUtilisateur = idCheckUtilisateur;
    }
     public Utilisateur(int idStatus, String nomUtilisateur, String prenomUtilisateur, String e_mailUtilisateur, String motDePassUtilisateur, Date dateDeNaissanceUtilisateur, int numRueUtilisateur, String nomRueUtilisateur, String villeUtilisateur, String codePostalUtilisateur, String provinceUtilisateur, String paysUtilisateur, String telUtilisateur, boolean CheckedUtilisateur, String idCheckUtilisateur) {
        this.idStatus = idStatus;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.e_mailUtilisateur = e_mailUtilisateur;
        this.motDePassUtilisateur = motDePassUtilisateur;
        this.dateDeNaissanceUtilisateur = dateDeNaissanceUtilisateur;
        this.numRueUtilisateur = numRueUtilisateur;
        this.nomRueUtilisateur = nomRueUtilisateur;
        this.villeUtilisateur = villeUtilisateur;
        this.codePostalUtilisateur = codePostalUtilisateur;
        this.provinceUtilisateur = provinceUtilisateur;
        this.paysUtilisateur = paysUtilisateur;
        this.telUtilisateur = telUtilisateur;
        this.CheckedUtilisateur = CheckedUtilisateur;
        this.idCheckUtilisateur = idCheckUtilisateur;
    }
    
    public Utilisateur(int idUtilisateur, int idStatus, String nomUtilisateur, String prenomUtilisateur, String e_mailUtilisateur, String motDePassUtilisateur, Date dateDeNaissanceUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.idStatus = idStatus;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.e_mailUtilisateur = e_mailUtilisateur;
        this.motDePassUtilisateur = motDePassUtilisateur;
        this.dateDeNaissanceUtilisateur = dateDeNaissanceUtilisateur;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getE_mailUtilisateur() {
        return e_mailUtilisateur;
    }

    public void setE_mailUtilisateur(String e_mailUtilisateur) {
        this.e_mailUtilisateur = e_mailUtilisateur;
    }

    public String getMotDePassUtilisateur() {
        return motDePassUtilisateur;
    }

    public void setMotDePassUtilisateur(String motDePassUtilisateur) {
        this.motDePassUtilisateur = motDePassUtilisateur;
    }

    public Date getDateDeNaissanceUtilisateur() {
        return dateDeNaissanceUtilisateur;
    }

    public void setDateDeNaissanceUtilisateur(Date dateDeNaissanceUtilisateur) {
        this.dateDeNaissanceUtilisateur = dateDeNaissanceUtilisateur;
    }

    public int getNumRueUtilisateur() {
        return numRueUtilisateur;
    }

    public void setNumRueUtilisateur(int numRueUtilisateur) {
        this.numRueUtilisateur = numRueUtilisateur;
    }

    public String getNomRueUtilisateur() {
        return nomRueUtilisateur;
    }

    public void setNomRueUtilisateur(String nomRueUtilisateur) {
        this.nomRueUtilisateur = nomRueUtilisateur;
    }

    public String getVilleUtilisateur() {
        return villeUtilisateur;
    }

    public void setVilleUtilisateur(String villeUtilisateur) {
        this.villeUtilisateur = villeUtilisateur;
    }

    public String getCodePostalUtilisateur() {
        return codePostalUtilisateur;
    }

    public void setCodePostalUtilisateur(String codePostalUtilisateur) {
        this.codePostalUtilisateur = codePostalUtilisateur;
    }

    public String getProvinceUtilisateur() {
        return provinceUtilisateur;
    }

    public void setProvinceUtilisateur(String provinceUtilisateur) {
        this.provinceUtilisateur = provinceUtilisateur;
    }

    public String getPaysUtilisateur() {
        return paysUtilisateur;
    }

    public void setPaysUtilisateur(String paysUtilisateur) {
        this.paysUtilisateur = paysUtilisateur;
    }

    public String getTelUtilisateur() {
        return telUtilisateur;
    }

    public void setTelUtilisateur(String telUtilisateur) {
        this.telUtilisateur = telUtilisateur;
    }

    public boolean isCheckedUtilisateur() {
        return CheckedUtilisateur;
    }

    public void setCheckedUtilisateur(boolean isCheckedUtilisateur) {
        this.CheckedUtilisateur = isCheckedUtilisateur;
    }

    public String getIdCheckUtilisateur() {
        return idCheckUtilisateur;
    }

    public void setIdCheckUtilisateur(String idCheckUtilisateur) {
        this.idCheckUtilisateur = idCheckUtilisateur;
    }
    
}

