Q：Java基本数据类型？
基本数据类型包括 byte、int、char、long、float、double、boolean 和 short。

Q：Java事件委托机制？
概念：一个源产生一个事件并将它送到一个或多个监听器那里。
在这种方案中，监听器简单的等待，知道它收到一个事件。一旦事件被接受，监听器将处理这个事件，然后返回。


Q：Java垃圾回收机制？
垃圾收集（Gabage Collection，简称GC）是将分配给对象但不再使用的内存回收或释放的过程。
如果一个对象没有指向它的引用或者其赋值null，则此对象适合进行垃圾回收。
Java提供的GC功能可以自动监测对象是否超过作用域从而达到自动回收内存的目的。

Q：transient，变量修饰符？
如果用transient声明一个实例变量，当对象存储时，它的值不需要维持。
换句话来说就是，用transient关键字标记的成员变量不参与序列化过程。


Q：Volatile原理？
概念：Java语言提供了一种稍弱的同步机制，即volatile变量，用来确保将变量的更新操作通知到其他线程。
当把变量声明为volatile类型后，编译器与运行时都会注意到这个变量是共享的，因此不会将该变量上的操作与其他内存操作一起重排序。
volatile变量不会被缓存在寄存器或者对其他处理器不可见的地方，因此在读取volatile类型的变量时总会返回最新写入的值。
在访问volatile变量时不会执行加锁操作，因此也就不会使执行线程阻塞，因此volatile变量是一种比sychronized关键字更轻量级的同步机制。
当对非volatile变量进行读写的时候，每个线程先从内存拷贝变量到CPU缓存中。
如果计算机有多个CPU，每个线程可能在不同的CPU上被处理，这意味着每个线程可以拷贝到不同的CPU cache中。
而声明变量是volatile的，JVM保证了每次读变量都从内存中读，跳过CPU cache这一步。
参考：Java并发编程：volatile关键字解析  http：//www.importnew.com/18126.html


Q：Java多态的实现？
1、静态的多态（重载）：方法名相同，参数个数或类型不相同
2、动态的多态（重写）：
	2.1、子类覆盖父类的方法，将子类的实例传与父类的引用，调用的是子类的方法
	2.2、实现接口的实例传与接口的引用，调用的实现类的方法


Q：重写与重载的区别？
重载（Overload）：一个类的多个方法，名称相同，参数个数类型不同。
重写（Override）：子类覆盖父类的方法，将子类传与父类的引用调用的还是子类的方法。


Q：Comarator与Comparable的区别？
1、比较器Comparator 位于包java.util下，相当于一种工具，它定义了两个方法，
	分别是 int compare(T o1， T o2) 和 boolean equals(Object obj)
	用法：PersonComparator comparator= new PersonComparator(); comparator.compare(person1，person2);
2、接口Comparable 位于包 java.lang下，如果一个类的实例需要和同一类的别的实例比较大小，
	而这个大小关系你希望是你自己定义的，重写它只提供了 int compareTo(T o)方法
	用法：person1.comparTo(person2)


Q：Set接口及其实现类？
实现类：
1、HashSet
	实际存储在HashMap map中，其中元素存储在key中，而value中存储一个静态Object
	当把某个类的对象存储在HashSet中，要override该类的equals()和hashCode()
2、TreeSet
	底层存储使用NavigableMap，实际使用TreeMap
	所有元素根据指定排序规则保持有序状态


Q：Map接口及其实现类？
实现类：
1、HashMap
	包含一个实现Map.Entry接口的内部类Node，即为键值对
	实际存储在Node[] table中
2、TreeMap
	树的存储方式：红黑树，自平衡二叉查找树，即每个节点的值，>=左子树，<=右子树，且左右高度差不超过1
	所有Entry总是按key根据指定排序规则保持有序状态
	存取元素时要遍历
3、Hashtable
	线程安全


Q：List接口及其实现类？
实现类：
1、ArrayList，顺序存储的线性表
	实际存储：transient Object[] elementData
		系统序列化该类对象时不会直接序列化elementData，
		而是通过ArrayList提供的writeObject()和readObject()来实现定制序列化
	可以通过Collections.synchronizedList()包装实现线程安全
	元素个数：变量size
2、LinkedList
	链式存储的线性表，相当于双向链表，实现了List，Deque，故还可以当做队列或栈使用
3、Vector
	实际存储：Object[] elementData   线程安全，基本方法都synchronized
	元素个数：变量elementCount


