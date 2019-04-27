/**
 * @author Matthew O'Neil
 * Assignment #Project
 * Date: 4/19/2019
 */

public class Player
{
    String playerName;
    int playerNumber;
    int playerScore = 0;
    int totalScore = 0;
    int playerWins = 0;
    
    public Player(int playerNumber, String name)
    {
        this.playerName = name;
        this.playerNumber = playerNumber;
    }
    public Player(int number, String name, int score)
    {
        this.playerName = name;
        this.playerNumber = number;
        this.totalScore = score;
    }
    
    public Player(int number, String name, int score, int wins, String status)
    {
        if(status.equals("Continue"))
        {
            this.playerName = name;
            this.playerNumber = number;
            this.playerScore = score;
            this.playerWins = wins;
        }
        else
        {
            this.playerName = name;
            this.playerNumber = number;
            this.totalScore = score;
            this.playerWins = wins;
        }
        
    }
    
    public int getPlayer()
    {
        return playerNumber;
    }
    public void setPlayer(int player)
    {
        this.playerNumber = player;
    }
    public String getName()
    {
        return playerName;
    }
    public void setName(String name)
    {
        this.playerName = name;
    }
    public int getScore()
    {
        return this.playerScore;
    }
    public void setScore(int entScore)
    {
        this.playerScore = entScore;
    }
    public int getTotalScore()
    {
        return totalScore;
    }
    public void setTotalScore(int score)
    {
        this.totalScore = score;
    }
    public int getWins()
    {
        return playerWins;
    }
    public void setWins(int wins)
    {
        this.playerWins = wins;
    }
    public void addToScore(int eScore)
    {
        this.playerScore = playerScore + eScore;
    }
    public String displayInfo(){
        return playerNumber+" "+playerName+" "+getScore()+" "+playerWins;
    }
    public String displayEndgameInfo(){
        return playerNumber+" "+playerName+" "+getTotalScore()+" "+playerWins;
    }
}
