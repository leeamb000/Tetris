package views;

import controllers.TetrisController;
import models.*;
import wheelsunh.users.*;
import wheelsunh.users.Frame;
import wheelsunh.users.Rectangle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

/**
 * TetrisBoard.java:
 * Class to model the tetris board
 *
 * @author Professor Rossi and Amber Lee
 *
 * @see java.awt.Color
 * @see java.awt.event.KeyListener
 * @see java.awt.event.KeyEvent
 */
public class TetrisBoard implements KeyListener
{
    /**
     * Constant to represent the width of the board
     */
    public static final int WIDTH = 10;

    /**
     * Constant to represent the height of the board
     */
    public static final int HEIGHT = 24;

    private final TetrisController CONTROLLER;
    private Tetronimo tetronimo;
    private Rectangle[][] playingField;

    private Rectangle previewBG = new Rectangle(300, 225); //preview box background
    private TextBox previewText = new TextBox(300, 195); //text box for preview box
    private Tetronimo tetronimoPreview; // preview tetronimo
    private TextBox scoreBoard = new TextBox(300,75); //score board

    /**
     * Constructor to initialize the board
     *
     * @param frame The wheelsunh frame (so we can add this class as a key listener for the frame)
     */
    public TetrisBoard( Frame frame )
    {
        frame.addKeyListener( this );
        this.CONTROLLER = new TetrisController( this );

        this.buildBoard();

        this.run();
    }

    /**
     * Builds the playing field for tetris
     */
    private void buildBoard()
    {
        this.playingField = new Rectangle[ WIDTH ][ HEIGHT ];

        for ( int i = 0; i < TetrisBoard.WIDTH; i++ )
        {
            for ( int j = 0; j < TetrisBoard.HEIGHT; j++ )
            {
                this.playingField[ i ][ j ] = new Rectangle();
                this.playingField[ i ][ j ].setLocation( i * 20 + 40, j * 20 );
                this.playingField[ i ][ j ].setSize( Tetronimo.SIZE, Tetronimo.SIZE );
                this.playingField[ i ][ j ].setColor( Color.WHITE );
                this.playingField[ i ][ j ].setFrameColor( Color.BLACK );
            }
        }
    }

