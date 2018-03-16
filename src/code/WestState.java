package code;

public class WestState implements EelState
{
    @Override
    public void moveEels()
    {
        for(Eel e : ESchool.getEelList())
            e.goWest();
    }
}
