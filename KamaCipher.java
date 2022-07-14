import com.sun.jdi.Value;

public class KamaCipher {

    public static char[] setUpArray (String inputValue)  {
        char[] array = inputValue.toCharArray();
        return array;
    }

    public static void printKeys (char[] key1, char[] key2) {
        System.out.print("Key1: ");
        for (int i = 0; i < key1.length; i++) {
            System.out.print(key1[i]);
        }
        System.out.println();

        System.out.print("Key2: ");
        for (int i = 0; i < key2.length; i++) {
            System.out.print(key2[i]);
        }
        System.out.println();
    }

    public static void complianceCheck (char[] key1, char[] key2, char[] string) {

        if (key1.length != 13) {
            System.out.println("The length of the key must be 13. Your first key length = " + key1.length + ".");
            System.exit(1);
        } else if (key2.length != 13) {
            System.out.println("The length of the key must be 13. Your second key length = " + key2.length + ".");
            System.exit(1);
        }

        if (Character.isUpperCase(key1[0]) && Character.isUpperCase(key2[0]) && Character.isUpperCase(string[0])) {
            for (int i = 0; i < key1.length; i++) {
                if (!Character.isUpperCase(key1[i]) || !Character.isUpperCase(key2[i])) {
                    System.out.println("All letters most be in one case (Upper or Lower). Check keys.");
                    printKeys(key1,key2);
                    System.exit(1);
                }
            }
            for (int i = 0; i < string.length; i++) {
                if (!Character.isUpperCase(string[i])) {
                    System.out.println("All letters most be in one case (Upper or Lower). Check your string.");
                    System.out.println("String: " + String.valueOf(string));
                    System.exit(1);
                }
            }
        } else if (Character.isLowerCase(key1[0]) && Character.isLowerCase(key2[0]) && Character.isLowerCase(string[0])) {
            for (int i = 0; i < key1.length; i++) {
                if (!Character.isLowerCase(key1[i]) || !Character.isLowerCase(key2[i])) {
                    System.out.println("All letters most be in one case (Upper or Lower). Check keys.");
                    printKeys(key1,key2);
                    System.exit(1);
                }
            }
            for (int i = 0; i < string.length; i++) {
                if (!Character.isLowerCase(string[i])) {
                    System.out.println("All letters most be in one case (Upper or Lower). Check your string.");
                    System.out.println("String: " + String.valueOf(string));
                    System.exit(1);
                }
            }
        } else {
            System.out.println("All letters most be in one case (Upper or Lower). Check keys or your string.");
            printKeys(key1,key2);
            System.out.println("String: " + String.valueOf(string));
            System.exit(1);
        }

        for (int i = 0; i < key1.length; i++) {
            String letterFK1 = String.valueOf(key1[i]);
            String letterSK1 = String.valueOf(key2[i]);

            for (int j = i + 1; j < key1.length; j++) {
                String letterFK2 = String.valueOf(key1[j]);
                String letterSK2 = String.valueOf(key2[j]);

                if (letterFK1.equals(letterFK2)) {
                    System.out.println("The key cannot contain the same letters. Check first key. The same letter = " + letterFK1 + ".");
                    printKeys(key1,key2);
                    System.exit(1);
                } else if(letterSK1.equals(letterSK2)) {
                    System.out.println("The key cannot contain the same letters. Check second key. The same letter = " + letterSK1 + ".");
                    printKeys(key1,key2);
                    System.exit(1);
                } else if(letterFK1.equals(letterSK2)) {
                    System.out.println("The keys cannot contain the same letters in each other. Check both keys and in someone rewrite letter \"" + letterFK1 + "\".");
                    printKeys(key1,key2);
                    System.exit(1);
                }
            }
        }

    }

    public static String kamaCiphering (String inputString, String inputKey1, String inputKey2) {
        char[] key1 = setUpArray(inputKey1);
        char[] key2 = setUpArray(inputKey2);

        char[] string = setUpArray(inputString);

        complianceCheck(key1,key2,string);

        for (int i = 0; i < string.length; i++) {
            String letter = String.valueOf(string[i]);

            for (int j = 0; j < key1.length; j++) {
                String keyLet1 = String.valueOf(key1[j]);
                String keyLet2 = String.valueOf(key2[j]);

                if (letter.equals(keyLet1)) {
                    string[i] = key2[j];
                } else if(letter.equals(keyLet2)){
                    string[i] = key1[j];
                }
            }
        }

        String result = String.valueOf(string);

        return result;
    }


    public static void main(String[] args) {

        String inputKey1 = args[0];
        String inputKey2 = args[1];
        String inputWord = args[2];

        String result = kamaCiphering(inputWord,inputKey1,inputKey2);

        System.out.println(result);

        System.exit(0);
    }

}
// java KamaCipher THEQUICKBROWN FXJMPSVLAZYDG SOMEWORD
// java KamaCipher thequickbrown fxjmpsvlazydg someword