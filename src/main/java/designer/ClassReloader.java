package designer;

import java.io.*;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Class that can be used to dynamically load a class, and then reload an updated version
 * This is set up to only load classes whose names end with "Special", but you can change
 * that easy enough
 *
 * You may use this class as you wish, either as a basis for your own code
 * or just drop it into your project
 * However, I will not be held responisble if your program doesn't work, or
 * your computer blows up
 * @author Simon Macneall macneall@iinet.net.au
 */
public class ClassReloader extends ClassLoader
{
    private Hashtable classes_hash = new Hashtable();

    public ClassReloader()
    {
    }

    public synchronized Class loadClass(String typeName, boolean resolveIt) throws ClassNotFoundException
    {
        // See if type has already been loaded by
        // this class loader
        Class result = findLoadedClass(typeName);
        if (result != null)
        {
            // Return an already-loaded class
            return result;
        }

        // Check with the primordial class loader
        // you can change the next line to make it only load certain classes
        // I found the easiest way was to name the reloadable classes
        // a special way.
        if (!typeName.equals("InternalModel"))
        {
            try
            {
                result = super.findSystemClass(typeName);
                // Return a system class
                return result;
            } catch (ClassNotFoundException e)
            {
            }
        }

        // Try to load it
        byte typeData[] = getType(typeName);
        if (typeData == null)
        {
            throw new ClassNotFoundException();
        }

        // Parse it
        result = defineClass(typeName, typeData, 0, typeData.length);
        if (result == null)
        {
            throw new ClassFormatError();
        }

        if (resolveIt)
        {
            resolveClass(result);
        }

        // return class
        return result;
    }

    private byte[] readClassFromDisk(String classname)
    {
        System.out.println("classname="+classname);

        byte[] result;
        String complete_classname = classname.replace('.',File.separatorChar)+".class";
        try
        {
            FileInputStream fi = new FileInputStream(complete_classname);
            result = new byte[fi.available()];
            fi.read(result);
            return result;
        } catch (Exception e)
        {
            return null;
        }
    }

    private byte[] getType(String typeName)
    {
        FileInputStream fis;
        String fileName = typeName.replace('.', File.separatorChar) + ".class";
        try
        {
            fis = new FileInputStream(fileName);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("file not found");
            return null;
        }

        BufferedInputStream bis = new BufferedInputStream(fis);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try
        {
            int c = bis.read();
            while (c != -1)
            {
                out.write(c);
                c = bis.read();
            }
        } catch (IOException e)
        {
            System.out.println("io exception");
            return null;
        }
        return out.toByteArray();
    }
}

