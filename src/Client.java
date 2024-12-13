public class Client extends Personne {
    private Reservation[] res;

    public Client(String nom, String prenom, String email, int age, int maxRes) {
        super(nom, prenom, email, age);
        this.res = new Reservation[maxRes];
    }

    @Override
    public void afficheDetails() {
        System.out.println("Nom : " + super.getNom());
        System.out.println("Prénom : " + super.getPrenom());
        System.out.println("Email : " + super.getEmail());
        System.out.println("Age : " + super.getAge());
        System.out.println("Réservations : ");
        for (Reservation r : res) {
            if (r != null) {
                r.afficheRes();
            }
        }
    }

    public void ajouteReservation(Reservation reservation) {
        for (int i = 0; i < res.length; i++) {
            if (res[i] == null) {
                res[i] = reservation;
                return;
            }
        }
        System.out.println("Impossible d'ajouter une réservation. Tableau plein.");
    }

    public void supprimeReservation(Reservation reservation) {
        for (int i = 0; i < res.length; i++) {
            if (res[i] != null && res[i].equals(reservation)) {
                res[i] = null;
                System.out.println("Réservation supprimée avec succès.");
                return;
            }
        }
        System.out.println("Réservation introuvable.");
    }
    public Reservation getRes(Spectacle s) {
        for (Reservation i : res) {
            if (i != null && s.getNom().equals(i.getSpectacle().getNom())) {
                return i;
            }
        }
        return null;
    }
}
