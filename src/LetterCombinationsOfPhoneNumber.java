import java.util.*;

public class LetterCombinationsOfPhoneNumber {
    /**
     * MEDIUM
     * 17. Letter Combinations of a Phone Number
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
     * Return the answer in any order.
     * A mapping of digit to letters (just like on the telephone buttons) is given below.
     * Note that 1 does not map to any letters.
     *
     * Example 1:
     * Input: digits = "23"
     * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     *
     * Example 2:
     * Input: digits = ""
     * Output: []
     *
     * Example 3:
     * Input: digits = "2"
     * Output: ["a","b","c"]
     *
     * Constraints:
     * 0 <= digits.length <= 4
     * digits[i] is a digit in the range ['2', '9'].
     * */
    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        map.put("1", Arrays.asList(""));
        map.put("2", Arrays.asList("a","b","c"));
        map.put("3", Arrays.asList("d","e","f"));
        map.put("4", Arrays.asList("g","h","i"));
        map.put("5", Arrays.asList("j","k","l"));
        map.put("6", Arrays.asList("m","n","o"));
        map.put("7", Arrays.asList("p","q","r", "s"));
        map.put("8", Arrays.asList("t","u","v"));
        map.put("9", Arrays.asList("w","x","y", "z"));

        for (int i = 1; i < digits.length(); i++) {
            map.put(digits.substring(0,i+1), getCombinationList(map.get(digits.substring(0,i)), map.get(digits.substring(i,i+1))));
        }

        return map.get(digits);
    }

    public List<String> getCombinationList(List<String> list1, List<String> list2) {
        List<String> resultList = new ArrayList<>();

        for (String str1 : list1) {
            for (String str2 : list2) {
                resultList.add(str1+str2);
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber solution = new LetterCombinationsOfPhoneNumber();
        List<String> result = solution.letterCombinations("234");
        result.forEach(System.out::println);
    }
}
