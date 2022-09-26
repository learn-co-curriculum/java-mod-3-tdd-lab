public class FizzBuzz {
    public String fizzBuzzString(int number) {
        if ((number % 3 == 0) && (number % 5 == 0)) {
            // if divisble by both 3 and 5
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            // if divisible by 3
            return "Fizz";
        } else if (number % 5 == 0) {
            // if divisible by 5
            return "Buzz";
        } else {
            // Will return a String object with the number as its value
            return Integer.toString(number);
        }
    }
}
