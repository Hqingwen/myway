/**
 * 节点类(带后置节点）
 * @author HQW
 * @version 0.0.1
 */
public class Node<T>{

    private T data;
    private Node next;

    public Node(T data){
        this.data = data;
    }

    public void setData(T data){
        this.data = data;
    }
    public T getData(){
        return this.data;
    }
    public void setNext(Node next){
        this.next = next;
    }
    public Node getNext(){
        return this.next;
    }

    public String toString(){
        return "{\"data\":"+this.data+",\"next\":"+this.next+"}";
    }
}

/**
 * 节点类（带前置后置节点）
 * @author HQW
 * @version 0.0.1
 */
public class DNode<T>{

    private T data;
    private DNode prev;
    private DNode next;

    public DNode(T data){
        this.data = data;
    }

    public void setPrev(DNode prev){
        this.prev = prev;
    }
    public DNode getPrev(){
        return this.prev;
    }
    public void setData(T data){
        this.data = data;
    }
    public T getData(){
        return this.data;
    }
    public void setNext(DNode next){
        this.next = next;
    }
    public DNode getNext(){
        return this.next;
    }

    public String toString(){
        return this.prev+" -> {"+this.data+"} -> "+this.next;
    }
}

/**
 * 单链表（后置法）
 * @author HQW
 * @version 0.0.1
 */
public class NodeList<T>{
    private Node head;
    private int length;

    public NodeList(Node head){
        this.head = head;
        this.length = 1;
    }

    public boolean isEmpty(){
        return this.length==0;
    }
    public void insertNode(T data){
        Node node = new Node(data);
        node.next = this.head;
        this.head = node;
    }
    public Node deleteNode(){
        Node node = this.head;
        if(!isEmpty){
            this.head = this.head.next;
        }
        return node;
    }
}

/**
 * 栈
 * @author HQW
 * @version 0.0.1
 */
public class Stack {

    Object[] data;
    int maxSize;
    int top;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        data = new Object[maxSize];
        top = -1;
    }

    /**
    * 依次加入数据
    * @param data 要加入的数据
    * @return 添加是否成功
    */
    public boolean push(Object data) {
        if(top+1== maxSize) {
            System.out.println("栈已满!");
            return false;
        }
        this.data[++top] = data;
        return true;
    }

    /**
    * 从栈中取出数据
    * @return 取出的数据
    */
    public Object pop() throws Exception{
        if(top==-1){
            throw new Exception("栈已空!");
        }
        return this.data[top--];
    }
}