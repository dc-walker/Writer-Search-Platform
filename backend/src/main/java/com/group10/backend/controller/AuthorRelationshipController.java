package com.group10.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group10.backend.entity.Author;
import com.group10.backend.entity.AuthorRelationship;
import com.group10.backend.entity.Book;
import com.group10.backend.service.AuthorRelationshipService;
import com.group10.backend.service.AuthorService;
import com.group10.backend.service.BookService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("")
@CrossOrigin
public class AuthorRelationshipController {
    @Resource
    private AuthorRelationshipService authorRelationshipService;
    @Resource
    private BookService bookService;
    @Resource
    private AuthorService authorService;

    @GetMapping("/getRelation")
    public Map<String, Object> getAuthorRelationship(@RequestParam("id") int authorId) {
        Map<String, Object> result = new HashMap<>();
        List<String> relationList = new ArrayList<>();
        List<String> otherList = new ArrayList<>();
        List<String> bookList = new ArrayList<>();

        // book
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("title");
        queryWrapper.eq("authorID", authorId);
        List<Book> books = bookService.list(queryWrapper);

        for (Book book : books) {
            bookList.add(book.getTitle());
        }

        // relation
        QueryWrapper<AuthorRelationship> ARQueryWrapper = new QueryWrapper<>();
        ARQueryWrapper.select("relationship");
        ARQueryWrapper.eq("authorID", authorId);
        List<AuthorRelationship> relationships = authorRelationshipService.list(ARQueryWrapper);
        for (AuthorRelationship relationship : relationships)
            relationList.add(relationship.getRelationship());

        // name
        QueryWrapper<Author> AQueryWrapper = new QueryWrapper<>();
        AQueryWrapper.select("*").eq("id", authorId);
        Author author = authorService.list(AQueryWrapper).get(0);
        String province = author.getProvence();
        String name = author.getName();

        QueryWrapper<Author> AQueryWrapper_ = new QueryWrapper<>();
        AQueryWrapper_.select("name").eq("provence", province);
        List<Author> authors = authorService.list(AQueryWrapper_);
        // other
        for (Author a : authors)
            otherList.add(a.getName());
        otherList.remove(name);
        result.put("name", name);
        result.put("book", bookList);
        result.put("relation", relationList);
        result.put("other", otherList);

        return result;
    }
}
