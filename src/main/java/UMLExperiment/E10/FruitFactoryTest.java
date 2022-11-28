package UMLExperiment.E10;

import java.lang.reflect.InvocationTargetException;

abstract class Fruit{
    abstract public String getCategory();
    abstract public int getWeight();

    public void getDescription(String category){
        System.out.println("This is a(an) " + category + ".");
    }
}

class Apple extends Fruit {
    private String brand;
    private String producer;
    private int weight;

    public Apple(String brand, String producer, int weight) {
        this.brand = brand;
        this.producer = producer;
        this.weight = weight;
    }

    @Override
    public String getCategory() {
        return "apple";
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    public void getFruitDescription(){
        super.getDescription(this.getCategory());
        System.out.println("brand: " + this.brand +"\n"
        + "producer: " + this.producer +"\n"
        + "weight: " + getWeight());
    }
}

class Grape extends Fruit {
    private final String brand;
    private final String producer;
    private final int weight;

    public Grape(String brand, String producer, int weight) {
        this.brand = brand;
        this.producer = producer;
        this.weight = weight;
    }

    @Override
    public String getCategory() {
        return "grape";
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    public void getFruitDescription(){
        super.getDescription(this.getCategory());
        System.out.println("brand: " + this.brand +"\n"
                + "producer: " + this.producer +"\n"
                + "weight: " + getWeight());
    }
}

interface FruitFactory{
    public Fruit produce(String brand, int weight);
    public void service();
}

class AppleFruitWorker implements FruitFactory {
    private final String name;

    public AppleFruitWorker(String name) {
        this.name = name;
    }

    @Override
    public Fruit produce(String brand, int weight){
        Apple apple = new Apple(brand, this.name, weight);
        apple.getFruitDescription();
        System.out.println();
        return apple;
    }

    @Override
    public void service() {
        System.out.println("The following fruits will be produced by a FruitWorker named " + this.name);
        System.out.println("-----------------------------------------------");
    }
}

class GrapeFruitWorker implements FruitFactory {
    private final String name;

    public GrapeFruitWorker(String name) {
        this.name = name;
    }

    @Override
    public Fruit produce(String brand, int weight){
        Grape grape = new Grape(brand, this.name, weight);
        grape.getFruitDescription();
        System.out.println();
        return grape;
    }

    @Override
    public void service() {
        System.out.println("The following fruits will be produced by a FruitWorker named " + this.name);
        System.out.println("-----------------------------------------------");
    }
}



public class FruitFactoryTest {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        GrapeFruitWorker ZhangSan = new GrapeFruitWorker(Worker.ZhangSan.getName());
        ZhangSan.service();

        //Fruit 1
        ZhangSan.produce(Worker.ZhangSan.getFruitCategory(), 250);
        //Fruit 2
        ZhangSan.produce("巨峰", 300);

        AppleFruitWorker LiSi = new AppleFruitWorker("李四");
        LiSi.service();

        //Fruit 3
        LiSi.produce("红富士", 350);
        //Fruit 4
        LiSi.produce("巨峰", 400);

        //Fruit 5
        FruitFactory fruitFactory = (FruitFactory) Class.forName("UMLExperiment.E10.AppleFruitWorker").getDeclaredConstructor(String.class).newInstance("王五");
        fruitFactory.service();
        fruitFactory.produce("红富士", 350);

        //Fruit 6
        fruitFactory = (FruitFactory) Class.forName("UMLExperiment.E10.GrapeFruitWorker").getDeclaredConstructor(String.class).newInstance("赵六");
        fruitFactory.service();
        fruitFactory.produce("巨峰", 400);

    }
}

enum Worker{
    ZhangSan("张三", "巨峰"),
    LiSi("李四", "红富士");


    private final String name;
    private final String fruitCategory;

    Worker(String name, String fruitCategory) {
        this.name = name;
        this.fruitCategory = fruitCategory;
    }

    public String getName() {
        return name;
    }

    public String getFruitCategory() {
        return fruitCategory;
    }
}

