package ObjectOriented;

public class ConnectFour {
    Player X, O;
    Board board;
    public ConnectFour() {
        X = new Player('X');
        O = new Player('O');
        board = new Board(new char[6][7]);
    }
    public static void main(String[] args) {

    }
}
