public class Reservation {
    private int id;
    private double prix;
    private Spectacle spectacle;
    private Client client;
    private boolean confirme = false;
    private boolean pay;

    public Reservation(int id, double prix, Spectacle spectacle, Client client, boolean pay) {
        this.id = id;
        this.prix = prix;
        this.spectacle = spectacle;
        this.client = client;
        this.confirme = confirme;
        this.pay = pay;
    }

    public Spectacle getSpectacle() {
        return spectacle;
    }

    public void estPaye() {
        this.pay = true;
    }

    public void confirmer() {
        if (this.pay) {
            this.confirme = true;
        }
    }

    public boolean estConfirme() {
        this.confirmer();
        return confirme;
    }

    public void afficheRes() {
        System.out.println("Reservation ID: " + id + ", Prix: " + prix + ", Confirmé: " + estConfirme());
    }
}
