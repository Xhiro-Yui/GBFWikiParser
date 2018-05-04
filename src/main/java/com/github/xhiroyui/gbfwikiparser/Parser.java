package com.github.xhiroyui.gbfwikiparser;

import com.github.xhiroyui.gbfwikiparser.beans.GBFEvent;
import com.github.xhiroyui.gbfwikiparser.beans.GBFSummon;
import com.github.xhiroyui.gbfwikiparser.beans.GBFWeapon;
import com.github.xhiroyui.gbfwikiparser.beans.GBFWikiObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Parser {
    private static Logger log = LoggerFactory.getLogger(Parser.class);
    public static class Builder {
        private WikiObjectType type;
        private String query;

        public Builder(String query, WikiObjectType type) {
            this.type = type;
            this.query = query;
        }

        public Parser build() {
            try {
                switch (this.type) {
                    case WEAPON:
                        return new Parser(new URL(String.format("https://gbf.wiki/api.php?action=cargoquery&format=json&tables=weapons&fields=name,title,grp=group,rarity,element,type,jpname=jpName,jptitle=jpTitle,release_date=releaseDate,obtain,obtain_text=obtainText,parent,img_full=imgFull,img_icon=imgIcon,img_square=imgSquare,img_tall=imgTall,evo_min=evoMin,evo_base=evoBase,evo_max=evoMax,evo_red=evoRed,atk1,atk2,atk3,atk4,hp1,hp2,hp3,hp4,ca1_name=ougi1Name,ca1_desc=ougi1Desc,ca2_name=ougi2Name,ca2_desc=ougi2Desc,s1_icon=skill1Icon,s1_name=skill1Name,s1_lvl=skill1Level,s1_desc=skill1Desc,s1u1_icon=skill1Upgrade1Icon,s1u1_name=skill1Upgrade1Name,s1u1_lvl=skill1Upgrade1Level,s1u1_desc=skill1Upgrade1Desc,s2_icon=skill2Icon,s2_name=skill2Name,s2_lvl=skill2Level,s2_desc=skill2Desc,s2u1_icon=skill2Upgrade1Icon,s2u1_name=skill2Upgrade1Name,s2u1_lvl=skill2Upgrade1Level,s2u1_desc=skill2Upgrade1Desc,bullets,bullet1,bullet2,bullet3,bullet4,bullet5,bullet6,bullet7,bullet8,bullet9&limit=500&order_by=name+ASC&where=name=%%22%s%%22",query)), this.type);
                    case SUMMON:
                        return null;
                    case EVENT:
                        return null;
                    default:
                        return null;
                }
            } catch (MalformedURLException e) {
                log.error("Creation of Parser object failed due to MalformedURLException. This problem is likely due to the library, please report this problem at https://github.com/Xhiro-Yui/GBFWikiParser/issues", e);
            }
            return null;
        }
    }

    private final URL url;
    private final WikiObjectType type;

    private Parser(URL url, WikiObjectType type) {
        this.url = url;
        this.type = type;

    }


    /**
     * Parses GBFWiki based on given arguments from builder.
     * If container isn't desired, individual calls to specific methods can be used (e.g. {@link Parser#parseWeapon()} or {@link Parser#parseSummon()}).
     *
     * @return A container {@link GBFWikiObject} which contains the specific object or null if no results were found.
     */
    public GBFWikiObject parse() {
        switch (this.type) {
            case WEAPON:
                return new GBFWikiObject(parseWeapon());
            case SUMMON:
                return new GBFWikiObject(parseSummon());
            case EVENT:
                return new GBFWikiObject(parseEvent());
        }
        return null;
    }

    public GBFWeapon parseWeapon() {
        try (InputStreamReader reader = new InputStreamReader(this.url.openStream())) {
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonContent = jsonParser.parse(reader).getAsJsonObject().get("cargoquery").getAsJsonArray().get(0).getAsJsonObject().get("title").getAsJsonObject();
            System.out.println("Parsing weapon pt2");
            return new Gson().fromJson(jsonContent, GBFWeapon.class);

        } catch (IndexOutOfBoundsException e) {
            log.info("Parser found no results. Returning null.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GBFSummon parseSummon() {
        return new GBFSummon();
    }

    public GBFEvent parseEvent() {
        return new GBFEvent();

    }

}
