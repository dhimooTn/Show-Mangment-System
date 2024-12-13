public class Artiste extends Personne {
    private String role;
    private Spectacle[] s;

    public Artiste(String nom, String prenom, String email, int age, String typeSpectacle) {
        super(nom, prenom, email, age);
        this.role = typeSpectacle;
        this.s = new Spectacle[10]; // Taille de tableau par défaut
    }

    @Override
    public void afficheDetails() {
        System.out.println("Nom : " + super.getNom());
        System.out.println("Prénom : " + super.getPrenom());
        System.out.println("Email : " + super.getEmail());
        System.out.println("Age : " + super.getAge());
        System.out.println("Rôle : " + this.role);
    }

    public void ajouteSpectacle(Spectacle spc) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] == null) {
                s[i] = spc;
                return;
            }
        }
    }

    public void supprimeSpectacle(Spectacle spc) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] == spc) {
                s[i] = null;
                return;
            }
        }
        System.out.println("Spectacle non trouvé.");
    }
}
