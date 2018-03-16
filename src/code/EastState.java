package code;

public class EastState implements EelState
{
    @Override
    public void moveEels()
    {
        for (Eel e : ESchool.getEelList())
            e.goEast();
    }
}
