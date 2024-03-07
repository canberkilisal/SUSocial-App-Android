package com.SUSocial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SUSocial.model.Comment;
import com.SUSocial.model.Post;
import com.SUSocial.model.SUNew;
import com.SUSocial.model.User;
import com.SUSocial.repository.CommentRepository;
import com.SUSocial.repository.PostRepository;
import com.SUSocial.repository.SUNewRepository;

@Service
public class SocialService {

	@Autowired 
	private CommentRepository commentRepository;
	
	@Autowired 
	private PostRepository postRepository;
	
	@Autowired 
	private SUNewRepository sunewRepository;
	
	public SUNew createNews(SUNew sunew) {
        
		return sunewRepository.save(sunew);
    }
	
    public List<SUNew> getAllNews() {
    	
        return sunewRepository.findAll();
    }
	
    public List<SUNew> searchNewsByTitle(String title) {
        
    	return sunewRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public SUNew addCommentToNews(String newsId, Comment comment) {
        
    	SUNew news = sunewRepository.findById(newsId).orElse(null);
        
    	if (news != null) {
            
    		if (news.getComments() != null) {
    			
	    		List<Comment> comments = news.getComments();
	            comments.add(comment);
	            news.setComments(comments);
            }
    		
    		else {
    			
    			List<Comment> newComments = new ArrayList<>();
    			newComments.add(comment);
    			news.setComments(newComments);
    		}
            
            return sunewRepository.save(news);         
        }
        return null;
    }
    
    public List<Comment> getCommentsForNews(String newsId) {
        
    	SUNew news = sunewRepository.findById(newsId).orElse(null);
        
    	if (news != null) {
            
    		return news.getComments();
        }
        return null;
    }

    public Post createPost(Post post) {
    	
        return postRepository.save(post);
    }
    
    public List<Post> getAllPosts() {
        
    	return postRepository.findAll();
    }
    
    public Post getPostByID(String id) {
        
    	return postRepository.findById(id).orElse(null);
    }
    
    public Post addCommentToPost(String postId, Comment comment) {
        
    	commentRepository.save(comment);
    	
    	Post post = postRepository.findById(postId).orElse(null);
        
    	if (post != null) {
                   
    		if (post.getComments() != null) {
    			
	    		List<Comment> comments = post.getComments();
	            comments.add(comment);
	            post.setComments(comments);
            }
    		
    		else {
    			
    			List<Comment> newComments = new ArrayList<>();
    			newComments.add(comment);
    			post.setComments(newComments);
    		}
            
            return postRepository.save(post); 
            
        }
        return null;
    }
    
    public List<Comment> getCommentsForPost(String postId) {
        
    	Post post = postRepository.findById(postId).orElse(null);
        
    	if (post != null) {
            
    		return post.getComments();
        }
        return null;
    }
    
	public SUNew findByNewsId(String newsId) {
		
		if (sunewRepository.findById(newsId) == null) {
			
			return null;
		}
		
		else {
			
			SUNew news = sunewRepository.findById(newsId).orElse(null);;
			
			return news;
		}

	}
    
}
