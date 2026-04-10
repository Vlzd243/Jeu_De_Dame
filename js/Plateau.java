public class Plateau {
    public static final int TAILLE = 10;
    private Case[][] grille;

    public Plateau() {
        grille = new Case[TAILLE][TAILLE];
        initialiserPlateau();
    }

    private void initialiserPlateau() {
        for (int l = 0; l < TAILLE; l++) {
            for (int c = 0; c < TAILLE; c++) {
                grille[l][c] = new Case();
                // Les pièces se placent uniquement sur les cases sombres
                if ((l + c) % 2 != 0) {
                    if (l < 4) {
                        grille[l][c].setPiece(new Pion(false)); // Pions noirs en haut
                    } else if (l > 5) {
                        grille[l][c].setPiece(new Pion(true)); // Pions blancs en bas
                    }
                }
            }
        }
    }

    public Case getCase(int l, int c) {
        if (l >= 0 && l < TAILLE && c >= 0 && c < TAILLE) {
            return grille[l][c];
        }
        return null;
    }
}