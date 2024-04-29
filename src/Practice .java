import java.util.Scanner;

/*
// Create Node class to design the structure of the AVL Tree Node
class Node {
    int element;
    int h;  // for height
    Node leftChild;
    Node rightChild;

    // Default constructor to create null node
    public Node() {
        leftChild = null;
        rightChild = null;
        element = 0;
        h = 0;
    }

    // Parameterized constructor
    public Node(int element) {
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
    }
}

// Create class AVLTree for constructing AVL Tree
class AVLTree {
    private Node rootNode;

    // Constructor to set null value to the rootNode
    public AVLTree() {
        rootNode = null;
    }

    // Create removeAll() method to make AVL Tree empty
    public void removeAll() {
        rootNode = null;
    }

    // Create checkEmpty() method to check whether the AVL Tree is empty or not
    public boolean checkEmpty() {
        return rootNode == null;
    }

    // Create insertElement() to insert element into the AVL Tree
    public void insertElement(int element) {
        rootNode = insertElement(element, rootNode);
    }

    // Create getHeight() method to get the height of the AVL Tree
    private int getHeight(Node node) {
        return node == null ? -1 : node.h;
    }

    // Create maxNode() method to get the maximum height from left and right node
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {
        return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
    }

    // Create insertElement() method to insert data into the AVL Tree recursively
    private Node insertElement(int element, Node node) {
        // Check whether the node is null or not
        if (node == null)
            node = new Node(element);
            // Insert a node in case when the given element is lesser than the element of the root node
        else if (element < node.element) {
            node.leftChild = insertElement(element, node.leftChild);
            if (getHeight(node.leftChild) - getHeight(node.rightChild) == 2)
                if (element < node.leftChild.element)
                    node = rotateWithLeftChild(node);
                else
                    node = doubleWithLeftChild(node);
        } else if (element > node.element) {
            node.rightChild = insertElement(element, node.rightChild);
            if (getHeight(node.rightChild) - getHeight(node.leftChild) == 2)
                if (element > node.rightChild.element)
                    node = rotateWithRightChild(node);
                else
                    node = doubleWithRightChild(node);
        }
        node.h = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        return node;
    }

    // Creating rotateWithLeftChild() method to perform rotation of binary tree node with left child
    private Node rotateWithLeftChild(Node node2) {
        Node node1 = node2.leftChild;
        node2.leftChild = node1.rightChild;
        node1.rightChild = node2;
        node2.h = getMaxHeight(getHeight(node2.leftChild), getHeight(node2.rightChild)) + 1;
        node1.h = getMaxHeight(getHeight(node1.leftChild), node2.h) + 1;
        return node1;
    }

    // Creating rotateWithRightChild() method to perform rotation of binary tree node with right child
    private Node rotateWithRightChild(Node node1) {
        Node node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1;
        node1.h = getMaxHeight(getHeight(node1.leftChild), getHeight(node1.rightChild)) + 1;
        node2.h = getMaxHeight(getHeight(node2.rightChild), node1.h) + 1;
        return node2;
    }

    // Create doubleWithLeftChild() method to perform double rotation of binary tree node.
    private Node doubleWithLeftChild(Node node3) {
        node3.leftChild = rotateWithRightChild(node3.leftChild);
        return rotateWithLeftChild(node3);
    }

    // Create doubleWithRightChild() method to perform double rotation of binary tree node.
    private Node doubleWithRightChild(Node node1) {
        node1.rightChild = rotateWithLeftChild(node1.rightChild);
        return rotateWithRightChild(node1);
    }

    // Create deleteElement() method to delete element from the AVL Tree
    public void deleteElement(int element) {
        rootNode = deleteElement(element, rootNode);
    }

    // Create deleteElement() method to delete data from the AVL Tree recursively
    private Node deleteElement(int element, Node node) {
        if (node == null)
            return node;

        if (element < node.element)
            node.leftChild = deleteElement(element, node.leftChild);
        else if (element > node.element)
            node.rightChild = deleteElement(element, node.rightChild);
        else {
            if ((node.leftChild == null) || (node.rightChild == null)) {
                Node temp = null;
                if (temp == node.leftChild)
                    temp = node.rightChild;
                else
                    temp = node.leftChild;

                if (temp == null) {
                    temp = node;
                    node = null;
                } else
                    node = temp;
            } else {
                Node temp = minValueNode(node.rightChild);
                node.element = temp.element;
                node.rightChild = deleteElement(temp.element, node.rightChild);
            }
        }

        if (node == null)
            return node;

        node.h = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.leftChild) >= 0)
            return rotateWithRightChild(node);

        if (balance > 1 && getBalance(node.leftChild) < 0) {
            node.leftChild = rotateWithLeftChild(node.leftChild);
            return rotateWithRightChild(node);
        }

        if (balance < -1 && getBalance(node.rightChild) <= 0)
            return rotateWithLeftChild(node);

        if (balance < -1 && getBalance(node.rightChild) > 0) {
            node.rightChild = rotateWithRightChild(node.rightChild);
            return rotateWithLeftChild(node);
        }

        return node;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.leftChild != null)
            current = current.leftChild;
        return current;
    }

    // Get balance factor of node
    private int getBalance(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.leftChild) - getHeight(node.rightChild);
    }

    // Create inorderTraversal() method for traversing AVL Tree in in-order form
    public void inorderTraversal() {
        inorderTraversal(rootNode);
    }

    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.leftChild);
            System.out.print(node.element + " ");
            inorderTraversal(node.rightChild);
        }
    }
}

// Create AVLTreeExample class to demonstrate AVL Tree operations
class AVLTreeExample {
    // main() method starts
    public static void main(String[] args) {
        // Creating Scanner class object to get input from the user
        Scanner sc = new Scanner(System.in);

        // Create object of AVLTree class for constructing AVL Tree
        AVLTree tree = new AVLTree();

        char choice; // Initialize a character type variable to choice

        // Perform operations on AVL Tree using switch
        do {
            System.out.println("\nSelect an operation:\n");
            System.out.println("1. Insert a node");
            System.out.println("2. Delete a node");
            System.out.println("3. Display AVL Tree in In order");
            System.out.println("4. Exit");

            // Get choice from the user
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Please enter an element to insert in AVL Tree");
                    tree.insertElement(sc.nextInt());
                    break;
                case 2:
                    System.out.println("Please enter an element to delete from AVL Tree");
                    tree.deleteElement(sc.nextInt());
                    break;
                case 3:
                    System.out.println("\nDisplay AVL Tree in In order");
                    tree.inorderTraversal();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("\n ");
                    break;
            }
            System.out.println("\nPress 'y' or 'Y' to continue \n");
            choice = sc.next().charAt(0);
        } while (choice == 'Y' || choice == 'y');
    }
}
*/
/* 

    // create class MinHeap to construct Min heap in Java  
class MinHeap {   
        // declare array and variables  
private int[] heapData;   
private int sizeOfHeap;   
private int heapMaxSize;   

    private static final int FRONT = 1;   
        //use constructor to initialize heapData array  
public MinHeap(int heapMaxSize)  {   
this.heapMaxSize = heapMaxSize;   
this.sizeOfHeap = 0;   
heapData = new int[this.heapMaxSize + 1];   
heapData[0] = Integer.MIN_VALUE;   
        }   

        // create getParentPos() method that returns parent position for the node   
private int getParentPosition(int position)  {   
    return position / 2;   
        }   

        // create getLeftChildPosition() method that returns the position of left child   
private int getLeftChildPosition(int position)  {   
    return (2 * position);   
        }   

        // create getRightChildPosition() method that returns the position of right child  
private int getRightChildPosition(int position)  {   
    return (2 * position) + 1;   
        }   

        // checks whether the given node is leaf or not  
private boolean checkLeaf(int position)  {   
    if (position >= (sizeOfHeap / 2) && position <= sizeOfHeap) {   
    return true;   
            }   
    return false;   
        }   

        // create swapNodes() method that perform swapping of the given nodes of the heap   
        // firstNode and secondNode are the positions of the nodes  
private void swap(int firstNode, int secondNode)  {   
int temp;   
    temp = heapData[firstNode];   
heapData[firstNode] = heapData[secondNode];   
heapData[secondNode] = temp;   
        }   

        // create minHeapify() method to heapify the node for maintaining the heap property  
private void minHeapify(int position)  {   

            //check whether the given node is non-leaf and greater than its right and left child  
if (!checkLeaf(position)) {   
    if (heapData[position] >heapData[getLeftChildPosition(position)] || heapData[position] >heapData[getRightChildPosition(position)]) {   

                    // swap with left child and then heapify the left child   
    if (heapData[getLeftChildPosition(position)] <heapData[getRightChildPosition(position)]) {   
swap(position, getLeftChildPosition(position));   
minHeapify(getLeftChildPosition(position));   
                    }   

                    // Swap with the right child and heapify the right child   
    else {   
swap(position, getRightChildPosition(position));   
minHeapify(getRightChildPosition(position));   
                    }   
                }   
            }   
        }   

        // create insertNode() method to insert element in the heap  
public void insertNode(int data)  {   
    if (sizeOfHeap>= heapMaxSize) {   
    return;   
            }   
heapData[++sizeOfHeap] = data;   
int current = sizeOfHeap;   

    while (heapData[current] <heapData[getParentPosition(current)]) {    
swap(current, getParentPosition(current));   
    current = getParentPosition(current);   
            }   
        }   

        // crreatedisplayHeap() method to print the data of the heap   
public void displayHeap()  {   
System.out.println("PARENT NODE" + "\t" + "LEFT CHILD NODE" + "\t" + "RIGHT CHILD NODE");  
    for (int k = 1; k <= sizeOfHeap / 2; k++) {   
System.out.print(" " + heapData[k] + "\t\t" + heapData[2 * k] + "\t\t" + heapData[2 * k + 1]);   
System.out.println();   
            }   
        }   

       // create designMinHeap() method to construct min heap  
public void designMinHeap()  {   
    for (int position = (sizeOfHeap / 2); position >= 1; position--) {   
minHeapify(position);   
            }   
        }   

        // create removeRoot() method for removing minimum element from the heap  
public int removeRoot()  {   
int popElement = heapData[FRONT];   
heapData[FRONT] = heapData[sizeOfHeap--];   
minHeapify(FRONT);   
return popElement;   
        }   
    }  

    // create MinHeapJavaImplementation class to create heap in Java  
class MinHeapJavaImplementation{

        // main() method start  
public static void main(String[] arg)  {   
        // declare variable  
int heapSize;  

        // create scanner class object  
        Scanner sc = new Scanner(System.in);  

System.out.println("Enter the size of Min Heap");  
heapSize = sc.nextInt();  

MinHeap heapObj = new MinHeap(heapSize);  

for(int i = 1; i<= heapSize; i++) {  
System.out.print("Enter "+i+" element: ");  
int data = sc.nextInt();  
heapObj.insertNode(data);  
        }  

            // close scanner class obj
sc.close();  

            //construct a min heap from given data  
heapObj.designMinHeap();   

            //display the min heap data  
System.out.println("The Min Heap is ");   
heapObj.displayHeap();   

            //removing the root node from the heap  
System.out.println("After removing the minimum element(Root Node) "+heapObj.removeRoot()+", Min heap is:");   
heapObj.displayHeap();   

        }   
    }
 */
