package sweeper;

class Bomb
{
    private Matrix bombMap;
    private int totalBombs;

    Bomb (int totalBombs)
    {
        this.totalBombs = totalBombs;
        fixBombCount();
    }

    void start ()
    {
        bombMap = new Matrix(Box.ZERO);
        for (int j = 0; j < totalBombs; j++)
            placeBomb();
    }

    Box get (Coords cord)
    {
        return bombMap.get(cord);
    }

    private  void fixBombCount ()
    {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 3;  // измел с 2 на 3
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }
    private void placeBomb()
    {
        while (true)
        {
            Coords cord = Ranges.getRandomCord();
            if (Box.BOMB == bombMap.get(cord))
                continue;
            bombMap.set(new Coords(cord.x, cord.y), Box.BOMB);
            incNumbersAroundBomb(cord);
            break;
        }

    }

    private void incNumbersAroundBomb(Coords cord)
    {
        for (Coords around : Ranges.getCordsAround(cord))
            if (Box.BOMB != bombMap.get (around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
    }

    int getTotalBombs()
    {
        return totalBombs;
    }
}
