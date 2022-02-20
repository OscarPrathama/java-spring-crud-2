package com.crud.repositories;

import java.util.List;

import com.crud.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    /**
     * search query
     * 1 adalah satu bukan huruf l
    */
    @Query("SELECT p FROM Post p WHERE p.postTitle LIKE %?1%")
    public List<Post> search(@Param("keyword") String keyword);

}
