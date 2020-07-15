package Draughts.Players;

import java.util.Objects;

public abstract class Player {
    boolean isWhiteSide;
    boolean humanPlayer;

    public boolean isWhiteSide(){
        return isWhiteSide;
    }

    public boolean isHumanPlayer() {
        return humanPlayer;
    }

    @Override
    public String toString() {
        return "Player{" +
                "isWhiteSide=" + isWhiteSide +
                ", humanPlayer=" + humanPlayer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return isWhiteSide == player.isWhiteSide &&
                humanPlayer == player.humanPlayer;
    }
}
