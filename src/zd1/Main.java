package zd1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //1.	Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
        System.out.println("Задание 1");
        String[] arrstring = new String[]{"a", "b", "c"};
        Integer[] arrint = new Integer[]{1, 2, 3};
        System.out.println(Arrays.toString(arrstring));
        changeElement(arrstring, 0, 1);
        System.out.println(Arrays.toString(arrstring));

        System.out.println(Arrays.toString(arrint));
        changeElement(arrint, 1, 2);
        System.out.println(Arrays.toString(arrint));


        //2.	Написать метод, который преобразует массив в ArrayList;


        System.out.println("задание 2");

        List<String> list = convert(arrstring);
        List<Integer> listint = convert(arrint);
        System.out.println(list);
        System.out.println(listint);


        //3.	Задача: с коробкой
        System.out.println("задание 3");
        Box<Apple> appleBox = new Box<>();
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());

        Box<Orange> orangeBox1 = new Box<>();
        orangeBox1.addFruit(new Orange());
        orangeBox1.addFruit(new Orange());
        orangeBox1.addFruit(new Orange());

        Box<Orange> orangeBox2 = new Box<>();
        orangeBox2.addFruit(new Orange());
        orangeBox2.addFruit(new Orange());
        // orangeBox2.addFruit(new Orange());

        System.out.println("Количество фруктов в коробках одинаковое: " + orangeBox1.compare(orangeBox2));
//пересыпаем коробку
        System.out.println("Количество в коробках до пересыпания");

        System.out.println(orangeBox1.getWeight());
        System.out.println(orangeBox2.getWeight());

        orangeBox1.transferTo(orangeBox2);
        System.out.println("Количество в коробках после пересыпания");
        System.out.println(orangeBox1.getWeight());
        System.out.println(orangeBox2.getWeight());

    }

    static <T> void changeElement(T[] arr, int first, int second) {

        if (first >= 0 && first < arr.length && second >= 0 && second < arr.length) {
            T temp = arr[second];
            arr[second] = arr[first];
            arr[first] = temp;
        } else System.out.println("индексы вне диапазона замена не произведена");

    }

    static <T> List<T> convert(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }
}
