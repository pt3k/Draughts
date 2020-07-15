package Draughts.Players;

public class ComputerPlayer extends Player{

    public ComputerPlayer(boolean whiteSide){
        this.isWhiteSide = whiteSide;
        this.humanPlayer = false;
    }
}
