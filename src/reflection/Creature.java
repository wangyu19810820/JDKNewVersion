package reflection;

public class Creature<T> {
    public int weight;
    private String gender;

    public void show() {
        System.out.println("我是一个生物");
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
