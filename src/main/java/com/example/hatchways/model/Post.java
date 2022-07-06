package com.example.hatchways.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {

    private final String author;
    private final int id;
    private final int authorId;
    private final int likes;
    private final double popularity;
    private final int reads;
    private final String[] tags;

    public Post(@JsonProperty("author") String author, @JsonProperty("authorId") int authorId,@JsonProperty("id") int id,@JsonProperty("likes") int likes,@JsonProperty("popularity") double popularity,@JsonProperty("reads") int reads,@JsonProperty("tags") String[] tags) {
        this.author = author;
        this.authorId = authorId;
        this.id = id;
        this.likes = likes;
        this.popularity = popularity;
        this.reads = reads;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getLikes() {
        return likes;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getReads() {
        return reads;
    }

    public String[] getTags() {
        return tags;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(obj instanceof Post)
        {
            Post temp = (Post) obj;
            if(this.id == temp.id)
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub

        return (this.id);
    }

}
