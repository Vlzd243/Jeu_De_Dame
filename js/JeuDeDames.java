import javax.swing.SwingUtilities;

public class JeuDeDames {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Jeu jeu = new Jeu();
            InterfaceGraphique ig = new InterfaceGraphique(jeu);
            ig.afficher();
        });
    }
}