package com.problem.solid;

public class SingleResponsibility {
    private ProcessService processService;
    private DBService dbService;
}

class ProcessService{
    public void processData(){}
}
class DBService{
    public void fetchDBData(){}
}



class BadSingleResponsibility{
    public void fetchDBData(){}
    public void processData(){}
    public void publishData(){}

}