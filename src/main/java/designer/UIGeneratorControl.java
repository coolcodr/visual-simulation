package designer;

import designer.deployment.*;

import designer.report.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import java.util.Hashtable;
import java.util.Enumeration;
import java.awt.*;

public class UIGeneratorControl
{
    private DeployObject deployObject;
    private String modelName = null;

    public UIGeneratorControl()
    {
    }

    private Hashtable tempDeployCardPane = new Hashtable();

    public void generatePane(DesignPane designPane)
    {
        DeployFrame frameObject = new DeployFrame((JComponent)designPane, designPane.getTitle(), designPane.getID(), designPane.getFrameName());
        System.out.println("ADD TO DEPLOYOBJECT: " + frameObject.getID());
        deployObject.addFrameObject(frameObject);

        for (int j = 0; j < designPane.setPaneComponent.size(); j++)
        {
            JComponent currentComponent = (JComponent) designPane.setPaneComponent.elementAt(j);

            if (currentComponent instanceof DiagramComponentSetPanel)
                ; //DiagramComponentSetPanel Must do nothing
            else if (currentComponent instanceof CardPane)
            {
                String id = ( (DesignPane) currentComponent.getParent().getParent()).getID();
                Vector designPanes = ( (CardPane) currentComponent).getDesignPanes();
                String[] tempID = new String[designPanes.size()];
                for (int i = 0; i < tempID.length; i++)
                {
                    tempID[i] = ( (DesignPane) designPanes.elementAt(i)).getID();
                }
                CardComponent cardComponent = new CardComponent((JPanel)currentComponent, ((CardPane)currentComponent).getID(), ((CardPane)currentComponent).getText());//new CardComponent( (CardPane) currentComponent);
                Object[] object =
                    {
                    cardComponent, tempID};
                //tempDeployCardPane.add(object);
                tempDeployCardPane.put( ( (CardPane) currentComponent).getID(), object);
                ( (DeployFrame) deployObject.getFrameObject(id)).addCardComponent(cardComponent);
            }
            else if (currentComponent instanceof TitlePane)
            {
                //String id = ((TitlePane)currentComponent).getID();
                String id = ( (DesignPane) currentComponent.getParent().getParent()).getID();
                //System.out.println("Frame ID: " + id);
                String thisID = ( (DesignPane) currentComponent).getID();
                System.out.println("Frame THIS ID: " + thisID);
                /*
                                     DesignPane designPane = (DesignPane)allFrame.elementAt(i);
                             DeployFrame frameObject = new DeployFrame(designPane.getBottomPane(), designPane.getTitle(), designPane.getID());
                             deployObject.addFrameObject(frameObject);
                 */
                /*
                                 TitlePane titlePane = (TitlePane) currentComponent;
                                 DeployFrame frameObject = new DeployFrame(titlePane.getBottomPane(), titlePane.getText(), null);
                                 ( (DeployFrame) deployObject.getFrameObject(id)).addFrameComponent(frameObject);
                 */
                TitlePane titlePane = (TitlePane) currentComponent;
                generatePane(titlePane);
                ( (DeployFrame) deployObject.getFrameObject(id)).addFrameComponent( (DeployFrame) deployObject.getFrameObject(thisID));
            }
            else if (currentComponent instanceof CButton)
            {
                CButton button = (CButton) currentComponent;
                DeployActionComponent actionComponent = new DeployActionComponent(button, button.getDAction() == null ? null : button.getDAction().createNewObject());
                deployObject.addActionObject(actionComponent);

                //if (currentComponent.isDisplayable() && currentComponent.getParent().getParent() != null)
                if ( currentComponent.getParent().getParent() != null)
                {
                    String id = ( (DesignPane) currentComponent.getParent().getParent()).getID();
                    ( (DeployFrame) deployObject.getFrameObject(id)).addActionComponent(actionComponent);
                }
            }
            else if (currentComponent instanceof CLabel)
            {
                DeployComponent2 deployComponent = new DeployComponent2(currentComponent, ((CLabel)currentComponent).getText());
                deployObject.addOtherObject(deployComponent);

                //if (currentComponent.isDisplayable() && currentComponent.getParent().getParent() != null)
                if ( currentComponent.getParent().getParent() != null)
                {
                    String id = ( (DesignPane) currentComponent.getParent().getParent()).getID();
                    System.out.println("Frame ID: " + id);
                    ( (DeployFrame) deployObject.getFrameObject(id)).addOtherComponent(deployComponent);
                }
            }
        }

    }

