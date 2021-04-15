package cecs227project;

import java.io.*;

public class FileNode{
    private File file;
    String filename;
    
    public FileNode(String filename){
      file = new File(filename);
    }
    public FileNode(String name, File f){
        filename = name;
        file = f;
    }
    public File getFile(){
        return file;
    }
    public String toString(){
        if(file.getName().equals("")){
            return file.getPath();
        }else{
            return file.getName();
        }
    }
    public boolean isDirectory(){
        return file.isDirectory();
    }
}