package com.problem.fliesystem;

public class File extends Entity {
    private String content;
    private int size;
    File(Directory dir, String name, String content, int size){
        super(dir, name, "file");
        this.content = content;
        this.size = size;
    }

    public void delete(){
        super.parent.deleteFile(this);
    }

}
