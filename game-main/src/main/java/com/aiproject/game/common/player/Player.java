package com.aiproject.game.common.player;

import com.aiproject.game.common.pieces.Piece;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by cliffycheng on 2/4/2017.
 */
@Getter
public class Player
{
    List<Piece> ownedPieces;
    String id;

    public Player()
    {
        ownedPieces = new ArrayList<>();
        id = UUID.randomUUID().toString();
    }

    public void addPiece(Piece p)
    {
        ownedPieces.add(p);
    }

    public void resetPieces()
    {

    }

}
