/**
 * 节点类(带后置节点）
 * @author HQW
 * @version 0.0.1
 */
public class Node{

    private Object data;
    private Node next;

    public Node(){
        this.data = null;
        this.next = null;
    }
    public Node(Object data,Node next){
        this.data = data;
        this.next = next;
    }

    public void setData(Object data){
        this.data = data;
    }
    public Object getData(){
        return this.data;
    }
    public void setNext(Node next){
        this.next = next;
    }
    public Node getNext(){
        return this.next;
    }

    public String toString(){
        rerturn "[\"data\":"+this.data.toString()+",\"next\":"+this.next+"]";
    }
}

/**
 * 节点类（带前置后置节点）
 * @author HQW
 * @version 0.0.1
 */
public class DNode(){

    private Object data;
    private DNode prev;
    private DNode next;

    public DNode(){
        this.data = null;
        this.prev = null;
        this.next = null;
    }
    public DNode(Object data, DNode prev, DNode next){
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public void setData(Object data){
        this.data = data;
    }
    public Object getData(){
        return this.data;
    }
    public void setPrev(DNode prev){
        this.prev = prev;
    }
    public DNode getPrev(){
        return this.prev;
    }
    public void setNext(DNode next){
        this.next = next;
    }
    public Dnode getNext(){
        return this,next;
    }

    public String toString(){
        rerturn "{\"data\":["+this.data.toString()+"], \"prev\":["+this.next++"], \"next\":["+this.next+"]}";
    }
}

/**
 * 单链表
 * @author HQW
 * @version 0.0.1
 */
public class NodeList





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