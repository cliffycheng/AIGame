package com.aiproject.game.common.player;

import com.aiproject.game.common.pieces.Piece;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cliffycheng on 2/4/2017.
 */
@Data
public class Player
{
    List<Piece> ownedPieces;

    public Player()
    {
        ownedPieces = new ArrayList<>();
    }

    public void addPiece(Piece p)
    {
        ownedPieces.add(p);
    }

    public void resetPieces()
    {

    }

}
