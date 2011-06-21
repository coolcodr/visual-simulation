package designer;

import java.io.*;

public class SimpleClassLoader extends ClassLoader {
    public synchronized Class loadClass(String name, boolean resolve) {
        Class c = findLoadedClass(name);
        if (c != null) return c;
        if(!name.equals("desginer.MainControl")) {
            try {
                c = findSystemClass(name);
                if (c != null) return c;
            } catch(ClassNotFoundException e) {}
        }
        try {
            RandomAccessFile file = new RandomAccessFile(name + ".class", "r");
            byte data[] = new byte[(int)file.length()];
            file.readFully(data);
            c = defineClass(name, data, 0, data.length);
        } catch(IOException e) { System.out.println("Error reading file.");}
        if (resolve)
            resolveClass(c);
        return c;
    }
}
