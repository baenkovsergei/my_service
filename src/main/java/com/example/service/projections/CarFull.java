package com.example.service.projections;

import java.util.List;
import java.util.Set;

public interface CarFull {
    String getModel();

    Set<CategoryProjection> getCategories();

    List<CommentUser> getComments();

    interface CategoryProjection {
        String getName();
    }

}

