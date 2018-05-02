package com.github.xhiroyui.gbfwikiparser.misc;

public enum Images {
    RARITY_SSR("https://gbf.wiki/images/f/fb/Label_Rarity_SSR.png"),
    RARITY_SR("https://gbf.wiki/images/0/01/Label_Rarity_SR.png"),
    RARITY_R("https://gbf.wiki/images/4/41/Label_Rarity_R.png");


    private final String url;

    private Images(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
