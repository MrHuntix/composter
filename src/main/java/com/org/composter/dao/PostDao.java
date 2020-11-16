package com.org.composter.dao;

import com.org.composter.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Post, Long> {
}
