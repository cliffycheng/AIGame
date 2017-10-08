package com.aiproject.game.common.board;

import com.aiproject.game.common.board.exceptions.BoardStateException;
import com.aiproject.game.common.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cliffycheng on 2/4/2017.
 */
public class PieceMap
{
    Map<Integer, Map<Integer, Piece>> map;

    int xLimit;
    int yLimit;

    /**
     * Constructor
     *
     * Creates nested hashmaps with default capacity appropriate to the sizes
     * given by xLimit and yLimit
     *
     * Throws RuntimeException if given limits are bad, as there's no way to proceed
     * if a bad board is created
     * @param xLimit
     * @param yLimit
     */
    public PieceMap(int xLimit, int yLimit)
    {
        if (xLimit <= 0 || yLimit <= 0)
        {
            throw new RuntimeException("Bad board limits: x:" +xLimit + ", y:" + yLimit);
        }

        map = new HashMap<Integer, Map<Integer, Piece>>(xLimit);
        for (int i = 0; i < yLimit; i++)
        {
            map.put(i, new HashMap<Integer, Piece>(yLimit));
        }

        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    /**
     * Puts given piece into the map that represents the board.
     * If the board already has a piece at the given x y coordinate,
     * returns false
     * @param piece
     * @param x x coordinate. Must be greater than/equal zero and less than xLimit
     * @param y y coordinate. Must be greater than/equal zero and less than ylimit
     * @return true if successful put, false otherwise
     */
    public boolean put(Piece piece, int x, int y) throws BoardStateException
    {
        if (x < 0 || x >= xLimit || y < 0 || y >= yLimit)
        {
            throw new BoardStateException("Coordinates are out of bounds: x=" + x + ", y=" + y
                + " xLimit: " + xLimit + ", yLimit: " + yLimit);
        }

        if (map.get(x) == null)
        {
            map.put(x, new HashMap<Integer, Piece>(yLimit));
        }

        if (map.get(x).get(y) == null)
        {
            map.get(x).put(y, piece);
        }
        else
        {
            return false;
        }

        return true;
    }

    /**
     * Get the piece at the given coordinates
     * @param x
     * @param y
     * @return the piece at coordinate x and y. If no piece exists, return null
     */
    public Piece get(int x, int y)
    {
        if (map.get(x) == null)
        {
            return null;
        }
        return map.get(x).get(y);
    }

    /**
     * Get the piece at the given Coordinate. If no piece exists at the given
     * coordinate, return null.
     * @param coor coordinates of piece to retrieve
     * @return
     */
    public Piece get(Coordinate coor)
    {
        return get(coor.getX(), coor.getY());
    }

    /**
     * Swaps pieces at position (x1, y1) with the position (x2, y2).
     * Will change board state if piece does not already exist at that place.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void movePiece(int x1, int y1, int x2, int y2) throws BoardStateException
    {
        Piece temp = get(x1, y1);
        remove(x1, y1);
        put(temp, x2, y2);
    }

    public void remove(int x, int y) throws BoardStateException
    {
        if (x < 0 || x >= xLimit || y < 0 || y >= yLimit)
        {
            throw new BoardStateException("Coordinates are out of bounds: x=" + x + ", y=" + y
                    + " xLimit: " + xLimit + ", yLimit: " + yLimit);
        }

        map.get(x).put(y, null);
    }

}
