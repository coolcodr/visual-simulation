package designer.deployment;

public class GetStringInput extends GetDataInput
{
    public GetStringInput()
    {
    }
    public Object getInput ( String s, InputComponent object  ) throws InvalidDataException
    {
        return s;
    }

}