/*
class MaxHeap {   
    // declare array and variables  
private int[] heapData;   
private int sizeOfHeap;   
private int heapMaxSize;   

    private static final int FRONT = 1;   

    //use constructor to initialize heapData array  
public MaxHeap(int heapMaxSize)  {   
this.heapMaxSize = heapMaxSize;   
this.sizeOfHeap = 0;   
heapData = new int[this.heapMaxSize];  
    }   

    // create getParentPos() method that returns parent position for the node   
private int getParentPosition(int position)  {   
        return (position - 1) / 2;   
    }   

    // create getLeftChildPosition() method that returns the position of left child   
private int getLeftChildPosition(int position)  {   
        return (2 * position);   
    }   

    // create getRightChildPosition() method that returns the position of right child  
private int getRightChildPosition(int position)  {   
        return (2 * position) + 1;   
    }   

    // checks whether the given node is leaf or not  
private boolean checkLeaf(int position)  {   
        if (position > (sizeOfHeap / 2) && position <= sizeOfHeap) {   
            return true;   
        }   
        return false;   
    }   

    // create swapNodes() method that perform swapping of the given nodes of the heap   
    // firstNode and secondNode are the positions of the nodes  
private void swap(int firstNode, int secondNode)  {   
int temp;   
        temp = heapData[firstNode];   
heapData[firstNode] = heapData[secondNode];   
heapData[secondNode] = temp;   
    }   

    // create maxHeapify() method to heapify the node for maintaining the heap property  
private void maxHeapify(int position)  {   

         //check whether the given node is non-leaf and greater than its right and left child  
if (!checkLeaf(position)) {   
    if (heapData[position] <heapData[getLeftChildPosition(position)] || heapData[position] <heapData[getRightChildPosition(position)]) {   

                 // swap with left child and then heapify the left child   
    if (heapData[getLeftChildPosition(position)] >heapData[getRightChildPosition(position)]) {   
swap(position, getLeftChildPosition(position));   
maxHeapify(getLeftChildPosition(position));   
                 }   

                 // Swap with the right child and heapify the right child   
    else {   
swap(position, getRightChildPosition(position));   
maxHeapify(getRightChildPosition(position));   
                 }   
             }   
         }   
    }   

    // create insertNode() method to insert element in the heap  
public void insertNode(int data)  {   
heapData[sizeOfHeap] = data;   
int current = sizeOfHeap;   

        while (heapData[current] >heapData[getParentPosition(current)]) {    
swap(current, getParentPosition(current));   
            current = getParentPosition(current);   
        }   
sizeOfHeap++;  
    }   

    // create displayHeap() method to print the data of the heap   
public void displayHeap()  {   
System.out.println("PARENT NODE" + "\t" + "LEFT CHILD NODE" + "\t" + "RIGHT CHILD NODE");  
        for (int k = 0; k <sizeOfHeap / 2; k++) {   
System.out.print(" " + heapData[k] + "\t\t" + heapData[2 * k + 1] + "\t\t" + heapData[2 * k + 2]);   
System.out.println();   
        }   
    }   

    // create designMaxHeap() method to construct min heap  
public void designMaxHeap()  {   
for (int position = 0;  position < (sizeOfHeap / 2); position++) {   
maxHeapify(position);   
        }   
    }   

    // create removeRoot() method for removing maximum element from the heap  
public int removeRoot()  {   
int popElement = heapData[FRONT];   
heapData[FRONT] = heapData[sizeOfHeap--];   
maxHeapify(FRONT);   
return popElement;   
    }   
}  

//create MinHeapJavaImplementation class to create heap in Java  
class MaxHeapJavaImplementation{

    // main() method start  
public static void main(String[] arg)  {   
    // declare variable  
int heapSize;  

    // create scanner class object  
    Scanner sc = new Scanner(System.in);  

System.out.println("Enter the size of Max Heap");  
heapSize = sc.nextInt();  

MaxHeap heapObj = new MaxHeap(50);  

for(int i = 1; i<= heapSize; i++) {  
System.out.print("Enter "+i+" element: ");  
int data = sc.nextInt();  
heapObj.insertNode(data);  
    }  

     // close scanner class obj
sc.close();  

     //construct a max heap from given data  
heapObj.designMaxHeap();   

     //display the max heap data  
System.out.println("The Max Heap is ");   
heapObj.displayHeap();   

     //removing the root node from the heap  
System.out.println("After removing the maximum element(Root Node) "+heapObj.removeRoot()+", Max heap is:");   
heapObj.displayHeap();   

 }   
}
*/
class MaxHeap {   
    // declare array and variables  
    private int[] heapData;   
    private int sizeOfHeap;   
    private int heapMaxSize;   

