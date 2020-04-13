package zd5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MainClass {
    public static final int CARS_COUNT = 4;


    static CyclicBarrier cbStart = new CyclicBarrier(CARS_COUNT, () ->
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"));

    static CyclicBarrier cbEnd = new CyclicBarrier(CARS_COUNT, () ->
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!"));

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {

            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
            cars[i].setStartBarrier(cbStart);
            cars[i].setEndBarrier(cbEnd);

        }
        for (Car car : cars) {
            new Thread(car).start();
        }

    }
}
