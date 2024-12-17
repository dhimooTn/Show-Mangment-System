import java.util.TreeMap;
import java.time.LocalDate;

public class GestionDeSpectacle {
    private Spectacle[] spectacles;
    private Lieu[][] lieux;
    private LocalDate[][] dates;

    public GestionDeSpectacle(int maxSpectacles, int maxLieuxParSpectacle) {
        this.spectacles = new Spectacle[maxSpectacles];
        this.lieux = new Lieu[maxSpectacles][maxLieuxParSpectacle];
        this.dates = new LocalDate[maxSpectacles][maxLieuxParSpectacle];
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

    public void ajouteLieuEtDatePourSpectacle(Spectacle spectacle, Lieu lieu, LocalDate date) {
        if (lieu == null || date == null) {
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

    public Spectacle[] getSpectacles() {
        return spectacles;
    }

    public void afficheGes() {
        for (int i = 0; i < spectacles.length; i++) {
            if (spectacles[i] != null) {
                spectacles[i].affiche();
                for (int j = 0; j < lieux[i].length; j++) {
                    if (lieux[i][j] != null && dates[i][j] != null) {
                        System.out.println("  Lieu: " + lieux[i][j].getNom() + ", Date: " + dates[i][j]);
                    }
                }
            }
        }
    }
    public void afficheCalendrier() {
        TreeMap<LocalDate, String> calendrier = new TreeMap<>();

        for (int i = 0; i < spectacles.length; i++) {
            if (spectacles[i] != null) {
                for (int j = 0; j < dates[i].length; j++) {
                    if (dates[i][j] != null) {
                        LocalDate date = dates[i][j];
                        String nomSpectacle = spectacles[i].getNom();
                        calendrier.put(date, calendrier.getOrDefault(date, "") + nomSpectacle + "\n");
                    }
                }
            }
        }

        System.out.println("------ Calendrier des Spectacles ------");
        if (calendrier.isEmpty()) {
            System.out.println("Aucun spectacle planifié.");
        } else {
            for (LocalDate date : calendrier.keySet()) {
                System.out.println("Date : " + date);
                System.out.println(calendrier.get(date));
            }
        }
    }


    public void supprimeSpectacle(Spectacle spectacle) {
        for (int i = 0; i < spectacles.length; i++) {
            if (spectacles[i] == spectacle) {
                for (int j = 0; j < lieux[i].length; j++) {
                    lieux[i][j] = null;
                    dates[i][j] = null;
                }
                spectacles[i] = null;
                System.out.println("Spectacle supprimé avec succès.");
                return;
            }
        }
        System.out.println("Spectacle non trouvé.");
    }


}
