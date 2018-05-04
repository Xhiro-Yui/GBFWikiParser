package com.github.xhiroyui.gbfwikiparser.beans;

public class GBFWikiObject {
    private GBFWeapon gbfWeapon;
    private GBFSummon gbfSummon;
    private GBFCharacter gbfCharacter;
    private GBFEvent gbfEvent;

    public GBFWikiObject(GBFWeapon weapon) {
        this.gbfWeapon = weapon;
    }

    public GBFWikiObject(GBFSummon summon) {
        this.gbfSummon = summon;
    }

    public GBFWikiObject(GBFEvent event) {
        this.gbfEvent = event;
    }

    public GBFWeapon getGbfWeapon() {
        return gbfWeapon;
    }
    public GBFCharacter getGbfCharacter() {
        return gbfCharacter;
    }
}
