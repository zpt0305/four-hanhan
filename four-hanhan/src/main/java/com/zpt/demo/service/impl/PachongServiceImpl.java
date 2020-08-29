package com.zpt.demo.service.impl;

import com.zpt.demo.mapper.PachongMapper;
import com.zpt.demo.model.LolHero;
import com.zpt.demo.service.PachongService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PachongServiceImpl implements PachongService {

    @Autowired
    PachongMapper pachongMapper;

    public void pc(String url){

        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Chorme").get();//模拟火狐浏览器
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements ul = doc.getElementsByClass("games_list");
        Elements li = ul.select("li");//查找table标签
        List<LolHero> list = new ArrayList<>();
        for (Element tb : li) {
            try {
                Thread.sleep(0);//让线程操作不要太快 1秒一次 时间自己设置，主要是模拟人在点击
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LolHero lolHero = new LolHero();
            Elements a = tb.select("a");
            String name = a.attr("title");
            String image = tb.select("img").attr("src");
            //System.out.println(name + image);
            lolHero.setL_image(image);
            lolHero.setL_name(name);
            list.add(lolHero);
        }
        pachongMapper.insert(list);
    }
}
