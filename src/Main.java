import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int difficulte, nombreEssaie, tailleSequence ,chiffre ,essaie = 1;
        ArrayList<Integer> sequenceSecrete = new ArrayList<Integer>();
        ArrayList<Integer> sequenceJoueur = new ArrayList<Integer>();
        ArrayList<String> historique = new ArrayList<String>();
        String verif;
        boolean finDePartie = false;
        Scanner clavier = new Scanner(System.in);
        do{
            System.out.println("Voulez vous changer la taille de la séquence à deviner ? (Oui ou Non)");
            verif = clavier.nextLine().toUpperCase();
            if (verif.equals("OUI")){
                System.out.println("Quelle taille voulez vous ?");
                tailleSequence = nombreInt();
            }else {
                tailleSequence = 5;
            }
            System.out.println("Voulez vous changer le nombre d'essaie ? (Oui ou Non)");
            verif = clavier.nextLine().toUpperCase();
            if (verif.equals("OUI")){
                System.out.println("Quelle taille voulez vous ?");
                nombreEssaie = nombreInt();
            }else {
                nombreEssaie = 12;
            }
            System.out.println("Quel difficulté vous voulez ?\n\t1-Facile\n\t2-Moyen\n\t3-Difficile\n\t4-Extreeeeeemmmmee");
            do {
                difficulte = nombreInt();
                if (difficulte>4 || difficulte<1){
                    System.out.println("Entre 1 et 4 les difficultés, veuillez resaisir la difficulté :\n\t1-Facile\n\t2-Moyen\n\t3-Difficile\n\t4-Extreeeeeemmmmee ");
                    difficulte = nombreInt();
                }
            }while(difficulte>4 || difficulte<1);
            sequenceSecrete = randomSequence(tailleSequence, difficulte);
            historique.add("Séquence à trouver durant cette partie : "+ sequenceSecrete);
            do{
                System.out.println(essaie+" essai");
                System.out.println("Quelle séquence de chiffre de 1 à 9, allez vous tester ? (Selon votre difficulté, il ne peut pas avoir de doublon dans les chiffres donc faites attention");
                for (int i = 0; i < tailleSequence; i++) {
                    System.out.print(i+1 + " chiffre : ");
                    if (difficulte == 1 || difficulte == 3) {
                        do {
                            chiffre = nombreInt();
                            if (sequenceJoueur.contains(chiffre)){
                                System.out.print("Ce chiffre est déjà dans la séquence de vous voulez tester, veuillez rechoisir un chiffre : ");
                                chiffre = nombreInt();
                            }
                            if (chiffre>9 || chiffre<1){
                                System.out.println("Un chiffre j'ai dit..., aller, on re-saisit ");
                                chiffre = nombreInt();
                            }
                        }while(sequenceJoueur.contains(chiffre) || chiffre>9 || chiffre<1);
                        sequenceJoueur.add(chiffre);
                    }else {
                        sequenceJoueur.add(nombreInt());
                    }
                }
                historique.add("Essai n°"+essaie+ ": " + sequenceJoueur);
                System.out.println("Ok, vérification !");
                if (difficulte == 1 || difficulte == 3){
                    essaie++;
                    for (int i = 0; i < tailleSequence; i++) {
                        if (sequenceJoueur.equals(sequenceSecrete)){
                            System.out.println("Bravo c'est gagné");
                            finDePartie = true;
                            break;
                        }else if (!sequenceSecrete.contains(i)){
                            System.out.println(sequenceJoueur.get(i)+" n'existe pas la séquence à trouver.");
                        }else{
                            if (sequenceJoueur.get(i).equals(sequenceSecrete.get(i))){
                                System.out.println(sequenceJoueur.get(i)+ " est à la bonne place!");
                            }else {
                                System.out.println(sequenceJoueur.get(i)+" existe mais pas à la bonne place");
                            }
                        }
                    }
                }else {
                    essaie++;
                    int nbBonnePlace = 0;
                    for (int i = 0; i < tailleSequence; i++) {
                        if (sequenceJoueur.equals(sequenceSecrete)){
                            System.out.println("Bravo c'est gagné");
                            finDePartie = true;
                            break;
                        }else if (sequenceJoueur.get(i).equals(sequenceSecrete.get(i))){
                            nbBonnePlace++;
                        }
                    }
                    if (!sequenceJoueur.equals(sequenceSecrete)){
                        System.out.println(nbBonnePlace + " chiffre(s) à la bonne place et "+ (tailleSequence-nbBonnePlace)+" pas à la bonne place");
                    }
                }
                if (essaie > nombreEssaie){
                    System.out.println("Vous avez perdu !\n");
                    finDePartie = true;
                }
                sequenceJoueur.clear();
            }while(!finDePartie);
            sequenceSecrete.clear();
            for (String i : historique){
                System.out.println(i);
            }
            System.out.println("Voulez vous continuer à jouer ? ( oui ou non )");
            verif = clavier.nextLine().toUpperCase();
        }while(verif.equals("OUI"));
        System.out.println("Merci d'avoir joué !");
    }

    public static ArrayList<Integer> randomSequence(int tailleSequence, int difficulte) {
        int nombreAleatoire, i = 0;
        ArrayList<Integer> sequenceSecrete = new ArrayList<Integer>();
        Random random = new Random();
        if (difficulte == 1 || difficulte == 3) {
            while(i<tailleSequence){
                nombreAleatoire = random.nextInt(10);
                if (nombreAleatoire == 0 || sequenceSecrete.contains(nombreAleatoire)){
                    nombreAleatoire = random.nextInt(10);
                }else{
                    sequenceSecrete.add(nombreAleatoire);
                    i++;
                }

            }
        }else if (difficulte == 2 || difficulte == 4) {
            while(i<tailleSequence){
                nombreAleatoire = random.nextInt(10);
                if (nombreAleatoire == 0){
                    nombreAleatoire = random.nextInt(10);
                }else{
                    sequenceSecrete.add(nombreAleatoire);
                    i++;
                }

            }
        }

        return sequenceSecrete;
    }

    public static int nombreInt() {
        Scanner clavier = new Scanner(System.in);
        while (!clavier.hasNextInt()) {
            System.out.println("Pas un chiffre");
            clavier.nextLine();
            System.out.println("Entrer un chiffre SANS virgule");
        }

        return clavier.nextInt();
    }
}