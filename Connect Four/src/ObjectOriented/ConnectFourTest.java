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
}
