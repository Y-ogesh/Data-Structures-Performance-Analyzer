public class SortedLinkedList<T extends Comparable<T>> extends UnsortedLinkedList<T> {
    private int sortComparisons; // To count comparisons during sorting

    // Constructor to initialize the sorted linked list and the comparisons counter
    public SortedLinkedList() {
        super(); // Call to the parent class constructor (UnsortedLinkedList)
        sortComparisons = 0; // Initialize sortComparisons to zero
    }

    // Public method to initiate merge sort on the linked list
    public void sort() {
        // Base case: If the list is empty or contains only one element, it is already sorted
        if (head == null || head.getNext() == null) return;
        // Start the merge sort process by calling the recursive mergeSort method
        head = mergeSort(head);
    }

    // Recursive method to perform merge sort on the linked list
    private LLNode<T> mergeSort(LLNode<T> node) {
        // Base case: if the list is empty or has only one element, return the node (no sorting needed)
        if (node == null || node.getNext() == null) {
            return node;
        }

        // Split the list into two halves by finding the middle node
        LLNode<T> middle = getMiddle(node); // Find the middle of the list
        LLNode<T> nextToMiddle = middle.getNext(); // Get the node after the middle
        middle.setNext(null); // Break the list into two halves

        // Recursively sort both halves of the list
        LLNode<T> left = mergeSort(node); // Sort the left half
        LLNode<T> right = mergeSort(nextToMiddle); // Sort the right half

        // Merge the two sorted halves and return the merged list
        return merge(left, right);
    }

    // Method to merge two sorted linked lists into one sorted list
    private LLNode<T> merge(LLNode<T> left, LLNode<T> right) {
        LLNode<T> dummy = new LLNode<>(null); // Create a temporary dummy node to simplify merge logic
        LLNode<T> current = dummy; // Pointer to the current node in the merged list

        // Compare elements from both left and right lists and merge them in sorted order
        while (left != null && right != null) {
            sortComparisons++; // Count the comparison made during the merge process
            if (left.getData().compareTo(right.getData()) <= 0) {
                // If left node's data is less than or equal to right node's data, add left to merged list
                current.setNext(left);
                left = left.getNext(); // Move to the next node in the left list
            } else {
                // Otherwise, add right node to the merged list
                current.setNext(right);
                right = right.getNext(); // Move to the next node in the right list
            }
            current = current.getNext(); // Move the current pointer to the newly added node
        }

        // Append any remaining nodes from left or right list
        if (left != null) {
            current.setNext(left); // Append the remaining nodes from the left list
        } else {
            current.setNext(right); // Append the remaining nodes from the right list
        }

        return dummy.getNext(); // Return the merged list, skipping the dummy node
    }

    // Helper method to find the middle node of the linked list
    private LLNode<T> getMiddle(LLNode<T> node) {
        if (node == null) return null; // Return null if the list is empty

        LLNode<T> slow = node; // Slow pointer starts at the head
        LLNode<T> fast = node.getNext(); // Fast pointer starts one step ahead

        // Move slow pointer by one step and fast pointer by two steps
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext(); // Move slow by one step
            fast = fast.getNext().getNext(); // Move fast by two steps
        }

        // When fast reaches the end, slow will be at the middle of the list
        return slow;
    }

    // Method to get the number of comparisons made during the sorting process
    public int getSortComparisons() {
        return sortComparisons; // Return the count of comparisons made during sorting
    }
}
