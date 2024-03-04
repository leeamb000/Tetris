package models;

import java.awt.*;

/**
 * LBlock.java:
 * Creates an L shaped tetronimo
 *
 * @author Amber Lee
 *
 * @see java.awt.Point
 */
public class LBlock extends Tetronimo
{
    /**
     * Creates the tetronimo and puts it in the vertical orientation
     */
    public LBlock()
    {
        super.r1.setLocation( 0, Tetronimo.SIZE);
        super.r2.setLocation( 0, Tetronimo.SIZE * 2);
        super.r3.setLocation( 0, Tetronimo.SIZE * 3 );
        super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE * 3 );

        super.r1.setColor(Color.ORANGE);
        super.r2.setColor(Color.ORANGE);
        super.r3.setColor(Color.ORANGE);
        super.r4.setColor(Color.ORANGE);

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
        if (r1.getXLocation() <= Tetronimo.SIZE * 9)
        {
            super.rotate();

            Point curLoc = super.getLocation();
            super.setLocation(0, 0);

            if (super.curRotation % 2 == 0) {
                super.r1.setLocation(0, Tetronimo.SIZE);
                super.r2.setLocation(Tetronimo.SIZE, Tetronimo.SIZE);
                super.r3.setLocation(Tetronimo.SIZE * 2, Tetronimo.SIZE);
                super.r4.setLocation(Tetronimo.SIZE * 2, 0);
            } else {
                super.r1.setLocation(0, Tetronimo.SIZE);
                super.r2.setLocation(0, Tetronimo.SIZE * 2);
                super.r3.setLocation(0, Tetronimo.SIZE * 3);
                super.r4.setLocation(Tetronimo.SIZE, Tetronimo.SIZE * 3);
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
            return Tetronimo.SIZE * 2;
        }
        else
        {
            return Tetronimo.SIZE * 3;
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
            return Tetronimo.SIZE * 3;
        }
        else
        {
            return Tetronimo.SIZE * 2;
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
        return Color.ORANGE;
    }

    /**
     * Gets y location depending on rotation of tetronimo
     *
     * @return y location for lowest part of tetronimo
     */
    @Override
    public int getYLocationFall() //put in other block classes
    {
        if (this.curRotation % 2 != 0 && this.curRotation != 1)
        {
            return super.getYLocation() + Tetronimo.SIZE;
        }
        else
            return super.getYLocation();
    }
}
