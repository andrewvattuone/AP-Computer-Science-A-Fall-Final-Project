import java.util.*;
public class Skier //#16 - meaninful class name
{
  //#9 - Instance variables
  private Skis ski; //Ski made out of skis class
  private ArrayList<Double> times = new ArrayList<Double>(); //#13 - Array List of doubles, used to store the time it takes the skier to ski down each slope 
  private String[] otherEquipment = {"Hat", "Right Glove", "Left Glove", "Ski Goggles", "Facemask", "Poles"};//#12 - array of Strings, used to store the extra skiing equipment that a skier normally has in addition to typical skis
  private int energyIndex = (int) Math.pow((Math.random() * 3) + 1, 2) + 1; //#6 - call to Math.pow and Math.random, #8 - use of casting, #3 - int data type, #16 - meaningful variable name, variable used to generate a random number for energyIndex, which represents the total energy that the skier has for the day, which can be used to go skiing 
  private int numItemsLost; //#3 - int data type, used to track the number of otherEquipment items the user has lost

  //#9 - multi-arg constructor for Skier class, using a Skis variable as a parameter
  public Skier(Skis ski)
  {
    this.ski = ski;
  }

  public void loseEquipment() //#10 - student-designed "other method", #9 - processor method, #16 - meaningful method name, used to remove a random piece of the other equipment a skier carries besides their skis. Note that if a randomly chosen piece of equipment is already missing, then no piece of equipment will go missing when this method is used (this is intentional)
  {
    int lostItemIndex = (int) (Math.random() * otherEquipment.length); //#3 - int data type,Generates a random number that will be one of the indicies of otherEquipment
    String lostItem = otherEquipment[lostItemIndex]; 
    //Sets the lostItem string equal to the string at lostItemIndex of the array otherEquipment
    //#2 - if statement used to check if the chosen item isn't already lost
    if (lostItem.length() >= 8 && !lostItem.substring(0,8).equals("Missing ")) //#7 - string method .substring used, #1 - >= relational operator, #1 - ! logical operator
    {
      otherEquipment[lostItemIndex] = "Missing " + lostItem; //adds the word "Missing" to the front of the string in the array otherEquipment at lostItemIndex to identify that the item has been lost
    }
    numItemsLost++;
  }

  //#10 - student designed "other method", #9 - processor method, method is used to make the skier "go skiing." The user inputs the number of times they want to ski down the particular slope, the length of the slope in feet, and a boolean to indicate whether or not the slope is icy. The method calculates the time it takes the user to ski down the slope each time, and stores each value in the times array list
  public void goSkiing(int numTimes, double slopeLength, boolean isIcySlope) //#3 - int, double, and boolean data types used in goSkiing parameters
  {
    times.clear(); //Clears the times array list to avoid having times from previous calls to the method goSkiing being accidentally added to the list, which is only supposed to contain the times of a single call to goSkiing
    int timeNum = 0; //#3 - int data type, variable used to store the number of times the user has gone down this particular slope

    //#4 - while loop, used to determine how much time it takes the user to go down the slope for every time they ski down the slope, storing that value in the times array list
    while (timeNum < numTimes && energyIndex > 0) //#1 - < and > relational operators, && logical operator
    {
      double mph = Math.random() * 7 + 13; //#3 - double data type, random number used to determine the speed of the skier for this particular run

      //#5 - switch statement, used to increase or decrease the skier's speed based on the type of skis they have
      switch(ski.getType())
      {
        case "Alpine":
        mph += 1;
        break;
        case "Nordic":
        mph -= 0.5;
        break;
        default:
        mph -= 1;
      }

      //#2 - nested if-then-else statement used to increase the skier's speed if the slope is icy by a value based on the length of the skier's skis
      if (isIcySlope)
      {
        if (ski.calcSkiLength() >= 165) //#1 - relational operator >=
        {
          mph += 1.5;
        }
        else if (ski.calcSkiLength() <= 140) //#1 - relational operator <=
        {
          mph += 0.5;
        }
        else
        {
          mph += 1;
        }
      }

      //#3 - double data type, next three variables used to conver the slope length into miles, get the time it takes the skier to go down the slope in hours, and then converts that time into secodns
      double lengthInMiles = slopeLength / 5280;
      double timeInHours = lengthInMiles / mph;
      double time = timeInHours * 3600; //in seconds

      times.add(time);//adds the time it takes the skier to go down the slope to the times array list
      timeNum++; //increases timeNum by 1 to indicate that the skier has completed another run of the slope
    }
    loseEquipment(); //causes a random piece of the skier's other equipment to become lost
    energyIndex -= 4; //decreases the energyIndex of the skier by 4
  }

  //#10 - student designed "other method," #9 - processor method, used to help print out the values of the times Array List in the main method with words such as "and" and "seconds" for grammatical purposes
  public void printTimes()
  {
    System.out.println("Your times for that slope were:");
    //#13 - for loop used to traverse the times ArrayList
    for (int index = 0; index < times.size(); index++)
    {
      //If-then-else statement used to determine what words should be printed out along with the times based on the location of the current value in the times ArrayList
      //#2 - if-then-esle statement, first statement used to determine if the current index is the last index in the times ArrayList
      if (index == times.size() - 1 && times.size() != 1) //#1-relational operators == and != used, logical operator && used
      {
        System.out.println(" and " + times.get(index) + " seconds");
      }
      //used to determine if the times ArrayList contains only one value
      else if (times.size() == 1)
      {
        System.out.println(times.get(index) + " seconds");
      }
      else //executes for all other indicies 
      {
        System.out.print(times.get(index) + " seconds, ");
      }
    }
  }

