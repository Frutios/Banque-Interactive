package com.formation.exercise;

public class Main {

    public static void main(String[] args) {



        Banque banque = new Banque("BNP");
        BanqueInteractive banqueInteractive = new BanqueInteractive(banque);
        banqueInteractive.demander();



    }
}
