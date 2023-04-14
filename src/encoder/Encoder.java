package encoder;
import java.util.*;


public class Encoder {

    static String encodedString = "";// declaring static string for encoding string
    static char[] charTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '*', '+',
',', '-', '.', '/' };//Given character Table
    
    static char[] charTableCopy = charTable.clone(); //Make copy of given table
    static char offChar; //character of Offset i.e B or F or any
    static int newOffSet = 0; //To store Offset Index/number
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);// To get input from keyboard

        String samepleString ="HELLO WORLD";  //Given Input String
       // String str = "HELLO WORLD";
        System.out.println("Enter Offset Character: "); //Enter Offset character
        char in = sc.next().charAt(0); //Read character

        int offset = 0;
        for (int i = 0; i < 44; i++) 
        { //Store offset character index in offset varible
           if (in == charTable[i]) 
           {
               offset = i;
           }
        }
        
        offChar = charTable[offset];//Store offset character
        newOffSet = offset;
        Encoder ec = new Encoder(); //Create Object of Main class
        System.out.println("Encoded String:= " + in + ec.encode(samepleString)); //call public String encode(plaintext) method
        System.out.println("Decoded String:= "+ec.decode(encodedString)); //call public String encode(plaintext) method
             
    }
    
    public String decode(String plaintext)
    {
            char[] encodedArray = encodedString.toCharArray();//convert encodedString to array
            String tr = "";
            int flag = 0;

            for (int i = 0; i < encodedArray.length; i++) 
            { //To Decode String
                for (int k = 0; k < charTable.length; k++) 
                {
                        if (encodedArray[i] == charTable[k]) 
                        { //If encoded characters matches with Offset table then add Offset to Decode String
                                flag = 1;
                                tr = tr + charTable[k + newOffSet];
                                break;
                        }
                
                        if (encodedArray[i] == ' ') 
                        { //If spaces present in array add space
                                tr = tr + " ";
                                break;
                        }

                }

                if (flag == 0) 
                { //If f=0 means No character present in table so just re-print it
                    tr = tr + encodedArray[i];
                }

                flag = 0; //Re-initialize f=0 for further Calculatitons
            }

             return tr; //return resultant String

    }
    
     public String encode(String plaintext)
     {
         char[] result = plaintext.toCharArray(); // convert plaintext to character array
         int space = 0; //varible to store number of spaces
         
         for (int i = 0; i < result.length; i++)
         { //Calculate Space in String
            if (result[i] == ' ')
            {
                space++;
            }
         }
         
         for (int j = 0; j < newOffSet; j++) 
         {

                char tem = charTable[charTable.length - 1]; //store last element of given array in tem varible
                int length = charTable.length; //Calculate length of array
                int last = charTable.length - 1;

                for (int i = length - 2; i >= 0; i--) 
                { //Shift every character from begenning to end by one time
                    charTable[last] = charTable[i]; //store ar[i]th element in last location of array
                    last--; //decrement last so all element upto 1st will Shifted to last
                }
                charTable[0] = tem; //Store last element of array at 1st location

         }
        String t = "";
        int[] position = new int[result.length]; //integer array to store index of resultant array
        int post = 0; //Index of position array
        int g = 0;
        
        int flag = 0; //Flag to check wheather element of String is present on Table or not
        for (int k = 0; k < result.length; k++)
        {
                for (int i = 0; i < charTableCopy.length; i++) 
                {
                        if (result[k] == charTableCopy[i]) 
                        { // IF element from String present in table then add its position in array
                                flag = 1; //Indicate element of String is present in Table
                                position[post] = i; //Store that element
                                post++; //Increment pos index of array
                                break;
                        }

                }
                if (flag == 0 && result[k] != ' ') 
                { //If element from input String not present in table then type cast that character to integer and store in array
                    position[post] = (int) result[k];
                    post++;
                }

                flag = 0;
                if (result[k] == ' ') 
                {//If space is present in input String then add 0 to position array
                    position[post] = 0;
                    post++;
                }
        }
        String tr = ""; //resultant encoded String to be return

        for (int i = 0; i < position.length; i++) 
        {//If 0 present in position array it Indicate presence of space so add space to encoded String
                if (position[i] == 0) 
                {
                        tr = tr + " ";
                }
                if (position[i] != 0 && position[i] >= 0 && position[i] <= 43)
                {//To encode element from table
                        tr = tr + charTable[position[i]];
                }

                if (position[i] >= 97 && position[i] <= 122)
                { //To encode element of lowerCases
                        char single = (char) position[i]; //Typecast integer to character from position array
                        tr = tr + single;
                }

                if (position[i] >= 58 && position[i] <= 64) 
                { //To encode element of special character
                        char single = (char) position[i]; ////Typecast integer to character from position array
                        tr = tr + single;
                }

                if (position[i] >= 91 && position[i] <= 96) 
                {//To encode element of special character
                        char single = (char) position[i]; //Typecast integer to character from position array
                        tr = tr + single;
                }

                if (position[i] >= 123 && position[i] <= 126) 
                {//To encode element of special character
                        char single = (char) position[i]; //Typecast integer to character from position array
                        tr = tr + single;
                }
        }
	encodedString = encodedString + tr; //Update static encodedString String for decode
	return tr; //return resultant encoded string
         
     }
}

