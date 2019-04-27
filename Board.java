import java.util.Random;

/**
 * @author Matthew O'Neil
 * Assignment #Project
 * Date:4/18/2019
 */

public class Board
{
    private String extension;
    
    /**
     * Checks to see if the player has landed at the bottom of a ladder or
     * the top of a snake. If the player does, then it sets their score/title equal to the
     * the score/tile at the top of the ladder or end of the snake.
     * @param square gets the title the player is on, also known as their score.
     * @return 
     */
    public int squareCheck(int square)
    {
        //These are the ladders
        switch(square)
        {
            case 4: square = 14; break;
            case 9: square = 31; break;
            case 21: square = 42; break;
            case 28: square = 84; break;
            case 36: square = 44; break;
            case 51: square = 91; break;
            case 80: square = 100; break;
            default: square = square;
        }
        
        //These are the chutes
        switch(square){
            case 16: square = 6; break;
            case 47: square = 26; break;
            case 49: square = 11; break;
            case 56: square = 53; break;
            case 62: square = 19; break;
            case 64: square = 60; break;
            case 87: square = 24; break;
            case 93: square = 73; break;
            case 95: square = 75; break;
            case 98: square = 78; break;
            default: square = square;
        }
        
        return square;
    }
    
    /**
     * Returns the extension String, which is either "Ladder" or "Snake" to then
     * be used in the displayed text when the user lands on either or.
     * @param oldScore the score before using the squareCheck() method
     * @param newScore the score after using the squareCheck() method
     * @return 
     */
    public String getExtension(int oldScore, int newScore){
        if(oldScore > newScore)
        {
            extension = "snake";
        }
        else
        { 
            extension = "ladder";
        }
        return extension;
    }
    
    /**
     * 
     * @return 
     */
    public int rollDice()
    {
        Random rand = new Random();
        int roll = rand.nextInt(12) + 1;
        return roll;
    }
}