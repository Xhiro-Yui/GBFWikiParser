package com.github.xhiroyui.gbfwikiparser;

import com.github.xhiroyui.gbfwikiparser.beans.GBFWeapon;
import com.github.xhiroyui.gbfwikiparser.beans.GBFWikiObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Parser {
    private final WikiObjectType type;
    private final URL url;

    /**
     * A GBFWiki Parser object. Contains a type indicator and a URL
     *
     * @param type Type of object to parse (use the enum)
     * @param query named object query
     * @throws MalformedURLException When link malformed itself
     */
    public Parser(WikiObjectType type, String query) throws MalformedURLException {
        this.type = type;
        switch (this.type) {
            case WEAPON: {
                url = new URL(String.format("https://gbf.wiki/api.php?action=cargoquery&format=json&tables=weapons&fields=name,title,grp=group,rarity,element,type,jpname=jpName,jptitle=jpTitle,release_date=releaseDate,obtain,obtain_text=obtainText,parent,img_full=imgFull,img_icon=imgIcon,img_square=imgSquare,img_tall=imgTall,evo_min=evoMin,evo_base=evoBase,evo_max=evoMax,evo_red=evoRed,atk1,atk2,atk3,atk4,hp1,hp2,hp3,hp4,ca1_name=ougi1Name,ca1_desc=ougi1Desc,ca2_name=ougi2Name,ca2_desc=ougi2Desc,s1_icon=skill1Icon,s1_name=skill1Name,s1_lvl=skill1Level,s1_desc=skill1Desc,s1u1_icon=skill1Upgrade1Icon,s1u1_name=skill1Upgrade1Name,s1u1_lvl=skill1Upgrade1Level,s1u1_desc=skill1Upgrade1Desc,s2_icon=skill2Icon,s2_name=skill2Name,s2_lvl=skill2Level,s2_desc=skill2Desc,s2u1_icon=skill2Upgrade1Icon,s2u1_name=skill2Upgrade1Name,s2u1_lvl=skill2Upgrade1Level,s2u1_desc=skill2Upgrade1Desc,bullets,bullet1,bullet2,bullet3,bullet4,bullet5,bullet6,bullet7,bullet8,bullet9&limit=500&order_by=name+ASC&where=name=%%22%s%%22",query));
                break;
            }
            case SUMMON:
                url = new URL("https://gbf.wiki");
                break;
            case EVENT:
                url = new URL("https://gbf.wiki");
                break;
            default:
                url = null;
                break;

        }
    }

    /**
     * Set of objects the parser is able to parse. One of these should be used as the first argument of the constructor when creating a parser object.
     */

    public GBFWikiObject parse() throws IOException {
        GBFWikiObject wikiObject = new GBFWikiObject();
        switch (this.type) {
            case WEAPON:
                parseWeapon(wikiObject);
        }
        return wikiObject;
    }

    /**
     * Parses the image returned by the parser into a full link.
     *
     * @param imageUrlInput input link
     * @return full link path
     */
    public String parseImageLink(String imageUrlInput) {
        return imageUrlInput;
    }

    private void parseWeapon(GBFWikiObject wikiObject) throws IOException {
        GBFWeapon weapon;

        InputStreamReader reader = new InputStreamReader(this.url.openStream());
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonContent = jsonParser.parse(reader).getAsJsonObject().get("cargoquery").getAsJsonArray().get(0).getAsJsonObject().get("title").getAsJsonObject();
        Gson gson = new Gson();

        weapon = gson.fromJson(jsonContent, GBFWeapon.class);

        wikiObject.setGbfWeapon(weapon);
    }
}