  //#14 - method used to acces the minimum value of the times ArrayList, #9 - processor method, in this case calculating the fastest time in the list
  public double calcFastestTime()
  {
    //#2 - if-else statement that throws an error if the times ArrayList is empty, which means the skier hasn't gone skiing yet
    if (times.isEmpty())
    {
      throw new IllegalArgumentException("You need to go skiing before you can access your fastest times!");
    } 
    else
    {
      double fastestTime = times.get(0); //#3 - double data type, variable that will help to determine the fastest time in the array list
      //for loop that goes through each value in the array list
      for (int index = 0; index < times.size(); index++)
      {
        //if a value is smaller than fastestTime, fastestTime is set to that value
        if (times.get(index) < fastestTime) //#1 - relational operator <
        {
          fastestTime = times.get(index);
        }
      }
      return fastestTime;
    }
  }

  //#10 - student designed "other method," #9 - processor method, used to calculate the average time for each run on the specific slope
  public double calcAverageTime()
  {
    //#2 - if-then-else statement with throw error
    if (times.isEmpty())
    {
      throw new IllegalArgumentException("You need to go skiing before you can access your fastest times!");
    } 
    else
    {
      double sum = 0; //#3 - double data type
      //#4 - for-each loop, used to sum up each value in the times ArrayList
      for (double time : times)
      {
        sum += time;
      }
      double average = sum / times.size(); //#3 - double data type,used to calculate the average based on the sum calculated using the for-each loop and the size of the times ArrayList
      return average;
    }
  }

  //#10 - getter method, #9 - getter method, used to acces the skier's energyIndex
  public int getEnergyIndex()
  {
    return energyIndex;
  }

  //#10 - student designed "other method," #9 - processor method, used to calculate the minimum amount of time the skier needs to rest for to replenish enough of their energy points so they can leave(in hours and half hours)
  public String calcMinRestTime()
  {
    double restHours = Math.abs(energyIndex) / 2.0;
    String restTime;

    if (restHours == 0.5)
    {
      restTime = " 1 hour";
    }
    else if (restHours % 1 != 0) //#1 - != relational operator
    {
      int hoursOnly = (int) (restHours - (restHours % 1)); //#8 - use of casting
      restTime = hoursOnly + 1 + " hours";
    }
    else
    {
      int restHoursInteger = (int) restHours; //#8 - use of casting
      restTime = restHoursInteger + " hours and 30 minutes";
    }
    return restTime;
  }

  //#10 - student designed "other method," #9 - processor method, used to increase the skier's energyIndex by a certain amount based on the numbers of hours they rest (one point is added for each half hour rested)
  public void rest(double hours)
  {
    int numHalfHours = (int) (hours * 2.0); //#3 - int data type. #8 - use of casting
    //#4 - for loop, used to add points back to the energyIndex based on the number of hours rested
    for (int index = 0; index < numHalfHours; index++)
    {
      energyIndex += 1;
    }
  }

  //#10 - student designed "other method," #9 - processor method, used to identify which items have been lost, adding those items to a string so it can be printed out in the main method
  public String identifyLostItems()
  {
    String lostItems = "";
    //#12 - for loop used to traverse otherEquipment ArrayList
    for (int index = 0; index < otherEquipment.length; index++)
    {
      String lostItem = otherEquipment[index];
      if (lostItem.length() >= 8 && lostItem.substring(0,8).equals("Missing "))
      {
        lostItems += otherEquipment[index].substring(8) + ", ";
      }
    }

    if (lostItems.equals(""))
    {
      return "None";
    }
    lostItems = lostItems.substring(0,lostItems.length() - 2);
    return lostItems;
  }

  //#10 - overloaded method, #9 - processor method, used to find the items that were marked as "missing" and unmark those items as missing, afterward printing out a statement saying that all the items have been found
  public void findLostItems()
  {
    //for loop used to check each value in otherEquipment array
    for (int index = 0; index < otherEquipment.length; index++)
    {
      String lostItem = otherEquipment[index]; //created so that otherEquipment[index] doesn't have to be written in the code so many times
      if (lostItem.length() >= 8 && lostItem.substring(0,8).equals("Missing "))
      {

        otherEquipment[index] = lostItem.substring(8); //removes the "Missing " string from the string, so that the string returns to normal and is no longer marked as lost
      }
    }
    System.out.println("You have found all of your lost items! You can leave the resort now.");
  }

  //#10 - overloaded method, #9 - processor method, functions exactly the same as the other findLostItems method except takes an argument that represents the number of items that the user wants to find (so the user can choose to find some of their items rather than all of their items)
  public void findLostItems(int numItemsToFind)
  {
    int numItemsFound = 0;
    if (numItemsToFind > numItemsLost)
    {
      throw new IllegalArgumentException("Error. You cannot search for more items than the number you have already lost!");
    }
    //For loop used to search for items labled as missing, then removing the "missing" label from it once the missing item is found
    for (int index = 0; index < otherEquipment.length; index++)
    {
      String lostItem = otherEquipment[index];
      if (lostItem.length() >= 8 && lostItem.substring(0,8).equals("Missing "))
      {

        otherEquipment[index] = lostItem.substring(8);
        numItemsFound++;
      }

      //executes if the requested number of items have been found, and if the requested number of items to find are less than the actual number of items found
      if (numItemsFound == numItemsToFind && numItemsFound < numItemsLost)
      {
        System.out.println("After finding " + numItemsToFind + " of your items, you decide to leave the resort.");
        return; //Used to end the method once the requested number of items have been found
      }
    }
    //Prints out statement if all the items have been found
    System.out.println("You have found all of your lost items! You can leave the resort now.");
  }
}