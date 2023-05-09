package ObjectOriented;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    char[][] testBoardArray = new char[6][7];
    Board testBoard = new Board(testBoardArray);
    @Test
    public void testBoardSizes() {
        assertThat(6, equalTo(testBoard.getRowSize()));
        assertThat(7, equalTo(testBoard.getColSize()));
    }
    @Test
    public void testIsEmpty() {
        testBoard.resetBoard();
        assertThat(' ', equalTo(testBoard.getCharAt(0, 0)));
    }
    @Test
    public void getCharAtWhileTaken() {
        testBoard.resetBoard();
        testBoard.board[0][0] = 'X';
        assertThat('X', equalTo(testBoard.getCharAt(0, 0)));
    }
    @Test
    public void setBoardSpot() {
        testBoard.resetBoard();
        testBoard.setBoardSpot(0, 0, 'X');
        assertThat('X', equalTo(testBoard.getCharAt(0, 0)));
    }
}