Q：什么时候用LinkedList，什么时候用ArrayList？
1、ArrayList是使用数组实现的list，本质上就是数组。ArrayList中的元素可以通过索引随机获取一个元素。
	但是如果该数组已满，当添加新元素时需要分配一个新的数组然后将原来数组的元素移动过去，需要O(n)的时间复杂度。
	添加或删除一个元素需要移动数组中的其他元素。这是ArrayList最大的缺点。
2、LinkedList是一个双向链表。因此，当需要获取list中某个元素，需要从头到尾遍历list。
	另一方面，在链表中添加或删除元素很快，只需要O(1)的时间复杂度。
	从空间上来说，在链表中一个节点需要两个额外的指针来指向它的previous和next节点。
总结：
	从时间复杂度来说，如果对 list 增加或删除操作较多，优先用LinkedList；如果查询操作较多，优先用ArrayList。
	从空间复杂度来说，LinkedList 会占用较多空间。


Q：HashMap和TreeMap区别？
HashMap 支持key=null 但是 Hashtable 不支持 key =null。
Hashtable 的方法是同步的，而 HashMap 的方法不是。
TreeMap 是基于"红黑树"实现，HashMap 是基于哈希表实现。


Q：AbstractMap抽象类和SortedMap接口？
1、AbstractMap抽象类：覆盖了equals()和hashCode()方法以确保两个相等映射返回相同的哈希码。
	如果两个映射大小相等、包含同样的键且每个键在这两个映射中对应的值都相同，则这两个映射相等。
	映射的哈希码是映射元素哈希码的总和，其中每个元素是Map.Entry接口的一个实现。
	因此，不论映射内部顺序如何，两个相等映射会报告相同的哈希码。
2、SortedMap接口：它用来保持键的有序顺序。SortedMap接口为映像的视图(子集)，包括两个端点提供了访问方法。
	除了排序是作用于映射的键以外，处理SortedMap和处理SortedSet一样。
	添加到SortedMap实现类的元素必须实现Comparable接口，否则您必须给它的构造函数提供一个Comparator接口的实现。
	TreeMap类是它的唯一一份实现。


3.两种常规Map性能
HashMap：适用于在Map中插入、删除和定位元素。
Treemap：适用于按自然顺序或自定义顺序遍历键(key)。
4.总结：HashMap通常比TreeMap快一点(树和哈希表的数据结构使然)，建议多使用HashMap，在需要排序的Map时候才用TreeMap。


Q：如何边遍历边移除Collection中的元素
边遍历边修改Collection的唯一正确方式是使用Iterator.remove()方法，如下：
Iterator<Integer> it = list.iterator();
while(it.hasNext()){
    // do something
    it.remove();
}
一种最常见的错误代码如下：
for(Integer i ： list){
    list.remove(i)
}
运行以上错误代码会报ConcurrentModificationException异常。
这是因为当使用foreach(for(Integer i ： list))语句时，会自动生成一个iterator来遍历该list，但同时该list正在被Iterator.remove()修改。
在Java中，一般不允许一个线程在遍历collection时另一个线程在修改它。


Q：过滤一个Collection最好的方法是什么？如过滤掉list中大于5的整数。
Iterator<Integer> it = list.iterator();
while(it.hasNext()){
    int i = it.next();
    if(i > 5) {  //过滤掉大于5的整数
        it.remove();
    }
}


Q：有序的collection？
Java 里有很多方法来维持一个collection有序。有的需要实现Comparable接口，有的需要自己指定Comparator。
Collections.sort()可以用来对list排序。该排序是稳定的，并且可以保证nlog(n)的性能。

PriorityQueue 提供排序的队列。PriorityQueue和Collections.sort()的区别是，
PriorityQueue 动态维护一个有序的队列（每添加或删除一个元素就会重新排序），但是只能获队列中的头元素。

如果collection中没有重复的元素，TreeSet 是另一个选择。
跟PriorityQueue一样的是，TreeSet 也动态维护一个有序的集合。可以从TreeSet中获取最大和最小的元素。

总结：Collections.sort()提供一个一次排序的list。PriorityQueue 和 TreeSet 动态维护排序的collection。


Q：HashSet为什么快？
在3.5之前，想用哈希表来提高集合的查询效率，只有Hashtable和Dictionary<TKey,TValue>两种选择，而这两种都是键-值方式的存储。
但有些时候，我们只需要其中一个值，例如一个Email集合，如果用泛型哈希表来存储，往往要在Key和Value各保存一次，
不可避免的要造成内存浪费。而HashSet<T>只保存一个值，更加适合处理这种情况。

