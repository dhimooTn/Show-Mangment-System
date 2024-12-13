public class GestionDeSpectacle {
    private Spectacle[] spectacles;
    private Lieu[][] lieux;
    private String[][] dates;

    public GestionDeSpectacle(int maxSpectacles, int maxLieuxParSpectacle) {
        this.spectacles = new Spectacle[maxSpectacles];
        this.lieux = new Lieu[maxSpectacles][maxLieuxParSpectacle];
        this.dates = new String[maxSpectacles][maxLieuxParSpectacle];
    }

    public void ajouteSpectacle(Spectacle spectacle) {
        for (int i = 0; i < spectacles.length; i++) {
            if (spectacles[i] == null) {
                spectacles[i] = spectacle;
                return;
            }
        }
        System.out.println("Impossible d'ajouter un nouveau spectacle. Tableau plein.");
    }

    public void ajouteLieuEtDatePourSpectacle(Spectacle spectacle, Lieu lieu, String date) {
        if (lieu == null || date == null || date.isEmpty()) {
            System.out.println("Lieu ou date invalide.");
            return;
        }

        for (int i = 0; i < spectacles.length; i++) {
            if (spectacles[i] == spectacle) {
                for (int j = 0; j < lieux[i].length; j++) {
                    if (lieux[i][j] == null) {
                        lieux[i][j] = lieu;
                        dates[i][j] = date;
                        return;
                    }
                }
                System.out.println("Impossible d'ajouter un nouveau lieu pour ce spectacle. Tableau plein.");
                return;
            }
        }
        System.out.println("Spectacle non trouvé.");
    }

    public void afficheGes() {
        for (int i = 0; i < spectacles.length; i++) {
            if (spectacles[i] != null) {
                System.out.println("Spectacle: " + spectacles[i].getNom());
                for (int j = 0; j < lieux[i].length; j++) {
                    if (lieux[i][j] != null && dates[i][j] != null) {
                        System.out.println("  Lieu: " + lieux[i][j].getNom() + ", Date: " + dates[i][j]);
                    }
                }
            }
        }
    }
}
