public class Joueur {
    private String nom;
    private boolean joueBlanc;

    public Joueur(String nom, boolean joueBlanc) {
        this.nom = nom;
        this.joueBlanc = joueBlanc;
    }

    public String getNom() { return nom; }
    public boolean isBlanc() { return joueBlanc; }
}