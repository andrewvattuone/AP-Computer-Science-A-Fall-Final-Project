import java.util.*;
public class Main //#11 - Driver class
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in); //#17 - scanner used for user input

    //Following code asks the user to input the relevant information to create an object of the Skis class, storing each input as a variable
    System.out.println("Please enter the brand of your skis and the type of your skis.");
    String brand = input.nextLine();
    String type = input.nextLine();
    System.out.println("Please enter your height in inches and enter \"true\" or \"false\" to state whether you are a beginner skier or not.");
    int height = input.nextInt(); //#3 - int data type
    boolean isBeginner = input.nextBoolean(); //#3 - boolean data type

    //creates a new Skis object called ski1 and prints out its information using its toString method
    Skis ski1 = new Skis(brand, type, height, isBeginner);    
    System.out.println(ski1);

    //creates a new Skier object out of the ski1 object
    Skier skier1 = new Skier(ski1);
    System.out.println(); //empty line of space
    System.out.println("It's time to go skiing! Enter the number of times you want to ski, the length of the slope (in feet) you are skiing, and enter \"true\" or \"false\" to indicate whether the slope is icy or not.");

    boolean needsRest = false;//#3 - boolean data type, used to indicate whether the skier needs rest
    String skiingChoice;//initialized so that string can be used later in the "while" part of the do-while loop
    //#4 - do-while loop, used to perform the goSkiing method, print out the times for each run, and calculate the fastest and average times as many times as the skier wants (so long as their input is "yes" and their energy index doesn't fall to 0 or lower)
    do
    {
      //variables that store user-inputted values that are necessary for the goSkiing method to function
      int numTimes = input.nextInt();//#3 - int data type
      double slopeLength = input.nextDouble(); //#3 - double data type
      boolean isIcySlope = input.nextBoolean(); //#3 - boolean data type

      //performs goSkiing, prints out the times, prints out the fastest time, and prints out the average time of each run
      skier1.goSkiing(numTimes, slopeLength, isIcySlope);
      skier1.printTimes();
      System.out.println("Your fastest time was: " + skier1.calcFastestTime() + " seconds");
      System.out.println("Your average time was: " + skier1.calcAverageTime() + " seconds");

      //tests to see if the energyIndex of the skier is less than or equal to 0
      if (skier1.getEnergyIndex() <= 0) //#1 - relational operater <= used
      {
        System.out.println(); //empty line of space
        System.out.println("You are too tired to keep skiing today. You need to rest for at least " + skier1.calcMinRestTime() + " before you can leave the resort. Enter the number of hours you want to rest for as a double.");
        needsRest = true; //sets to true so later when it goes to the portion of the code where the skier rests, it doesn't force the skier to rest if the skier hasn't lost all of their energyIndex points
        break;//breaks out of do-while loop
      }


      System.out.println("Would you like to go skiing again?");
      input.nextLine(); //this piece of code is used to avoid an error where the input.nextLine() statement is skipped, as after input.nextInt() or input.nextDouble() is used the nextLine statement won't be viewed unless this statement is used, which would otherise make it impossible for a user to input a string and thus make the code unable to properly perform its functions
      skiingChoice = input.nextLine();

      //#2 - if-then-else statement, tests to see whether the user chose "yes" or "no," breaking out of the for loop if the answer was no while printing out a statement asking the user to re-enter the values if yes
      if (skiingChoice.equalsIgnoreCase("No")) //#7 -  .equalsIgnoreCase string method used
      {
        break;
      }
      else if (skiingChoice.equalsIgnoreCase("Yes")) //#7 - string method .equalsIgnoreCase used
      {
        System.out.println("Great! Re-enter the same data, but this time for a different slope.");
      }
    }
    while (skiingChoice.equalsIgnoreCase("Yes")); //loop only runs while the user chooses to keep skiing, and the input will be "yes" if so and "no" if not

    //statement, tests whether the skier needs rest or not. If so, it asks the skier to rest for a certain number of hours before they can leave the resort, and if not, just prints out a statement saying that the skier has decided to leave the resort
    if (needsRest)
    {
      //#4 - do-while loop, keeps running until the energyIndex of the skier is greater than 0 so the skier can't leave the resort before this requirement is met
      do
      {
        double restTime = input.nextDouble(); //#3 - double data type
        skier1.rest(restTime);
        //used to see if the energy index is still lower than 0 after rest, if so printing out a statement to get more rest
        if (skier1.getEnergyIndex() <= 0)
        {
          System.out.println("You need to rest for at least " +  skier1.calcMinRestTime() + " longer before you can leave the resort.");
        }
      }
      while (skier1.getEnergyIndex() <= 0); 
      System.out.println("Your have rested for long enough and can now leave the resort.");
      input.nextLine(); //this piece of code is used to avoid an error where the input.nextLine() statement is skipped, as after input.nextInt() or input.nextDouble() is used the nextLine statement won't be viewed unless this statement is used, which would otherise make it impossible for a user to input a string and thus make the code unable to properly perform its functions
    }
    else
    {
      System.out.println("You have stopped skiing for the day and decide to leave the resort.");
    }

    //prints out statement telling the user that they have lost some of their other skiing equipment, and asks them if they want to find it before they leave the resort   
    System.out.println("While you are leaving the resort, you realize that you have lost some of your other skiing equipment. Below is a list of the equipment you have lost:");
    System.out.println(skier1.identifyLostItems());
    System.out.println("Would you like to find your lost items?");
    String itemsChoice = input.nextLine();

    //if-then-else statement, if first statement is true, executes findLostItems method (with no argument version), while if it's fale then prints out a separate statement
    if (itemsChoice.substring(0,1).equalsIgnoreCase("y")) //#7 - string method .equalsIgnoreCase used
    {
      skier1.findLostItems();
    }
    else
    {
      System.out.println("You feel the cold chill of the wind as you leave the resort, wishing you had retrieved your lost items before you left.");
    }
  }
}