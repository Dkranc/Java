
package calculater;
/*
the number class is a nasic very small class . 
we have two instance variables:
num is the actual number that the number holds 
positive is a boolean that is true when the number is positive and false when its negetive
we have two get methods one for each variable.
*/
public class Number {
    private double _num;
    private boolean _positive;
    
    /*
    basic constructor. we dont have a defualt constructor
    */
    public Number(double num,boolean pos){
    this._num=num;
    this._positive=pos;
    }
    
    public double getNum(){
        if(this._positive==true) return this._num;
        else return -this._num;
    }
    public boolean getPos(){
    return this._positive;
    }
}
