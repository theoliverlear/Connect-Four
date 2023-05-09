package ObjectOriented;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnectFourTest {
    ConnectFour connectFourTest = new ConnectFour();
    @Test
    public void testInitPlayerPiece() {
        assertThat('X', equalTo(connectFourTest.getPlayerX().getPlayerPiece()));
        assertThat('O', equalTo(connectFourTest.getPlayerO().getPlayerPiece()));
    }
    @Test
    public void testInitCurrentPlayer() {
        assertThat(connectFourTest.getPlayerX(), equalTo(connectFourTest.getCurrentPlayer()));
    }
    @Test
    public void testGetCharAt() {
        connectFourTest.getBoard().resetBoard();
        assertThat(' ', equalTo(connectFourTest.getBoard().getCharAt(0, 0)));
    }
    @Test
    public void testInitBoard() {
        connectFourTest.getBoard().resetBoard();
        for (int row = 0; row < connectFourTest.getBoard().getRowSize(); row++) {
            for (int col = 0 ; col < connectFourTest.getBoard().getColSize(); col++) {
                assertThat(' ', equalTo(connectFourTest.getBoard().getCharAt(row, col)));
            }
        }
    }
    @Test
    public void testIsWinner() {
        connectFourTest.getBoard().resetBoard();
        for (int row = 0; row < 4; row++) {
            connectFourTest.getBoard().setBoardSpot(row, 0, connectFourTest.getCurrentPlayer().getPlayerPiece());
        }
        assertThat(connectFourTest.isWinner(), equalTo('X'));
        connectFourTest.getBoard().resetBoard();
        for (int col = 0; col < 4; col++) {
            connectFourTest.getBoard().setBoardSpot(0, col, connectFourTest.getCurrentPlayer().getPlayerPiece());
        }
        assertThat(connectFourTest.isWinner(), equalTo('X'));
        connectFourTest.getBoard().resetBoard();
        for (int duo = 0; duo < 4; duo++) {
            connectFourTest.getBoard().setBoardSpot(duo, duo, connectFourTest.getCurrentPlayer().getPlayerPiece());
        }
        assertThat(connectFourTest.isWinner(), equalTo('X'));
        connectFourTest.getBoard().resetBoard();
        for (int cycle = 0; cycle < 4; cycle++) {
            connectFourTest.getBoard().setBoardSpot(connectFourTest.getBoard().getRowSize() - 1 - cycle, connectFourTest.getBoard().getColSize() - 1 - cycle, connectFourTest.getCurrentPlayer().getPlayerPiece());
        }
        assertThat(connectFourTest.isWinner(), equalTo('X'));
    }
}
