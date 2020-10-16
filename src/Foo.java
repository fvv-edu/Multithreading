public class Foo {
    public void first() {
        System.out.print("first");
    }

    public void second() {
        System.out.print("second");
    }

    public void third() {
        System.out.print("third");
    }
}

class FooThread implements Runnable {
    Foo foo;
    String name;

    FooThread(Foo foo, String name) {
        this.foo = foo;
        this.name = name;
        new Thread(this, name).start();
    }

    @Override
    public void run() {
        try {
            if (name.equals("A")) {
                foo.first();
            }else if (name.equals("B")) {
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e) {
                    System.out.println(e);
                }
                foo.second();
            }else if (name.equals("C")) {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    System.out.println(e);
                }
                foo.third();
            }else {
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}

class FooRunner {
    public static void main(String[] args) {
        Foo foo = new Foo();
        FooThread A = new FooThread(foo, "A");
        FooThread B = new FooThread(foo, "B");
        FooThread C = new FooThread(foo, "C");
    }
}
