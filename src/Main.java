import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphoreNS = new Semaphore(1, true);
        Semaphore semaphoreWE = new Semaphore(0, true);
        int NSSum = 0;
        int WESum = 0;
        ArrayList<Car> carsNS = new ArrayList<>();//arraylist to add all the cars to
        ArrayList<Car> carsWE = new ArrayList<>();//arraylist to add all the cars to

        for (int i = 0; i < 50; i++) {
            if ((i % 2) == 0) {
                Car car = new Car(semaphoreNS, semaphoreWE, "N-S");
                carsNS.add(car);
                NSSum += car.getCarValue();
            } else {
                Car car = new Car(semaphoreNS, semaphoreWE, "W-E");
                carsWE.add(car);
                WESum += car.getCarValue();
            }
        }
        for (Car car : carsNS) {
            System.out.println(car);
        }
        for (Car car : carsWE) {
            System.out.println(car);
        }
        for (Car car : carsNS) {
            car.start();
        }
        for (Car car : carsWE) {
            car.start();
        }
        System.out.println("The sum for N-S cars is : " + NSSum);
        System.out.println("The sum for W-E cars is : " + WESum);

    }

}