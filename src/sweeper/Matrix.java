package sweeper;

class Matrix
{
    private final Box [] [] matrix;

    Matrix (Box defaultBox)
    {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coords cord : Ranges.getAllCords())
            matrix [cord.x] [cord.y] = defaultBox;
    }

    Box get (Coords cord)
    {
        if (Ranges.inRange (cord))
            return matrix [cord.x] [cord.y];
        return null;
    }

    void set (Coords cord, Box box)
    {
        if (Ranges.inRange (cord))
        matrix [cord.x] [cord.y] = box;
    }
}
