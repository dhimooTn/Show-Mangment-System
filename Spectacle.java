public class Spectacle {
    private String nom;
    private String type;
    private Artiste[] artistes;
    private Client[] clients;
    private Reservation[] reservations;
    private int nbArtistes;
    private int nbClients;



    public Spectacle(String nom, String type, int maxArtistes, int maxClients) {
        this.nom = nom;
        this.type = type;
        this.artistes = new Artiste[maxArtistes];
        this.clients = new Client[maxClients];
        this.reservations = new Reservation[maxClients];
        this.nbArtistes = 0;
        this.nbClients = 0;
    }
    public void ajouteArtiste(Artiste artiste) {
        if (nbArtistes < artistes.length) {
            artistes[nbArtistes] = artiste;
            artiste.ajouteSpectacle(this); // Synchronisation avec l'artiste
            nbArtistes++;
        } else {
            System.out.println("Impossible d'ajouter un artiste. Tableau plein.");
        }
    }
    public void supprimeArtiste(Artiste artiste) {
        for (int i = 0; i < nbArtistes; i++) {
            if (artistes[i] == artiste) {
                artistes[i].supprimeSpectacle(this); // Synchronisation avec l'artiste
                // Shift elements left
                for (int j = i; j < nbArtistes - 1; j++) {
                    artistes[j] = artistes[j + 1];
                }
                artistes[nbArtistes - 1] = null; // Remove last reference
                nbArtistes--;
                return;
            }
        }
        System.out.println("Artiste non trouvé.");
    }
    public void ajouteClient(Client client) {
        if (nbClients < clients.length) {
            clients[nbClients] = client;
            reservations[nbClients] = client.getRes(this);
            nbClients++;
        } else {
            System.out.println("Impossible d'ajouter un client. Tableau plein.");
        }
    }
    public void supprimeClient(Client client) {
        for (int i = 0; i < nbClients; i++) {
            if (clients[i] != null && clients[i].equals(client)) {
                clients[i] = null;
                reservations[i] = null;
                for (int j = i; j < nbClients - 1; j++) {
                    clients[j] = clients[j + 1];
                    reservations[j] = reservations[j + 1];
                }
                clients[nbClients - 1] = null;
                reservations[nbClients - 1] = null;
                nbClients--;
                System.out.println("Client supprimé avec succès.");
                return;
            }
        }
        System.out.println("Client introuvable. Impossible de le supprimer.");
    }


    public void affiche() {
        System.out.println("Spectacle: " + nom + ", Type: " + type);

        System.out.println("Liste des artistes :");
        for (int i = 0; i < nbArtistes; i++) {
            if (artistes[i] != null) {
                artistes[i].afficheDetails();
            }
        }

        System.out.println("Liste des clients :");
        for (int i = 0; i < nbClients; i++) {
             {
                clients[i].afficheDetails();
            }
        }
    }
    public String getNom() {
        return this.nom;
    }

    public Reservation[] getReservations() {
        return reservations;
    }

    public String getType() {
        return this.type;
    }
    public Artiste[] getArtistes() {
        return artistes;
    }
    public Client[] getClients() {
        return clients;
    }
}
