import java.util.concurrent.Phaser;

public class FizzBuzz {
    String printFizz = "fizz";
    String printBuzz = "buzz";
    String printFizzBuzz = "fizzbuzz";
    int n;
    int printNumber[];

    public FizzBuzz(int n) {
        this.n = n;
        printNumber = new int[n];
        for (int i = 0; i < n; i++) {
            printNumber[i] = i+1;
        }
    }

    public void fizz() {
        System.out.print(printFizz + ", ");
    }

    public void buzz() {
        System.out.print(printBuzz + ", ");
    }

    public void fizzbuzz() {
        System.out.print(printFizzBuzz + ", ");
    }

    public void number(int num) {
        System.out.print(num + ", ");
    }
}


class FizzBuzzTread1 implements Runnable {
    FizzBuzz fb;
    Phaser phaser;

    FizzBuzzTread1(Phaser phaser, FizzBuzz fb) {
        this.phaser = phaser;
        this.fb = fb;
        phaser.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < fb.printNumber.length; i++) {
            if (fb.printNumber[i] % 3 == 0 && fb.printNumber[i] % 5 != 0) {
                fb.fizz();
            }
            if (i < fb.printNumber.length-1) {
                phaser.arriveAndAwaitAdvance();
            }else {
                phaser.arriveAndDeregister();
            }
        }
    }
}


class FizzBuzzTread2 implements Runnable {
    FizzBuzz fb;
    Phaser phaser;

    FizzBuzzTread2(Phaser phaser, FizzBuzz fb) {
        this.phaser = phaser;
        this.fb = fb;
        phaser.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < fb.printNumber.length; i++) {
            if (fb.printNumber[i] % 5 == 0 && fb.printNumber[i] % 3 != 0) {
                fb.buzz();
            }
            if (i < fb.printNumber.length-1) {
                phaser.arriveAndAwaitAdvance();
            }else {
                phaser.arriveAndDeregister();
            }
        }
    }
}


class FizzBuzzTread3 implements Runnable {
    FizzBuzz fb;
    Phaser phaser;

    FizzBuzzTread3(Phaser phaser, FizzBuzz fb) {
        this.phaser = phaser;
        this.fb = fb;
        phaser.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < fb.printNumber.length; i++) {
            if ((fb.printNumber[i] % 3 == 0) && (fb.printNumber[i] % 5 == 0)) {
                fb.fizzbuzz();
            }
            if (i < fb.printNumber.length-1) {
                phaser.arriveAndAwaitAdvance();
            }else {
                phaser.arriveAndDeregister();
            }
        }
    }
}


class FizzBuzzTread4 implements Runnable {
    FizzBuzz fb;
    Phaser phaser;

    FizzBuzzTread4(Phaser phaser, FizzBuzz fb) {
        this.phaser = phaser;
        this.fb = fb;
        phaser.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < fb.printNumber.length; i++) {
            if ((fb.printNumber[i] % 3 != 0) && (fb.printNumber[i] % 5 != 0)) {
                fb.number(fb.printNumber[i]);
            }
            if (i < fb.printNumber.length-1) {
                phaser.arriveAndAwaitAdvance();
            }else {
                phaser.arriveAndDeregister();
            }
        }
    }
}


class FizzBuzzRunner {
    public static void main(String[] args) {
        FizzBuzz fb = new FizzBuzz(15);
        Phaser phaser = new Phaser(1);
        FizzBuzzTread1 A = new FizzBuzzTread1(phaser, fb);
        FizzBuzzTread2 B = new FizzBuzzTread2(phaser, fb);
        FizzBuzzTread3 C = new FizzBuzzTread3(phaser, fb);
        FizzBuzzTread4 D = new FizzBuzzTread4(phaser, fb);

        for (int i = 0; i < fb.printNumber.length; i++) {
            phaser.arriveAndAwaitAdvance();
        }
    }
}
