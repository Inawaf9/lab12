package com.nawaf.lab12.Service;

import com.nawaf.lab12.Api.ApiException;
import com.nawaf.lab12.Model.Blog;
import com.nawaf.lab12.Model.User;
import com.nawaf.lab12.Repository.AuthRepository;
import com.nawaf.lab12.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;


    public List<Blog> getBlogs(Integer userId){
        User user = authRepository.findUserById(userId);

        if(user == null) throw new ApiException("User not found");

        return blogRepository.findAllByUser(user);
    }

    public Blog getBlogById(Integer userId, Integer blogId){
        User user = authRepository.findUserById(userId);

        if(user == null) throw new ApiException("User not found");

        return blogRepository.findBlogById(blogId);
    }

    public void addBlog(Integer userId, Blog blog){
        User user = authRepository.findUserById(userId);

        if(user == null) throw new ApiException("User not found");

        blog.setUser(user);

        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId, Integer blogId, Blog blog){
        User user = authRepository.findUserById(userId);
        Blog oldBlog = blogRepository.findBlogById(blogId);

        if(user == null) throw new ApiException("User not found");
        if(oldBlog == null) throw new ApiException("Blog not found");
        if(oldBlog.getUser().getId() != userId) throw new ApiException("Cannot update");

        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());

        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer userId, Integer blogId){
        User user = authRepository.findUserById(userId);
        Blog oldBlog = blogRepository.findBlogById(blogId);

        if(user == null) throw new ApiException("User not found");
        if(oldBlog == null) throw new ApiException("Blog not found");
        if(oldBlog.getUser().getId() != userId) throw new ApiException("Cannot update");

        blogRepository.delete(oldBlog);
    }

    public Blog getBlogByTitle(Integer userId, String title){
        User user = authRepository.findUserById(userId);
        Blog blog = blogRepository.findBlogByTitle(title);

        if(user == null) throw new ApiException("User not found");
        if(blog == null) throw new ApiException("Blog not found");

        return blog;
    }
}
