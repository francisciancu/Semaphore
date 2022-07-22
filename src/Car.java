import java.util.Random;
import java.util.concurrent.Semaphore;


public class Car extends Thread {

    private final Semaphore semaphoreNS;
    private final Semaphore semaphoreWE;
    private final String carDirection;
    private final int carValue;

    private final int carNumber;

    private static int carNumberCounter = 0;

    //private final String[] directions = {"N-S","W-V"};
    public Car(Semaphore semaphoreNS, Semaphore semaphoreWE, String carDirection) {
        this.semaphoreNS = semaphoreNS;
        this.semaphoreWE = semaphoreWE;
        this.carNumber = ++carNumberCounter;
        this.carDirection = carDirection;
        this.carValue = randomValueGenerator();
    }

    @Override
    public String toString() {
        return "Car " + this.carNumber + " { direction : " + this.carDirection + ", value : " + this.carValue + " }";
    }

    public int getCarValue() {
        return carValue;
    }

    private int randomValueGenerator() {
        Random random = new Random();
        return random.nextInt(0, 10);
    }

//    private String randomDirectionGenerator(String[] directions) {
//        Random random = new Random();
//        return directions[random.nextInt(directions.length)];
//    }

    @Override
    public void run() {
        passingIntersection(semaphoreNS, semaphoreWE, carDirection);
    }

    private void passingIntersection(Semaphore semaphoreNS, Semaphore semaphoreWE, String carDirection) {
        if (carDirection.equals("N-S")) {
            semaphores(semaphoreNS, semaphoreWE);

        } else if (carDirection.equals("W-E")) {
            semaphores(semaphoreWE, semaphoreNS);
        }
    }

    private void semaphores(Semaphore semaphore1, Semaphore semaphore2) {

        try {
            semaphore1.acquire();
            sleep(2000);
            System.out.println("Car " + this.carNumber + " passed the intersection from " + this.carDirection + " with value " + this.carValue);

            if (semaphore1.availablePermits() == 0 && semaphore2.availablePermits() == 0) {
                semaphore2.release();
            }
        } catch (InterruptedException ignored) {
        }
    }
}
