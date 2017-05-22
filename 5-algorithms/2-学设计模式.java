
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
 *                    工厂模式使其创建过程延迟到子类进行。
 *****************************************************************************/
interface  Shape{}
class Circle implements Shape{}
class Square implements Shape{}
class Rectangle implements Shape{}

//工厂类
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

//工厂抽象类
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

//工厂的工厂
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
//二级对象接口
interface Item{
    public String name();
    public Packing packing();
    public float price();
}

//一级对象接口
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

//实现二级对象接口的抽象类1
abstract class Burger implements Item{
    public Packing packing(){
        return new Wrapper();
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

//实现二级对象接口的抽象类2
abstract class ColdDrink implements Item{
    public Packing packing(){
        return new Bottle();
    }
    public abstract float price();
}
class Pepsi extends ColdDrink{
    public float price(){
        return 25.0f;
    }
    public String name(){
        return "Pepsi";
    }
}

//三级对象
class Meal {
    private List<Item> items = new ArrayList<Item>();
    public void addItem(Item item){
        items.add(item);
    }
    public folat getCost(){
        float cost = 0.0f;
        for(Item item : items){
            cost += item.price();
        }
        return cost;
    }
    public void showItems(){
        for(Item item : items){
            ....
        }
    }
}

//最终对象建造类
class MealBuilder {
    public Meal PrepareVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegMeal());
        meal.addItem(new Coke());
        return meal;
    }
    public Meal PrepareNonVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}


/*****************************************************************************
 * 原型模式：用于创建重复的对象
 *****************************************************************************/
//实现Cloneable接口的抽象类
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
class Rectangle extends Shape{.....}
class Square extends Shape{.....}
class Circle extends Shape{.....}

//包含需要创建对象的缓冲类
class ShapeCache{
    private static Hashtable<String, Shape> shapeMap = new Hashtable<String, Shape>();
    public static Shape getShape(String shape){
        Shape cacheShape = shapeMap.get(shape);
        return (Shape)cacheShape.clone();
    }
    public static void loadCache(){
        shapeMap.put("1",new Rectangle());
        shapeMap.put("2",new Square());
        shapeMap.put("3",new Circle());
    }
}

/*****************************************************************************
 * 适配器模式：
 * 例子：内存卡-读卡器-笔记本  ，读卡器就是适配器
 *****************************************************************************/
//高级类接口及其实现
interface AdvancedMediaPlayer{
    public void playVlc(String fileName)；
    public void playMp4(String fileName)；
}
class VlcPlayer implements AdvancedMediaPlayer{
    public void playVlc(String fileName){
        .....
    }
    public void playMp4(String fileName){}
}
class Mp4Player implements AdvancedMediaPlayer{
    public void playVlc(String fileName){}
    public void playMp4(String fileName){
        .....
    }
}

//普通类接口
interface MediaPlayer{
    public void play(String audioType, String fileName);
}

//实现普通类接口的适配器
class MediaAdapter implements MediaPlayer{
    AdvancedMediaPlayer advancedMediaPlayer;
    public MediaAdapter(String audioType){
        if(audioType.equalsIgnoreCase("VLS")){
            advancedMediaPlayer = new VlcPlayer();
        }else if(audioType.equalsIgnoreCase("MP4")){
            advancedMediaPlayer = new Mp4Player();
        }
    }
    public coid play(String audioType, String fileName){
        if(audioType.equalsIgnoreCase("VLS")){
            advancedMediaPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("MP4")){
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}

//实现普通类接口的高级类，且其包含了上述适配器
class AudioPlayer implements MediaPlayer{
    MediaAdapter mediaAdapter;
    public coid play(String audioType, String fileName){
        if(audioType.equalsIgnoreCase("MP3")){
            ......
        }else if(audioType.equalsIgnoreCase("VLS")
            || audioType.equalsIgnoreCase("MP4")){
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }else{
            ......
        }
    }
}

/*****************************************************************************
 * 桥接模式
 *****************************************************************************/








































































