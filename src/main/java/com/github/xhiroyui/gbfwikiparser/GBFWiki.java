package com.github.xhiroyui.gbfwikiparser;

import com.github.xhiroyui.gbfwikiparser.beans.*;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class GBFWiki {
    private static Logger log = LoggerFactory.getLogger(GBFWiki.class);
    private WikiObjectType objectType;
    private String query;
    private int queryLimit = 1;

    public GBFWiki() {
    }

    public static IQuery wikiObjectType(WikiObjectType objectType) {
        return new GBFWiki.Builder(objectType);
    }

    public interface IQuery {
        IParse query(String query);
    }

    public interface IParse {
        IParseList queryLimit(int queryLimit);

        GBFWikiObject parse();
    }

    public interface IParseList {
        List<GBFWikiObject> parseAsList();
    }


    private static class Builder implements IQuery, IParse, IParseList {
        private final GBFWiki results = new GBFWiki();

        public Builder(WikiObjectType objectType) {
            results.objectType = objectType;
        }


        @Override
        public IParse query(String query) {
            results.query = query;
            return this;
        }

        @Override
        public GBFWikiObject parse() {
            return processData().get(0);
        }


        @Override
        public IParseList queryLimit(int queryLimit) {
            results.queryLimit = queryLimit;
            return this;
        }

        @Override
        public List<GBFWikiObject> parseAsList() {
            return processData();
        }

        /*
        Processes data with the given parameters
         */
        public List<GBFWikiObject> processData() {
            List<GBFWikiObject> data = new ArrayList<>();
            if (results.objectType == WikiObjectType.WEAPON) {
                try (InputStreamReader reader = new InputStreamReader(new URL(MessageFormat.format("{0}&tables=weapons&fields=name,title,grp=group,rarity,element,type,jpname=jpName,jptitle=jpTitle,release_date=releaseDate,obtain,obtain_text=obtainText,parent,img_full=imgFull,img_icon=imgIcon,img_square=imgSquare,img_tall=imgTall,evo_min=evoMin,evo_base=evoBase,evo_max=evoMax,evo_red=evoRed,atk1,atk2,atk3,atk4,hp1,hp2,hp3,hp4,ca1_name=ougi1Name,ca1_desc=ougi1Desc,ca2_name=ougi2Name,ca2_desc=ougi2Desc,s1_icon=skill1Icon,s1_name=skill1Name,s1_lvl=skill1Level,s1_desc=skill1Desc,s1u1_icon=skill1Upgrade1Icon,s1u1_name=skill1Upgrade1Name,s1u1_lvl=skill1Upgrade1Level,s1u1_desc=skill1Upgrade1Desc,s2_icon=skill2Icon,s2_name=skill2Name,s2_lvl=skill2Level,s2_desc=skill2Desc,s2u1_icon=skill2Upgrade1Icon,s2u1_name=skill2Upgrade1Name,s2u1_lvl=skill2Upgrade1Level,s2u1_desc=skill2Upgrade1Desc,bullets,bullet1,bullet2,bullet3,bullet4,bullet5,bullet6,bullet7,bullet8,bullet9&limit={1}&where=name%20LIKE%20%22%{2}%%22", Constants.GBF_WIKI_LINK_SUFFIX, results.queryLimit, results.query)).openStream())) {
                    for (JsonElement each : new JsonParser().parse(reader).getAsJsonObject().get("cargoquery").getAsJsonArray())
                        data.add(new Gson().fromJson(each.getAsJsonObject().get("title").getAsJsonObject(), GBFWeapon.class));
                } catch (IndexOutOfBoundsException e) {
                    log.info(Constants.ERR_MSG_NO_RESULTS);
                } catch (IOException e) {
                    log.info(Constants.ERR_MSG_IO_EXCEPTION);
                    e.printStackTrace();
                }
            } else if (results.objectType == WikiObjectType.CLASS_SKILL) {
                try (InputStreamReader reader = new InputStreamReader(new URL(MessageFormat.format("{0}&tables=class_skill&fields=ix,family,row,class=job,icon,name,description,cooldown,duration,emp_cost=empCost,notes,ex&limit={1}&where=name%20LIKE%20%22%{2}%%22%20OR%20class%20LIKE%20%22%{2}%%22%20OR%20description%20LIKE%20%22%{2}%%22", Constants.GBF_WIKI_LINK_SUFFIX, results.queryLimit, results.query)).openStream())) {
                    for (JsonElement each : new JsonParser().parse(reader).getAsJsonObject().get("cargoquery").getAsJsonArray())
                        data.add(new Gson().fromJson(each.getAsJsonObject().get("title").getAsJsonObject(), GBFClassSkill.class));
                } catch (IndexOutOfBoundsException e) {
                    log.info(Constants.ERR_MSG_NO_RESULTS);
                } catch (IOException e) {
                    log.info(Constants.ERR_MSG_IO_EXCEPTION);
                    e.printStackTrace();
                }
            } else if (results.objectType == WikiObjectType.EVENT) {
                try (InputStreamReader reader = new InputStreamReader(new URL(MessageFormat.format("{0}&tables=event_history&fields=name,grp,enname=enName,jpname=jpName,wiki_page=wikiPage,external_url=externalUrl,time_known=timeKnown,time_start=timeStart,time_end=timeEnd,utc_start=utcStart,utc_end=utcEnd,jst_start=jstStart,jst_end=jstEnd,jpwiki_url=jpwikiUrl,jpwiki_name=jpwikiName,image,enimage=enImage,enimage_soon=enImageSoon,jpimage=jpImage,jpimage_soon=jpImageSoon,unf_element=unfElement&limit={1}&where=name%20LIKE%20%22%{2}%%22", Constants.GBF_WIKI_LINK_SUFFIX, results.queryLimit, results.query)).openStream())) {
                    for (JsonElement each : new JsonParser().parse(reader).getAsJsonObject().get("cargoquery").getAsJsonArray())
                        data.add(new Gson().fromJson(each.getAsJsonObject().get("title").getAsJsonObject(), GBFEvent.class));
                } catch (IndexOutOfBoundsException e) {
                    log.info(Constants.ERR_MSG_NO_RESULTS);
                } catch (IOException e) {
                    log.info(Constants.ERR_MSG_IO_EXCEPTION);
                    e.printStackTrace();
                }
            } else if (results.objectType == WikiObjectType.SM_SKILL) {
                try (InputStreamReader reader = new InputStreamReader(new URL(MessageFormat.format("{0}&tables=weapon_sm_skills&fields=name,rarity,rarity_text=rarityText,element,element_text=elementText,type,type_text=typeText,slot,desc_text=descriptionText,cd,cd_text=cdText,dur=duration,dur_text=durationText,cost,cost_text=costText&limit={1}&where=name%20LIKE%20%22%{2}%%22%20OR%20desc_text%20LIKE%20%22%{2}%%22", Constants.GBF_WIKI_LINK_SUFFIX, results.queryLimit, results.query)).openStream())) {
                    for (JsonElement each : new JsonParser().parse(reader).getAsJsonObject().get("cargoquery").getAsJsonArray())
                        data.add(new Gson().fromJson(each.getAsJsonObject().get("title").getAsJsonObject(), GBFSmSkill.class));
                } catch (IndexOutOfBoundsException e) {
                    log.info(Constants.ERR_MSG_NO_RESULTS);
                } catch (IOException e) {
                    log.info(Constants.ERR_MSG_IO_EXCEPTION);
                    e.printStackTrace();
                }
            }

//        else if (objectType == WikiObjectType.SUMMON)
//            return null;
//        else if (objectType == WikiObjectType.CHARACTER)
//            return null;
            return data;


        }
    }


}
