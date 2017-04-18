
Q：编程：编写一个截取字符串的函数，输入为一个字符串和字节数，输出为按字节截取的字符串。
但是要保证汉字不被截半个，如"我ABC"4，应该截为"我AB"，输入"我ABC汉DEF"，6，应该输出为"我ABC"而不是"我ABC+汉的半个"。
public static void split(String source，int num) throws Exception  {
    int k=0;
    String temp="";
    for (int i = 0; i<source.length(); i++)  {
        byte[] b = (source.charAt(i)+"").getBytes();
        k = k + b.length;
        if(k>num)  break;
        temp=temp+source.charAt(i);
    }
    System.out.println(temp);
}


Q：设计 4个线程，其中两个线程每次对 j 增加 1，另外两个线程对 j每次减少 1？
public class TestThread
{
    private int j;
    public synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "-inc：" + j);
    }
    public synchronized void dec(){
        j--;
        System.out.println(Thread.currentThread().getName() + "-dec：" + j);
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


给定一个正整数n，构造一个n*n 维的矩阵，从1,2,........到n*n使其位置的轨迹构成一个螺旋状，举例来说，如果给定n=5，则矩阵看起来如下：
1    10、2    11、3    12、4    13、5
16    14、17    15、18    16、19    17、6
15    18、24    19、25    20、20    21、7
14    22、23    23、22    24、21    25、8
13    26、12    27、11    28、10    29、9
函数声明如下：
public int[][] getScrewMatrix(int n)
请考虑是否可以在函数体中只用一条循环语句就完成螺旋矩阵的构建？如可以，请说明思路，并完成函数体中的部分。








































































































































































