
import java.util.Scanner;

//this class has the methods that checks to make sure inputs are integers or doubles and if not prompts user to re-enter correct input

public class Check {
    private Scanner beedo = new Scanner(System.in);
    public int integerCheck()
    {
        int inputCheck = 0;
        boolean inputValid = false;
        while (!inputValid)
        {
            String input =  beedo.nextLine();
            try
            {
                inputCheck = Integer.parseInt(input);
                inputValid = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println(input + " is not a valid integer. Please enter an integer: ");
            }
        }
        return inputCheck;
    }

    public double doubleCheck()
    {
        double inputCheck = 0.0;
        boolean inputValid = false;
        while (!inputValid)
        {
            String input = beedo.nextLine();
            try
            {
                inputCheck = Double.parseDouble(input);
                inputValid = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println(input + " is not a valid double. Please enter a double: ");
            }
        }
        return inputCheck;

    }
}
