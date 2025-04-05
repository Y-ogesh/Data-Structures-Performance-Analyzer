// This class represents a node in a singly linked list (LLNode)
public class LLNode<T> {
    private T data;  // The data stored in the node, of generic type T
    private LLNode<T> next;  // A reference to the next node in the list

    // Constructor to initialize a new node with data and a null next reference
    public LLNode(T data) {
        this.data = data;  // Set the node's data to the provided value
        this.next = null;  // Initially, the node's next reference is null (no next node)
    }

    // Getter method to retrieve the data stored in the node
    public T getData() {
        return data;  // Return the data stored in this node
    }

    // Setter method to set the data stored in the node
    public void setData(T data) {
        this.data = data;  // Update the node's data to the provided value
    }

    // Getter method to retrieve the reference to the next node in the list
    public LLNode<T> getNext() {
        return next;  // Return the reference to the next node
    }

    // Setter method to update the reference to the next node in the list
    public void setNext(LLNode<T> next) {
        this.next = next;  // Update the next node reference to the provided node
    }
}
