package models;

import java.awt.*;

/**
 * StraightLine.java:
 * Creates a straight line tetronimo
 *
 * @author Professor Rossi and Amber Lee
 *
 * @see java.awt.Point
 */
public class StraightLine extends Tetronimo
{
    /**
     * Creates the tetronimo and puts it in the vertical orientation
     */
    public StraightLine()
    {
        super.r1.setLocation( 0, 0 );
        super.r2.setLocation( 0, Tetronimo.SIZE );
        super.r3.setLocation( 0, Tetronimo.SIZE * 2 );
        super.r4.setLocation( 0, Tetronimo.SIZE * 3 );

        super.r1.setColor(Color.CYAN);
        super.r2.setColor(Color.CYAN);
        super.r3.setColor(Color.CYAN);
        super.r4.setColor(Color.CYAN);

        super.r1.setFrameColor( Color.BLACK );
        super.r2.setFrameColor( Color.BLACK );
        super.r3.setFrameColor( Color.BLACK );
        super.r4.setFrameColor( Color.BLACK );

        super.add( r1 );
        super.add( r2 );
        super.add( r3 );
        super.add( r4 );
    }

    /**
     * Rotates the tetronimo
     */
    @Override
    public void rotate()
    {
        if (r1.getXLocation() <= Tetronimo.SIZE * 8)
        {
            super.rotate();

            Point curLoc = super.getLocation();
            super.setLocation(0, 0);

            if (super.curRotation % 2 == 0) //add && here to keep from rotating through wall?
            {
                super.r1.setLocation(0, 0);
                super.r2.setLocation(Tetronimo.SIZE, 0);
                super.r3.setLocation(Tetronimo.SIZE * 2, 0);
                super.r4.setLocation(Tetronimo.SIZE * 3, 0);
            } else {
                super.r1.setLocation(0, 0);
                super.r2.setLocation(0, Tetronimo.SIZE);
                super.r3.setLocation(0, Tetronimo.SIZE * 2);
                super.r4.setLocation(0, Tetronimo.SIZE * 3);
            }

            super.setLocation(curLoc);
        }
    }

    /**
     * Gets the height of the tetronimo based on the orientation
     *
     * @return The height of the tetronimo
     */
    @Override
    public int getHeight()
    {
        if( this.curRotation % 2 == 0 )
        {
            return Tetronimo.SIZE;
        }
        else
        {
            return Tetronimo.SIZE * 4;
        }
    }

    /**
     * Gets the width of the tetronimo based on the orientation
     *
     * @return The width of the tetronimo
     */
    @Override
    public int getWidth()
    {
        if( this.curRotation % 2 == 0 )
        {
            return Tetronimo.SIZE * 4;
        }
        else
        {
            return Tetronimo.SIZE;
        }
    }

    /**
     * Gets the color of this tetronimo
     *
     * @return the color of the tetronimo
     */
    @Override
    public Color getColorOfBlock()
    {
        return Color.CYAN;
    }


}
