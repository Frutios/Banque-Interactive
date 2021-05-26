package com.formation.exercise;

import java.util.Scanner;

public class BanqueInteractive {

    private final Scanner saisieConsole = new Scanner(System.in);
    private final Banque banque;

    public BanqueInteractive(Banque banque){
        this.banque = banque;
    }

    public void demander(){
        boolean quitter = false;

        bienvenue();

        System.out.println("[1] Ajouter un client");
        if (this.banque.getNbClients() > 0){
            System.out.println("[2] Effectuer une opération sur un client");
        }
        System.out.println("[3] Afficher un bilan général");
        System.out.println("[4] Quitter");

        int choix = this.saisieConsole.nextInt();
        if (choix == 1){
            ajouterClientBanque();
        } else if(choix == 2) {
            selectionnerClient();
        } else if(choix == 3) {
            this.banque.afficherBilan();
        } else {
            quitter = true;
        }
        if (quitter){
            adios();
        } else {
            continuerNavigation();
        }
    }

    private void ajouterClientBanque(){
        System.out.println("Entrez le nom du nouveau client.");
        String nomNouveauClient = saisieConsole.next();
        this.banque.ajouterClient(nomNouveauClient);
    }

    private void selectionnerClient(){
        System.out.println("Choisissez un client pour réaliser une opération");
        for (int numeroClient = 0; numeroClient < this.banque.getNbClients(); numeroClient++) {
            System.out.println("["+ numeroClient + 1 + "] " + this.banque.clients[numeroClient].getNom());
        }
        int clientChoisi = this.saisieConsole.nextInt() -1;
        choixOperationClient(clientChoisi);
    }

    private void choixOperationClient(int numeroClient){
        boolean quitter = false;
        System.out.println("[1] Afficher un bilan des comptes");
        System.out.println("[2] Faire un retrait");
        System.out.println("[3] Faire un dépôt");
        System.out.println("[4] Créer un compte");
        System.out.println("[5] Faire un virement");
        System.out.println("[6] Afficher solde d'un compte");
        System.out.println("[7] Quitter");

        int choixaction = this.saisieConsole.nextInt();
        Client client = this.banque.clients[numeroClient];
        if (choixaction == 1){
            client.afficherSolde();
        } else if (choixaction == 2){
            clientRetrait(client);
        } else if (choixaction == 3){
            clientDepot(client);
        } else if (choixaction == 4){
            client.ajouterCompte(1);
        } else if (choixaction == 5){
            clientVirement(client);
        } else if (choixaction == 6){
            clientAfficherSoldeCompte(client);
        } else {
            quitter = true;
        }
        if (quitter){
            adios();
        } else {
            clientContinuer(numeroClient);
        }
    }

    private void clientRetrait(Client client){
        Compte compte = selectionnerCompte(client, "Choisissez le compte sur lequel vous voulez faire un retrait");
        System.out.println("Entrez le montant que vous souhaitez retirer");
        int montantChoisi = this.saisieConsole.nextInt();

        float solde = compte.getSolde();
        if (montantChoisi > solde){
            System.out.println("Vas-y molo ! Tu n'as pas cette somme sur ton compte");
        } else {
            compte.retrait(montantChoisi);
        }
    }

    public void clientDepot(Client client){
        Compte compte = selectionnerCompte(client, "Choisissez le compte sur lequel vous voulez faire un dépot");
        System.out.println("Entrez le montant que vous souhaitez déposer");
        float montantDepose = this.saisieConsole.nextFloat();
        compte.depot(montantDepose);
    }

    public Compte selectionnerCompte(Client client, String question){
        int nbComptesClient = client.nbComptes;

        System.out.println(question);
        for (int numeroCompte = 0; numeroCompte < nbComptesClient; numeroCompte++){
            int affichageCompte = numeroCompte + 1;
            System.out.println("[" + affichageCompte + "] Compte numéro " + affichageCompte);
        }
        int compteChoisi = saisieConsole.nextInt();
        int numeroCompte = compteChoisi -1;
        return client.comptes[numeroCompte];
    }

    public void clientVirement(Client client){
        Compte comptePrelevement = selectionnerCompte(client, "Selectionnez le compte qui sera prélevé");
        Compte compteDestination = selectionnerCompte(client, "Selectionnez le compte destinataire");

        System.out.println("Quel montant voulez-vous virer ?");

        float virement = saisieConsole.nextFloat();
        float solde = comptePrelevement.getSolde();
        if (solde < virement){
            System.out.println("Vous n'avez pas les moyens ! Shame !");
        } else {
            comptePrelevement.virer(virement, compteDestination);
        }

    }

    private void continuerNavigation(){
        int autreOperation = continuerAutreOperation("Souhaitez vous faire une autre opération ?");
        if (autreOperation == 1){
            demander();
        } else {
            adios();
        }
    }

    private void clientContinuer(int numeroClient){
        int autreOperation = continuerAutreOperation("Souhaitez vous faire une autre opération sur ce compte client ?");
        if (autreOperation == 1){
            choixOperationClient(numeroClient);
        }
    }

    private int continuerAutreOperation(String question){
        System.out.println(question);
        System.out.println("[1] Oui");
        System.out.println("[2] Non");
        return this.saisieConsole.nextInt();
    }

    private void clientAfficherSoldeCompte(Client client){
        Compte compte = selectionnerCompte(client, "Sur quel compte voulez vous voir le solde ?");
        compte.afficherSolde();
    }

    private void bienvenue(){
        System.out.println("Bienvenue chez " + this.banque.getNOM());
    }

    private void adios(){
        System.out.println("Merci d'être passé nous voir ! ;)");
    }
}
