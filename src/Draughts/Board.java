package Draughts;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class Board {

    GridPane root;
    int width, height;
    final int size = 10;
    ArrayList<Cell> fields = new ArrayList<>();

    //colors of the chessboard
    String color1 = "#fffff0";
    String color2 = "#51a13b";
    String selectedColor = "#c4bc25";

    Board(int width, int height){
        root = new GridPane();

        this.width = width; this.height = height;

        for(int row = size-1; row >= 0; row--){
            for(int col = 0; col < size; col++){
                StackPane square = new StackPane();

                String color;
                if((row+col)%2 == 0)
                    color = color1;
                else
                    color = color2;

                square.setStyle("-fx-background-color: "+color+";");
                root.add(square,col,row);
                fields.add(new Cell(col, size - row - 1, square, color));
            }
        }

        for (int i = 0; i < size; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(5, width/size, width/size, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5, height/size, height/size, Priority.ALWAYS, VPos.CENTER, true));
        }


        addPieces();
        repaintPieces();
    }

    void addPieces(){
        int pieceSize = (int)(0.85*(width/size));

        for(int i = 0; i < 10; i=i+2){
            fields.get(i).piece = new Piece(true,"Resources\\white_piece.png", pieceSize);
            fields.get(i+size+1).piece = new Piece(true,"Resources\\white_piece.png", pieceSize);
            fields.get(i+2*size).piece = new Piece(true,"Resources\\white_piece.png", pieceSize);
            fields.get(i+3*size+1).piece = new Piece(true,"Resources\\white_piece.png", pieceSize);
        }

        for(int i = size*size-1; i > size*size - 10; i=i-2){
            fields.get(i).piece = new Piece(false,"Resources\\black_piece.png", pieceSize);
            fields.get(i-size-1).piece = new Piece(false,"Resources\\black_piece.png", pieceSize);
            fields.get(i-2*size).piece = new Piece(false,"Resources\\black_piece.png", pieceSize);
            fields.get(i-3*size-1).piece = new Piece(false,"Resources\\black_piece.png", pieceSize);
        }
    }

    void repaintPieces(){
        for(Cell c : fields){
            if(c.piece != null){
                root.getChildren().remove(c.piece.imageView);
                root.add(c.piece.imageView,c.getX(),size - c.getY() - 1);
            }
        }
    }

    public void capturePiece(Cell c){
        root.getChildren().remove(c.getPiece().imageView);
        c.piece = null;
    }

    public void executeMove(Move move){
        move.end.piece = move.start.piece;
        move.start.piece = null;
        move.start.setDefaultColor();

        if(move.attackedCell != null)
            capturePiece(move.attackedCell);
    }

    public int getColumn(int x){
        return x/(width/size);
    }

    public int getRow(int y){
        return size - 1 - y/(height/size);
    }

    public Cell getField(int x, int y){
        return fields.get(y*size+x);
    }

    public int getSize() {
        return size;
    }
}
