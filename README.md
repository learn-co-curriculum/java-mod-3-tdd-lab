# TDD Code-Along

## Learning Goals

- Practice TDD.

## Introduction

Let's consider our "FizzBuzz" example from the previous lesson, with one more
level of complexity: we need to apply this FizzBuzz logic to an array of integers
1 to n (where n is the input). We will iterate over the array and
replace each individual value in the array with its FizzBuzz-based replacement.
For example, if we pass the method the integer 15, we would get an output like
this:

```plaintext
1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz
```

## Instructions

Fork and clone this code-along repository. You should see something that looks
like the solution to our last lab in this repository. So let's reuse that to
integrate this new functionality:

```java
public class FizzBuzz {
    public String fizzBuzzString(int number) {
        if ((number % 3 == 0) && (number % 5 == 0)) {
            // if divisible by both 3 and 5
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
```

```java
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {

    FizzBuzz fizzBuzz;

    @BeforeEach
    void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testElse() {
        assertEquals("7", fizzBuzz.fizzBuzzString(7));
    }

    @Test
    void testFizz() {
        assertEquals("Fizz", fizzBuzz.fizzBuzzString(3));
    }

    @Test
    void testBuzz() {
        assertEquals("Buzz", fizzBuzz.fizzBuzzString(5));
    }

    @Test
    void testFizzBuzz() {
        assertEquals("FizzBuzz", fizzBuzz.fizzBuzzString(15));
    }
}
```

Follow along in this code-along to see how we can practice TDD.

## Test Driven Development Cycle

Now let's approach this new problem with TDD! You would take the following steps:

1. Write a simple test to test this functionality.
2. Run the test (which should fail on the first run since we haven't written any
   code yet).
3. Write the code to pass the test we just wrote.
4. Run the test again to see if it passed.
5. Repeat the process again.

Go ahead and add a test method to the `FizzBuzzTest.java` file that looks like
this:

```java
@Test
void testSingularFizzBuzzArray() {
    String[] expectedResult = {"1"};
    assertArrayEquals(expectedResult, fizzBuzz.fizzBuzzArray(1));
}
```

Now run just that test method. You should see it fail since we have not yet
written a method called `fizzBuzzArray()` yet.

So let's write that method now! Create a method called `fizzBuzzArray()` that
takes an integer parameter and returns a `String` array.

```java
public String[] fizzBuzzArray(int number) {
    String[] result = new String[number];
    return result;
}
```

If you were to run the test again, you would see it fail still since the array
is empty!

Time to refactor the method and add some real code to it!

```java
public String[] fizzBuzzArray(int number) {
    String[] result = new String[number];
    result[0] = fizzBuzzString(number);
    return result;
}
```

The code above is not sustainable to the entire problem since there is only one
element in the array. But the point of TDD is to implement small pieces at a
time and then build and refactor them as you go.

If you run your test again, it should pass this time! Yippee! So now what?

According to the TDD cycle and steps above, you will repeat this process until
you have finished developing.

## The Disabled Annotation

Sometimes when we see our tests failing before we implement the code, we may
feel a little disheartened. We may just want to ignore that unit test for now
until we have implemented it.

If we decide we want to do that, we can use the `@Disabled` annotation. Go ahead
and add the annotation to the unit test we just wrote:

```java
@Disabled
@Test
void testSingularFizzBuzzArray() {
    String[] expectedResult = {"1"};
    assertArrayEquals(expectedResult, fizzBuzz.fizzBuzzArray(1));
}
```

When you do this, you might also notice that it adds a new import statement too
to the test class:

```java
import org.junit.jupiter.api.Disabled;
```

Now run all the tests in `FizzBuzzTest` and see what happens.

