package com.aiproject.game.common.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by cliffycheng on 3/18/2017.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Coordinate implements Comparable<Coordinate>
{
    int x;
    int y;

    public Coordinate up()
    {
        return new Coordinate(x - 1, y);
    }

    public Coordinate down()
    {
        return new Coordinate(x + 1, y);
    }

    public Coordinate left()
    {
        return new Coordinate(x, y - 1 );
    }

    public Coordinate right()
    {
        return new Coordinate(x, y + 1);
    }

    @Override
    public int compareTo(Coordinate other)
    {
        return x == other.getX() ? y - other.getY() : x - other.getX();
    }
}
