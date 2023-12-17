import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // This creates a new instance of the scanner class so that users can have input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the sentence validator");
        System.out.println("Enter your sentence or 0 to exit: ");
        // Users input their first sentence into string sentence
        String sentence = scanner.nextLine();
        // The while loop runs until 0 is put in and ends the program
        while (!sentence.equals("0")) {
            System.out.println("Is your sentence valid? ");
            // This calls the isValidSentence method and passes in the users sentence
            isValidSentence(sentence);
            // This repeats the users input and will run the method again unless 0 is put in
            System.out.println("Enter your next sentence or 0 to exit: ");
            sentence = scanner.nextLine();
        }
        // Lets user know they have exited
        System.out.println("You have exited");
    }

    // This is the isValidSentence method with the users passed in sentence
    // It will check the 5 rules and will let the user know if their sentence is valid
    private static void isValidSentence(String sentence) {
        // Initialise the sentence as true at first before checking the rules
        boolean sentenceTrue = true;

        // Rule 1: Check that the sentence starts with a capital letter.
        // Checks if empty and then checks if first and second character are not uppercase
        if (sentence.isEmpty() || !Character.isUpperCase(sentence.charAt(0)) && !Character.isUpperCase(sentence.charAt(1))) {
            System.out.println("First letter is not a capital!");
            // Sets the sentence as not valid
            sentenceTrue = false;
        }

        //Rule 2: Check if the sentence has an even number of quotation marks.
        //For loop will loop through the sentence string as a character array
        //If checks to see if there is quotation marks and will count them in quoteCount
        int quoteCount = 0;
        for (char c : sentence.toCharArray()) {
            if (c == '"' || c == '“' || c == '”') {
                quoteCount++;
            }
        }
        // If checks to see if there is not an even number of quotation marks
        if (quoteCount % 2 != 0) {
            System.out.println("There is not an even number of quotation marks!");
            // Sets the sentence as not valid
            sentenceTrue = false;
        }

        // Rule 3: String ends with one of the specified sentence termination characters.
        // lastChar gets the character at the end of the sentence
        // If statement checks it is not . or ! or ?
        char lastChar = sentence.charAt(sentence.length() - 1);
        if (lastChar != '.' && lastChar != '?' && lastChar != '!') {
            System.out.println("Last character is not . or ! or ?");
            // Sets the sentence as not valid
            sentenceTrue = false;
        }

        // Rule 4: String has no period characters other than the last character.
        // Checks that the index of the period is not equal to the index of the last character

        if (sentence.indexOf('.') != sentence.indexOf(lastChar)) {
            System.out.println("There is period characters in the sentence other than the last character!");
            // Sets the sentence as not valid
            sentenceTrue = false;
        }

        // Rule 5: There are integers below 13 not spelled out.
        //It will split it by whitespaces, period marks or comas and put each word or integer into a string array
        //It splits by period marks incase the last character in the sentence is a number not spelled out below 13
        String[] words = sentence.split("[\\s.,]+");
        // Loops through all the split up words in the string array
        for (String word : words) {
            try {
                // parseInt turns the words into integers
                int parsedInt = Integer.parseInt(word);
                // Checks the integers are not between 0 and 13
                if (parsedInt > 0 && parsedInt < 13) {
                    System.out.println("There is a number below 13 not spelt out number is: " + parsedInt);
                    // Sets the sentence as not valid
                    sentenceTrue = false;
                }
            } catch (NumberFormatException e) {
                // Skips to the next iteration if the word is not a valid integer
                continue;
            }
        }

        // Outputs to the user after all the checks if their sentence was valid or not
        System.out.println("Your sentence is: " + sentenceTrue);
    }
}