此外，HashSet<T>的Add方法返回bool值，在添加数据时，如果发现集合中已经存在，则忽略这次操作，并返回false值。
而Hashtable和Dictionary<TKey,TValue>碰到重复添加的情况会直接抛出错误。

从使用上来看，HashSet<T>和线性集合List<T>更相似一些，但前者的查询效率有着极大的优势。
假如，用户注册时输入邮箱要检查唯一性，
而当前已注册的邮箱数量达到10万条，如果使用List<T>进行查询，需要遍历一次列表，时间复杂度为O（n），
而使用HashSet<T>则不需要遍历，通过哈希算法直接得到列表中是否已存在，时间复杂度为O（1），这是哈希表的查询优势


Q：HashMap为什么根据Key查找速度会快，hashcode的实现原理？
哈希表与哈希方法
哈希方法在“键- 值对”的存储位置与它的键之间建立一个确定的对应函数关系 hash() ，
使得每一个键与结构中的一个唯一的存储位置相对应：存储位置=hash( 键 )

在搜索时，首先对键进行hash 运算，把求得的值当做“键 - 值对”的存储位置，在结构中按照此位置取“键 - 值对”进行比较，
若键相等，则表示搜索成功。在存储“键 - 值对”的时候，依照相同的 hash 函数计算存储位置，并按此位置存放，
这种方法就叫做哈希方法，也叫做散列方法。
在哈希方法中使用的转换函数 hash 被称作哈希函数 ( 或者散列函数 ) 。按照此中算法构造出来的表叫做哈希表 ( 或者散列表 ) 。

哈希函数建立了从“键- 值对”到哈希表地址集合的一个映射，有了哈希函数，我们就可以根据键来确定“键 - 值对”在哈希表中的位置的地址。
使用这种方法由于不必进行多次键的比较，所以其搜索速度非常快，很多系统都使用这种方法进行数据的组织和检索。
举一个例子，有一组“键值对”：<5, ” tom ” >、 <8, ” Jane ” >、 <12, ” Bit ” >、 <17, ” Lily ” >、 <20, ” sunny ” >，
我们按照如下哈希函数对键进行计算 :hash(x)=x%17+3 ，得出如下结果：
hash(5)=8 、 hash(8)=11 、 hash(12)=15 、 hash(17)=3 、 hash(20)=6 。
我们把 <5, ” tom ” >、 <8, ” Jane ” >、 <12, ” Bit ” >、 <17, ” Lily ” >、 <20, ” sunny ” >
分别放到地址为 8 、 11 、 15 、 3 、 6 的位置上。
当要检索 17 对应的值的时候，只要首先计算 17 的哈希值为 3 ，
然后到地址为 3 的地方去取数据就可以找到 17 对应的数据是“ Lily ”了，可见检索速度是非常快的。


Q：如何将List转化成int[]？
List.toArray()方法只可能得到Integer[]，无法得到int[]。

最简单的方法是使用Apache Commons Lang库中的ArrayUtils。
int[] array = ArrayUtils.toPrimitive(list.toArray(new Integer[0]));

正确的做法如下：
int[] array = new int[list.size()];
for(int i = 0; i < list.size(); i++){
    array[i] = list.get(i);
}


Q：如何将int[]转化成List？
不能用Arrays.asList()，因为不能以int[]作为该方法的参数，要的话也只能是Integer[]。
关于Arrays.asList()方法有如下特性：
    1.该方法对于基本数据类型的数组支持并不好，当数组是基本数据类型时不建议使用
    2.当使用asList()方法时，数组就和列表链接在一起了。当更新其中之一时，另一个将自动获得更新。
    因为asList获得的List实际引用的就是数组。注意：仅仅针对对象数组类型，基本数据类型数组不具备该特性。
    3.asList得到的数组是的没有add和remove方法的。因为asList返回的List是Arrays中的内部类，而该类并没有定义add和remove方法。

正确的做法如下：
int[] array = {1，2，3，4，5};
List<Integer> list = new ArrayList<Integer>();
for(int i： array) {
  list.add(i);
}


Q：将 List 转化成 Set 最简单的方法？
有两种方法，取决于你怎么要怎么定义两个元素相等。
1、将list放入HashSet里，该方法元素是否相等是通过它们的hashCode()来比较的。
    	Set<Integer> set = new HashSet<Integer>(list);
2、如果需要自己定义比较的方法，需要用TreeSet。
	Set<Integer> set = new TreeSet<Integer>(aComparator);
	set.addAll(list);


