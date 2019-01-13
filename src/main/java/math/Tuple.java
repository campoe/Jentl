package math;

public class Tuple<T> {

    public T left, right;

    public Tuple(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public Tuple() {
        this.left = this.right = null;
    }

}
