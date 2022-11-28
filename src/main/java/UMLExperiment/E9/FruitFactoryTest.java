package UMLExperiment.E9;


import java.util.Scanner;

public class FruitFactoryTest {
    public static Fruit produceFruit(String category){
        switch (category) {
            case "apple":
                return new Apple();
            case "grape":
                return new Grape();
            default:
                System.out.println("This fruit is not supported yet!");
                return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter the fruit you want to:");
        Fruit fruit = FruitFactoryTest.produceFruit(new Scanner(System.in).nextLine());
        if (fruit != null){
            fruit.grow();
            fruit.produce();
        }
    }
}
interface Fruit{
    default void grow(){
        System.out.println("a fruit was grown");
    }
    default void produce(){
        System.out.println("a fruit was produced");
    }
}
class Apple implements Fruit{
    @Override
    public void grow() {
        System.out.println("you grew an apple");
    }

    @Override
    public void produce() {
        System.out.println("You produced an apple");
    }
}
class Grape implements Fruit{
    @Override
    public void grow() {
        System.out.println("you grew a grape");
    }

    @Override
    public void produce() {
        System.out.println("You produced a grape");
    }
}
