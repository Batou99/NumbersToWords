import logic.SuperInteger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lorenzo
 * Date: 24/11/12
 * Time: 09:38
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        List<SuperInteger> list = new ArrayList<SuperInteger>();
        list.add(new SuperInteger(1));
        list.add(new SuperInteger(21));
        list.add(new SuperInteger(105));
        list.add(new SuperInteger(56945781));
        list.add(new SuperInteger(999999999));

        for(SuperInteger num:list) {
            System.out.println(num + " => " + num.toText());
        }

    }
}
