package com.formation.exercise;

/**
 * @author B.Tourneur
 */

public class Compte {
    // attributs

    private final int NUMERO;
    private float solde;

    public Compte(int NUMERO){
        this.NUMERO = NUMERO;
    }

    //méthodes


    protected void afficherSolde(){
        System.out.println(getSolde());
    }

    protected void virer(float valeur, Compte destinataire){
        this.solde = solde - valeur;
        destinataire.depot(valeur);
    }

    protected void depot(float valeur){
        this.solde = solde + valeur;
    }

    protected void retrait(float valeur){
        this.solde = solde - valeur;
    }

    protected float getSolde(){
        return this.solde;
    }

    protected int getNUMERO() {
        return this.NUMERO;
    }
}
