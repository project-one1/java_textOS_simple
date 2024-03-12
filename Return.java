public class Return{
    private int intValue;
    private double doubleValue;

    public Return(int intValue, double doubleValue){
        this.intValue = intValue;
        this.doubleValue = doubleValue;
    }

    public int getInt(){
        return intValue;
    }

    public double getDouble(){
        return doubleValue;
    }
}