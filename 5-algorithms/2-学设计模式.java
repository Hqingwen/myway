
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
interface  Shape{}
class Circle implements Shape{}
class Square implements Shape{}
class Rectangle implements Shape{}
class ShapeFactory{
    public Shape getShape(String shapeType){
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }
        return null;
    }
}

/*****************************************************************************
 * 抽象工厂模式：抽象一个工厂的工厂
 *****************************************************************************/
interface  Shape{}
class Circle implements Shape{}
class Square implements Shape{}
class Rectangle implements Shape{}
interface Color{}
class Red implements Color{}
class Blue implements Color{}
class Green implements Color{}
abstract class AbstractFactory{
    abstract Color getColor(String color);
    abstract Shape getShape(String shape);
}
class ColorFactory implements AbstractFactory{
    public Color getColor(String color){
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        }else if(color.equalsIgnoreCase("BULE")){
            return new Blue();
        }else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        }
        return null;
    }

    Shape getShape(String shape){
        return null;
    }
}
class ShapeFactory implements AbstractFactory{
    Color getColor(String color){
        return null;
    }
    public Shape getShape(String shape){
        if(shape.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }else if(shape.equalsIgnoreCase("SQUARE")){
            return new Square();
        }else if(shape.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }
        return null;
    }
}
class FactoryProducer{
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}

/*****************************************************************************
 * 建造者模式：创建一个复杂对象，其通常是由各个部分的子对象用一定的算法构成
 *****************************************************************************/
interface FoodItem{
    public String name();
    public Packing packing();
    public float price();
}
interface Packing{
    public String pack();
}
class Wrapper implements Packing{
    public String pack(){
        return "Wrapper";
    }
}
class Bottle implements Packing{
    public String pack(){
        return "Bottle";
    }
}
abstract class Burger implements FoodItem{
    public Packing packing(){
        return new Wrapper();
    }
    public abstract float price();
}
abstract class ColdDrink implements FoodItem{
    public Packing packing(){
        return new Bottle();
    }
    public abstract float price();
}
class VegBurger extends Burger{
    public float price(){
        return 25.0f;
    }
    public String name(){
        return "Veg Burger";
    }
}
class ChickenBurger extends Burger{
    public float price(){
        return 25.0f;
    }
    public String name(){
        return "Chicken Burger";
    }
}
class Pepsi extends ColdDrink{
    public float price(){
        return 25.0f;
    }
    public String name(){
        return "Pepsi";
    }
}

/*****************************************************************************
 * 原型模式：用于创建重复的对象
 *****************************************************************************/
abstract class Shape implements Cloneable{
    ......
    public Object clone(){
        Object clone = null;
        try{
            clone = super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return clone;
    }
}
class Rectangle extends Shape{}
class Square extends Shape{}
class Circle extends Shape{}
class ShapeCache{
    private static Hashtable<String, Shape> shapeMap = new Hashtable<String, Shape>();
    public static Shape getShape(String shape){
        Shape cacheShape = shapeMap.get(shape);
        return (Shape)cacheShape.clone();
    }
    public static void loadCache(){
        shapeMap.pur("1",new Rectangle());
        shapeMap.pur("2",new Square());
        shapeMap.pur("3",new Circle());
    }
}

/*****************************************************************************
 * 适配器模式
 *****************************************************************************/

