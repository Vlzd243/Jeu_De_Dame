public class Jeu {
    private Plateau plateau;
    private Joueur joueur1;
    private Joueur joueur2;
    private boolean tourBlanc;
    
    // Variables pour gérer la sélection dans l'IHM
    private int selL = -1;
    private int selC = -1;

    public Jeu() {
        plateau = new Plateau();
        joueur1 = new Joueur("Joueur 1 (Blancs)", true);
        joueur2 = new Joueur("Joueur 2 (Noirs)", false);
        tourBlanc = true; // Les blancs commencent
    }

    public Plateau getPlateau() { return plateau; }
    public int getSelL() { return selL; }
    public int getSelC() { return selC; }

    public void gererClicInteractif(int l, int c) {
        Case caseCliquee = plateau.getCase(l, c);
        if (caseCliquee == null) return;

        // 1. Sélectionner une pièce
        if (selL == -1 && selC == -1) {
            Piece p = caseCliquee.getPiece();
            if (p != null && p.isBlanc() == tourBlanc) {
                selL = l;
                selC = c;
            }
        } 
        // 2. Déplacer la pièce sélectionnée
        else {
            if (caseCliquee.estVide() && (l + c) % 2 != 0) {
                tenterDeplacement(l, c);
            } else {
                // Annuler la sélection si on clique ailleurs ou sur une case invalide
                selL = -1;
                selC = -1;
            }
        }
    }

    private void tenterDeplacement(int destL, int destC) {
        int dL = destL - selL;
        int dC = destC - selC;
        Piece p = plateau.getCase(selL, selC).getPiece();
        
        boolean deplacementValide = false;
        boolean capture = false;
        int caseCaptureL = -1, caseCaptureC = -1;

        if (!p.estDame()) {
            // Mouvement simple (1 case en diagonale en avant)
            if (Math.abs(dC) == 1) {
                if ((tourBlanc && dL == -1) || (!tourBlanc && dL == 1)) {
                    deplacementValide = true;
                }
            } 
            // Prise simple (Saut par-dessus une pièce adverse)
            else if (Math.abs(dC) == 2 && Math.abs(dL) == 2) {
                caseCaptureL = selL + dL / 2;
                caseCaptureC = selC + dC / 2;
                Piece pieceSautee = plateau.getCase(caseCaptureL, caseCaptureC).getPiece();
                
                if (pieceSautee != null && pieceSautee.isBlanc() != tourBlanc) {
                    deplacementValide = true;
                    capture = true;
                }
            }
        } else {
            // Déplacement très simplifié pour la Dame (diagonale libre)
            if (Math.abs(dL) == Math.abs(dC)) {
                deplacementValide = true;
            }
        }

        if (deplacementValide) {
            // Effectuer le mouvement
            plateau.getCase(destL, destC).setPiece(p);
            plateau.getCase(selL, selC).setPiece(null);
            
            if (capture) {
                plateau.getCase(caseCaptureL, caseCaptureC).setPiece(null);
            }

            // Promotion en Dame si la dernière ligne est atteinte
            if (!p.estDame()) {
                if ((tourBlanc && destL == 0) || (!tourBlanc && destL == Plateau.TAILLE - 1)) {
                    plateau.getCase(destL, destC).setPiece(new Dame(tourBlanc));
                }
            }

            // Changer de tour et réinitialiser la sélection
            tourBlanc = !tourBlanc;
            selL = -1;
            selC = -1;
        }
    }
}