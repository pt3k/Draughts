package Draughts;

import javafx.scene.layout.StackPane;

import java.util.Objects;

public class Cell {
    int x, y;
    Piece piece;
    StackPane square;
    String color;

    Cell(int x, int y, StackPane square, String color){
        this.x = x;
        this.y = y;
        this.square = square;
        this.color = color;
    }

    public void setColor(String color) {
        square.setStyle("-fx-background-color: "+color+";");
    }

    public void setDefaultColor(){
        square.setStyle("-fx-background-color: "+color+";");
    }

    public Piece getPiece() {
        return piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y &&
                Objects.equals(piece, cell.piece) &&
                Objects.equals(square, cell.square) &&
                Objects.equals(color, cell.color);
    }
}
