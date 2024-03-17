public class Node<T extends Entry>{
    public Node<Entry> left;
    public Node<Entry> right;
    public int height;
    public Entry value;

    public Node(Node<Entry> left, Node<Entry> right, int height){
        this.left = left;
        this.right = right;
        this.height = height;
    }

    public Node(Entry value) {
        this.value = value;
        this.right = null;
        this.left = null;
    }

    public Node<Entry> getLeft() {
        return left;
    }

    public void setLeft(Node<Entry> left) {
        this.left = left;
    }

    public Node<Entry> getRight() {
        return right;
    }

    public void setRight(Node<Entry> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString(){
        return value.term;
    }

}
