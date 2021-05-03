package CECS277_Project;

import java.io.*;

public class FileNode {
    File file;
    String fileName;

    public FileNode(String fileName){
        file = new File(fileName);
    }

    public FileNode(String name, File f){
        fileName = name;
        file = f;
    }

    public File getFile(){
        return file;
    }

    public String toString(){
        if(file.getName().equals(""))
            return file.getPath();
        return file.getName();
    }

    public boolean isDirectory(){
        return file.isDirectory();
    }

}
