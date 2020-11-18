package com.org.composter.controller;

import com.org.composter.dao.PostDao;
import com.org.composter.model.Post;
import com.org.composter.request.PostRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    private static final Logger LOG = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostDao postDao;

    @GetMapping(value = "/posts", produces = "application/json")
    public ResponseEntity<List<Post>> getPosts() {
        LOG.info("start of post fetch process");
        return ResponseEntity.ok(postDao.findAll());
    }

    @PutMapping(value = "/posts", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> addPost(@RequestBody PostRequest request) {
        LOG.info("start of post add process");
        Post p = new Post();
        p.setNews(request.getPost());
        p.setPostedon(request.getDate());
        postDao.saveAndFlush(p);
        return ResponseEntity.ok("succes");
    }
}
