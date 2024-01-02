package com.group10.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group10.backend.entity.Author;
import com.group10.backend.entity.AuthorRelationship;
import com.group10.backend.entity.Book;
import com.group10.backend.entity.TimeLine;
import com.group10.backend.entity.WordCloud;
import com.group10.backend.service.AuthorRelationshipService;
import com.group10.backend.service.AuthorService;
import com.group10.backend.service.BookService;
import com.group10.backend.service.TimeLineService;
import com.group10.backend.service.WordCloudService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthorDisplayController {
    @Resource
    private AuthorRelationshipService authorRelationshipService;
    @Resource
    private BookService bookService;
    @Resource
    private AuthorService authorService;
    @Resource
    private TimeLineService timeLineService;
    @Resource
    private WordCloudService wordCloudService;

    @GetMapping("/writerDetail")
    public Map<String, Object> getAuthorDisplayInfo(@RequestParam("id") int authorId) {
        Map<String, Object> response = new HashMap<>();

        Author author = authorService.getById(authorId);
        if (author == null) {
            response.put("error", "Author not found");
            return response;
        }

        response.put("name", author.getName());
        response.put("age", author.getAge());
        response.put("gender", author.getGender());
        response.put("date", author.getBirthDate() + " - " + author.getDeathDate());
        response.put("Writtingstyle", author.getWritingStyle());
        response.put("provence", author.getProvence());
        response.put("pseudonym", author.getPseudonym());
        response.put("Representative", author.getRepresentative());
        response.put("BriefIntroduction", author.getBriefIntroduction());
        response.put("honors", author.getHonors());
        response.put("imgUrl", author.getImgUrl());

        return response;
    }

    @GetMapping("/getWordCloud")
    public List<Map<String, Object>> getWordCloud(@RequestParam("id") int authorId) {
        QueryWrapper<WordCloud> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "value");
        queryWrapper.eq("authorID", authorId);
        List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
        for (WordCloud t :wordCloudService.list(queryWrapper)) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("name", t.getName());
            tmp.put("value", t.getValue());
            result.add(tmp);
        }
        return result;
    }

    @GetMapping("/getTimeLine")
    public List<Map<String, Object>> getTimeLine(@RequestParam("id") int authorId) {
        QueryWrapper<TimeLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.eq("authorID", authorId);
        queryWrapper.orderByAsc("timeID");
        List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
        for (TimeLine t :timeLineService.list(queryWrapper)) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("children", t.getChildren());
            result.add(tmp);
        }

        return result;
    }
}
