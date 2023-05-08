package ObjectOriented;
public class Player {
    char playerPiece;
    boolean isCurrentPlayer;
    String playerName;
    boolean isWinner;
    public Player(char playerPiece, boolean isCurrentPlayer, String playerName) {
        this.playerPiece = playerPiece;
        this.isCurrentPlayer = isCurrentPlayer;
        this.playerName = playerName;
        this.isWinner = false;
    }
    public char getPlayerPiece() {
        return this.playerPiece;
    }
    public boolean getIsCurrentPlayer() {
        return this.isCurrentPlayer;
    }
    public void setIsCurrentPlayer(boolean currentPlayer) {
        this.isCurrentPlayer = currentPlayer;
    }
    public String getPlayerName() {
        return this.playerName;
    }
    public boolean getIsWinner() {
        return this.isWinner;
    }
    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }
}
