package com.github.xhiroyui.gbfwikiparser;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class Constants {
    public static final String GBF_WIKI_IMAGE_LINK_SUFFIX = "https://gbf.wiki/images/";
    public static final String GBF_WIKI_LINK_SUFFIX = "https://gbf.wiki/api.php?action=cargoquery&format=json&order_by=name+ASC";

    public static final String ERR_MSG_NO_RESULTS = "No results found.";
    public static final String ERR_MSG_IO_EXCEPTION = "IO Exception. Link is probably malformed?";

    public static final Pattern WIKI_TEXT_PATTERN = compile("\\[\\[([\\w\\d\\s:.|=]*)]]", Pattern.MULTILINE);
    public static final Pattern HTML_TEXT_PATTERN = compile("(<[\\w-=_\\s/:;\"]*>)", Pattern.MULTILINE);
    public static final String HTML_TEXT_REGEX = "(\\<[\\w-=_\\s\\/:;\"]*\\>)";
    public static final String HTML_BR_AND_HR_REGEX = "(<[bh]r \\/>)";
    public static final String EXTRA_DOTS_REGEX = "(\\.[\\.\\s]*)";


}
