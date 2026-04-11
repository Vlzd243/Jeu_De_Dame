public class Dame extends Piece {
    public Dame(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estDame() {
        return true;
    }
}