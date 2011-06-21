package designer.deployment;

import java.io.Serializable;

public abstract class GetDataInput implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5378959167315167315L;

    public abstract Object getInput(String s, InputComponent object) throws InvalidDataException;
}
