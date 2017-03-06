
/**
 * 适配器模式
 */
//1、定义顶层接口
public interface ClassName1{
    public void func1();
}
//2、定义实现抽象类
public ststract class ClassName2{
    public void func2(){
    }
}
//3、定义实现类，继承抽象类，并实现接口
public class ClasName3 extends ClassName2 implements ClassName1{
    public fun1(){                  //实现接口函数
        fun2();                         //调用抽象类函数
    }
}



/**
 * 单例模式
 */
public class Singleton{
    public  Singleton(){}
    //饿汉式
    private static Singleton instance = new Singleton();
    public static Singleton getInstance(){
        return instance;
    }

    //懒汉式
    private static Singleton instance;
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    //饿汉式，线程安全
    public static synchronized Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    //双重校验
    private volatile static Singleton instance;
    public static Singleton getInstance(){
        if (instance == null) {
            synchronized(Singleton.class){
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    //静态内部类
    private static class SingletonHoder{
        private static final Singleton INSTANCE = new Singleton();
    }
    public static final Singleton getInstance(){
        return SingletonHoder.INSTANCE;
    }
}
//枚举单例
public enum Singleton{
    INSTANCE;
    ....
}




























