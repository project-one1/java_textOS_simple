//import java.util.Scanner;

/*
 * This the file that will be run!
 * @author Justin Hwang
 * @credits ChatGP
 */

public class March84G{

    //Call this func when needing to round to a specific digit
    public static double round(double num, int digit){
        double multiplier = Math.pow(10, digit); //ex) 10 to the power of 2. Multiply by 100 and truncate rest...
        //...meaning 2 digits will be left.
        return Math.round(num*multiplier)/multiplier; //divide by multiplier to give back the decimal precision
    }
    public static void spinner(int seconds){
        char[] spinner = new char[] {'/', '|', '\\', '-'};
        int milliseconds = 1000*seconds;
        int iterations = milliseconds/100;
        for(int i=0; i<iterations; i++){
            StdOut.print(spinner[i % spinner.length]);
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                StdOut.println("Animation Interrupted");
            }
            StdOut.print("\b\b"); //Type only one \b to make the spinning thing move across the screen
        }
    }

    public static void comingSoon(int totalSeconds){
        StdOut.println("Coming Soon!");
                    
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
            return; //interruption handling
        }

        StdOut.print("Returning to menu in " + (totalSeconds - 1) + " seconds...\n");
        spinner(totalSeconds - 1);
        StdOut.print("\b ");

        /*
        * Technically, you can use multithreading to execute the spinner
        * at the same time as another Thread.sleep(). That is, you can
        * do "Returning to menu in 3... 2... 1..." while the spinner is
        * running. Cool, huh?
        */
    }

    public static String luckToday(){
        double luck = 100*Math.random();
        if(luck>=0 && luck<49){
            return "Unlucky day! " + round(luck, 2) + " out of 100.";
        }
        else if(luck>=50 && luck<90){
            return "Not bad! " + round(luck, 2) + " out of 100.";
        }
        else{
            return "The luck of the Irish. " + round(luck, 2) + " out of 100.";
        }
    }

    public static Return numGuesser(int n){
        int trueValue = (int)(100*Math.random());
        double absDifference = Math.abs(trueValue - n);
        double percentError = 100*(absDifference/trueValue);
        percentError = round(percentError, 2); //Calculates a rounded percent error
        return new Return(trueValue, percentError);
    }

    public static prefReturner prefReader(){
        int decimalRound = Integer.parseInt(StdIn.readLine());
        int genericLoadingTime = Integer.parseInt(StdIn.readLine());
        int luckMultiplier = Integer.parseInt(StdIn.readLine());

        return new prefReturner(decimalRound, genericLoadingTime, luckMultiplier);
    }

    public static void userPref(String fileName){

        // int line=0; //Reads lines
        // while(StdIn.readLine() != null){
        //     line++;
        // }
        // The above lines are responsible for counting how many lines are
        // read in config.txt but it's buggy :(

        StdIn.setFile(fileName);

        prefReturner valuesRead = prefReader();
        // StdOut.println("Read " + line + " lines.");
        StdOut.println("Reports decimals to the " + valuesRead.getDecRound() + "nd place.");
        StdOut.println("Loading bar set to run for " + valuesRead.genericLoadingTime() + " seconds.");
        StdOut.println("luckMultiplier: " + valuesRead.luckMultiplier());

        StdIn.resetFile();
    }

    public static int fib(int n){ //calculates and returns the nth fibonacci number
        //first term is n=1
        //0, 1, 1, 2, 3, 5, 8, 13
        //fib(5) = fib(4) + fib(3)
        //fib(7) = fib(6) + fib(5)
        //fib(n) = fib(n-1) + fib(n-2)
        if(n<=0){
            return -1;
        }
        else if(n==1){
            return 0;
        }
        else if(n==2 || n==3){
            return 1;
        }
        n = fib(n-1) + fib(n-2);
        return n;
    }

    public static void arrayPrinter(String[][] array){ //This is only for rectangular arrays.
        //It will fail with jagged arrays.

        StdOut.println("Row major, Forward iteration");
        for(int i=0; i<array.length; i++){
            for(int j=0; j<array[i].length; j++){
                StdOut.print(array[i][j] + " "); //Row maj, Forward iteration
            }
            StdOut.println();
        }

        StdOut.println("Row major, Backward iteration");
        for(int i=array.length - 1; i>=0; i--){
            for(int j=array[i].length - 1; j>=0; j--){
                StdOut.print(array[i][j] + " "); //Row maj, Backward iteration
            }
            StdOut.println();
        }

        StdOut.println("Column major, Forward iteration");
        for(int j=0; j<array[0].length; j++){
            for(int i=0; i<array.length; i++){
                StdOut.print(array[i][j] + " "); //Col maj, Forward iteration
            }
            StdOut.println();
        }

        StdOut.println("Column major, Backward iteration");
        for(int j=array[0].length - 1; j>=0; j--){
            for(int i=array.length - 1; i>=0; i--){
                StdOut.print(array[i][j] + " "); //Col maj, Backward iteration
            }
            StdOut.println();
        }
    }

    public static int[] selectionSort(int[] array){
        for(int i=0; i<array.length; i++){
            int minIndex = i;
            for(int j = i + 1; j<array.length; j++){
                if(array[j] < array[minIndex]){
                    minIndex = j;
                }

                if(minIndex != i){
                    int temp = array[minIndex];
                    array[minIndex] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }

    public static int binarySearch(int[] array, int target){
        int low = 0; //starting index
        int high = array.length - 1;

        while(low <= high){
            int mid = (low + high)/2;

            if(array[mid] == target){
                return mid;
            }

            else if(array[mid] > target){ //if found value is higher than target
                high = mid - 1;
            }

            else if(array[mid] < target){ //if found value is lower than target
                low = mid + 1;
            }
        }
        return -1; //if nothing is found (hence if the array cannot be subdivided anymore)
    }

    public static void main(String[] args){
        boolean running = true;
        while(running){
            StdOut.println("\n\n--------------------\nOptions:\n0. Quit\n1. Your Luck Today\n2. Number Guesser");
            StdOut.println("3. 4-Func Calculator\n4. Settings\n5. nth Fibonacci Number\n6. 2D Arrays");
            StdOut.println("7. Binary Search\n8. Selection Sort\n--------------------");
            int choice = StdIn.readInt();

            switch(choice){
                case 0:
                    StdOut.println("Quitting...");
                    System.exit(0);
                case 1:
                    StdOut.println(luckToday());
                    break;
                case 2:
                    StdOut.println("Guess any number from 0 to 100:");
                    int guess = StdIn.readInt();
                    Return result = numGuesser(guess);
                    if(guess != result.getInt()){
                        StdOut.println("Your guess was off by " + result.getDouble() + " percent.");
                        StdOut.println("The actual number is " + result.getInt() + ".");
                    }
                    else{
                        StdOut.println("Nice! You guessed the number " + result.getInt() + " correctly!");
                    }
                    break;
                case 3:
                    comingSoon(4);
                    break;
                case 4:
                /*
                 * Maybe do a settings for this menu 
                 * One that uses a global variable and lets you change how many digits
                 * decimals are reported with. Neat little config.
                 */
                    StdOut.println("Enter config file name: ");
                    String fileName = StdIn.readString();
                    userPref(fileName);
                    break;
                case 5:
                    boolean stillRunning = true;
                    while(stillRunning){
                        StdOut.println("Enter a positive integer: ");
                        int n = StdIn.readInt();
                        int nFib = fib(n);
                    if(nFib == -1){
                        StdOut.println("Positive integers only.");
                    }
                    else{
                        String th = "th";
                        if(n%10 == 1){
                            th = "st";
                        }
                        else if(n%10 == 2){
                            th = "nd";
                        }
                        else if(n%10 == 3){
                            th = "rd";
                        }
                        StdOut.println("The " + n + th + " Fibonacci number is " + nFib + ".");
                        stillRunning = false;
                    }
                }
                break;
                case 6: //This one can do arrays n stuff. Good ol' CS111 practices.
                    //ALWAYS put break; after the case ends. else it'll print "invalid option" at
                    //the end of the output.

                    StdOut.println("Enter file name:");
                    String filName = StdIn.readString();
                    StdIn.setFile(filName);
                    
                    // Counting the number of lines and columns
                    int rows = 0;
                    int cols = 0;
                    
                    while (!StdIn.isEmpty()) {
                        String line = StdIn.readLine(); // Read each line
                        if (cols == 0) { // Count columns only once
                            String[] values = line.split(" "); // Split the line to count columns
                            cols = values.length;
                        }
                        rows++; // Count rows
                    }
                    
                    String[][] array = new String[rows][cols];
                    StdIn.resetFile(); // Reset to read the file again
                    StdIn.setFile(filName);
                    
                    for (int i = 0; i < rows; i++) {
                        if (!StdIn.isEmpty()) {
                            String[] lineValues = StdIn.readLine().split(" "); // Split each line into values
                            for (int j = 0; j < cols; j++) {
                                array[i][j] = lineValues[j]; // Assign values to array
                            }
                        }
                    }
                    
                    arrayPrinter(array);
                    StdIn.resetFile();                    
                    break;

                case 7:
                    int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                    StdOut.println("Enter target number: ");
                    int target = StdIn.readInt();
                    int result1 = binarySearch(a, target);
                    if(result1 == -1){
                        StdOut.println("Target value not found in array.");
                    }
                    else{
                        StdOut.println("Target value " + target + " found in array at index value " + result1 + ".");
                    }
                    break;

                case 8:
                    int[] unsorted = {7, 8, 10, 3, 21, 5, 42, 0};
                    int[] unsortedClone = unsorted.clone();
                    int[] sorted = selectionSort(unsorted);
                    boolean theSame = true;
                    for(int i=0; i<unsorted.length; i++){
                        if(unsortedClone[i] != sorted[i]){
                            theSame = false;
                        }
                    }

                    if(theSame){
                        StdOut.println("The array is already sorted. Below is the array:");
                        for(int i=0; i<unsorted.length; i++){
                            StdOut.print(unsorted[i] + " ");
                        }
                    }
                    else{
                        StdOut.println("The sorted array is:");
                        for(int i=0; i<sorted.length; i++){
                            StdOut.print(sorted[i] + " ");
                        } 
                    }
                    
                    break;
                default:
                    StdOut.println("Invalid option");

                /*
                 * I definitely could try to handle keyboard interrupt while
                 * on the main menu but I'm too lazy to do that rn.
                 * Gotta make do with the exception when shutting down for now.
                 */
            }
        }
    }
}