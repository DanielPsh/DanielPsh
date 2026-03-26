import java.lang.System.Logger.Level;

public class FRQ {
    private Level levelOne;
    private Level levelTwo;
    private Level levelThree;
    
    public int getScore()
    {
        int score = 0;
        if(levelOne.goalReached())
        {
            score = levelOne.getPoints();
            if(levelTwo.goalReached())
            {
                score += levelTwo.getPoints();
                if(levelThree.getPoints())
                {
                    score += levelThree.getPoints();
                }
            }
        }
        if(isBonus())
        {
            score *= 3;
        }
        return score;
    }
    
    public int playManyTimes(int num)
    {
        int scoreMax = 0;
        for(int i = 0; i < num; i ++)
        {
            play();
            int score = getScore();
            if(score > scoreMax)
            {
                score = scoreMax;
            }
        }
        return scoreMax;
    }

    
}
