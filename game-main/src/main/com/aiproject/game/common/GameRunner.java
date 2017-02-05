package com.aiproject.game.common;

import com.aiproject.game.common.board.GameBoard;
import com.aiproject.game.common.pieces.Swordmaster;
import com.aiproject.game.common.player.Player;

/**
 * Created by cliffycheng on 12/31/2016.
 */
public class GameRunner
{
    public static void main(String[] args)
    {
        GameBoard board = new GameBoard(2,2);
        Player p1 = new Player();
        Player p2 = new Player();

        p1.addPiece(new Swordmaster());
        p2.addPiece(new Swordmaster());

        board.placePiece(p1.getOwnedPieces().get(0), 0, 0);
        board.placePiece(p2.getOwnedPieces().get(0), 1, 1);


    }
}
