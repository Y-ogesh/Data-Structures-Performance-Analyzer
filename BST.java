import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;

public class BST<T extends Comparable<T>> {
    // Inner class for representing a node in the Binary Search Tree (BST)
    private class Node {
        T value;   // Value stored in the node
        Node left, right;  // References to left and right child nodes

        // Constructor to initialize a new node with a given value
        Node(T value) {
            this.value = value;
            left = right = null; // Initially, the node has no children
        }
    }

    public Node root;  // Root node of the BST
    private int comparisons;  // Counter for comparisons during search operations

    // Constructor to initialize an empty BST
    public BST() {
        root = null;  // Initially, the tree is empty (no root node)
        comparisons = 0;  // No comparisons have been made yet
    }

    // Method to add a value to the tree
    public void add(T value) {
        root = addRecursive(root, value);  // Start the recursive add operation from the root
    }

    // Recursive helper method to add a value in the tree
    private Node addRecursive(Node node, T value) {
        if (node == null) {
            return new Node(value);  // Create a new node if we find an empty spot
        }

        // Compare the value to the current node's value and decide where to add it
        if (value.compareTo(node.value) < 0) {
            node.left = addRecursive(node.left, value);  // Go to the left child if value is smaller
        } else if (value.compareTo(node.value) > 0) {
            node.right = addRecursive(node.right, value);  // Go to the right child if value is larger
        }
        return node;  // Return the current node (no changes if the value is equal)
    }

    // Method to search for a value in the tree
    public boolean search(T value) {
        return searchRecursive(root, value);  // Start the recursive search operation from the root
    }

    // Recursive helper method to search for a value in the tree
    private boolean searchRecursive(Node node, T value) {
        comparisons++;  // Increment the comparison counter each time we check a node
        if (node == null) {
            return false;  // Value not found if we hit a null node
        }
        if (value.equals(node.value)) {
            return true;  // Value found, return true
        }

        // Continue the search in the left or right subtree based on comparison result
        return value.compareTo(node.value) < 0
                ? searchRecursive(node.left, value)  // Search the left subtree if value is smaller
                : searchRecursive(node.right, value);  // Search the right subtree if value is larger
    }

    // Get the number of comparisons made during the last search
    public int getComparisons() {
        return comparisons;  // Return the number of comparisons made
    }

    // Method to draw the BST on the GraphicsContext (from Canvas)
    public void draw(GraphicsContext gc) {
        // Calculate the tree's maximum width and height to determine canvas size
        int maxWidth = calculateMaxWidth(root);
        int height = calculateHeight(root);

        // Dynamically adjust the canvas size based on the tree's dimensions
        gc.getCanvas().setWidth(maxWidth * 200);  // Adjust width multiplier for spacing between nodes
        gc.getCanvas().setHeight(height * 200);  // Adjust height multiplier to allow space for depth

        // Start drawing the tree, starting from the root node
        drawRecursive(gc, root, gc.getCanvas().getWidth() / 2, 100, 500 , height);
    }

    // Helper method to calculate the maximum width of the tree
    private int calculateMaxWidth(Node node) {
        return calculateMaxWidthRecursive(node, 0);  // Start the recursive width calculation
    }

    // Recursive helper method to calculate the maximum width of the tree
    private int calculateMaxWidthRecursive(Node node, int level) {
        if (node == null) {
            return 0;  // No width for a null node
        }
        int leftWidth = calculateMaxWidthRecursive(node.left, level + 1);  // Left subtree width
        int rightWidth = calculateMaxWidthRecursive(node.right, level + 1);  // Right subtree width
        return Math.max(leftWidth, rightWidth) + 1;  // Account for the current node's width
    }

    // Helper method to calculate the height (depth) of the tree
    public int calculateHeight(Node node) {
        if (node == null) {
            return 0;  // No height for a null node
        }
        int leftHeight = calculateHeight(node.left);  // Height of the left subtree
        int rightHeight = calculateHeight(node.right);  // Height of the right subtree
        return Math.max(leftHeight, rightHeight) + 1;  // Add 1 for the current node's level
    }

    // Recursively draw the tree on the canvas using GraphicsContext
    private void drawRecursive(GraphicsContext gc, Node node, double x, double y, double offset, int height) {
        if (node == null) {
            return;  // If the node is null, there's nothing to draw
        }

        // Draw the current node as an oval with its value inside
        gc.strokeOval(x - 20, y - 20, 40, 40);  // Draw oval with center at (x, y)
        gc.strokeText(node.value.toString(), x - 10, y + 5);  // Draw node's value inside the oval

        // Decrease the offset for the next level of the tree
        double newOffset = offset / 2;

        // Check if there is a left child and draw the left subtree recursively
        if (node.left != null) {
            gc.strokeLine(x, y, x - newOffset, y + 60);  // Draw a line to the left child
            drawRecursive(gc, node.left, x - newOffset, y + 60, newOffset, height + 1);  // Recursively draw left subtree
        }

        // Check if there is a right child and draw the right subtree recursively
        if (node.right != null) {
            gc.strokeLine(x, y, x + newOffset, y + 60);  // Draw a line to the right child
            drawRecursive(gc, node.right, x + newOffset, y + 60, newOffset, height + 1);  // Recursively draw right subtree
        }
    }

    // Display the tree structure in a TextArea (or in a simple string format)
    public void display(TextArea outputArea) {
        StringBuilder sb = new StringBuilder();  // StringBuilder for building the tree representation
        displayRecursive(root, sb, "", "");  // Start the recursive display operation
        outputArea.appendText(sb.toString());  // Output the tree representation to the TextArea
    }

    // Recursive helper method to generate a string representation of the tree
    private void displayRecursive(Node node, StringBuilder sb, String prefix, String childrenPrefix) {
        if (node != null) {
            sb.append(prefix);  // Append the prefix for the current level
            sb.append(node.value);  // Append the value of the current node
            sb.append("\n");  // Add a newline for the next node

            // Recurse for the left child with updated prefixes for proper tree formatting
            displayRecursive(node.left, sb, childrenPrefix + "├── ", childrenPrefix + "│   ");
            // Recurse for the right child with updated prefixes for proper tree formatting
            displayRecursive(node.right, sb, childrenPrefix + "└── ", childrenPrefix + "    ");
        }
    }
}
