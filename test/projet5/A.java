

public class A {
    private B b;

    public A() {
        b = new B();
    }

    public void doSomething() {
        System.out.println("A is doing something.");
        b.doSomethingElse();
    }
}
