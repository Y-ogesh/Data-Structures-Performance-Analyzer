import javafx.scene.control.TextArea;

public class UnsortedLinkedList<T extends Comparable<T>> {
    protected LLNode<T> head; // Head of the linked list (points to the first node)
    private int comparisons; // To count the number of comparisons made during search operations

    // Constructor to initialize the linked list
    public UnsortedLinkedList() {
        head = null; // Initially, the list is empty, so head is null
        comparisons = 0; // Initialize comparisons counter to 0
    }

    // Method to add a value to the linked list
    public void add(T value) {
        // Create a new node with the given value
        LLNode<T> newNode = new LLNode<>(value);

        // Set the next pointer of the new node to the current head of the list
        newNode.setNext(head);

        // Update the head of the list to the new node (it becomes the first node)
        head = newNode;
    }

    // Method to search for a value in the linked list
    public boolean search(T value) {
        comparisons = 0; // Reset comparisons counter before the search
        LLNode<T> current = head; // Start with the head of the list

        // Traverse the list to find the value
        while (current != null) {
            comparisons++; // Increment comparison count for each node checked
            // Check if the current node's data equals the search value
            if (current.getData().equals(value)) {
                return true; // Return true if the value is found
            }
            current = current.getNext(); // Move to the next node in the list
        }
        return false; // Return false if the value is not found in the list
    }

    // Method to display the elements of the linked list in a TextArea
    public void display(TextArea outputArea) {
        LLNode<T> current = head; // Start from the head of the list
        int count = 0; // Counter to keep track of the number of elements displayed

        // Traverse the list and display each element
        while (current != null) {
            outputArea.appendText(current.getData() + " "); // Append the data of the current node
            count++; // Increment the counter
            // After every 20 elements, add a newline to format the output
            if (count % 20 == 0) {
                outputArea.appendText("\n");
            }
            current = current.getNext(); // Move to the next node
        }
        outputArea.appendText("\n"); // Append a final newline after displaying all elements
    }

    // Method to get the number of comparisons made during the last search operation
    public int getComparisons() {
        return comparisons; // Return the count of comparisons made during the search
    }
}