    private static final int FRONT = 1;   

    //use constructor to initialize heapData array  
    public MaxHeap(int heapMaxSize)  {   
        this.heapMaxSize = heapMaxSize;   
        this.sizeOfHeap = 0;   
        heapData = new int[this.heapMaxSize];  
    }   

    // create getParentPos() method that returns parent position for the node   
    private int getParentPosition(int position)  {   
        return (position - 1) / 2;   
    }   

    // create getLeftChildPosition() method that returns the position of left child   
    private int getLeftChildPosition(int position)  {   
        return (2 * position);   
    }   

    // create getRightChildPosition() method that returns the position of right child  
    private int getRightChildPosition(int position)  {   
        return (2 * position) + 1;   
    }   

    // checks whether the given node is leaf or not  
    private boolean checkLeaf(int position)  {   
        if (position > (sizeOfHeap / 2) && position <= sizeOfHeap) {   
            return true;   
        }   
        return false;   
    }   

    // create swapNodes() method that perform swapping of the given nodes of the heap   
    // firstNode and secondNode are the positions of the nodes  
    private void swap(int firstNode, int secondNode)  {   
        int temp;   
        temp = heapData[firstNode];   
        heapData[firstNode] = heapData[secondNode];   
        heapData[secondNode] = temp;   
    }   

