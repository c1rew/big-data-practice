/**
 * @author c1rew
 * @date 2020-12-22 13:39
 */

public class StringTest2 {

    public static String string(String... strings) {
        String string = null;
        for (String s : strings) {
            string += s;
        }
        return string;
    }

    public static void main(String[] args) {
        System.out.println(string("a"));
        System.out.println(string("a" + "b"));
        System.out.println(string("a" + "b" + "c"));

        System.out.println();

        // 空字符串
        String blankString = "";
        // isEmpty
        String isEmptyString = new String();
        // null
        String nullString = null;

        System.out.println("blankString: " + blankString);
        System.out.println("isEmptyString: " + isEmptyString);
        System.out.println("nullString: " + nullString);

        System.out.println();

        System.out.println("blankString.getBytes().length: " + blankString.getBytes().length);
        System.out.println("isEmptyString.getBytes().length: " + isEmptyString.getBytes().length);
        //System.out.println("nullString.getBytes().length: " + nullString.getBytes().length);

        System.out.println();

        System.out.println("blankString.isEmpty: " + blankString.isEmpty());
        System.out.println("isEmptyString.isEmpty: " + isEmptyString.isEmpty());
        //System.out.println("nullString.isEmpty: " + nullString.isEmpty());

        System.out.println();

        System.out.println("\"\".equals(blankString): " + "".equals(blankString));
        System.out.println("\"\".equals(isEmptyString): " + "".equals(isEmptyString));
        System.out.println("\"\".equals(nullString): " + "".equals(nullString));

        System.out.println();

        System.out.println("blankString==null: " + (blankString == null));
        System.out.println("isEmptyString==null: " + (isEmptyString == null));
        System.out.println("nullString==null: " + (nullString == null));

        System.out.println();

        System.out.println("blankString.equals(isEmptyString): " + (blankString.equals(isEmptyString)));
        System.out.println("blankString == isEmptyString: " + (blankString == isEmptyString));
    }
}
