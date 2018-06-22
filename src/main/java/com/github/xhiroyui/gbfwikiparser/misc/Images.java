package com.github.xhiroyui.gbfwikiparser.misc;

/**
 * A collection of images that can be found in GBFWikiObject but does not fit anywhere else. Mainly for convenient access to the image URLs.
 */
public enum Images {
    RARITY_SSR("https://gbf.wiki/images/f/fb/Label_Rarity_SSR.png"),
    RARITY_SR("https://gbf.wiki/images/0/01/Label_Rarity_SR.png"),
    RARITY_R("https://gbf.wiki/images/4/41/Label_Rarity_R.png");


    private final String url;

    private Images(String url) {
        this.url = url;
    }

    /**
     * Returns the URL of the image.
     * @return URL of image
     */
    public String getUrl() {
        return this.url;
    }
}
