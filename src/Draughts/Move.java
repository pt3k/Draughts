package Draughts;

import Draughts.Players.Player;

import java.util.Objects;

public class Move {
    Player player;
    Cell start;
    Cell end;
    Cell attackedCell;
    Piece pieceMoved;

    public Move(Player player, Cell start, Cell end, Cell attackedCell){
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
        this.attackedCell = attackedCell;
    }

    @Override
    public String toString() {
        return "Move{" +
                "player=" + player +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Move move = (Move) o;
        return Objects.equals(player, move.player) &&
                Objects.equals(start, move.start) &&
                Objects.equals(end, move.end) &&
                Objects.equals(pieceMoved, move.pieceMoved);
    }

}
