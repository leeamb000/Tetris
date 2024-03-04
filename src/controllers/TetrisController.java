package controllers;

import models.*;
import views.TetrisBoard;
import wheelsunh.users.Rectangle;

import java.awt.*;
import java.util.Random;

/**
 * TetrisController.java:
 * Class to hold all of the game logic for tetris
 *
 * @author Professor Rossi and Amber Lee
 */
public class TetrisController
{
    private final TetrisBoard TETRIS_BOARD;
    int score = 0; //initialization for score

    /**
     * Constructor to take in a tetris board so the controller and the board can communciate
     *
     * @param tetrisBoard A tetris board instance
     */
    public TetrisController( TetrisBoard tetrisBoard )
    {
        this.TETRIS_BOARD = tetrisBoard;
    }

    /**
     * Randomly chooses the next tetronimo
     *
     * @return A number representing the next tetronimo to be played
     */
    public int getNextTetronimo()
    {
        Tetronimo tetronimo;

        //select random tetronimo:
        Random rand = new Random();
        int piece = rand.nextInt(7);

        return piece;
    }

    /**
     * Method to determine if the tetronimo has landed
     *
     * @param tetronimo The tetronimo to evaluate
     * @return True if the tetronimo has landed (on the bottom of the board or another tetronimo), false if it has not
     */
    public boolean tetronimoLanded( Tetronimo tetronimo )
    {
        int nextY = tetronimo.getYLocationFall() + tetronimo.getHeight() + Tetronimo.SIZE;

        int piece = 0; //int for switch statement indicating which piece is in use:

        if (tetronimo.getColorOfBlock() == Color.CYAN)
            piece = 0;
        else if (tetronimo.getColorOfBlock() == Color.ORANGE)
            piece = 1;
        else if (tetronimo.getColorOfBlock() == Color.BLUE)
            piece = 2;
        else if (tetronimo.getColorOfBlock() == Color.YELLOW)
            piece = 3;
        else if (tetronimo.getColorOfBlock() == Color.MAGENTA)
            piece = 4;
        else if (tetronimo.getColorOfBlock() == Color.GREEN)
            piece = 5;
        else if (tetronimo.getColorOfBlock() == Color.RED)
            piece = 6;

        Rectangle[][] playingField = TETRIS_BOARD.getPlayingField(); //playing field from Board

        if (nextY > 480) //if block hits the bottom
        {
            return false;
        }
        else if (nextY <= 480) //check if block hits another block on the way down:
        {
            switch (piece) //Let each piece know when to stop depending on rotation + shape of piece:
            {
                case 0: //StraightLine
                {
                    Color nextColor1 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 2]
                            [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                    if (nextColor1 != Color.WHITE)
                        return false;

                    if(tetronimo.getWidth() == Tetronimo.SIZE * 4) //if block is sideways:
                    {
                        if (nextY > 480) //prevents errors with array below
                        {
                            nextY = nextY - Tetronimo.SIZE;
                        }

                        Color nextColor2 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 1]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor2 != Color.WHITE)
                            return false;

                        Color nextColor3 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE)]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor3 != Color.WHITE)
                            return false;

