import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import java.util.Random;

public class Main extends Application {

    // Data structures for different types of lists and a binary search tree (BST)
    private UnsortedArray<Integer> unsortedArray = new UnsortedArray<>(512);
    private UnsortedLinkedList<Integer> unsortedLinkedList = new UnsortedLinkedList<>();
    private SortedArray<Integer> sortedArray = new SortedArray<>(512);
    private SortedLinkedList<Integer> sortedLinkedList = new SortedLinkedList<>();
    private BST<Integer> bst = new BST<>();

    // Canvas and GraphicsContext for drawing the BST structure visually
    private Canvas bstCanvas = new Canvas(800, 600);
    private GraphicsContext gc = bstCanvas.getGraphicsContext2D();

    public static void main(String[] args) {
        launch(args);  // Launch the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        Random random = new Random();  // Random number generator for generating data

        // Create buttons with styled appearance and drop shadow effect
        Button generateUnsortedButton = createStyledButton("Generate Unsorted Data");
        Button generateSortedButton = createStyledButton("Generate Sorted Data");
        Button buildBSTButton = createStyledButton("Build and Display BST");
        Button searchButton = createStyledButton("Search");
        Button performanceButton = createStyledButton("Performance Analysis");

        // Exit button to close the application
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-background-color: #ff6f61; -fx-text-fill: white;");
        exitButton.setPrefWidth(200);  // Set preferred width and height
        exitButton.setPrefHeight(40);
        exitButton.setOnAction(event -> primaryStage.close());  // Close the application when clicked

        // TextArea for displaying output messages and results
        TextArea outputArea = new TextArea();
        outputArea.setPrefWidth(600);
        outputArea.setPrefHeight(200);
        outputArea.setEditable(false);  // Make it read-only
        outputArea.setWrapText(true);  // Enable text wrapping
        outputArea.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");

        // TextField for taking search input
        TextField searchField = new TextField();
        searchField.setPromptText("Enter value to search...");
        searchField.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");

        // Set up layout for the data generation buttons
        VBox dataGenerationLayout = new VBox(10, generateUnsortedButton, generateSortedButton, buildBSTButton, searchButton, performanceButton, exitButton);
        dataGenerationLayout.setAlignment(Pos.CENTER);  // Center the buttons
        dataGenerationLayout.setPadding(new Insets(10));

        // ScrollPane to display the canvas with the BST drawing
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(bstCanvas);  // Set the canvas content
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportWidth(800);  // Set the viewport width and height
        scrollPane.setPrefViewportHeight(300);

        // Button actions - what happens when each button is clicked
        generateUnsortedButton.setOnAction(event -> {
            outputArea.clear();  // Clear previous output
            for (int i = 0; i < 512; i++) {
                int value = random.nextInt(1000);  // Generate random number between 0 and 999
                unsortedArray.add(value);  // Add value to unsorted data structures
                unsortedLinkedList.add(value);
                sortedArray.add(value);  // Add value to sorted data structures
                sortedLinkedList.add(value);
            }
            // Display the generated data structures in the output area
            outputArea.appendText("Unsorted Array: \n");
            unsortedArray.display(outputArea);
            outputArea.appendText("\nUnsorted Linked List: \n");
            unsortedLinkedList.display(outputArea);
        });

        // Generate sorted data and display in output area
        generateSortedButton.setOnAction(event -> {
            outputArea.clear();  // Clear previous output
            sortedArray.sort();  // Sort the array
            sortedLinkedList.sort();  // Sort the linked list
            outputArea.appendText("Sorted Array: \n");
            sortedArray.display(outputArea);
            outputArea.appendText("\nSorted Linked List: \n");
            sortedLinkedList.display(outputArea);
        });

        // Build and display the Binary Search Tree (BST)
        buildBSTButton.setOnAction(event -> {
            outputArea.clear();
            // Add first 20 elements from the unsorted array to the BST
            for (int i = 0; i < 20; i++) {
                Integer value = unsortedArray.get(i);
                if (value != null) bst.add(value);
            }

            // Calculate and display the height of the BST
            int totalLayers = bst.calculateHeight(bst.root);
            outputArea.appendText("Total Visible Layers of the BST: " + totalLayers + "\n");

            // If there are more layers, display a message
            if (totalLayers > 0) {
                outputArea.appendText("Remaining layers not printed to keep the Binary Search Tree visually intact");
            }

            // Draw the tree on the canvas
            gc.clearRect(0, 0, bstCanvas.getWidth(), bstCanvas.getHeight());
            bst.draw(gc);  // Draw the BST structure (ensure that draw method is implemented in BST class)
        });

        // Search button action - performs search across all data structures
        searchButton.setOnAction(event -> {
            String searchText = searchField.getText();  // Get the value from the search field
            if (!searchText.isEmpty()) {
                int searchValue = Integer.parseInt(searchText);
                outputArea.clear();  // Clear previous output
                outputArea.appendText("Searching for: " + searchValue + "\n");

                // Perform search in all data structures and display results
                outputArea.appendText("Unsorted Array:\n");
                outputArea.appendText("Found: " + unsortedArray.search(searchValue) + "\n");

                outputArea.appendText("Unsorted Linked List:\n");
                outputArea.appendText("Found: " + unsortedLinkedList.search(searchValue) + "\n");

                outputArea.appendText("Sorted Array:\n");
                outputArea.appendText("Found: " + sortedArray.search(searchValue) + "\n");

                outputArea.appendText("Sorted Linked List:\n");
                outputArea.appendText("Found: " + sortedLinkedList.search(searchValue) + "\n");

                outputArea.appendText("BST:\n");
                outputArea.appendText("Found: " + bst.search(searchValue) + "\n");
            }
        });

        // Performance analysis button action - displays number of comparisons for each data structure
        performanceButton.setOnAction(event -> {
            String searchText = searchField.getText();  // Get the value from the search field
            if (!searchText.isEmpty()) {
                outputArea.clear();  // Clear previous output
                outputArea.appendText("Performance analysis: \n");

                // Display performance (comparisons) for each data structure
                outputArea.appendText("\nUnsorted Array:\n");
                outputArea.appendText("Comparisons: " + unsortedArray.getComparisons() + "\n");

                outputArea.appendText("\nUnsorted Linked List:\n");
                outputArea.appendText("Comparisons: " + unsortedLinkedList.getComparisons() + "\n");

                outputArea.appendText("\nSorted Array:\n");
                outputArea.appendText("Comparisons: " + sortedArray.getComparisons() + "\n");

                outputArea.appendText("\nSorted Linked List:\n");
                outputArea.appendText("Comparisons: " + sortedLinkedList.getComparisons() + "\n");

                outputArea.appendText("\nBST:\n");
                outputArea.appendText("Comparisons: " + bst.getComparisons() + "\n");
            }
        });

        // Layout setup for the entire user interface
        VBox layout = new VBox(20, dataGenerationLayout, searchField, outputArea, scrollPane);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        // Set up the scene and stage
        Scene scene = new Scene(layout, 900, 700);
        primaryStage.setTitle("CS401 Project GUI");  // Set window title
        primaryStage.setScene(scene);
        primaryStage.show();  // Display the stage
    }

    // Helper method to create buttons with styled appearance and shadow effect
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-background-color: #b1eef9; -fx-text-fill: black;");
        button.setPrefWidth(200);
        button.setPrefHeight(40);
        DropShadow shadow = new DropShadow();  // Drop shadow effect for button
        shadow.setRadius(5);
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);
        button.setEffect(shadow);
        button.setOnMouseEntered(event -> button.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-background-color: #1e90ff; -fx-text-fill: white;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-background-color: #b1eef9; -fx-text-fill: black;"));
        return button;
    }
}
