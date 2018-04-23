package com.showtime.jkgl.util;

import com.showtime.jkgl.JkglApplication;
import com.showtime.jkgl.mapper.DietStorageMapper;
import com.showtime.jkgl.model.entity.DietStorage;
import com.showtime.jkgl.model.entity.EnergyMeasurement;
import com.showtime.jkgl.service.DietStorageService;
import com.showtime.jkgl.service.EnergyMeasurementService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JkglApplication.class)
@Slf4j
public class GetDietByNet {

    @Autowired
    private DietStorageService dietStorageService;

    @Autowired
    private EnergyMeasurementService energyMeasurementService;

    @Autowired
    private DietStorageMapper dietStorageMapper;

    @Test
    public void getDietByNet() throws Exception {

        //从网上获取数据
        Map<String, ArrayList<String>> map = new HashMap<>();
        String title = null;

        for(int i=1; i<=40; ++i){
            ArrayList<String> list = new ArrayList<>();
            String url = "http://www.boohee.com/food/group/" + i;
            Document document = Jsoup.connect(url).timeout(10000).get();
            Elements tElement = document.select(".g" + i);
            title = tElement.text();

            for(int page=1; page<=10; ++page){
                String urlWithPage = url + "?page=" + page;
                Document d = Jsoup.connect(urlWithPage).timeout(10000).get();
                Elements body = d.select(".food-list").select(".text-box").select("a");
                for(String href:body.eachAttr("href"))
                    list.add(href);
            }
            map.put(title, list);
        }

        ArrayList<DietStorage> ds = new ArrayList<>();
        ArrayList<EnergyMeasurement> es = new ArrayList<>();

        for(Map.Entry<String, ArrayList<String>> entry:map.entrySet()){
            for(String href:entry.getValue()){
                DietStorage d = new DietStorage();
                String url = "http://www.boohee.com/" + href;
                Document document = Jsoup.connect(url).timeout(10000).get();

                log.info((href.split("/"))[2]);
                d.setSpell((href.split("/"))[2]);
                try {
                    d.setName((document.select(".crumb").text())
                            .split(" / ")[2]);
                }catch (Exception e){
                    continue;
                }

                Elements elements = document.select(".basic-infor").select("li");
                for(String str:elements.eachText()){
                    if(str.contains("别名：")){
                        d.setNickName(str.substring(3));
                    }
                    if(str.contains("热量：")){
                        d.setEnergy(str.substring(3));
                    }
                    if(str.contains("分类：")){
                        d.setClassification(str.substring(3));
                    }
                }

                List<String> st = document.select("span[class=dt]").eachText();
                List<String> sc = document.select("span[class=dd]").eachText();
                for(int i=0; i<st.size(); ++i){
                    if("一".equals(sc.get(i))) continue;
                    switch (st.get(i)) {
                        case "营养素":
                            d.setContentType(sc.get(i).substring(2));
                            break;

                        case "碳水化合物(克)":
                            d.setCarbohydrate(new BigDecimal(sc.get(i)));
                            break;

                        case "脂肪(克)":
                            d.setFat(new BigDecimal(sc.get(i)));
                            break;

                        case "蛋白质(克)":
                            d.setProtein(new BigDecimal(sc.get(i)));
                            break;

                        case "纤维素(克)":
                            d.setCellulose(new BigDecimal(sc.get(i)));
                            break;

                        case "维生素A(微克)":
                            d.setVitaminA(new BigDecimal(sc.get(i)));
                            break;

                        case "维生素C(毫克)":
                            d.setVitaminC(new BigDecimal(sc.get(i)));
                            break;

                        case "维生素E(毫克)":
                            d.setVitaminE(new BigDecimal(sc.get(i)));
                            break;

                        case "胡萝卜素(微克)":
                            d.setRenieratene(new BigDecimal(sc.get(i)));
                            break;

                        case "硫胺素(毫克)":
                            d.setThiamine(new BigDecimal(sc.get(i)));
                            break;

                        case "核黄素(毫克)":
                            d.setRiboflavin(new BigDecimal(sc.get(i)));
                            break;

                        case "烟酸(毫克)":
                            d.setNiacin(new BigDecimal(sc.get(i)));
                            break;

                        case "胆固醇(毫克)":
                            d.setCholesterol(new BigDecimal(sc.get(i)));
                            break;

                        case "镁(毫克)" :
                            d.setMagnesium(new BigDecimal(sc.get(i)));
                            break;

                        case "钙(毫克)":
                            d.setCalcium(new BigDecimal(sc.get(i)));
                            break;

                        case "铁(毫克)":
                            d.setIron(new BigDecimal(sc.get(i)));
                            break;

                        case "锌(毫克)":
                            d.setZinc(new BigDecimal(sc.get(i)));
                            break;

                        case "铜(毫克)":
                            d.setCopper(new BigDecimal(sc.get(i)));
                            break;

                        case "锰(毫克)":
                            d.setManganese(new BigDecimal(sc.get(i)));
                            break;

                        case "钾(毫克)":
                            d.setPotassium(new BigDecimal(sc.get(i)));
                            break;

                        case "磷(毫克)":
                            d.setPhosphorus(new BigDecimal(sc.get(i)));
                            break;

                        case "钠(毫克)":
                            d.setSodium(new BigDecimal(sc.get(i)));
                            break;

                        case "硒(微克)":
                            d.setSelenium(new BigDecimal(sc.get(i)));
                            break;

                        default:
                            break;
                    }
                }

                try {
                    dietStorageMapper.insertSelective(d);
                }catch (Exception e){
                    continue;
                }

                List<String> energys = document.select(".widget-unit").select("tbody").select("td").eachText();
                EnergyMeasurement e = new EnergyMeasurement();
                e.setFoodStorageId(d.getId());
                for(int i=0; i<energys.size(); ++i){
                    if(i % 2 == 0){
                        e.setMeasurement(energys.get(i));
                    }else{
                        e.setEnergy(energys.get(i));
                        es.add(new EnergyMeasurement(e));
                    }
                }
            }
        }


        //存入数据库
        energyMeasurementService.InsertBatch(es);
    }
}
