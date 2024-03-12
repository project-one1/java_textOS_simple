public class prefReturner {
    private int decimalRound;
    private int genericLoadingTime;
    private int luckMultiplier;

    public prefReturner(int decimalRound, int genericLoadingTime, int luckMultiplier){
        this.decimalRound = decimalRound;
        this.genericLoadingTime = genericLoadingTime;
        this.luckMultiplier = luckMultiplier;
    }

    public int getDecRound(){
        return decimalRound;
    }

    public int genericLoadingTime(){
        return genericLoadingTime;
    }

    public int luckMultiplier(){
        return luckMultiplier;
    }
}
