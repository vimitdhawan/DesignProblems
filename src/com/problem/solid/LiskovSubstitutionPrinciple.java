package com.problem.solid;

public class LiskovSubstitutionPrinciple {
    interface Shape{
        void setHeight(double d);
    }

    interface BothShape extends Shape{
        void Width(double d);
    }

    class Rectangle implements BothShape {
        private double width;
        private double height;
        public void setHeight(double h) { height = h;}
        public void Width(double w) { width = w; }
    }

    class Square implements Shape {
        private double height;
        public void setHeight(double h) {
            this.height = h;
        }
    }

}





// Bad Examplee
class Rectangle {
    private double height;
    private double width;
    public void setHeight(double h) { height = h; }
    public void setWidth(double w) { width = w; }
}
class Square extends Rectangle {
    public void setHeight(double h) {
        super.setHeight(h);
        super.setWidth(h);
    }
    public void setWidth(double h) {
        super.setHeight(h);
        super.setWidth(h);
    }
}

