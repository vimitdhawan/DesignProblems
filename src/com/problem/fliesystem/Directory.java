package com.problem.fliesystem;

import java.util.List;

public class Directory extends Entity {
    private List<Directory> directories;
    private List<File> files;

    Directory(Directory parent, String name) {
        super(parent, name, "directory");
    }

    public boolean deleteFile(File file) {
        return files.remove(file);
    }

    public boolean deleteDir(Directory dir){
        return directories.remove(dir);
    }

    public boolean delete(){
        return super.parent.deleteDir(this);
    }
}
