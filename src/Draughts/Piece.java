package Draughts;

import Draughts.Players.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Objects;

public class Piece {

    boolean isWhite;
    public ImageView imageView;

    public Piece(boolean isWhite, String imgName, int imgSize){
        this.isWhite = isWhite;

        try {
            FileInputStream inputStream = new FileInputStream(imgName);
            Image image = new Image(inputStream);
            imageView = new ImageView(image);
            imageView.setFitHeight(imgSize);
            imageView.setFitWidth(imgSize);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Move> availableMoves(Board board, Player player, Cell start) {

        ArrayList<Move> moves = new ArrayList<>();

        int direction = start.getPiece().isWhite ? 1 : -1;
        Piece piece = start.getPiece();
        Cell target;

        //check for possible attack
        for(int i = -1; i <= 1; i+=2){
            for(int j = -1; j <= 1; j+=2){

                try {
                    target = board.getField(start.getX()+i,start.getY()+j*direction);
                } catch (Exception e){
                    continue;
                }


                if(target.piece != null
                        && (target.piece.isWhite != piece.isWhite)
                        && (0 <= target.getX() && target.getX() < board.getSize())
                        && (0 <= target.getY() && target.getY() < board.getSize()) ){

                    Cell attackedCell = target;
                    target = board.getField(start.getX()+2*i,start.getY()+j*2*direction);

                    if(target.getPiece() == null)
                        moves.add(new Move(player, start, target, attackedCell));
                }
            }
        }

        //if attack is possible player must do it
        if(!moves.isEmpty()){
            return moves;
        }

        //normal moves
        for(int i = -1; i <= 1; i+=2){
            target = board.getField(start.getX()+i,start.getY()+direction);
            if(target.piece == null
                    && (0 <= target.getX() && target.getX() < board.getSize())
                    && (0 <= target.getY() && target.getY() < board.getSize()) ){
                moves.add(new Move(player, start, target, null));
            }
        }

        return moves;
    }

    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "isWhite=" + isWhite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return isWhite == piece.isWhite &&
                Objects.equals(imageView, piece.imageView);
    }
}