    // create maxHeapify() method to heapify the node for maintaining the heap property  
    private void maxHeapify(int position)  {   

        //check whether the given node is non-leaf and greater than its right and left child  
        if (!checkLeaf(position)) {   
            if (heapData[position] < heapData[getLeftChildPosition(position)] || heapData[position] < heapData[getRightChildPosition(position)]) {   

                // swap with left child and then heapify the left child   
                if (heapData[getLeftChildPosition(position)] > heapData[getRightChildPosition(position)]) {   
                    swap(position, getLeftChildPosition(position));   
                    maxHeapify(getLeftChildPosition(position));   
                }   

                // Swap with the right child and heapify the right child   
                else {   
                    swap(position, getRightChildPosition(position));   
                    maxHeapify(getRightChildPosition(position));   
                }   
            }   
        }   
    }   

    // create insertNode() method to insert element in the heap  
    public void insertNode(int data)  {   
        heapData[sizeOfHeap] = data;   
        int current = sizeOfHeap;   

        while (heapData[current] > heapData[getParentPosition(current)]) {    
            swap(current, getParentPosition(current));   
            current = getParentPosition(current);   
        }   
        sizeOfHeap++;  
    }   

    // create displayHeap() method to print the data of the heap   
    public void displayHeap()  {   
        System.out.println("PARENT NODE" + "\t" + "LEFT CHILD NODE" + "\t" + "RIGHT CHILD NODE");  
        for (int k = 0; k < sizeOfHeap / 2; k++) {   
            System.out.print(" " + heapData[k] + "\t\t" + heapData[2 * k + 1] + "\t\t" + heapData[2 * k + 2]);   
            System.out.println();   
        }   
    }   

