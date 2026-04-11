import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfaceGraphique extends JFrame {
    private Jeu jeu;
    private JPanel panneauPlateau;

    public InterfaceGraphique(Jeu jeu) {
        this.jeu = jeu;
        setTitle("Jeu de Dames");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // Centrer la fenêtre

        panneauPlateau = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dessinerPlateauEtPieces(g);
            }
        };

        panneauPlateau.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int tailleCase = panneauPlateau.getWidth() / Plateau.TAILLE;
                int c = e.getX() / tailleCase;
                int l = e.getY() / tailleCase;
                jeu.gererClicInteractif(l, c);
                panneauPlateau.repaint(); // Mettre à jour l'affichage après le clic
            }
        });

        add(panneauPlateau);
    }

    private void dessinerPlateauEtPieces(Graphics g) {
        int tailleCase = panneauPlateau.getWidth() / Plateau.TAILLE;

        for (int l = 0; l < Plateau.TAILLE; l++) {
            for (int c = 0; c < Plateau.TAILLE; c++) {
                // Dessin des cases claires et sombres
                if ((l + c) % 2 == 0) {
                    g.setColor(new Color(240, 217, 181)); // Beige
                } else {
                    g.setColor(new Color(181, 136, 99)); // Marron
                }
                g.fillRect(c * tailleCase, l * tailleCase, tailleCase, tailleCase);

                // Mise en surbrillance de la case sélectionnée
                if (l == jeu.getSelL() && c == jeu.getSelC()) {
                    g.setColor(Color.GREEN);
                    g.fillRect(c * tailleCase, l * tailleCase, tailleCase, tailleCase);
                }

                // Dessin des pièces
                Piece p = jeu.getPlateau().getCase(l, c).getPiece();
                if (p != null) {
                    if (p.isBlanc()) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    // Cercle pour le pion
                    g.fillOval(c * tailleCase + 5, l * tailleCase + 5, tailleCase - 10, tailleCase - 10);

                    // Indicateur visuel si c'est une Dame
                    if (p.estDame()) {
                        g.setColor(Color.RED);
                        g.fillOval(c * tailleCase + 20, l * tailleCase + 20, tailleCase - 40, tailleCase - 40);
                    }
                }
            }
        }
    }

    public void afficher() {
        setVisible(true);
    }
}