package com.picposter.repository;
import com.picposter.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO  extends JpaRepository<Comment, Integer> {
/*    T insert(T insertedObject);
    List<T> selectAll();
    T selectById(UUID id);
    T deleteById(UUID id);
    T updateById(UUID id, T updatedObject);*/
}
