package com.example.hatchways.controller;

import com.example.hatchways.model.Post;
import com.example.hatchways.model.Posts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.hatchways.exception.TagsNotFoundException;
import com.example.hatchways.exception.SortNotFoundException;
import com.example.hatchways.exception.DirectionNotFoundException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Function;

@Controller
public class PostsController {

    private RestTemplate restTemplate;
    final String url = "https://api.hatchways.io/assessment/blog/posts";

    @GetMapping("/api/posts")
    @ResponseBody
    public Posts getPosts(@RequestParam(required = false) String tags, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String direction) throws JsonProcessingException {
        if (tags == null) throw new TagsNotFoundException();
        restTemplate = new RestTemplate();
        List<Post> listPost = new ArrayList<Post>();
        String[] arrayTags = tags.split(",", 0);
        for (String tag : arrayTags) {
            String result = restTemplate.getForObject(url + "?tag=" + tag, String.class);
            ObjectMapper objectMan = new ObjectMapper();
            List<Post> temp = objectMan.readValue(objectMan.readTree(result).get("posts").toString(), new TypeReference<ArrayList<Post>>() {
            });
            listPost.addAll(temp);
        }
        //Removing Duplicates;
        Set<Post> set = new HashSet<Post>();
        set.addAll(listPost);
        listPost = new ArrayList<Post>();
        listPost.addAll(set);
        Collections.sort(listPost, Comparator.comparing(Post::getId));
        Comparator comparator = Comparator.comparing(Post::getId);
        switch (sortBy) {
            case "id":
                comparator = Comparator.comparing(Post::getId);
                break;
            case "reads":
                comparator = Comparator.comparing(Post::getReads);
                break;
            case "likes":
                comparator = Comparator.comparing(Post::getLikes);
                break;
            case "popularity":
                comparator = Comparator.comparing(Post::getPopularity);
                break;
            default:
                throw new SortNotFoundException();
        }
//        comparator.reversed();
        switch (direction) {
            case "desc":
                Collections.sort(listPost, comparator.reversed());
                break;
            case "asc":
                Collections.sort(listPost, comparator);
                break;
            default:
                throw new DirectionNotFoundException();
        }


        Posts posts = new Posts(listPost);
        return posts;

    }


}
