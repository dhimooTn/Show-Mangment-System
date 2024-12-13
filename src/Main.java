public class Main {
    public static void main(String[] args) {
        Artiste salah=new Artiste("salah","ben salah","salahbensalh@gmail.com",20,"musicien");
        Client monji=new Client("monji","ben monji","monjibenmonji@gmail.com",27,7);
        Spectacle harba=new Spectacle("harba","musique",2,2,1);
        Reservation r1=new Reservation(12,120,harba,monji,true);
        monji.ajouteReservation(r1);
        harba.ajouteClient(monji);
        harba.ajouteArtiste(salah);
        harba.affiche();
        Lieu carthage=new Lieu("carthage","carthage",10);
        Lieu bizert=new Lieu("bizert","bizert",10);
        GestionDeSpectacle ges=new GestionDeSpectacle(1,2);
        ges.ajouteSpectacle(harba);
        ges.ajouteLieuEtDatePourSpectacle(harba,carthage,"12/02/2004" );
        ges.ajouteLieuEtDatePourSpectacle(harba,bizert,"14/02/2004");
        ges.afficheGes();
        harba.supprimeClient(monji);
        harba.affiche();

    }
}
