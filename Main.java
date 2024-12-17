import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Création de l'objet GestionDeSpectacle
        GestionDeSpectacle gestionDeSpectacle = new GestionDeSpectacle(10, 5);

        while (true) {
            // Affichage du menu
            System.out.println("------TIN-TIN-EVENT------");
            System.out.println("\n------Menu de gestion de spectacle------");
            System.out.println("1. Ajouter un spectacle");
            System.out.println("2. Ajouter un lieu et une date pour un spectacle");
            System.out.println("3. Ajouter un artiste à un spectacle");
            System.out.println("4. Ajouter un client");
            System.out.println("5. Ajouter une réservation pour un client");
            System.out.println("6. Supprimer un artiste d'un spectacle");
            System.out.println("7. Supprimer un client d'un spectacle");
            System.out.println("8. Supprimer un spectacle");
            System.out.println("9. Afficher le calendrier des spectacles");
            System.out.println("10. Afficher les Spectacles");
            System.out.println("11. Quitter");

            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nom du spectacle: ");
                    String nomSpectacle = scanner.nextLine();

                    String typeSpectacle = "";
                    boolean validType = false;
                    String[] validTypes = {"Musique", "Théâtre", "Magique"};

                    while (!validType) {
                        System.out.print("Type du spectacle (Musique, Théâtre, Magique): ");
                        typeSpectacle = scanner.nextLine();

                        for (String validTypeOption : validTypes) {
                            if (typeSpectacle.equalsIgnoreCase(validTypeOption)) {
                                validType = true;
                                break;
                            }
                        }

                        if (!validType) {
                            System.out.println("Type invalide, veuillez choisir parmi les types suivants : Musique, Théâtre, Magique.");
                        }
                    }

                    int maxClients, maxArtistes;

                    System.out.print("Nombre maximum de clients: ");
                    while (true) {
                        if (scanner.hasNextInt()) {
                            maxClients = scanner.nextInt();
                            if (maxClients >= 0) break;
                            else System.out.print("Veuillez entrer un nombre positif: ");
                        } else {
                            System.out.print("Veuillez entrer un nombre valide pour le nombre de clients: ");
                            scanner.next();
                        }
                    }
                    scanner.nextLine();

                    System.out.print("Nombre maximum d'artistes: ");
                    while (true) {
                        if (scanner.hasNextInt()) {
                            maxArtistes = scanner.nextInt();
                            if (maxArtistes >= 0) break;
                            else System.out.print("Veuillez entrer un nombre positif: ");
                        } else {
                            System.out.print("Veuillez entrer un nombre valide pour le nombre d'artistes: ");
                            scanner.next();
                        }
                    }

                    gestionDeSpectacle.ajouteSpectacle(new Spectacle(nomSpectacle, typeSpectacle, maxClients, maxArtistes));
                    System.out.println("Spectacle ajouté avec succès !");
                    break;

                case 2:
                    System.out.print("Nom du spectacle: ");
                    String spectacleNom = scanner.nextLine();
                    Spectacle selectedSpectacle = findSpectacleByName(gestionDeSpectacle, spectacleNom);

                    if (selectedSpectacle != null) {
                        System.out.print("Nom du lieu: ");
                        String lieuNom = scanner.nextLine();
                        System.out.print("Adresse du lieu: ");
                        String adresseLieu = scanner.nextLine();
                        System.out.print("Capacité du lieu: ");
                        int capaciteLieu = scanner.nextInt();
                        scanner.nextLine();

                        int annee, mois, jour;
                        System.out.println("Date (ex: 2024-12-20): ");
                        do {
                            System.out.print("L'année : ");
                            annee = scanner.nextInt();
                        } while (annee < 2024 || annee > 2025);

                        do {
                            System.out.print("Le mois : ");
                            mois = scanner.nextInt();
                        } while (mois < 1 || mois > 12);

                        do {
                            System.out.print("Le jour : ");
                            jour = scanner.nextInt();
                        } while (jour < 1 || jour > 31);

                        try {
                            LocalDate date = LocalDate.of(annee, mois, jour);
                            gestionDeSpectacle.ajouteLieuEtDatePourSpectacle(selectedSpectacle, new Lieu(lieuNom, adresseLieu, capaciteLieu), date);
                            System.out.println("Lieu et date ajoutés !");
                        } catch (Exception e) {
                            System.out.println("Date invalide.");
                        }
                    } else {
                        System.out.println("Spectacle introuvable.");
                    }
                    break;

                case 3:
                    System.out.print("Nom de l'artiste: ");
                    String artisteNom = scanner.nextLine();
                    System.out.print("Prénom de l'artiste: ");
                    String artistePrenom = scanner.nextLine();
                    System.out.print("Email de l'artiste: ");
                    String artisteEmail = scanner.nextLine();
                    System.out.print("Âge de l'artiste: ");
                    int artisteAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Rôle de l'artiste: ");
                    String artisteRole = scanner.nextLine();

                    Artiste artiste = new Artiste(artisteNom, artistePrenom, artisteEmail, artisteAge, artisteRole);

                    String addMore = "oui";
                    while (addMore.equalsIgnoreCase("oui")) {
                        System.out.print("Nom du spectacle pour ajouter l'artiste: ");
                        String spectacleNomForArtist = scanner.nextLine();

                        Spectacle spectacleForArtist = findSpectacleByName(gestionDeSpectacle, spectacleNomForArtist);

                        if (spectacleForArtist != null) {
                            spectacleForArtist.ajouteArtiste(artiste);
                            System.out.println("Artiste ajouté au spectacle " + spectacleForArtist.getNom());
                        } else {
                            System.out.println("Spectacle introuvable.");
                        }

                        System.out.print("Ajouter cet artiste à un autre spectacle ? (oui/non): ");
                        addMore = scanner.nextLine();
                    }
                    break;

                case 4:
                    System.out.print("Nom du client: ");
                    String clientNom = scanner.nextLine();
                    System.out.print("Prénom du client: ");
                    String clientPrenom = scanner.nextLine();
                    System.out.print("Email du client: ");
                    String clientEmail = scanner.nextLine();
                    System.out.print("Âge du client: ");
                    int clientAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre maximum de réservations: ");
                    int maxReservations = scanner.nextInt();
                    scanner.nextLine();

                    Client client = new Client(clientNom, clientPrenom, clientEmail, clientAge, maxReservations);

                    String addMore1 = "oui";
                    while (addMore1.equalsIgnoreCase("oui")) {
                        System.out.print("Nom du spectacle pour ajouter le client: ");
                        String spectacleNomForClient = scanner.nextLine();

                        Spectacle spectacleForClient = findSpectacleByName(gestionDeSpectacle, spectacleNomForClient);

                        if (spectacleForClient != null) {
                            spectacleForClient.ajouteClient(client);
                            System.out.println("Client ajouté au spectacle " + spectacleForClient.getNom());
                        } else {
                            System.out.println("Spectacle introuvable.");
                        }

                        System.out.print("Ajouter ce client à un autre spectacle ? (oui/non): ");
                        addMore = scanner.nextLine();
                    }
                    break;
                case 5:
                    System.out.print("Nom du spectacle pour la réservation: ");
                    String spectacleNomForReservation = scanner.nextLine();
                    Spectacle spectacleForReservation = findSpectacleByName(gestionDeSpectacle, spectacleNomForReservation);

                    if (spectacleForReservation != null) {
                        System.out.print("Nom du client: ");
                        String clientNomForReservation = scanner.nextLine();
                        Client clientForReservation = findClientByName(spectacleForReservation, clientNomForReservation);

                        if (clientForReservation != null) {
                            System.out.print("Prix de la réservation: ");
                            double prixReservation = scanner.nextDouble();
                            scanner.nextLine();

                            // Créer la réservation
                            Reservation reservation = new Reservation(
                                    spectacleForReservation.getReservations().length + 1,
                                    prixReservation,
                                    spectacleForReservation,
                                    clientForReservation,
                                    true
                            );

                            clientForReservation.ajouteReservation(reservation);
                            System.out.println("Réservation ajoutée avec succès pour le client " + clientForReservation.getNom());
                        } else {
                            System.out.println("Client introuvable dans ce spectacle.");
                        }
                    } else {
                        System.out.println("Spectacle introuvable.");
                    }
                    break;
                case 6:
                    System.out.print("Nom du spectacle: ");
                    String spectacleNomForDeleteArtist = scanner.nextLine();
                    Spectacle spectacleForDeleteArtist = findSpectacleByName(gestionDeSpectacle, spectacleNomForDeleteArtist);

                    if (spectacleForDeleteArtist != null) {
                        System.out.print("Nom de l'artiste à supprimer: ");
                        String artisteNomToDelete = scanner.nextLine();

                        Artiste artisteToDelete = findArtisteByName(spectacleForDeleteArtist, artisteNomToDelete);

                        if (artisteToDelete != null) {
                            spectacleForDeleteArtist.supprimeArtiste(artisteToDelete);
                            System.out.println("Artiste supprimé !");
                        } else {
                            System.out.println("Artiste introuvable.");
                        }
                    } else {
                        System.out.println("Spectacle introuvable.");
                    }
                    break;



                case 7:
                    System.out.print("Nom du spectacle: ");
                    String spectacleNomForDeleteClient = scanner.nextLine();
                    Spectacle spectacleForDeleteClient = findSpectacleByName(gestionDeSpectacle, spectacleNomForDeleteClient);

                    if (spectacleForDeleteClient != null) {
                        System.out.print("Nom du client à supprimer: ");
                        String clientNomToDelete = scanner.nextLine();

                        Client clientToDelete = findClientByName(spectacleForDeleteClient, clientNomToDelete);

                        if (clientToDelete != null) {
                            spectacleForDeleteClient.supprimeClient(clientToDelete);
                            System.out.println("Client supprimé !");
                        } else {
                            System.out.println("Client introuvable.");
                        }
                    } else {
                        System.out.println("Spectacle introuvable.");
                    }
                    break;
                case 8:
                    System.out.print("Nom du spectacle à supprimer: ");
                    String spectacleToDelete = scanner.nextLine();
                    Spectacle selectedSpectacleToDelete = findSpectacleByName(gestionDeSpectacle, spectacleToDelete);

                    if (selectedSpectacleToDelete != null) {
                        gestionDeSpectacle.supprimeSpectacle(selectedSpectacleToDelete);
                        System.out.println("Spectacle supprimé !");
                    } else {
                        System.out.println("Spectacle introuvable.");
                    }
                    break;


                case 9:
                    gestionDeSpectacle.afficheCalendrier();
                    break;

                case 10:
                    System.out.println("--------Dashboard Spectacles--------");
                    if (gestionDeSpectacle.getSpectacles() == null || gestionDeSpectacle.getSpectacles().length == 0) {
                        System.out.println("Pas de spectacle déjà existant");
                    } else {
                        gestionDeSpectacle.afficheGes();
                    }
                    break;



                case 11: // Quitter
                    System.out.println("Au revoir !");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        }
    }

    public static Spectacle findSpectacleByName(GestionDeSpectacle gestionDeSpectacle, String name) {
        for (Spectacle spectacle : gestionDeSpectacle.getSpectacles()) {
            if (spectacle != null && spectacle.getNom().equalsIgnoreCase(name)) {
                return spectacle;
            }
        }
        return null;
    }

    public static Client findClientByName(Spectacle spectacle, String name) {
        for (Client client : spectacle.getClients()) {
            if (client != null && client.getNom().equalsIgnoreCase(name)) {
                return client;
            }
        }
        return null;
    }

    public static Artiste findArtisteByName(Spectacle spectacle, String name) {
        for (Artiste artiste : spectacle.getArtistes()) {
            if (artiste != null && artiste.getNom().equalsIgnoreCase(name)) {
                return artiste;
            }
        }
        return null;
    }
}
