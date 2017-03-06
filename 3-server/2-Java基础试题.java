什么是类的返射机制?
      通过类(Class对象)，可以得出当前类的fields、 method、 construtor、 interface、
superClass、 modified等，同时可以通过类实例化一个实例、设置属性、唤醒方法。
Spring中一切都是返射、 struts、 hibernate都是通过类的返射进行开发的


获取Class实例的三种方式：
1、利用对象调用getClass()方法获取该对象的Class实例；
2、使用Class类的静态方法forName()，用类的名字获取一个Class实例；
3、运用.class的方式来获取Class实例，对于基本数据类型的封装类，
      还可以采用.TYPE来获取相对应的基本数据类型的Class实例
例如：
String str1="abc";
Class cls1=str1.getClass();
Class cls2=String.class;
Class cls3=Class.forName("java.lang.String");

//设计 4个线程，其中两个线程每次对 j 增加 1，另外两个线程对 j每次减少 1。
public class TestThread
{
    private int j;
    public synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "-inc:" + j);
    }
    public synchronized void dec(){
        j--;
        System.out.println(Thread.currentThread().getName() + "-dec:" + j);
    }
    public static void main(String[] args){
        TestThread t=new TestThread();
        for (int i = 0; i < 2; i++){
            Thread inc=new Thread(new Inc(t));
            Thread dec=new Thread(new Dec(t));
            inc.start();
            dec.start();
        }
    }
}
class Inc implements Runnable
{
    private TestThread obj;
    public Inc(TestThread obj){
        this.obj=obj;
    }
    public void run(){
        // for (int i = 0; i < 100; i++)
        // {
        this.obj.inc();
        // }
        }
}
class Dec implements Runnable
{
    private TestThread obj;
    public Dec(TestThread obj){
      this.obj=obj;
    }
    public void run(){
        // for (int i = 0; i < 100; i++)
        // {
          this.obj.dec();
        // }
    }
}
























