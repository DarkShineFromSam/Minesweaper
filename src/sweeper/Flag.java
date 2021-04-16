package sweeper;

class Flag
{
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start ()
    {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get (Coords cord)
    {
        return flagMap.get (cord);
    }

    public void setOpenedToBox(Coords cord)
    {
        flagMap.set(cord, Box.OPENED);
        countOfClosedBoxes --;
    }

    void toggleFlagToBox(Coords cord)
    {
        switch (flagMap.get(cord)){
            case FLAGED: setClosedToBox(cord);
            case CLOSED : setFlagToBomb(cord);
        }
    }

    private void setClosedToBox(Coords cord)
    {
        flagMap.set(cord, Box.CLOSED);
    }

    public void setFlagToBomb(Coords cord)
    {
        flagMap.set(cord, Box.FLAGED);
    }

    int getCountOfClosedBodes()
    {
        return countOfClosedBoxes;
    }

    void setBombedToBox (Coords cord)
    {
        flagMap.set(cord, Box.BOMBED);
    }

    void setOpenedToClosedBombBox(Coords cord)
    {
        if (flagMap.get(cord) == Box.CLOSED)
            flagMap.set(cord, Box.OPENED);
    }

    void setNoBombToFlagSafeBox(Coords cord)
    {
        if (flagMap.get(cord) == Box.FLAGED)
            flagMap.set(cord, Box.NOBOMB);
    }



    int getCountOfFlagBoxesAround(Coords cord)
    {
        int count = 0;
        for (Coords around: Ranges.getCordsAround(cord))
            if (flagMap.get(around) == Box.FLAGED)
                 count++;
            return count;
    }
}