![disable-test](https://curriculum-content.s3.amazonaws.com/java-mod-3/tdd-lab/disable-test.png)

Notice how it ran the other 4 tests and skipped over the one that we added the
`@Disabled` annotation to! This is a great annotation to use when we write a
test but have not yet implemented the code to go along with it yet. For the
purposes of this code-along on, and to show the process of test-driven
development, we will show the failed tests first and then add and remove the
`@Disabled` annotation.

**Important Note:** when you use this annotation, it is crucial that after you
implement the code that you remove the `@Disabled` annotation as we want to
ensure that all of our tests are passing and working as expected. You should
only really use this annotation when you are writing the code and ensuring that
it compiles and runs. Remove the `@Disabled` annotation when you are ready to
actually have it test your code.

## Repeat the TDD Steps

So let's go ahead and write another test:

```java
@Test
void testMediumFizzBuzzArray() {
    String[] expectedResult = {"1", "2", "Fizz"};
    assertArrayEquals(expectedResult, fizzBuzz.fizzBuzzArray(3));
}
```

If you run just this test, you'll see it fails since the code is currently only
considering a single element in the array. Since it fails, we will go ahead and
add the `@Disabled` annotation temporarily:

```java
@Disabled
@Test
void testMediumFizzBuzzArray() {
    String[] expectedResult = {"1", "2", "Fizz"};
    assertArrayEquals(expectedResult, fizzBuzz.fizzBuzzArray(3));
}
```

Now let's go back and refactor the code again!

```java
public String[] fizzBuzzArray(int number) {
    String[] result = new String[number];
    for (int index = 0; index < number; index++) {
        // Array values start with 1 and go to n
        result[index] = fizzBuzzString(index + 1);
    }
    return result;
}
```

Before we continue any further in our TDD cycle, let's take a moment to just
walk through this code:

- The `number` parameter is the size of the array. So we will instantiate the
  `String` array to hold `number` of elements.
- We will iterate through the array using a `for` loop.
  - Call the `fizzBuzzString()` method we implemented in the previous lab with
    `index + 1`. This is to ensure that we are starting with `"1"` in our array
    and going until n, which is the input, as specified in the requirements.
  - Assign `result[index]` to value that `fizzBuzzString(index + 1)` evaluated
    to.
- Return the `result` array.

Now that we understand the code above, let's run the `testMediumFizzBuzzArray()`
method again. Remove the `@Disabled` annotation and run just that test method.
We should see it pass!

Go back and double check that the `testSingularFizzBuzzArray()` method still
passes too. It should!

Great! The tests are passing! Let's add another test:

```java
@Test
void testLargeFizzBuzzArray() {
    String[] expectedResult = {"1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"};
    assertArrayEquals(expectedResult, fizzBuzz.fizzBuzzArray(15));
}
```

Try running just this test and see what happens.

Wow, it passes! You don't have to refactor anything!

There is a very important observation to make here. Your test that validates the
processing of the array does not need to go through every single combination of
the "Fizzbuzz Scenarios", because it's re-using the `fizzBuzzString()` method.
So as long as that method works properly _and_ the `fizzBuzzArray()` method
accurately breaks down the array and passes the correct values to the
`fizzBuzzString()` method, all the "FizzBuzz scenarios" will be covered
successfully.

## Edge Cases

The last thing for us to do is to add unit tests to cover the potential edge
cases. For example, what should happen if the number 0 is passed to the
`fizzBuzzArray()` method?

Let's add a test case for this and assume our array should be `null` if that is
the case!

```java
@Test
void testZeroFizzBuzzArray() {
    assertNull(fizzBuzz.fizzBuzzArray(0));
}
```

If you run the test above, you will see that it fails since the code did not
account for this! Add the `@Disabled` annotation to the test method for now, so
we can skip this test. Now let's go back and modify the code:

```java
public String[] fizzBuzzArray(int number) {
    if (number == 0) {
        return null;
    }
        
    String[] result = new String[number];
    for (int index = 0; index < number; index++) {
        // Array values start with 1 and go to n
        result[index] = fizzBuzzString(index + 1);
    }
    return result;
}
```

Add an `if` statement to handle the zero test case. Now re-run the test by
removing the `@Disabled` annotation and see if it passes this time!

Another edge case for us to consider is negative numbers. An array cannot have
a negative size. Add another test case to handle this edge case and assume the
method will return a `null` value here as well.

```java
@Test
void testNegativeFizzBuzzArray() {
    assertNull(fizzBuzz.fizzBuzzArray(-5));
}
```

When you execute this test, it will fail since, again, the code currently does
not handle this. Again, we will temporarily add the `@Disabled` annotation to
the test method, and then we'll do some more refactoring!

```java
public String[] fizzBuzzArray(int number) {
    if (number <= 0) {
        return null;
    }
        
    String[] result = new String[number];
    for (int index = 0; index < number; index++) {
        // Array values start with 1 and go to n
        result[index] = fizzBuzzString(index + 1);
    }
    return result;
}
```

In the above code, notice that the only change is the `==` to `<=` to catch any
non-positive integer that might be passed in.

Go ahead and run the `testNegativeFizzBuzzArray()` test method without the
`@Disabled` annotation and make sure it passes now.

Now let's run our entire test suite! Double check that all 9 unit tests
pass as expected and turn in your `FizzBuzz.java` and `FizzBuzzTest.java`
assignments.
