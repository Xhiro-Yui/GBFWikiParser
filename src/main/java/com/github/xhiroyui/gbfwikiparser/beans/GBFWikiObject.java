package com.github.xhiroyui.gbfwikiparser.beans;

public class GBFWikiObject {
    private GBFWeapon gbfWeapon;
    private GBFSummon gbfSummon;
    private GBFCharacter gbfCharacter;
    private GBFClassSkill gbfClassSkill;
    private GBFSmSkill gbfSmSkill;
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

    public GBFWikiObject(GBFClassSkill classSkill) {
        this.gbfClassSkill = classSkill;
    }

    public GBFWikiObject(GBFSmSkill smSkill) {
        this.gbfSmSkill = smSkill;
    }

    public GBFWikiObject(GBFCharacter character) {
        this.gbfCharacter = character;
    }

    public GBFWeapon getGbfWeapon() {
        return gbfWeapon;
    }
    public GBFCharacter getGbfCharacter() {
        return gbfCharacter;
    }

    public GBFSummon getGbfSummon() {
        return gbfSummon;
    }

    public GBFClassSkill getGbfClassSkill() {
        return gbfClassSkill;
    }

    public GBFSmSkill getGbfSmSkill() {
        return gbfSmSkill;
    }

    public GBFEvent getGbfEvent() {
        return gbfEvent;
    }
}
