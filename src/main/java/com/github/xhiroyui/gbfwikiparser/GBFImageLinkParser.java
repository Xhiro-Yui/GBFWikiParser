package com.github.xhiroyui.gbfwikiparser;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class GBFImageLinkParser {
//    private static Logger log = LoggerFactory.getLogger(GBFImageLinkParser.class);
    /**
     * Converts an image file name into a full link to the image on GBFWiki.
     *
     * @param input Filename inclusive with extension
     * @return A URL link to the image in String format
     */
    public static String convertImageToLink(String input) {
        if (input != null && input.length()>0) {
            StringBuilder output = new StringBuilder().append(Constants.gbfWikiImageLinkSuffix);
            String md5 = Hashing.md5().newHasher().putString(input, Charsets.UTF_8).hash().toString();
            output.append(md5, 0, 1).append("/")
                    .append(md5, 0, 2).append("/")
                    .append(input);
            return output.toString();
        }
        return input;
    }
}
