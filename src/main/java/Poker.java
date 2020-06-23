import java.util.List;

public class Poker implements Comparable {

    private Color color;
    private Number number;

    public Poker(Color color, Number number) {
        this.color = color;
        this.number = number;
    }

    @Override
    public int compareTo(Object o) {
        return this.number.compareTo(((Poker)o).number);
    }

    public Color getColor() {
        return color;
    }

    public Number getNumber() {
        return number;
    }
}
