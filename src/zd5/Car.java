package zd5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private final Race race;
    private final int speed;
    private final String name;
    static Lock winLock = new ReentrantLock();


    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    private CyclicBarrier startBarrier;

    public void setEndBarrier(CyclicBarrier endBarrier) {
        this.endBarrier = endBarrier;
    }

    public void setStartBarrier(CyclicBarrier startBarrier) {
        this.startBarrier = startBarrier;
    }

    private CyclicBarrier endBarrier;

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        boolean win = false;
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            startBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        try {
            if (winLock.tryLock()) {
                System.out.println(this.name + " WIN");
                win = true;
            }
            endBarrier.await();
            if (win) winLock.unlock();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
