package com.codeup.sequoiaspringbootblog.daos;

import com.codeup.sequoiaspringbootblog.models.Post;
import org.springframework.data.repository.CrudRepository;

// We can name this whatever we like, but it would be wise to stick
// to suffixing the class name with either `Dao` or `Repository`
public interface PostDao extends CrudRepository<Post, Long> {

}
