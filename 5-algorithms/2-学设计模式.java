
/*****************************************************************************
 * 适配器模式
 *****************************************************************************/
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



/*****************************************************************************
 * 单例模式
 *****************************************************************************/
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
    public void otherFunction(){}
}

/*****************************************************************************
 * 工厂模式：定义一个创建对象的接口， 让其子类自己决定实例化哪一个工厂类，
 *                 工厂模式使其创建过程延迟到子类进行。
 *****************************************************************************/
interface  Shape
{
    public void draw();
}
Class Circle implements Shape
{
    public void draw(){
        System.out.println("The Circle is draw.");
    }
}
Class Square implements Shape
{
    public void draw(){
        System.out.println("The Square is draw.");
    }
}
Class Rectangle implements Shape
{
    public void draw(){
        System.out.println("The Rectangle is draw.");
    }
}
Class ShapeFactory
{
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }else if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }
        return null;
    }
}




