    // create designMaxHeap() method to construct min heap  
    public void designMaxHeap()  {   
        for (int position = 0;  position < (sizeOfHeap / 2); position++) {   
            maxHeapify(position);   
        }   
    }   

    // create removeRoot() method for removing maximum element from the heap  
    public int removeRoot()  {   
        int popElement = heapData[FRONT];   
        heapData[FRONT] = heapData[sizeOfHeap--];   
        maxHeapify(FRONT);   
        return popElement;   
    }   
}  

//create MinHeapJavaImplementation class to create heap in Java  
class MaxHeapJavaImplementation{

    // main() method start  
    public static void main(String[] arg)  {   
        // declare variable  
        int heapSize = 8;  // Change heap size to 8

        MaxHeap heapObj = new MaxHeap(50);  

        // Insert elements 2, 3, 4, 5, 6, 7, 8 into the heap
        for(int i = 2; i <= heapSize; i++) {  
            heapObj.insertNode(i);  
        }  

        // Construct the max heap  
        heapObj.designMaxHeap();   

        // Display the heap
        System.out.println("The Max Heap is ");   
        heapObj.displayHeap();   

        // Remove any two nodes
        heapObj.removeRoot();
        heapObj.removeRoot();

        // Display the heap after removing two nodes
        System.out.println("After removing two nodes, Max heap is:");   
        heapObj.displayHeap();   
    }   
}
