package models;

import java.awt.*;

/**
 * Cube.java:
 * Creates a cube shaped tetronimo
 *
 * @author Amber Lee
 *
 * @see java.awt.Point
 */
public class Cube extends Tetronimo
{
    /**
     * Creates the tetronimo and puts it in the original orientation
     */
    public Cube()
    {
        super.r1.setLocation( 0, 0 );
        super.r2.setLocation( Tetronimo.SIZE, 0);
        super.r3.setLocation( 0, Tetronimo.SIZE );
        super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE);

        super.r1.setColor(Color.YELLOW);
        super.r2.setColor(Color.YELLOW);
        super.r3.setColor(Color.YELLOW);
        super.r4.setColor(Color.YELLOW);

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
        super.rotate();

        Point curLoc = super.getLocation();
        super.setLocation( 0, 0 );

        super.r1.setLocation( 0, 0 );
        super.r2.setLocation( Tetronimo.SIZE, 0);
        super.r3.setLocation( 0, Tetronimo.SIZE );
        super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE);

        super.setLocation( curLoc );
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
            return Tetronimo.SIZE * 2;
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
            return Tetronimo.SIZE * 2;
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
        return Color.YELLOW;
    }

}