    public void copy ( String filePath, String newPath, String fileName )
    {
        try
        {
            File inputFile = new File(filePath);
            File outputFile = new File(newPath + "\\" + fileName);

            FileReader in = new FileReader(inputFile);
            FileWriter out = new FileWriter(outputFile);
            int c;

            while ( (c = in.read()) != -1)
                out.write(c);

            in.close();
            out.close();
        }
        catch (Exception ex )
        {
        }
    }

    public void exportObject(Component parent)
    {

        if ( modelName == null )
        {
            exportDeploy(parent);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new BatFileFilter2());
        fileChooser.showSaveDialog(parent);
        File file = fileChooser.getSelectedFile();
        String path = "";
        String name;

        if (file != null)
        {
            path = file.getAbsolutePath();
            name = file.getName();
        }
        else
        {
            return;
        }

        try
        {
            File outFile = new File(path + ".bat");

            FileOutputStream outFileStream = new FileOutputStream(outFile);
            //ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);
            PrintWriter printWriter = new PrintWriter(outFileStream);

            printWriter.println("java -classpath " + modelName + ".jar RunSim " + name + ".vsx");

            printWriter.close();
            outFileStream.close();

            copy("PropertiesTable.txt", file.getParent(), "PropertiesTable.txt");
            generateUI(path);
        }
        catch (Exception ex)
        {

        }

    }
    public void exportDeploy(Component parent)
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new BatFileFilter());
        fileChooser.showSaveDialog(parent);
        File file = fileChooser.getSelectedFile();
        String path = "";
        String name;

        if (file != null)
        {
            path = file.getAbsolutePath();
            name = file.getName();
            modelName = name;
        }
        else
        {
            return;
        }
        //if (file.isFile())
        {
        /*
                  path = file.getAbsolutePath();
                  String pathOnly =  path.substring(0, path.length()-4);
                  String name = file.getName();*/

            try
            {
                Runtime _runtime = Runtime.getRuntime();
                Process _process;
                _process = _runtime.exec("jar cvf \"" + path + ".jar\" designer/deployment engine chart light mcomponent animation print statistic diagram/PropertiesTableReader.class  diagram/PropertiesTableData.class diagram/PropertiesSetting.class RunSim.class RunSim$1.class crimson.jar PropertiesTable.txt");
                System.out.println("RUNTIME EX: " + "jar cvf " + path + ".jar designer/deployment engine chart light mcomponent animation print statistic diagram/PropertiesTableReader.class  diagram/PropertiesTableData.class diagram/PropertiesSetting.class RunSim.class RunSim$1.class crimson.jar");

                File outFile = new File(path + ".bat");

                FileOutputStream outFileStream = new FileOutputStream(outFile);
                //ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);
                PrintWriter printWriter = new PrintWriter(outFileStream);

                printWriter.println("java -classpath .;" + name + ".jar RunSim " + name);

                printWriter.close();
                outFileStream.close();

                copy ( "PropertiesTable.txt", file.getParent(), "PropertiesTable.txt" );
                generateUI(path);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("Java home error");
            }
        }
    }
    public void generateUI ()
    {
        generateUI ( null );
    }
    public void generateUI( String deployPath )
    {
        DeployObject deployObject = generateDeployObject();
        try
        {
            File outFile = new File("temp_deploy_object.vsx");
            //File outFile = new File("F:\\Kenny's folder\\Kenny's works\\Software Engineering year3\\project work3\\Horst\\DeploymentDesigner\\vSim1901\\src\\TestingDeploymentObjects.dat");
            //File outFile = new File("E:\\Temp\\new vSim\\classes\\TestingDeploymentObjects.dat");
            FileOutputStream outFileStream = new FileOutputStream(outFile);
            ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);

            outObjectStream.writeObject(deployObject);
            outObjectStream.close();
            outFileStream.close();

            if ( deployPath != null )
            {
                File outFile2 = new File(deployPath + ".vsx");
                FileOutputStream outFileStream2 = new FileOutputStream(outFile2);
                ObjectOutputStream outObjectStream2 = new ObjectOutputStream(outFileStream2);

                outObjectStream2.writeObject(deployObject);
                outObjectStream2.close();
                outFileStream2.close();
            }
            Vector allReport = ReportDesignerControl.reports;
            for (int i = 0; i < allReport.size(); i++)
            {
                PrintEditor printEditor = (PrintEditor) allReport.elementAt(i);
                printEditor.getControl().getReportDocument().restoreImage();
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public DeployObject generateDeployObject()
    {
        tempDeployCardPane = new Hashtable();

        deployObject = new DeployObject();

        Vector allFrame = DesignerControl.designPanes;

        for (int i = 0; i < allFrame.size(); i++)
        {
            DesignPane designPane = (DesignPane) allFrame.elementAt(i);
            generatePane(designPane);
            /*
                         DesignPane designPane = (DesignPane)allFrame.elementAt(i);
                         DeployFrame frameObject = new DeployFrame(designPane.getBottomPane(), designPane.getTitle(), designPane.getID());
                         deployObject.addFrameObject(frameObject);
             */
        }
        Enumeration panes = tempDeployCardPane.elements();
        //for ( int i = 0 ; i < tempDeployCardPane.size() ; i ++ )
        for (; panes.hasMoreElements(); )
        {
            Object[] object = (Object[]) panes.nextElement();
            CardComponent cardComponent = (CardComponent) object[0];
            String[] frameID = (String[]) object[1];
            for (int j = 0; j < frameID.length; j++)
            {
                String id = frameID[j];
                DeployFrame deployFrame = (DeployFrame) deployObject.getFrameObject(id);
                cardComponent.addDeployFrame(deployFrame);
            }
        }
        /*
                        for ( int i = 0 ; i < DesignerControl.designPanes.size() ; i ++ )
                        {
                            DesignPane designPane = (DesignPane)DesignerControl.designPanes.elementAt(i);
                        }
         */

        //Temp duno use added component ( in current designPanes) or all ( in source list ) is better
        //Vector allSetPane = DesignerControl.currentDesignPane.setPaneComponent; // in current designe pane
        Vector allSetPane = UIDesigner.sourceList.inputComponent; //in source list

        for (int i = 0; i < allSetPane.size(); i++)
        {
            DiagramComponentSetPanel currentSetPane = (DiagramComponentSetPanel) allSetPane.elementAt(i);
			currentSetPane.refreshComboxBoxChoice();

            InputComponent imputComponent = new InputComponent(currentSetPane, currentSetPane.getName(), currentSetPane.toString(), currentSetPane.getGetMode(), currentSetPane.getDefultValue());

            imputComponent.addComponent(currentSetPane.getLabel(), currentSetPane.getLabel().getText(), InputComponent.LABEL);
            imputComponent.addComponent(currentSetPane.getTextField(), currentSetPane.getTextField().getText(), InputComponent.TEXT_FIELD);
            Object[] objects = new Object[currentSetPane.getComboBox().getItemCount()];
            for (int j = 0; j < objects.length; j++)
                objects[j] = currentSetPane.getComboBox().getItemAt(j);
            imputComponent.addComponent(currentSetPane.getComboBox(), objects, InputComponent.COMBO_BOX);
            imputComponent.addComponent(currentSetPane.getButton(), currentSetPane.getButton().getText(), InputComponent.BUTTON);

            deployObject.addInputObject(imputComponent);

            //System.out.println("CARDPANE: " + currentSetPane.getCardPane());
            if (currentSetPane.getCardPane() != null)
            {
                CardPane cardPane = currentSetPane.getCardPane();
                String id = cardPane.getID();
                if ( tempDeployCardPane.get(id) != null )
                {
                    CardComponent cardComponent = (CardComponent) ( (Object[]) tempDeployCardPane.get(id))[0];
                    imputComponent.setCardComponent(cardComponent);
                }
            }
            //REF: currentSetPane.getParent() is UpperPane
            //REF: currentSetPane.getParent().getParent() is DesignPane

            //if (currentSetPane.isDisplayable() && currentSetPane.getParent().getParent() != null)
            if ( currentSetPane.getParent() != null && currentSetPane.getParent().getParent() != null)
            {
                String id = ( (DesignPane) currentSetPane.getParent().getParent()).getID();
                System.out.println("Frame ID: " + id);
                ( (DeployFrame) deployObject.getFrameObject(id)).addInputComponent(imputComponent);
            }
        }

        Vector allInternalSetPane = UIDesigner.sourceList.inputComponentInternal; //in source list

        for (int i = 0; i < allInternalSetPane.size(); i++)
        {
            DiagramComponentSetPanel currentSetPane = (DiagramComponentSetPanel) allInternalSetPane.elementAt(i);

            InputComponent imputComponent = new InputComponent(currentSetPane, currentSetPane.getName(), currentSetPane.toString(), currentSetPane.getGetMode(), currentSetPane.getDefultValue());

            imputComponent.addComponent(currentSetPane.getLabel(), currentSetPane.getLabel().getText(), InputComponent.LABEL);
            imputComponent.addComponent(currentSetPane.getTextField(), currentSetPane.getTextField().getText(), InputComponent.TEXT_FIELD);
            Object[] objects = new Object[currentSetPane.getComboBox().getItemCount()];
            for (int j = 0; j < objects.length; j++)
                objects[j] = currentSetPane.getComboBox().getItemAt(j);
            imputComponent.addComponent(currentSetPane.getComboBox(), objects, InputComponent.COMBO_BOX);
            imputComponent.addComponent(currentSetPane.getButton(), currentSetPane.getButton().getText(), InputComponent.BUTTON);

            deployObject.addInternalInputObject(imputComponent);

            //if (currentSetPane.isDisplayable() && currentSetPane.getParent().getParent() != null)
            if ( currentSetPane.getParent() != null && currentSetPane.getParent().getParent() != null)
            {
                String id = ( (DesignPane) currentSetPane.getParent().getParent()).getID();
                System.out.println("Frame ID: " + id);
                ( (DeployFrame) deployObject.getFrameObject(id)).addInputComponent(imputComponent);
            }
        }

        Vector allReport = ReportDesignerControl.reports;

        for (int i = 0; i < allReport.size(); i++)
        {
            PrintEditor printEditor = (PrintEditor)allReport.elementAt(i);
            printEditor.getControl().getReportDocument().prepareImage();
            System.out.println("REPORT PAGES: "+printEditor.getControl().getReportDocument().getNumOfReportPage());
            deployObject.addReport(printEditor.getControl().getReportDocument());
        }

        return deployObject;



        /*
                 Vector allActionComponent = DesignerControl.currentDesignPane.actionComponent;
                 //TEMP commented
                 //int i = 0;
                 for ( int i = 0 ; i < allActionComponent.size() ; i ++ )
                 {
            DiagramActionSetPanel currentSetPane = (DiagramActionSetPanel)allActionComponent.elementAt(i);
            DeployActionComponent actionComponent = new DeployActionComponent(currentSetPane.getButton(), currentSetPane.getDAction());
            deployObject.addActionObject(actionComponent);
                 }
         */
    }
}