Q： 如何删除 ArrayList 中重复的元素？
如果不关心元素在ArrayList中的顺序，可以将list放入set中来删除重复元素，然后在放回list。
Set<Integer> set = new HashSet<Integer>(list);
list.clear();
list.addAll(set);
如果关心元素在ArrayList中的顺序，可以用LinkedHashSet。


Q：拷贝list
1、使用ArrayList构造器。
	ArrayList<Integer> dstList = new ArrayList<Integer>(srcList);
2、使用Collections.copy()。
	ArrayList<Integer> dstList = new ArrayList<Integer>(srcList.size());
	Collections.copy(dstList， srcList);
需要注意的是，使用该方法的话目标list至少跟源list长度一样长。否则会报IndexOutOfBoundsException异常。

另外有两点需要注意：两种方法都是浅拷贝
Collections.copy()方法的两个参数必须都是list，而ArrayList方法参数只要是collection即可，因此ArrayList方法更通用。


Q：Java下常用的包？
java.lang            		核心语言特征（系统默认使用）
java.awt            		Windows 和 GUI 特性
java.applet            	通用 Applet 特性
java.net            		网络
java.io            		数据输入和输出
java.util            		各种使用工具特征
java.math            	数学指令
java.security            安全
java.sql 			数据库操作
java.text            		文字处理特征


Q：java.util包下有哪些工具类
java.util.concurrent.Callable<V>
java.util.Calendar
java.util.concurrent.locks.ReentrantLock
java.util.Collection<E>
java.util.Formatter
java.util.Enumeration<E>
java.util.HashSet<E>
java.util.Hashtable<K, V>
java.util.Date
java.util.EventListener
java.util.List<E>
java.util.Observable
java.util.Random
java.util.UUID uid = new UUID(32, 16)
java.util.Properties


Q：接口（interface）的特征？
1、接口中的方法可以有参数列表和返回类型，但不能有任何方法体
2、接口中可以包含字段，但是会被隐式地声明为 static 和 final
3、接口中的字段只是被存储在该接口的静态存储区域内，而不属于该接口
4、接口中的方法可以被声明为 public 或不声明，但结果都会按照 public 类型处理
5、实现一个接口时，需要将被定义的方法声明为public类型，否则为默认访问类型，Java编译器不允许这种情况
6、实现一个接口时，如果没有实现接口中所有方法，那么创建的仍然是一个接口
	方法的名字、返回类型、参数必须与接口中完全一致。如果方法的返回类型不是void，则方法体必须至少有一条return语句
7、扩展一个接口来生成新的接口应使用关键字 extends，实现一个接口使用 implement

参考设计理念：
接口是一组规则的集合，它规定了实现本接口的类或接口必须拥有的一组规则。
面向对象思想和核心之一叫做多态性，什么叫多态性？说白了就是在某个粒度视图层面上对同类事物不加区别的对待而统一处理。

在系统分析和架构中，分清层次和依赖关系，每个层次不是直接向其上层提供服务（即不是直接实例化在上层中），
而是通过定义一组接口，仅向上层暴露其接口功能，上层对于下层仅仅是接口依赖，而不依赖具体类。
这样做的好处是显而易见的，首先对系统灵活性大有好处。
当下层需要改变时，只要接口及接口功能不变，则上层不用做任何修改。甚至可以在不改动上层代码时将下层整个替换掉，就像

我们将一个WD的60G硬盘换成一个希捷的160G的硬盘，计算机其他地方不用做任何改动，
而是把原硬盘拔下来、新硬盘插上就行了，因为计算机其他部分不依赖具体硬盘，
而只依赖一个IDE接口，只要硬盘实现了这个接口，就可以替换上去。


Q：Java抽象类和Java接口的比较？
参考设计理念：abstract class表示的是"is-a"关系，interface表示的是"like-a"关系
1、一个类可以实现 implements 多个接口，但却只能继承 extend 最多一个抽象类
2、抽象类可以包含具体方法；接口的所有方法都是抽象的
3、抽象类可以声明和使用字段；接口则不能，但可以创建静态的 final 常量
4、抽象类中的变量、方法可以是 public、protected、private 或者默认的package，其值可以在子类中重新定义，也可以重新赋值
	接口方法默认都是 public abstract 类型，变量都是 public static  final，且必须给其初值，所以实现类中不能重新定义，也不能改变其值
5、抽象类可以定义构造函数；接口不能

注：当功能需要积累时用抽象类，不需要时使用接口。


