package logic;

/**
 * Created with IntelliJ IDEA.
 * User: lorenzo
 * Date: 24/11/12
 * Time: 09:49
 * To change this template use File | Settings | File Templates.
 */
public class SuperInteger implements Printable {

    private Integer number;

    public SuperInteger(Integer number) {
        this.number = number;
    }

    public SuperInteger(int number) {
        this.number = new Integer(number);
    }

    @Override
    public String toText() {
        return NumberConverter.multipleToText(number);
    }

    @Override
    public String toString() {
        return number.toString();
    }
}
