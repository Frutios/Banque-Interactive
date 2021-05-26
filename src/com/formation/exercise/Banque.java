package com.formation.exercise;
import java.util.Scanner;

public class Banque {

    protected Client [] clients;
    private byte nbClients;
    private final String NOM;

    public Banque(String nom){
        this.NOM = nom;
        this.nbClients = 0;
        clients = new Client[50];
    }


    protected void ajouterClient(String nom){
        Client client = new Client(nom);
        this.clients[nbClients] = client;
        this.nbClients += 1;
        System.out.println("Le client " + nom + " a été ajouté");

    }

    protected void bilanClient(){

        for (int i = 0; i < nbClients; i++) {
            System.out.println("Le client " + clients[i].getNom() +
            " a " + clients[i].getSolde() + " € sur l'ensemble de ses comptes ");
        }
    }

    protected void afficherBilan(){
        if (this.nbClients == 0){
            System.out.println("Ajoutez des clients, vous n'en avez pas !");
        } else {
            float totalSommeClient = 0;
            for (int i = 0; i < this.nbClients ; i++) {
                float solde = this.clients[i].getSolde();
                totalSommeClient += solde;
            }

            System.out.println("Le total des comptes est de " +  totalSommeClient + " €" );
        }
    }

    protected int getNbClients(){
        return this.nbClients;
    }

    protected String getNOM(){
        return this.NOM;
    }
}
