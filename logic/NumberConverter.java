package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lorenzo
 * Date: 24/11/12
 * NOTE: The methods for this class are static because it makes not sense to instantiate and object to do the transformation (Like in Math.sqrt)
 *  Methods are public for easy testing
 */
public class NumberConverter {

    private final static String[] ENGLISH_NUMBER_NAMES = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine"};
    private final static String[] ENGLISH_TEN_TO_TWENTY_NAMES = new String[]{"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
    private final static String[] ENGLISH_TENTHS_NAMES = new String[]{"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
    private final static String[] ENGLISH_UNITS_NAMES = new String[]{"","hundred","thousand","hundred","million","hundred"};//{"billion","hundred","million","thousand","hundred",""};

    // Decompose a number in blocks eg:
    // 123,456,789 => [1,23,4,56,7,89] => One hundred, twenty three million, four hundred fifty six thousand seven hundred eighty nine
    public static List<Integer> decompose(Integer number) {
        String sNumber = String.format("%09d",number);
        int[] blocks = new int[6];
        blocks[0] = Integer.parseInt(sNumber.substring(0,1));
        blocks[1] = Integer.parseInt(sNumber.substring(1,3));
        blocks[2] = Integer.parseInt(sNumber.substring(3,4));
        blocks[3] = Integer.parseInt(sNumber.substring(4,6));
        blocks[4] = Integer.parseInt(sNumber.substring(6,7));
        blocks[5] = Integer.parseInt(sNumber.substring(7,9));

        List<Integer> out = new ArrayList<Integer>();
        for(int block:blocks)
            out.add(block);
        return clean(out);
    }

    // Remove empty leading blocks
    public static List<Integer> clean(List<Integer> list) {
        List<Integer> out = new ArrayList<Integer>();
        if (list.size()==0) {
            out.add(0);
            return out;
        }
        // If we get here list.size > 0
        if (list.get(0)!=0)
            return list;
        else
            // process tail
            return clean(list.subList(1,list.size()));
    }

    // Convert 0-9 to text
    public static String singleToText(int number) {
        if (number>9 || number<0) throw new IllegalArgumentException("Number must be in range 0-9");
        return ENGLISH_NUMBER_NAMES[number];
    }

    // Convert 0-99 to text
    public static String doubleToText(int number) {
        if (number>99) throw new IllegalArgumentException("Number must be in less than 99");
        if (number<10) return singleToText(number);
        if (number<20) return ENGLISH_TEN_TO_TWENTY_NAMES[number-10];
        int tenth = number/10;
        if (number % 10 == 0)
            return ENGLISH_TENTHS_NAMES[tenth-2];
        else
            return ENGLISH_TENTHS_NAMES[tenth-2] + " " + singleToText(number % 10);
    }

    // Convert 0-999,999,999 to text
    public static String multipleToText(int number) {
        if (number==0) return singleToText(0);
        List<Integer> blocks = decompose(number);
        String out = "";
        // Process all blocks except the last
        for (int n=0;n<blocks.size()-1;n++) {
            if (blocks.get(n)!=0) out+=blockToNumber(n,blocks) + " ";

        }

        if (blocks.get(blocks.size()-1)==0) return out.trim(); // No more processing
        // Process last block (to insert "and" if applicable
        if (blocks.size()>=2)
            out+= "and " + blockToNumber(blocks.size()-1,blocks);
        else
            out+= blockToNumber(blocks.size()-1,blocks);

        return out.trim();
    }

    private static String blockToNumber(int pos,List<Integer> blocks) {
        // Add "and" to hundred thousands
        if ((blocks.size()-pos-1)==3)
            return doubleToText(blocks.get(pos)) + " " + ENGLISH_UNITS_NAMES[blocks.size()-pos-1] + " and";
        else
            return doubleToText(blocks.get(pos)) + " " + ENGLISH_UNITS_NAMES[blocks.size()-pos-1];
    }
}
