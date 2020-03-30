package zd1;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public List<T> getFruits() {
        return fruits;
    }

    void addFruit(T fruit) {
        fruits.add(fruit);
    }

    float getWeight() {
        float res = 0.0f;
        for (T l : fruits) {
            res += l.getWeight();
        }
        return res;
    }

    boolean compare(Box<?> secondbox) {
        return Math.abs(this.getWeight() - secondbox.getWeight()) < 0.0001;
    }

    void transferTo(Box<T> dest) {
        if (dest == this) {
            System.out.println("нальзя пересыпать в туже самую коробку");
        } else {
            dest.getFruits().addAll(fruits);
            fruits.clear();
        }
    }
}
