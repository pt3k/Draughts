package Draughts;

import Draughts.Players.HumanPlayer;
import Draughts.Players.Player;

import java.util.ArrayList;

public class Game {

    Board board;
    int boardSize;
    Player whitePlayer, blackPlayer;
    Player currPlayer;
    Cell selectedCell;

    Game(int width, int height, int size){
        whitePlayer = new HumanPlayer(true);
        blackPlayer = new HumanPlayer(false);
        currPlayer = whitePlayer;
        board = new Board(width,height);
        boardSize = size;
    }

    public void manageClick(int x, int y){

        int clickedColumn = board.getColumn(x);
        int clickedRow = board.getRow(y);
        System.out.println(clickedColumn + " " + clickedRow);

        Cell clickedCell = board.fields.get(clickedRow*boardSize+clickedColumn);

        //getting all possible moves
        ArrayList<Move> allMoves = new ArrayList<>();
        for(Cell c : board.fields){
            if(c.getPiece() == null || c.getPiece().isWhite != currPlayer.isWhiteSide())
                continue;

            allMoves.addAll(c.getPiece().availableMoves(board,currPlayer,c));
        }

        //getting all attacking moves
        ArrayList<Cell> attackingMovesStarts = new ArrayList<>();
        for(Move m : allMoves){
            if(m.attackedCell != null)
                attackingMovesStarts.add(m.start);
        }
        System.out.println(attackingMovesStarts);

        //select piece
        if(selectedCell == null && clickedCell.piece != null
                && (currPlayer.isWhiteSide() == clickedCell.getPiece().isWhite())
                && (attackingMovesStarts.contains(clickedCell) || attackingMovesStarts.isEmpty())){
            selectedCell = clickedCell;
            clickedCell.setColor("red");
            return;
        }

        //return if no cell is selected
        if(selectedCell == null) {
            return;
        }

        //unselect cell
        if(clickedCell == selectedCell){
            selectedCell = null;
            clickedCell.setDefaultColor();
            return;
        }

        //making move
        Move selectedMove = new Move(currPlayer,selectedCell,clickedCell, null);
        ArrayList<Move> possibleMoves = selectedCell.getPiece().availableMoves(board,currPlayer,selectedCell);

        for (Move m : possibleMoves){
            if(selectedMove.equals(m)){

                board.executeMove(m);
                selectedCell = null;
                currPlayer = (currPlayer == whitePlayer) ? blackPlayer : whitePlayer;
            }
        }

        board.repaintPieces();
    }
}