    /**
     * Starts gameplay and is responsible for keeping the game going
     */
    public void run()
    {
        boolean firstTurn = true; //true if it is the first turn
        int previewTetronimo = this.CONTROLLER.getNextTetronimo(); //arbitrary initialization for previewTetronimo
        int currentTetronimo;

        while (!this.CONTROLLER.gameOver())
        {
            //get preview block for next turn:
            if (firstTurn)
            {
                currentTetronimo = this.CONTROLLER.getNextTetronimo();
                previewTetronimo = this.CONTROLLER.getNextTetronimo();
                firstTurn = false;
            }
            else
            {
                currentTetronimo = previewTetronimo;
                previewTetronimo = this.CONTROLLER.getNextTetronimo();

                //hides last preview block:
                displayPreview(previewTetronimo, false);
            }

            //displays the preview block:
            displayPreview(previewTetronimo, true);

            //displays score board text
            scoreBoard.setText("SCORE: \n" + CONTROLLER.getScore());

            //place tetronimo:
            switch(currentTetronimo)
            {
                case 0:
                {
                    tetronimo = new StraightLine();
                    tetronimo.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );
                    break;
                }
                case 1:
                {
                    tetronimo = new LBlock();
                    tetronimo.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );
                    break;
                }
                case 2:
                {
                    tetronimo = new JBlock();
                    tetronimo.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );
                    break;
                }
                case 3:
                {
                    tetronimo = new Cube();
                    tetronimo.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );
                    break;
                }
                case 4:
                {
                    tetronimo = new TBlock();
                    tetronimo.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );
                    break;
                }
                case 5:
                {
                    tetronimo = new SBlock();
                    tetronimo.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );
                    break;
                }
                case 6:
                {
                    tetronimo = new ZBlock();
                    tetronimo.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );
                    break;
                }
            }

            while (this.CONTROLLER.tetronimoLanded(this.tetronimo)) {
                this.tetronimo.setLocation(this.tetronimo.getXLocation(), this.tetronimo.getYLocation() + Tetronimo.SIZE);
                Utilities.sleep(500);
            }

            //get pieces that make up tetronimo:
            Set<AbstractShape> partsOfBlock = this.tetronimo.getShapes();

            Object[] parts = partsOfBlock.toArray();

            wheelsunh.users.Shape r1 = (wheelsunh.users.Shape) parts[0];
            wheelsunh.users.Shape r2 = (wheelsunh.users.Shape) parts[1];
            wheelsunh.users.Shape r3 = (wheelsunh.users.Shape) parts[2];
            wheelsunh.users.Shape r4 = (wheelsunh.users.Shape) parts[3];

            //paints board the color of the tetronimo pieces where they are:
            this.playingField[(r1.getXLocation() - 40 )/ Tetronimo.SIZE ][r1.getYLocation() / Tetronimo.SIZE]
                    .setColor(this.tetronimo.getColorOfBlock());
            this.playingField[(r1.getXLocation() - 40) / Tetronimo.SIZE ][r1.getYLocation() / Tetronimo.SIZE]
                    .setFrameColor(Color.BLACK);

            this.playingField[(r2.getXLocation() - 40) / Tetronimo.SIZE][r2.getYLocation() / Tetronimo.SIZE]
                    .setColor(this.tetronimo.getColorOfBlock());
            this.playingField[(r2.getXLocation()  - 40)/ Tetronimo.SIZE ][r2.getYLocation() / Tetronimo.SIZE]
                    .setFrameColor(Color.BLACK);

            this.playingField[(r3.getXLocation() - 40) / Tetronimo.SIZE][r3.getYLocation() / Tetronimo.SIZE]
                    .setColor(this.tetronimo.getColorOfBlock());
            this.playingField[(r3.getXLocation() - 40) / Tetronimo.SIZE][r3.getYLocation() / Tetronimo.SIZE]
                    .setFrameColor(Color.BLACK);

            this.playingField[(r4.getXLocation() - 40)/ Tetronimo.SIZE ][r4.getYLocation() / Tetronimo.SIZE]
                    .setColor(this.tetronimo.getColorOfBlock());
            this.playingField[(r4.getXLocation() - 40)/ Tetronimo.SIZE ][r4.getYLocation() / Tetronimo.SIZE]
                    .setFrameColor(Color.BLACK);

            //graphically hides tetronimo:
            this.tetronimo.hideTetronimo();

            //makes tetronimo null:
            this.tetronimo = null;

            //detect and remove filled rows, shift rows down:
            clearSomeRows();

            //update score:
            scoreBoard.setText("SCORE: \n" + CONTROLLER.getScore());
        }

        //game over graphic displys:
        TextBox gameOverDisplay = new TextBox(300,150);
        gameOverDisplay.setText("GAME OVER");
        gameOverDisplay.setColor(Color.RED);
    }

    /**
     * Method to display and remove a preview of the next block
     *
     * @param preview the next block to be dropped
     * @param display indicates whether the method should place or hide a block
     */
    private void displayPreview(int preview, boolean display)
    {
        //set up background:
        previewBG.setSize(100, 100);
        previewBG.setFillColor(Color.WHITE);
        previewBG.setFrameColor(Color.BLACK);

        //set up text:
        previewText.setSize(50,100);
        previewText.setText("NEXT:");

        if (display) //if showing next block
        {
            //place preview block:
            switch (preview) {
                case 0: {
                    tetronimoPreview = new StraightLine();
                    tetronimoPreview.setLocation(325, 240);
                    break;
                }
                case 1: {
                    tetronimoPreview = new LBlock();
                    tetronimoPreview.setLocation(325, 240);
                    break;
                }
                case 2: {
                    tetronimoPreview = new JBlock();
                    tetronimoPreview.setLocation(325, 240);
                    break;
                }
                case 3: {
                    tetronimoPreview = new Cube();
                    tetronimoPreview.setLocation(325, 240);
                    break;
                }
                case 4: {
                    tetronimoPreview = new TBlock();
                    tetronimoPreview.setLocation(325, 240);
                    break;
                }
                case 5: {
                    tetronimoPreview = new SBlock();
                    tetronimoPreview.setLocation(325, 240);
                    break;
                }
                case 6: {
                    tetronimoPreview = new ZBlock();
                    tetronimoPreview.setLocation(325, 240);
                    break;
                }
            }
        }
        else //if hiding this preview block to make room for next turn's preview block
        {
            tetronimoPreview.hideTetronimo();
        }
    }

    /**
     * Method to remove cleared rows and shift down the blocks from the rows above
     */
    private void clearSomeRows()
    {
        int numberOfLinesCleared = CONTROLLER.lineCleared();

        //get the row numbers that were cleared:
        int[] clearedRows = CONTROLLER.getLinesCleared(numberOfLinesCleared);

        //delete these blocks and shift down:
        for (int k = 0; k < numberOfLinesCleared; k++) //do this for each row that was cleared:
        {
            boolean shift = false; //true if the next rows up need to be shifted down

            for ( int j = TetrisBoard.HEIGHT - 1; j > 0; j-- ) //moving up from bottom row
            {
                for ( int i = 0; i < TetrisBoard.WIDTH; i++ )
                {
                    if (j == clearedRows[k] || shift)
                    {
                        this.playingField[i][j].setColor(this.playingField[i][j - 1].getFillColor());
                        this.playingField[i][j].setFrameColor(Color.BLACK);

                        shift = true;
                    }
                }
            }
        }

        //add to score based on number of lines cleared:
        CONTROLLER.addScore(numberOfLinesCleared);
    }

    /**
     * Getter method for the array representing the playing field
     *
     * @return The playing field
     */
    public Rectangle[][] getPlayingField()
    {
        return playingField;
    }

    /**
     * This method is not used in this program
     *
     * @param e The key event
     */
    @Override
    public void keyTyped( KeyEvent e )
    {
        //not in use
    }

    /**
     * Handles the key events by the user
     *
     * @param e The key event
     */
    @Override
    public void keyPressed( KeyEvent e )
    {
        int key = e.getKeyCode();

        if( this.tetronimo == null )
        {
            return;
        }

        switch( key )
        {
            case 38:
                this.tetronimo.rotate();
                break;
            case 37:
                if( this.tetronimo.getXLocationFall() - Tetronimo.SIZE >= 40)
                {
                    this.tetronimo.shiftLeft();
                }
                break;
            case 39:
                if( (this.tetronimo.getXLocationFall() + this.tetronimo.getWidth()) <
                        ((TetrisBoard.WIDTH * Tetronimo.SIZE) + 40))
                {
                    this.tetronimo.shiftRight();
                }
                break;
        }

    }

    /**
     * This method is not used in this program
     *
     * @param e The key event
     */
    @Override
    public void keyReleased( KeyEvent e )
    {
        //not in use
    }
}