public class WumpusSquare
{
    private boolean gold;
    private boolean ladder;
    private boolean pit;
    private boolean breeze;
    private boolean wumpus;
    private boolean deadWumpus;
    private boolean stench;
    private boolean visited;

    public WumpusSquare()
    {
        gold = false;
        ladder = false;
        pit = false;
        breeze = false;
        wumpus = false;
        deadWumpus = false;
        stench = false;
    }
    public boolean getGold()
    {
        return gold;
    }
    public boolean getLadder()
    {
        return ladder;
    }
    public boolean getPit()
    {
        return pit;
    }
    public boolean getBreeze()
    {
        return breeze;
    }
    public boolean getWumpus()
    {
        return wumpus;
    }
    public boolean getDeadWumpus()
    {
        return deadWumpus;
    }
    public boolean getStench()
    {
        return stench;
    }
    public boolean getVisited()
    {
        return visited;
    }
    public boolean setGold(boolean gold)
    {
        this.gold = gold;
        return gold;
    }
    public boolean setLadder(boolean ladder)
    {
        this.ladder = ladder;
        return ladder;
    }
    public boolean setPit(boolean pit)
    {
        this.pit = pit;
        return pit;
    }
    public boolean setBreeze(boolean breeze)
    {
        this.breeze = breeze;
        return breeze;
    }
    public boolean setWumpus(boolean wumpus)
    {
        this.wumpus = wumpus;
        return wumpus;
    }
    public boolean setDeadWumpus(boolean deadWumpus)
    {
        this.deadWumpus = deadWumpus;
        return deadWumpus;
    }
    public boolean setStench(boolean stench)
    {
        this.stench = stench;
        return stench;
    }
    public boolean setVisited(boolean visited)
    {
        this.visited = visited;
        return visited;
    }
    public String toString()
    {
        if (wumpus)
        {
            if (gold)
                return "@";
            else
                return "W";
        }
        else if (deadWumpus)
        {
            if (gold)
                return "@";
            else
                return "D";
        }
        else if (gold)
        {
            return "G";
        }
        else if (ladder)
        {
            return"L";
        }
        else if (pit)
        {
            return "P";
        }
        else if (breeze)
        {
            return "B";
        }
        else if (stench)
        {
            return "S";
        }
        return "*";
    }
}