                        Color nextColor4 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) + 1]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor4 != Color.WHITE)
                            return false;
                    }
                    break;
                }

                case 1: //LBlock
                {
                    if (tetronimo.getWidth() == Tetronimo.SIZE * 2) //if block is not sideways:
                    {
                        Color nextColor1 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 2]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor1 != Color.WHITE)
                            return false;

                        Color nextColor2 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 1]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor2 != Color.WHITE)
                            return false;
                    }
                    if (tetronimo.getWidth() == Tetronimo.SIZE * 3) //if block is sideways:
                    {
                        Color nextColor3 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE)]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor3 != Color.WHITE)
                            return false;

                        Color nextColor4 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 1]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor4 != Color.WHITE)
                            return false;

                        Color nextColor5 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 2]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor5 != Color.WHITE)
                            return false;
                    }
                    break;
                }

                case 2: //JBlock
                {
                    if (tetronimo.getWidth() == Tetronimo.SIZE * 2) //if block is not sideways:
                    {
                        Color nextColor1 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 2]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor1 != Color.WHITE)
                            return false;

                        Color nextColor2 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 1]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor2 != Color.WHITE)
                            return false;
                    }

                    if (tetronimo.getWidth() == Tetronimo.SIZE * 3) //if block is sideways:
                    {
                        Color nextColor3 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 1]
                                [(nextY / Tetronimo.SIZE) - 2].getFillColor();
                        if (nextColor3 != Color.WHITE)
                            return false;

                        Color nextColor4 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE)]
                                [(nextY / Tetronimo.SIZE) - 2].getFillColor();
                        if (nextColor4 != Color.WHITE)
                            return false;

                        Color nextColor5 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) + 1]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor5 != Color.WHITE)
                            return false;
                    }
                    break;
                }

                case 3: //Cube
                {
                    Color nextColor1 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 2]
                            [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                    if (nextColor1 != Color.WHITE)
                        return false;

                    Color nextColor2 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 1]
                            [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                    if (nextColor2 != Color.WHITE)
                        return false;
                    break;
                }

                case 4: //TBlock
                {
                    if (tetronimo.getWidth() == Tetronimo.SIZE * 3) //if block is not sideways:
                    {
                        Color nextColor1 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 1]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor1 != Color.WHITE)
                            return false;

                        Color nextColor2 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 2]
                                [(nextY / Tetronimo.SIZE) - 2].getFillColor();
                        if (nextColor2 != Color.WHITE)
                            return false;

                        Color nextColor3 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE)]
                                [(nextY / Tetronimo.SIZE) - 2].getFillColor();
                        if (nextColor3 != Color.WHITE)
                            return false;
                    }

                    if (tetronimo.getWidth() == Tetronimo.SIZE * 2) //if block is sideways:
                    {
                        Color nextColor4 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) ]
                                [(nextY / Tetronimo.SIZE) - 2].getFillColor();
                        if (nextColor4 != Color.WHITE)
                            return false;

                        Color nextColor5 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 1]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor5 != Color.WHITE)
                            return false;
                    }
                    break;
                }

                case 5: //SBlock
                {
                    if (tetronimo.getWidth() == Tetronimo.SIZE * 3) //if block is not sideways:
                    {
                        Color nextColor1 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 1]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor1 != Color.WHITE)
                            return false;

                        Color nextColor2 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE)]
                                [(nextY / Tetronimo.SIZE) - 2].getFillColor();
                        if (nextColor2 != Color.WHITE)
                            return false;

                        Color nextColor3 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 2]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor3 != Color.WHITE)
                            return false;
                    }

                    if (tetronimo.getWidth() == Tetronimo.SIZE * 2) //if block is sideways:
                    {
                        Color nextColor4 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE)]
                                [(nextY / Tetronimo.SIZE) - 2].getFillColor();
                        if (nextColor4 != Color.WHITE)
                            return false;

                        Color nextColor5 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) + 1]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor5 != Color.WHITE)
                            return false;
                    }
                    break;
                }

                case 6: //ZBlock
                {
                    if (tetronimo.getWidth() == Tetronimo.SIZE * 2) //if block is not sideways:
                    {
                        Color nextColor1 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 2]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor1 != Color.WHITE)
                            return false;

                        Color nextColor2 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 1]
                                [(nextY / Tetronimo.SIZE) - 2].getFillColor();
                        if (nextColor2 != Color.WHITE)
                            return false;
                    }

                    if (tetronimo.getWidth() == Tetronimo.SIZE * 3) //if block is sideways:
                    {
                        Color nextColor3 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) + 1]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor3 != Color.WHITE)
                            return false;

                        Color nextColor4 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE)]
                                [(nextY / Tetronimo.SIZE) - 1].getFillColor();
                        if (nextColor4 != Color.WHITE)
                            return false;

                        Color nextColor5 = playingField[(tetronimo.getXLocation() / Tetronimo.SIZE) - 1]
                                [(nextY / Tetronimo.SIZE) - 2].getFillColor();
                        if (nextColor5 != Color.WHITE)
                            return false;
                    }
                    break;
                }
                default:
                    return true; //arbitrary default
            }
        }
        return nextY <= 480; //arbitrary return
    }

    /**
     * Method to determine if game is over
     *
     * @return true if game is over, false if game is not over
     */
    public boolean gameOver()
    {
        Rectangle[][] playingField = TETRIS_BOARD.getPlayingField(); //playing field from Board
        boolean game_over = false; //variable to indicate if game is over

        //if top of board turns a color, the game is over:
        for(int i = 0; i < 10; i++)
        {
            if (playingField[i][0].getFillColor() != Color.WHITE)
            {
                game_over = true;
            }
        }

        return game_over; //arbitrary return
    }

    /**
     * Method to tell if line was cleared
     *
     * @return int number of lines cleared (including zero)
     */
    public int lineCleared()
    {
        int numberCleared = 0; //number of rows cleared
        Rectangle[][] playingField = TETRIS_BOARD.getPlayingField(); //playing field from Board

        for ( int j = 0; j < TetrisBoard.HEIGHT; j++ )
        {
            int coloredBlock = 0; //counter for number of blocks colored in in one row

            for (  int i = 0; i < TetrisBoard.WIDTH; i++ )
            {
                if (playingField[i][j].getFillColor() != Color.WHITE)
                {
                    coloredBlock++;
                }

                if (coloredBlock == 10) //if all 10 blocks are colored in
                {
                    numberCleared++; //then add 1 to number of cleared rows
                }
            }
        }
       return numberCleared;
    }

    /**
     * Method to tell which line was cleared
     *
     * @return int array of row numbers for line(s) that were cleared
     */
    public int[] getLinesCleared(int numberCleared)
    {
        int[] clearedRows = new int[numberCleared]; // array list to hold row numbers

        Rectangle[][] playingField = TETRIS_BOARD.getPlayingField(); //playing field from Board

        for ( int j = 0; j < TetrisBoard.HEIGHT; j++ )
        {
            int coloredBlock = 0; //counter for number of blocks colored in in one row

            for (  int i = 0; i < TetrisBoard.WIDTH; i++ )
            {
                if (playingField[i][j].getFillColor() != Color.WHITE)
                {
                    coloredBlock++;
                }

                if (coloredBlock == 10) //if all 10 blocks are colored in
                {
                    //get the row number(s):
                    if (clearedRows[0] == 0)
                    {
                        clearedRows[0] = j;
                    }
                    else if (clearedRows[1] == 0)
                    {
                        clearedRows[1] = j;
                    }
                    else if (clearedRows[2] == 0)
                    {
                        clearedRows[2] = j;
                    }
                    else if (clearedRows[3] == 0)
                    {
                        clearedRows[3] = j;
                    }
                }
            }
        }
        return clearedRows;
    }

    /**
     * Method to add on to current score
     *
     * @param linesCleared number of lines cleared
     */
    public void addScore(int linesCleared)
    {
        if (linesCleared == 4)
            score += 800;
        else
            score += 100 * linesCleared;
    }

    /**
     * Method to get score
     *
     * @return int holding the score
     */
    public int getScore()
    {
        return score;
    }
}