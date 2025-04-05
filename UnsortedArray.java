import javafx.scene.control.TextArea;

public class UnsortedArray<T extends Comparable<T>> {
    private T[] array; // Array to hold the elements
    private int size; // Current size of the array (number of elements)
    private int comparisons; // Counter to keep track of the number of comparisons made during search operations

    // Constructor to initialize the array with a given capacity
    public UnsortedArray(int capacity) {
        // Create a new array of Comparable objects with the specified capacity
        array = (T[]) new Comparable[capacity];
        size = 0; // Initialize size to 0 (array is empty initially)
        comparisons = 0; // Initialize comparisons counter to 0
    }

    // Method to add a value to the array
    public void add(T value) {
        // Check if there is space left in the array
        if (size < array.length) {
            // If space is available, add the value and increment size
            array[size++] = value;
        } else {
            // If array is full, print an error message
            System.out.println("Array is full!");
        }
    }

    // Method to get the element at the specified index
    public T get(int index) {
        // Check if the provided index is valid (within the current size of the array)
        if (index < 0 || index >= size) {
            // If index is out of bounds, throw an exception
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return array[index]; // Return the element at the given index
    }

    // Method to search for a value in the array
    public boolean search(T value) {
        comparisons = 0; // Reset comparisons counter at the start of each search
        // Iterate through the array to find the value
        for (int i = 0; i < size; i++) {
            comparisons++; // Increment comparison count for each iteration
            // Check if the current element is equal to the search value
            if (array[i].equals(value)) {
                return true; // Return true if the value is found
            }
        }
        return false; // Return false if the value is not found
    }

    // Method to display the elements of the array in a TextArea
    public void display(TextArea outputArea) {
        // Iterate through the array and append each element to the output area
        for (int i = 0; i < size; i++) {
            outputArea.appendText(array[i] + " "); // Append each element with a space
            // Add a newline after every 20 elements for better readability
            if ((i + 1) % 20 == 0) {
                outputArea.appendText("\n");
            }
        }
        // Append a final newline after displaying all elements
        outputArea.appendText("\n");
    }

    // Method to get the number of comparisons made during the last search operation
    public int getComparisons() {
        return comparisons; // Return the count of comparisons made during search
    }

    // New method to clear the array and reset its state
    public void clear() {
        size = 0; // Reset size to 0, effectively clearing the array
        comparisons = 0; // Reset comparisons count
    }
}
