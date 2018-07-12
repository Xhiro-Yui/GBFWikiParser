package com.github.xhiroyui.gbfwikiparser;

import org.apache.commons.text.StringEscapeUtils;

import java.util.regex.Matcher;

public class GBFWikiTextParser {

    /**
     * Inner method to remove HTML entities from input
     * @param input Input string
     * @return Input string without html entities
     */
    private static String removeHtml(String input) {
//        System.out.println("Output before modification : " + StringEscapeUtils.unescapeHtml4(input));
        return StringEscapeUtils.unescapeHtml4(input).replaceAll(Constants.HTML_BR_AND_HR_REGEX,". " ).replaceAll(Constants.HTML_TEXT_REGEX, "").replaceAll(Constants.EXTRA_DOTS_REGEX, ". ");
    }

    /**
     * <b>[WIP]</b> <br> Basic method for converting WikiText into a readable string method.
     * @param input Input string
     * @return Parsed input string
     */
    public static String getAsRawText(String input) {
        String output = removeHtml(input);
        Matcher matcher = Constants.WIKI_TEXT_PATTERN.matcher(output);
        while(matcher.find()) {
            String[] temp = matcher.group(1).split("\\|");
            if (temp.length == 1) {
                output = output.replace(matcher.group(0), temp[0]);
            }
        }
        return output;



    }

}
