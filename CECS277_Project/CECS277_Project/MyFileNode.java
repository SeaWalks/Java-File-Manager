package CECS277_Project;


import java.io.*;

public class MyFileNode {
    File file;
    String fileName;

    public MyFileNode(String fileName){
        file = new File(fileName);
    }

    public MyFileNode(String name, File f){
        fileName = name;
        file = f;
    }

    public File getFile(){
        return file;
    }

    @Override
	public String toString(){
        if(file.getName().equals(""))
            return file.getPath();
        return file.getName();
    }

    public boolean isDirectory(){
        return file.isDirectory();
    }



}
