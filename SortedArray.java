import javafx.scene.control.TextArea;

public class SortedArray<T extends Comparable<T>> {
    private T[] array;  // Array to store elements of type T (generic type)
    private int size;  // Current size of the array (number of elements in use)
    private int sortComparisons; // Counter to track the number of comparisons during sorting

    // Constructor to initialize the array with a specified capacity
    @SuppressWarnings("unchecked") // Suppresses warning for casting a generic array
    public SortedArray(int capacity) {
        // Initialize the array with the given capacity
        array = (T[]) new Comparable[capacity];
        size = 0;  // Initially, the size is 0 (array is empty)
        sortComparisons = 0;  // No comparisons yet
    }

    // Add a value to the array
    public void add(T value) {
        // Check if the array is full
        if (size >= array.length) {
            throw new RuntimeException("Array is full");  // Throw an error if the array is full
        }
        // Add the value at the current position and increment size
        array[size++] = value;
    }

    // Sort the array using Merge Sort algorithm
    public void sort() {
        // Perform Merge Sort on the entire array
        mergeSort(0, size - 1);
    }

    // Helper method to perform Merge Sort recursively on the array
    private void mergeSort(int left, int right) {
        if (left < right) {
            // Find the middle point of the array
            int mid = (left + right) / 2;

            // Recursively split the array into two halves
            mergeSort(left, mid);
            mergeSort(mid + 1, right);

            // Merge the sorted halves
            merge(left, mid, right);
        }
    }

    // Method to merge two sorted subarrays into a single sorted array
    private void merge(int left, int mid, int right) {
        // Calculate the sizes of the two subarrays
        int n1 = mid - left + 1;  // Size of the left subarray
        int n2 = right - mid;  // Size of the right subarray

        // Temporary arrays to hold the values
        T[] leftArray = (T[]) new Comparable[n1];
        T[] rightArray = (T[]) new Comparable[n2];

        // Copy data into the temporary arrays
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        // Indices for iterating through the temporary arrays
        int i = 0, j = 0;
        int k = left;  // Index for the original array where the merged values will be stored

        // Merge the two subarrays while there are elements in both arrays
        while (i < n1 && j < n2) {
            sortComparisons++;  // Count the comparison for sorting
            // Compare the elements from both subarrays and place the smaller element in the original array
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k++] = leftArray[i++];  // Place the left array element in the original array
            } else {
                array[k++] = rightArray[j++];  // Place the right array element in the original array
            }
        }

        // Copy remaining elements of leftArray[], if any
        while (i < n1) {
            array[k++] = leftArray[i++];  // Copy remaining left elements into the original array
        }

        // Copy remaining elements of rightArray[], if any
        while (j < n2) {
            array[k++] = rightArray[j++];  // Copy remaining right elements into the original array
        }
    }

    // Method to get the element at a specific index in the array
    public T get(int index) {
        // Validate the index to avoid ArrayIndexOutOfBoundsException
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return array[index];  // Return the element at the given index
    }

    // Display the contents of the array in the TextArea
    public void display(TextArea outputArea) {
        // Iterate through the array and append each element to the TextArea
        for (int i = 0; i < size; i++) {
            outputArea.appendText(array[i] + " ");  // Add the element to the output area
            if ((i + 1) % 20 == 0) {
                outputArea.appendText("\n");  // Insert a line break after every 20 elements
            }
        }
        outputArea.appendText("\n");  // Add a final line break after the full array is displayed
    }

    // Search for a value in the sorted array using Binary Search
    public boolean search(T value) {
        int left = 0;  // Start index of the array
        int right = size - 1;  // End index of the array

        // Perform binary search
        while (left <= right) {
            // Find the middle index of the array
            int mid = left + (right - left) / 2;
            sortComparisons++;  // Count the comparison for searching

            // Check if the value is found at the middle
            if (array[mid].compareTo(value) == 0) {
                return true;  // Return true if the value is found
            }
            // Adjust the search range based on the comparison
            if (array[mid].compareTo(value) < 0) {
                left = mid + 1;  // Narrow the search to the right half
            } else {
                right = mid - 1;  // Narrow the search to the left half
            }
        }
        return false;  // Return false if the value is not found
    }

    // Get the number of comparisons made during sorting or searching
    public int getComparisons() {
        return sortComparisons;  // Return the total number of comparisons
    }

    // Alias method to get the sort comparisons
    public int getSortComparisons() {
        return sortComparisons;  // Return the total number of comparisons during sorting
    }
}
