package com.group10.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group10.backend.entity.News;
import com.group10.backend.service.NewsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class NewsController {
    @Resource
    private NewsService newsService;
    @GetMapping("/getNews")
    public List<News> getNews(){
        Random random = new Random();
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        int newsNum = (int) newsService.count(newsQueryWrapper);
        Collection<Integer> newsIdCollection = new ArrayList<>();
        int k = 0;
        while(k != 3)
        {
            int newsId = random.nextInt(newsNum) + 1;
            if(!newsIdCollection.contains(newsId))
            {
                k++;
                newsIdCollection.add(newsId);
            }
        }
        newsQueryWrapper.in("news_id", newsIdCollection);
        List<News> result = newsService.list(newsQueryWrapper);
        System.out.println(result);
        return result;
    }
}
