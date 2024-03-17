import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class AVLOptions<T extends Entry> {
    private Node<Entry> rootNode;
    int opCount;

    //Constructor to set null value to the rootNode
    public AVLOptions() {
        rootNode = null;
        this.opCount = 0;
    }

    public AVLOptions(Node<Entry> root) {
        this.rootNode = root;
    }

    //create removeAll() method to make AVL Tree empty
    public void removeAll() {
        rootNode = null;
    }

    // create checkEmpty() method to check whether the AVL Tree is empty or not
    public boolean checkEmpty() {
        if (rootNode == null)
            return true;
        else
            return false;
    }

    //create getHeight() method to get the height of the AVL Tree
    private int getHeight(Node<Entry> node) {
        return node == null ? -1 : node.height;
    }

    //create maxNode() method to get the maximum height from left and right node
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {
        return Math.max(leftNodeHeight, rightNodeHeight);
    }

    // create insertElement() to insert element to to the AVL Tree
    public void insertElement(Entry element) {
        rootNode = insertElement(rootNode, element);
    }

    private Node<Entry> insertElement(Node<Entry> node, Entry value) {
        if (node == null) {
            return new Node<>(value);
        }

        // Insertion logic
        int cmp = value.getTerm().compareTo(node.value.getTerm());
        if (cmp < 0) {
            node.left = insertElement(node.left, value);
            opCount++;
        } else if (cmp > 0) {
            node.right = insertElement(node.right, value);
            opCount++;
        } else {
            // Duplicate value, handle according to specific requirements
            return node;
        }

        // Update height of this ancestor node
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // Get the balance factor
        int balance = getBalance(node);

        // Perform rotations to balance the tree
        // Left Left Case
        if (balance > 1 && value.getTerm().compareTo(node.left.value.getTerm()) < 0) {
            opCount++;
            return rotateWithRightChild(node);
        }

        // Right Right Case
        if (balance < -1 && value.getTerm().compareTo(node.right.value.getTerm()) > 0) {
            opCount++;
            return rotateWithLeftChild(node);
        }

        // Left Right Case
        if (balance > 1 && value.getTerm().compareTo(node.left.value.getTerm()) > 0) {
            node.left = rotateWithLeftChild(node.left);
            opCount++;
            return rotateWithRightChild(node);
        }

        // Right Left Case
        if (balance < -1 && value.getTerm().compareTo(node.right.value.getTerm()) < 0) {
            node.right = rotateWithRightChild(node.right);
            opCount++;
            return rotateWithLeftChild(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    private int getBalance(Node<Entry> N) {
        if (N == null)
            return 0;
        return getHeight(N.left) - getHeight(N.right);
    }


    // creating rotateWithLeftChild() method to perform rotation of binary tree node with left child
    private Node<Entry> rotateWithLeftChild(Node<Entry> x) {
        Node<Entry> y = x.right;
        opCount++;
        Node<Entry> T2 = y.left;
        opCount++;

        // Perform rotation
        y.left = x;
        opCount++;
        x.right = T2;
        opCount++;

        // Update heights
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        // Return new root
        return y;
    }

    // creating rotateWithRightChild() method to perform rotation of binary tree node with right child
    private Node<Entry> rotateWithRightChild(Node<Entry> y) {
        Node<Entry> x = y.left;
        opCount++;
        Node<Entry> T2 = x.right;
        opCount++;

        // Perform rotation
        x.right = y;
        opCount++;
        y.left = T2;
        opCount++;

        // Update heights
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        // Return new root
        return x;
    }

    //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child
    private Node<Entry> doubleWithLeftChild(Node<Entry> node3) {
        node3.left = rotateWithRightChild(node3.left);
        opCount++;
        return rotateWithLeftChild(node3);
    }

    //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child
    private Node<Entry> doubleWithRightChild(Node<Entry> node1) {
        node1.right = rotateWithLeftChild(node1.right);
        opCount++;
        return rotateWithRightChild(node1);
    }

    //create getTotalNumberOfNodes() method to get total number of nodes in the AVL Tree
    public int getTotalNumberOfNodes() {
        return getTotalNumberOfNodes(rootNode);
    }

    private int getTotalNumberOfNodes(Node<Entry> head) {
        if (head == null)
            return 0;
        else {
            int length = 1;
            length = length + getTotalNumberOfNodes(head.left);
            opCount++;
            length = length + getTotalNumberOfNodes(head.left);
            opCount++;
            return length;
        }
    }

    // create searchElement() method to find an element in the AVL Tree
    public void searchElement(String term) {
        Entry entry = searchElement(rootNode, term);
        if (entry != null) {
            System.out.println(entry.getTerm() + ": " + entry.getStatement() + " (" + entry.getConfidenceScore() + ")");
        } else {
            System.out.println("Term not found: " + term);
        }
    }

    private Entry searchElement(Node<Entry> head, String term) {
        while (head != null) {
            Entry headElement = head.value;
            if (term.compareTo(headElement.getTerm()) < 0) {
                head = head.left;
                opCount++;
            }
            else if (term.compareTo(headElement.getTerm()) > 0){
                head = head.right;
                opCount++;
            } else {
                return headElement;
            }
        }
        return null;
    }

    public void searchBoth(String term, String statement) {
        Entry entry = searchBoth(rootNode, (term + "\t" + statement));
        if (entry != null) {
            System.out.println("The confidence score for " + entry.getTerm() + ": " + "(" + entry.getConfidenceScore() + ")");
        } else {
            System.out.println(term + " not found!");
        }
    }

    private Entry searchBoth(Node<Entry> head, String query) {
        while (head != null) {
            Entry headElement = head.value;
            int comparison = query.toLowerCase().compareTo((headElement.term + "\t" + headElement.statement).toLowerCase());
            if (comparison < 0) {
                head = head.left;
                opCount++;
            } else if (comparison > 0) {
                head = head.right;
                opCount++;
            } else {
                return headElement;
            }
        }
        return null;
    }

    // create inorderTraversal() method for traversing AVL Tree in in-order form
    public void inorderTraversal() {
        inorderTraversal(rootNode);
    }

    private void inorderTraversal(Node<Entry> head) {
        if (head != null) {
            inorderTraversal(head.left);
            System.out.println(head.value + " ");
            inorderTraversal(head.right);
        }
    }

    // create preorderTraversal() method for traversing AVL Tree in pre-order form
    public void preorderTraversal() {
        preorderTraversal(rootNode);
    }

    private void preorderTraversal(Node<Entry> current) {
        if (current != null) {
            System.out.print(current.value + " ");
            preorderTraversal(current.left);
            preorderTraversal(current.left);
        }
    }

    // create postorderTraversal() method for traversing AVL Tree in post-order form
    public void postorderTraversal() {
        postorderTraversal(rootNode);
    }

    private void postorderTraversal(Node<Entry> current) {
        if (current != null) {
            postorderTraversal(current.left);
            postorderTraversal(current.left);
            System.out.print(current.value + " ");
        }
    }

    public void fillEntries(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                opCount++;
                String[] ent = line.split("\t");
                opCount++;
                if (ent.length >= 3) {
                    Entry x = new Entry(ent[0], ent[1], Double.parseDouble(ent[2]));
                    insertElement(x);
                    opCount++;
                } else {
                    // Log an error or handle the incomplete entry appropriately
                    System.err.println("Incomplete entry: " + line);
                }
            }
            scanner.close();
        } catch (IOException e) {
            // Handle file not found exception
            e.printStackTrace();
        }
    }

    public void printOpCounter(){
        System.out.println("Operations Count: "+ opCount);
    }

    public String fileFinder(String directoryPath, String fileName) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            return "Invalid directory path!";
        }
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    String filePath = fileFinder(file.getAbsolutePath(), fileName);
                    if (filePath != null) {
                        return filePath;
                    }
                } else {
                    if (file.getName().equals(fileName)) {
                        return file.getAbsolutePath();
                    }
                }
            }
        }
        return null;
    }
}