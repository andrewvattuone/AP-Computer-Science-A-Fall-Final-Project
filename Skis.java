public class Skis //#16 - meaningful class name
{
  //#9 - Instance variables for brand of skis, the type of skis, the height of the skier, and a boolean to determine whether or not the skier is a beginner
  private String brand; //manufacturer of the skis 
  private String type; //different types of skis with different shapes and used for different types of skiing, usually affect the speed of the skier depending on the specific type of skiing
  private int skierHeight; //#3 - int data type
  private boolean isBeginner; //#3 - boolean data type

  //#9 - Zero-arg constructor for Skis class
  public Skis()
  {
    brand = "Nordica";
    type = "Alpine";
    skierHeight = 70;
    isBeginner = true;
  }

  //#9 - Multi-arg constructor for Skis class using listed variables below as parameters
  public Skis(String brand, String type, int skierHeight, boolean isBeginner)
  {
    this.brand = brand;
    this.type = type;
    this.skierHeight = skierHeight;
    this.isBeginner = isBeginner;

    //#2 - If statement with throw error, checking to see if an invalid ski type is chosen and if so, throws the error message statement, #1-Logical operators ! and && used, #7 - String method .equals() used 
    if (!type.equals("Alpine") && !type.equals("Nordic") && !type.equals("Cross-Country") && !type.equals("Backcountry"))
    {
      throw new IllegalArgumentException("Invalid ski type! You can only choose \"Alpine,\" \"Backcountry,\" \"Cross-Country,\" or \"Nordic\" skis."); //Throw error message telling the user to input the correct types of skis
    }
  }

  //#10 - student-designed "other method," #9 - Processor method, used to calculate the length of a person's skis based on their height and whether or not they are a new skier
  public int calcSkiLength()
  {
    int skiLength = 0; //in centimeters
    //#2 - If-then-else statement (without throw error) used to determine the length of a person's skis, in cm, based on their height
    if (skierHeight >= 76) //#1 - Relational operator >=, used throughout the if-then-else statement/chain below
    {
      skiLength = 181;
    }
    else if (skierHeight >= 74) 
    {
      skiLength = 174;
    }
    else if (skierHeight >= 72)
    {
      skiLength = 170;
    }
    else if (skierHeight >= 70)
    {
      skiLength = 168;
    }
    else if (skierHeight >= 68)
    {
      skiLength = 161;
    }
    else if (skierHeight >= 66)
    {
      skiLength = 156;
    }
    else if (skierHeight >= 64)
    {
      skiLength = 150;
    }
    else if (skierHeight >= 62)
    {
      skiLength = 148;
    }
    else if (skierHeight >= 60)
    {
      skiLength = 142;
    }
    else if (skierHeight >= 58)
    {
      skiLength = 139;
    }
    else if (skierHeight >= 56)
    {
      skiLength = 134;
    }
    else if (skierHeight >= 54)
    {
      skiLength= 128;
    }
    else 
    {
      skiLength = 120;
    }

    //Decreases ski length by 5cm if boolean isBiginner is true
    if (isBeginner)
    {
      skiLength -= 5;
    }

    return skiLength;
  } 

  //#10 - Getter method, #9 - setter method, used to get the type of the skis
  public String getType()
  {
    return type;
  }

  //#10 - Setter method, #9 - setter method, used to change the brand of the skis
  public void setBrand(String brand)
  {
    this.brand = brand;
  }

  //#9 - toString method, used to print out the brand, type, and ski length
  public String toString()
  {
    return "The brand of your skis is: " + brand + "\nThe type of skis you have is: " + type + "\nThe length of your skis is: " + calcSkiLength() + " centimeters";
  }
}