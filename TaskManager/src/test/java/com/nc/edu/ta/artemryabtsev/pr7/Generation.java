package com.nc.edu.ta.artemryabtsev.pr7;

public class Generation {
    String[] lowerChar = new String[] {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l",
            "z", "x", "c", "v", "b", "n", "m"};
    String[] upperChar = new String[] {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L",
            "Z", "X", "C", "V", "B", "N", "M"};
    String[] numbers = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] symbol = new String[] {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "[", "]", "{", "}", ":", ";", "<", ">", ",", "?", "/"};

    public int randomNumber (int min, int max){
        max -= min;
        return (int) (Math.random()* ++max) + min;
    }

    public String generationLowerChar(int min, int max) {
        int lowerCharLength = randomNumber(min, max);
        String lowerCharString = "";
        for (int i = 0; i < lowerCharLength; i++) {
            lowerCharString += lowerChar[randomNumber(1, 25)];
        }
        return lowerCharString;
    }

    public String generationUpperChar(int min, int max) {
        int upperCharLength = randomNumber(min, max);
        String upperCharString = "";
        for (int i = 0; i < upperCharLength; i++) {
            upperCharString += upperChar[randomNumber(1, 25)];
        }
        return upperCharString;
    }

    public String generationNumbers(int min, int max) {
        int numbersLength = randomNumber(min, max);
        String numbersString = "";
        for (int i = 0; i < numbersLength; i++) {
            numbersString += numbers[randomNumber(1, 8)];
        }
        return numbersString;
    }

    public String generationSymbol(int min, int max) {
        int symbolLength = randomNumber(min, max);
        String symbolString = "";
        for (int i = 0; i < symbolLength; i++) {
            symbolString += symbol[randomNumber(1, 20)];
        }
        return symbolString;
    }

    public String generationUserName(){
        String lowerCharString = generationLowerChar(4, 15);
        String upperCharString = generationUpperChar(1, 13);
        String numbersString = generationNumbers(1, 13);
        String name = lowerCharString + upperCharString + numbersString;
        return name;
    }

    public String generationUserPassword(){
        String lowerCharString = generationLowerChar(5, 10);
        String upperCharString = generationUpperChar(1, 5);
        String numbersString = generationNumbers(1, 5);
        String symbolString = generationSymbol(1, 5);
        String password = lowerCharString + upperCharString + numbersString + symbolString;
        return password;
    }

    public String generationEmail(){
        String lowerCharString = generationLowerChar(5, 10);
        String email = lowerCharString + "@" + lowerCharString + ".com";
        return email;
    }
}
