package com.example.service.projections;

public interface CommentUser {
    Integer getId(); //testing
    String getCommentContent();
    UserProjection getUserOne();

    interface UserProjection {
        String getName();
    }
}
