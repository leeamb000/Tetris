package models;

import java.awt.*;

/**
 * JBlock.java:
 * Creates a J shaped tetronimo
 *
 * @author Amber Lee
 *
 * @see java.awt.Point
 */
public class JBlock extends Tetronimo
{
    /**
     * Creates the tetronimo and puts it in the vertical orientation
     */
    public JBlock()
    {
        super.r1.setLocation( Tetronimo.SIZE, 0 );
        super.r2.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
        super.r3.setLocation( Tetronimo.SIZE, Tetronimo.SIZE * 2 );
        super.r4.setLocation( 0, Tetronimo.SIZE * 2 );

        super.r1.setColor(Color.blue);
        super.r2.setColor(Color.blue);
        super.r3.setColor(Color.blue);
        super.r4.setColor(Color.blue);

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
        if (r1.getXLocation() <= Tetronimo.SIZE * 9 && r1.getXLocation() > Tetronimo.SIZE * 2 )
        {
            super.rotate();

            Point curLoc = super.getLocation();
            super.setLocation(0, 0);

            if (super.curRotation % 2 == 0) {
                super.r1.setLocation(Tetronimo.SIZE, 0);
                super.r2.setLocation(Tetronimo.SIZE * 2, 0);
                super.r3.setLocation(Tetronimo.SIZE * 3, 0);
                super.r4.setLocation(Tetronimo.SIZE * 3, Tetronimo.SIZE);
            } else {
                super.r1.setLocation(Tetronimo.SIZE, 0);
                super.r2.setLocation(Tetronimo.SIZE, Tetronimo.SIZE);
                super.r3.setLocation(Tetronimo.SIZE, Tetronimo.SIZE * 2);
                super.r4.setLocation(0, Tetronimo.SIZE * 2);
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
        return Color.BLUE;
    }

    /**
     * Gets x location depending on rotation of tetronimo
     *
     * @return value for x location
     */
    @Override
    public int getXLocationFall()
    {
        if (this.curRotation % 2 == 0)
        {
            return super.getXLocation() + Tetronimo.SIZE;
        }
        else
            return this.getXLocation();
    }
}