Q：序列化及其实现、反序列化？
序列化：
	处理对象流的机制，所谓对象流也就是将对象的内容进行流化。
	可以对流化后的对象进行读写操作，也可将流化后的对象传输于网络之间。

序列化的实现：
1、将需要被序列化的类实现Serializable接口，该接口没有需要实现的方法，implement Serializable 只是为了标注该对象是可被序列化的，
2、然后使用一个输出流（如：FileOutputStrean）来构造一个ObjectOutputStream（对象流）对象，
3、接着，使用 ObjectOutputStream 对象的 writeObject(Object obj) 方法就可以将参数为obj的对象写出（即保存期状态），
	要恢复（即反序列化）的话则用输入流。

注：序列化的实现方式还可以：
1、实现接口Externalizable
2、把对象包装成JSON字符串传输
3、采用谷歌的ProtoBuf

反序列化：与序列化相反的过程。


Q：同步与异步的区别？
同步：上一段代码没有完成，下一段必须等到上一段代码完成后才可以执行。	如买票排队
异步：上一段代码没有完成，下一段不必等到上一段代码完成就可以执行。		如手机发短信。


Q：Java.lang.object 里的三个方法wait() notify() notifyAll()？
wait()方法导致当前线程等待，直到其他线程调用同步监视器的notify方法或notifyAll方法来唤醒该线程。
wait(mills)方法都是等待指定时间后自动苏醒，调用wait方法的当前线程会释放该同步监视器的锁定，可以不用notify或notifyAll方法把它唤醒。
notify()：唤醒在同步监视器上等待的单个线程，如果所有线程都在同步监视器上等待，则会选择唤醒其中一个线程，
		选择是任意性的，只有当前线程放弃对该同步监视器的锁定后，也就是使用wait方法后，才可以执行被唤醒的线程。
notifyAll()方法唤醒在同步监视器上等待的所有的线程。只用当前线程放弃对该同步监视器的锁定后，才可以执行被唤醒的线程


Q：多线程的实现方法？
1、继承Thread类
2、实现Runnable接口


Q：Java线程中run和start方法的区别
1、用start方法来启动线程，真正实现了多线程运行，这时无需等待run方法体代码执行完毕而直接继续执行下面的代码。
通过调用Thread类的start()方法来启动一个线程，这时此线程处于就绪（可运行）状态，并没有运行，
一旦得到spu时间片，就开始执行run()方法，这里方法run()称为线程体，它包含了要执行的这个线程的内容，Run方法运行结束，此线程随即终止。
2、run()方法只是类的一个普通方法而已，如果直接调用Run方法，程序中依然只有主线程这一个线程，
其程序执行路径还是只有一条，还是要顺序执行，还是要等待run方法体执行完毕后才可继续执行下面的代码，这样就没有达到写线程的目的。
3、run()方法在本线程内调用该Runnable对象的run()方法，可以重复多次调用；
start()方法启动一个线程，调用该Runnable对象的run()方法，不能多次启动一个线程；
总结：调用start方法方可启动线程，而run方法只是Thread的一个普通方法调用，还是在主线程里执行。

