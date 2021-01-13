/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.common;



import java.text.Normalizer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public final class StringUtil {

    /*
     * Pattern to remove all invalid characters CHAR(9) - Horizontal Tab \t
     * CHAR(10) - New line feed \n CHAR(11) - Vertical Tab \u240B CHAR(13) -
     * Carriage return \r CHAR(16) - Data link escape \u2410 CHAR(160) -
     * horizontal bar \u2015 or \u00A0
     *
     * More @ http://www.fileformat.info/info/unicode/char/search.htm
     */
    private static final String VALIDATION_PATTERN = "[\\t\\n\\r\\u2410\\u2015\\u240B\\u00A0]";

    private StringUtil() {
    }

    public static String cleanupInputData(String str) {
        //Remove more than one white space
        str = str.replaceAll("\\s+", " ");
        //Commented due to compatibility with upload routine
        str = str.replaceAll(VALIDATION_PATTERN, "");
        return str.trim();
    }
    
    public static String unaccent(String src) {
		return Normalizer
				.normalize(src, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
	}

    /**
     * Convenience method to return a Collection as a CSV String.
     *
     * @param collection
     * @return
     */
    public static String collectionToCommaDelimitedString(Collection collection) {
        StringBuilder sb = new StringBuilder();
        String delim = "";
        for (Object obj : collection) {
            sb.append(delim).append(obj);
            delim = ",";
        }
        return sb.toString();
    }

    /**
     * Checks if a String is whitespace, empty ("") or null.
     *
     * @param value
     * @return
     */
    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Checks if a String is not empty (""), not null and not whitespace only.
     *
     * @param value
     * @return
     */
    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    /**
     * Return a string with leading and trailing whitespace removed, or null if
     * the supplied string was null or contained only white spaces.
     *
     * @param value
     * @return
     */
    public static String getTrimmedValueOrNull(String value) {
        if (value != null && !value.trim().isEmpty()) {
            return escapeSql(value.trim());
        }
        return null;
    }

    /*
     * Return a string with escaped character '[', preventing wrong sql querys
     * @param value
     */
    public static String escapeSql(String value) {
        return value != null ? value.replaceAll("\\[", "[[]") : null;
    }

    /**
     * Return a string in LIKE format, escaped and in lower case
     * @param value
     * @return 
     */
    public static String toLowerLikeSQL(String value) {
        return value != null ? "%" + escapeSql(value.toLowerCase()) + "%" : null;
    }

    /**
     * Method used to get limited Strings avoiding NPE
     *
     * @param content
     * @param limit
     * @return
     */
    public static String returnLimitedString(String content, int limit) {
        if (content != null && content.trim().length() > limit) {
            return content.substring(0, limit);
        }
        return content != null ? content.trim() : content;
    }

    /**
     * Returns the String without characters. Most used to get numbers only.
     *
     * @param value
     * @return
     */
    public static String removeChars(String value) {
        return value.replaceAll("\\D+", "");
    }

    /**
     * Get a string contain a list of integers separated by commas transform in
     * a proper list.
     *
     * @param input
     * @return
     */
    public static List<Integer> getListOfIntegerSeparatedByComma(String input) {
        List<Integer> list = new LinkedList<>();
        for (String value : input.split(",")) {
            list.add(Integer.valueOf(value));
        }
        return list;
    }

    /**
     * Given a list of values separated by comma check if the value is a item of
     * the list.
     *
     * @param list
     * @param value
     * @return
     */
    public static boolean isValueOnTheList(String list, String value) {
        for (String item : list.split(",")) {
            if (item.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsIgnoreCase(String firstValue, String sencondValue) {
        firstValue = firstValue.trim().toLowerCase();
        sencondValue = sencondValue.trim().toLowerCase();

        return firstValue.contains(sencondValue);
    }
}