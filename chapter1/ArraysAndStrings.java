import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;
/**
 * Created by nkhatore on 5/19/18.
 */
public class ArraysAndStrings {

    /** O(n) using HashSet **/
    private static Boolean isUnique(String s) {
        HashSet<Character> h = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (h.contains(c)) {
                return false;
            } else {
                h.add(c);
            }
        }
        return true;
    }

    /** O(n) using HashMap **/
    private static Boolean checkPermutation(String a, String b) {
        HashMap<Character, Integer> hb = new HashMap<>();
        HashMap<Character, Integer> ha = new HashMap<>();
        if (a.length() != b.length()) {
            return false;
        }
        for (char c : b.toCharArray()) {
            if (hb.containsKey(c)) {
                hb.replace(c, hb.get(c) + 1);
            } else {
                hb.put(c, 1);
            }
        }
        for (char c : a.toCharArray()) {
            if (ha.containsKey(c)) {
                ha.replace(c, ha.get(c) + 1);
            } else {
                ha.put(c, 1);
            }
        }
        for (char c : a.toCharArray()) {
            if (ha.get(c) != hb.get(c)) {
                return false;
            }
        }
        return true;
    }

    /** O(n) **/
    private static String URLify(String s, int trueLength) {
        String url = "";
        char[] arr = s.toCharArray();
        for (int i = 0; i < trueLength; i++) {
            if (arr[i] == ' ') {
                url += "%20";
            } else {
                url += arr[i];
            }
        }
        return url;
    }

    /** O(n) using HashMap **/
    private static Boolean palinPermutation(String s) {
        HashMap<Character, Integer> h = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (h.containsKey(c)) {
                h.replace(c, h.get(c) + 1);
            } else if (c != ' ') {
                h.put(c, 1);
            }
        }
        int midCount = 0;
        for (char c : s.toCharArray()) {
            if (c != ' ' && h.get(c) % 2 == 1) {
                midCount++;
            }
            if (midCount > 1) {
                return false;
            }
        }
        return true;
    }

    /** O(n) using HashMap **/
    private static Boolean oneAway(String a, String b) {
        if (Math.abs(a.length() - b.length()) > 1) {
            return false;
        }
        HashMap<Character, Integer> hb = new HashMap<>();
        HashMap<Character, Integer> ha = new HashMap<>();
        for (char c : b.toCharArray()) {
            if (hb.containsKey(c)) {
                hb.replace(c, hb.get(c) + 1);
            } else {
                hb.put(c, 1);
            }
        }
        for (char c : a.toCharArray()) {
            if (ha.containsKey(c)) {
                ha.replace(c, ha.get(c) + 1);
            } else {
                ha.put(c, 1);
            }
        }
        int edits = 0;
        for (char c : b.toCharArray()) {
            if (ha.containsKey(c)) {
                edits += Math.abs(hb.get(c) - ha.get(c));
            } else {
                edits += hb.get(c);
            }
        }
        return (edits <= 1);
    }

    /** O(n) **/
    private static String stringCompress(String s) {
        int count = 1;
        String compressed = "";
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1 || arr[i] != arr[i + 1]) {
                compressed += arr[i] + Integer.toString(count);
                count = 1;
            } else {
                count++;
            }
        }
        if (compressed.length() > s.length()) return s;
        return compressed;
    }

    /** O(n^2) in place using HashMap **/
    private static int[][] rotateMatrix(int[][] matrix) {
        HashMap<Integer, int[]> h = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            int[] copy = new int[3];
            System.arraycopy(matrix[i], 0, copy, 0, matrix[i].length);
            h.put(i, copy);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = h.get(matrix.length - j - 1)[i];
            }
        }
        return matrix;
    }

    /** O(mn) **/
    private static int[][] zeroMatrix(int[][] matrix) {
        boolean[] zeroRows = new boolean[matrix.length];
        boolean[] zeroCols = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows[i] = true;
                    zeroCols[j] = true;
                }
            }
        }
        for (int i = 0; i < zeroRows.length; i++) {
            if (zeroRows[i]) {
                matrix[i] = new int[matrix.length];
            }
        }
        for (int j = 0; j < zeroCols.length; j++) {
            if (zeroCols[j]) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    /** O(n) where isSubstring() is .contains() **/
    private static Boolean stringRotation(String s1, String s2) {
        String checkString = s2 + s2;
        return (checkString.length() == 2 * s1.length() && checkString.contains(s1));
    }

    public static void main(String args[]) {
        System.out.println(isUnique("abcdef"));
        System.out.println(isUnique("nilay khatore"));
        System.out.println();
        System.out.println(checkPermutation("tacocat", "atcotac"));
        System.out.println(checkPermutation("tacocat", "atcotcc"));
        System.out.println();
        System.out.println(URLify("Mr John Smith    ", 13));
        System.out.println();
        System.out.println(palinPermutation("actotac"));
        System.out.println(palinPermutation("abcba d"));
        System.out.println();
        System.out.println(oneAway("pale", "ple"));
        System.out.println(oneAway("pale", "pales"));
        System.out.println(oneAway("pale", "bale"));
        System.out.println(oneAway("pale", "pa"));
        System.out.println(oneAway("pale", "bake"));
        System.out.println();
        System.out.println(stringCompress("aabcccccaaa"));
        System.out.println(stringCompress("nilay khatore"));
        System.out.println();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.deepToString(matrix));
        System.out.println(Arrays.deepToString(rotateMatrix(matrix)));
        System.out.println();
        int[][] testMatrix = {{1, 2, 1}, {2, 0, 2}, {1, 2, 1}};
        System.out.println(Arrays.deepToString(testMatrix));
        System.out.println(Arrays.deepToString(zeroMatrix(testMatrix)));
        System.out.println();
        System.out.println(stringRotation("erbottlewat", "waterbottle"));
    }
}