Q ：为什么要使用线程通讯？
当使用synchronized 来修饰某个共享资源时(分同步代码块和同步方法两种情况）,当某个线程获得共享资源的锁后就可以执行相应的代码段，
直到该线程运行完该代码段后才释放对该 共享资源的锁，让其他线程有机会执行对该共享资源的修改。
当某个线程占有某个共享资源的锁时，如果另外一个线程也想获得这把锁运行就需要使用wait() 和notify()/notifyAll()方法来进行线程通讯了。



Q：什么是类的返射机制？
通过类(Class对象)，可以得出当前类的 fields、 method、 construtor、 interface、superClass、 modified等，
同时可以通过类实例化一个实例、设置属性、唤醒方法。
例如Spring中一切都是返射；struts、 hibernate都是通过类的返射进行开发的

Q：类的返射机制中的包及核心类？
java.lang.Class
java.lang.refrection.Method
java.lang.refrection.Field
java.lang.refrection.Constructor
ava.lang.refrection.Modifier
java.lang.refrection.Interface


Q：获取Class实例的三种方式：
1、利用对象调用getClass()方法获取该对象的Class实例；
2、使用Class类的静态方法forName()，用类的名字获取一个Class实例；
3、运用.class的方式来获取Class实例，对于基本数据类型的封装类，
      还可以采用.TYPE来获取相对应的基本数据类型的Class实例
例如：
String str1="abc";
Class cls1=str1.getClass();
Class cls2=String.class;
Class cls3=Class.forName("java.lang.String");


Q：super() 和 this() 的区别？
this指当前类的对象，super父类对象
super()：在子类访问父类的成员和行为，必须受类继承规则的约束
this()：代表当前对象，当然所有的资源都可以访问。
在子类构造函数中，如果第一行没有写super()，编译器会自动插入。
但是如果父类没有不带参数的构造函数，或这个函数被私有化了(用private修饰)，此时你必须加入对父类的实例化构造。
而this()就没有这个要求，因为它本身就进行实例化的构造。


Q：作用域的区别？
作用域                         当前类             同一包内            子孙类             其他包
public                              Y                       Y                       Y                       Y
protected                       Y                       Y                       Y                       N
private                             Y                       Y                       N                      N
default                             Y                       N                      N                      N


Q：Java语言如何进行异常处理，关键字：throws，throw，try，catch，finally分别代表什么意义？在try块中可以抛出异常吗？
Try：执行部分，产生异常
Catch：捕捉异常
Finally：不管有没有异常都执行
Throws：在方法声明处声明要抛出的异常，调用者必须对其进行处理。
Throw：抛出一个异常
在try中可以抛出异常，一般与声明的异常相同。
自定义异常要继承于Exception或Exception的子类


Q：最常见到的 runtime exception？
ArithmeticException    						ArrayStoreException
BufferOverflowException    				BufferUnderflowException
CannotRedoException    					CannotUndoException
ClassCastException    						CMMException
ConcurrentModificationException   		DOMException
EmptyStackException    					IllegalArgumentException
IllegalMonitorStateException    			IllegalPathStateException
IllegalStateException   						ImagingOpException
IndexOutOfBoundsException    			MissingResourceException
NegativeArraySizeException    				NoSuchElementException
NullPointerException   						ProfileDataException
ProviderException    						RasterFormatException
SecurityException    						SystemException
UndeclaredThrowableException   			UnmodifiableSetException
UnsupportedOperationException

一般异常:
   IOException
   FileNotFoundException
   SqlException

Q：Java异常？
类java.lang.Throwable是所有异常类的基类，它包括两个子类：Exception和Error
1、Error类
该类代表错误，指程序无法恢复的异常情况。对于所有错误类型以及其子类，都不要求程序进行处理。
常见的Error类例如内存溢出StackOverflowError等。
2、Exception类
该类代表异常，指程序有可能恢复的异常情况。该类就是整个Java语言异常类体系中的父类。使用该类，可以代表所有异常的情况。

Exception下除了RuntimeException，其它的是受检查的异常(checked exceptions),
其必须被 try{}catch语句块所捕获,或者在方法签名里通过throws子句声明.
受检查的异常必须在编译时被捕捉处理,命名为 Checked Exception 是因为Java编译器要进行检查,Java虚拟机也要进行检查,以确保这个规则得到遵守.
RuntimeException的异常是运行时异常(runtime exceptions),需要程序员自己分析代码决定是否捕获和处理,比如 空指针,被0除...

声明为Error的，则属于严重错误,需要根据业务信息进行特殊处理,Error不需要捕捉。

自定义异常类可以继承Throwable类或者Exception，而不要继承Error类。自定义异常类之间也可以有继承关系

Throwable  |- Error
                     |- Exception      |-    IOException    	|-    FileNotFoundException
                                                                         		|-    UnknowHostException
                                                                        		|-    EOFException
                                                 |-    ClassNotFound
                                                 |-    cloneNotSupported    Exception
                                                 |-    RuntimeException    	|-    AirthmeticException
                                                                                    		|-    IllegalArgumentException
                                                                                    		|-    IndexOutOfBoundsException
                                                                                    		|-    NoSuchElementException
                                                                                    		|-    NullPointerException


Q：Java中的流？
在java使用流的机制进行数据的传送，从文件到内存是输入流，从内存到文件是输出流，输入流可以通过read读取，输出流以write或print写入；
对于流可以是分为高层流和低层流，低层以一个字节或字符为单位进行处理，高层流以一批数据为单位进行处理。

字节流
	FileIputStream				FileOutputStrean
	ObjectInputStream			ObjectOutputStream
	BufferedInputStream		BufferedOutputStream
	DataInputStrean			DataOutputStream
字符流
	FileReader					FileWriter
	BufferedReader			BufferedWriter
转换流
	InputStreamReader		OutputStreamWriter
标准输出
	PrintStream				PrintWriter




















