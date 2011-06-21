
package designer.deployment;

import print.*;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

public class DeployObject implements Serializable
{
    private Vector inputObject = new Vector();
    private Hashtable inputObjectTable = new Hashtable();
    private Vector internalInputObject = new Vector();
    private Hashtable internalInputObjectTable = new Hashtable();
    private Vector actionObject = new Vector();
    private Vector otherObject = new Vector();
    private Hashtable cardPanes = new Hashtable();

    private Vector reportDocuments = new Vector ();

    //A terrible link in frameObject, there are some inputObject and internalInputObject or action object those are exactly
    //link of the above Vector, you duno how it does, but it does.
    //You cannot change, otherwize the deployment wont works any more.
    private Vector frameObject = new Vector();

    public DeployObject()
    {
    }
    public void addInputObject ( InputComponent inputComponent )
    {
        inputObject.add(inputComponent);

        inputObjectTable.put(inputComponent.getName()+"|"+inputComponent.getSetMode(), inputComponent);
    }
    public void addInternalInputObject ( InputComponent inputComponent )
    {
        internalInputObject.add(inputComponent);
        internalInputObjectTable.put(inputComponent.getName()+"|"+inputComponent.getSetMode(), inputComponent);
    }

    public void addActionObject ( DeployActionComponent actionComponent )
    {
        actionObject.add(actionComponent);
    }
    public void addCardPane ( String id, DeployComponent2 deployComponent )
    {
        cardPanes.put(id, deployComponent);
    }
    public void addOtherObject ( DeployComponent2 deployComponent )
    {
        otherObject.add(deployComponent);
    }
    public void addFrameObject ( DeployFrame frameComponent )
    {
        frameObject.add(frameComponent);
    }
    public void addReport ( ReportDocument reportDocument )
    {
        reportDocuments.add(reportDocument);
    }
    public int getInputObjectCount()
    {
        return inputObject.size();
    }
    public int getInternalInputObjectCount()
    {
        return internalInputObject.size();
    }
    public int getCardPanesCount()
    {
        return cardPanes.size();
    }

    public int getActionObjectConunt()
    {
        return actionObject.size();
    }
    public int getOtherObjectCount()
    {
        return otherObject.size();
    }
    public int getFrameObjectConunt()
    {
        return frameObject.size();
    }
    public int getReportCount()
    {
        return reportDocuments.size();
    }
    public InputComponent getInputObject ( int i )
    {
        if ( i < inputObject.size() )
            return (InputComponent) (inputObject.elementAt(i));
        else
            return null;
    }
    public InputComponent getInputObject ( String name, String setMode )
    {
        InputComponent result = (InputComponent)inputObjectTable.get(name+"|"+setMode);
        /*
        InputComponent result = null;
        for ( int i = 0 ; i < inputObject.size() ; i ++ )
        {
            InputComponent current = (InputComponent)(inputObject.elementAt(i));

            if ( current.getName().equalsIgnoreCase(name) &&
                 current.getSetMode().equalsIgnoreCase(setMode) )
                result = current;
        }*/
        return result;

    }
    public InputComponent getInternalInputObject ( int i )
    {
        if ( i < internalInputObject.size() )
            return (InputComponent) (internalInputObject.elementAt(i));
        else
            return null;
    }
    public InputComponent getInternalInputObject ( String name, String setMode )
    {
        InputComponent result = (InputComponent)internalInputObjectTable.get(name+"|"+setMode);
        /*
        for ( int i = 0 ; i < internalInputObject.size() ; i ++ )
        {
            InputComponent current = (InputComponent)(internalInputObject.elementAt(i));
            if ( current.getName().equalsIgnoreCase(name) &&
                 current.getSetMode().equalsIgnoreCase(setMode) )
                result = current;
        }*/
        return result;
    }
    public DeployActionComponent getActionObject ( int i )
    {
        if ( i < actionObject.size() )
            return (DeployActionComponent) (actionObject.elementAt(i));
        else
            return null;
    }
    public DeployFrame getFrameObject ( int i )
    {
        if ( i < frameObject.size() )
            return (DeployFrame) (frameObject.elementAt(i));
        else
            return null;
    }
    public DeployFrame getFrameObject ( String id )
    {
        DeployFrame result = null;
        for ( int i = 0 ; i < frameObject.size() ; i ++ )
        {
            DeployFrame current = (DeployFrame)(frameObject.elementAt(i));
            if ( current.getID().equalsIgnoreCase(id) )
                result = current;
        }
        return result;
    }
    public ReportDocument getReport ( int i )
    {
        if ( i < frameObject.size() )
            return (ReportDocument)reportDocuments.elementAt(i);
        else
            return null;
    }
}