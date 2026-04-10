public abstract class Piece {
    protected boolean estBlanc;

    public Piece(boolean estBlanc) {
        this.estBlanc = estBlanc;
    }

    public boolean isBlanc() {
        return estBlanc;
    }

    public abstract boolean estDame();
}