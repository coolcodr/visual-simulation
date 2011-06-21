package designer.deployment;
import java.io.Serializable;

public abstract class GetDataInput implements Serializable
{
    public abstract Object getInput( String s, InputComponent object ) throws InvalidDataException;
}