package com.formation.exercise;

public class Client {

    public Compte [] comptes = new Compte[5];
    protected byte nbComptes;
    private final String NOM;

    public Client(String NOM){
        this.NOM = NOM;
        this.nbComptes = 0;
    }


    public void afficherSolde(){
        System.out.println("Le solde total du compte de " + this.NOM + " est " + getSolde());
    }

    protected void ajouterCompte(int nombre){
         for (int i = 0; i < nombre; i++){
             comptes[nbComptes] = new Compte(nbComptes);
             nbComptes += 1;
         }
    }

    protected String getNom(){
        return this.NOM;
    }

    protected float getSolde(){

        float somme = 0.0f;

        for (int i = 0; i < nbComptes; i++) {
            somme = somme + comptes[i].getSolde();
        }
        float arrondi = Math.round(somme*100.0f)/100.0f;
        return arrondi;
    }

}
