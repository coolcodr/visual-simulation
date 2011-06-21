package designer.deployment;

public class GetStringInput extends GetDataInput {
    /**
     * 
     */
    private static final long serialVersionUID = -3700814246795276559L;

    public GetStringInput() {
    }

    public Object getInput(String s, InputComponent object) throws InvalidDataException {
        return s;
    }

}
