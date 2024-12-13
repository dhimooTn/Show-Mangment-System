public class Lieu {
    private String nom;
    private String adresse;
    private int capacite;

    public Lieu(String nom, String adresse, int capacite) {
        this.nom = nom;
        this.adresse = adresse;
        if (capacite >= 0) {
            this.capacite = capacite;
        } else {
            System.out.println("Capacité invalide. Initialisation à 0.");
            this.capacite = 0;
        }
    }

    public void afficheLieu() {
        System.out.println("Lieu: " + nom + ", Adresse: " + adresse + ", Capacité: " + capacite);
    }

    public String getNom() {
        return this.nom;
    }
}
