package com.SUSocial.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SUSocial.model.Comment;
import com.SUSocial.model.Post;
import com.SUSocial.model.Schedule;
import com.SUSocial.model.SUNew;
import com.SUSocial.model.User;
import com.SUSocial.service.UserService;
import com.SUSocial.service.SocialService;

@RestController
@RequestMapping("/susocial")
public class SUSocialController {


	@Autowired 
	private UserService userService;
	
	@Autowired 
	private SocialService socialService;
	
	
	@ResponseStatus(HttpStatus.CONFLICT)
	public class UsernameExistsException extends RuntimeException{
		
	    public UsernameExistsException(String message) {
	        super(message);
	    }
		
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public class UserNotFoundException extends RuntimeException{
		
	    public UserNotFoundException(String message) {
	        super(message);
	    }
		
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public class UserPassMissmatchException extends RuntimeException{
		
	    public UserPassMissmatchException(String message) {
	        super(message);
	    }
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public class FriendExistsException extends RuntimeException{
		
	    public FriendExistsException(String message) {
	        super(message);
	    }
		
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public class NewsNotFoundException extends RuntimeException{
		
	    public NewsNotFoundException(String message) {
	        super(message);
	    }
		
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public class PostNotFoundException extends RuntimeException{
		
	    public PostNotFoundException(String message) {
	        super(message);
	    }
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<String> signUp(@RequestBody User user)
	{
		User newUser = userService.createUser(user);
		
		if (newUser == null) {
			
			throw new UsernameExistsException("Username already exists!");
		}
		
		return new ResponseEntity<>("User created successfully!", HttpStatus.CREATED);
	}
	
	@PostMapping("/users/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        
		if (userService.authenticateUser(username, password)) {
            
			return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } 
		else {
        	
			User newUser = userService.findByUsername(username);
			
			if (newUser == null) {
				
				throw new UserNotFoundException(username + "not found!");
			}
			else {
				throw new UserPassMissmatchException("Password is wrong!");
			}
        }
    }
	
	@GetMapping("/users/all")
    public List<User> allUsers() {
        
		return userService.getAllUsers();
    }
	
	@GetMapping("/users/{username}")
	public List<User> searchUser(@PathVariable String username) {
		
		return userService.searchByUsername(username);
	}
	
	@PutMapping("/users/{username}/schedule")
    public ResponseEntity<String> updateUserSchedule(@PathVariable String username, @RequestBody Schedule newSchedule) {
		 
		 User user = userService.findByUsername(username);

	        if (user != null) {

	        	
                user.setSchedule(newSchedule);
                userService.saveUser(user);
 
	            return new ResponseEntity<>("Schedule updated successfully", HttpStatus.OK);
	        } 
	        
	        else {
	        	
	        	throw new UserNotFoundException(username + "not found!");
	        }
	 }
	 
    @GetMapping("/users/{username}/schedule")
    public Schedule getUserSchedule(@PathVariable String username) {
        
    	User user = userService.findByUsername(username);

    	return user.getSchedule();

    }
    
    @GetMapping("/users/{username}/friends")
    public ResponseEntity<List<User>> getUserFriends(@PathVariable String username) {
        
    	User user = userService.findByUsername(username);

        if (user != null) {
            
        	List<User> friends = user.getFriends();
        	
            return new ResponseEntity<>(friends, HttpStatus.OK);
        } 
        else {
            
        	throw new UserNotFoundException(username + "not found!");
        }
    }
    
    @PostMapping("/users/{username}/add-friend")
    public ResponseEntity<String> addFriend(@PathVariable String username, @RequestParam String friendToAdd) {

        User user = userService.findByUsername(username);
        User friend = userService.findByUsername(friendToAdd);

        if (user != null && friend != null) {
            
        	List<User> friends = user.getFriends();
            
        	if (friends == null) {
                friends = new ArrayList<>();
            }
        	
            if (!friends.contains(friend)) {
                
            	friends.add(friend);
                user.setFriends(friends);
                userService.saveUser(user);

                return new ResponseEntity<>("Friend added successfully", HttpStatus.OK);
            } 
            else {
                
            	throw new FriendExistsException(friend.getUsername() + "is already in the list!");
            }
        } 
        else {
        	throw new UserNotFoundException(username + "not found!");
        }
    }
    
    @PostMapping("/social/news")
    public ResponseEntity<SUNew> createNews(@RequestBody SUNew sunew) {
        
    	SUNew createdNews = socialService.createNews(sunew);
        
    	return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
    }
    
    @GetMapping("/social/news/all")
    public List<SUNew> getAllNews() {
        
    	List<SUNew> allNews = socialService.getAllNews();
        
    	return allNews;
    }
    
    @GetMapping("/social/news/search")
    public List<SUNew> searchNewsByTitle(@RequestParam String title) {
        
    	List<SUNew> searchResults = socialService.searchNewsByTitle(title);
        
    	return searchResults;
    }
	
    @PostMapping("/social/news/{newsId}/add-comment")
    public ResponseEntity<SUNew> addCommentToNews(@PathVariable String newsId, @RequestBody Comment comment) {
        
    	SUNew updatedNews = socialService.addCommentToNews(newsId, comment);
        
    	if (updatedNews != null) {
            
    		return new ResponseEntity<>(updatedNews, HttpStatus.OK);
        } 
    	else {
            
    		throw new NewsNotFoundException("News not found!");
        }
    }
    
    @GetMapping("/social/news/{newsId}/all-comments")
    public ResponseEntity<List<Comment>> getCommentsForNews(@PathVariable String newsId) {
        
    	SUNew news = socialService.findByNewsId(newsId);

        if (news != null) {
            
        	List<Comment> comments = news.getComments();
        	
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } 
        else {
            
        	throw new NewsNotFoundException("News not found!");
        }

    }
    
    @PostMapping("/social/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
    	
        Post createdPost = socialService.createPost(post);
        
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
    
    @GetMapping("/social/post/all")
    public List<Post> getAllPosts() {
        
    	List<Post> allPosts = socialService.getAllPosts();
        
    	return allPosts;
    }
    
    @GetMapping("/social/post/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable String postId) {
        
    	Post updatedPost = socialService.getPostByID(postId);
        
    	if (updatedPost != null) {
            
    		return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } 
    	else {
            
    		throw new PostNotFoundException("Post not found!");
        }
    }

    @PostMapping("/social/post/{postId}/add-comment")
    public ResponseEntity<Post> addCommentToPost(@PathVariable String postId, @RequestBody Comment comment) {
        
    	Post updatedPost = socialService.addCommentToPost(postId, comment);
        
    	if (updatedPost != null) {
            
    		return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } 
    	else {
            
    		throw new PostNotFoundException("Post not found!");
        }
    }
    
    @GetMapping("/social/post/{postId}/all-comments")
    public List<Comment> getCommentsForPost(@PathVariable String postId) {
        
    	List<Comment> comments = socialService.getCommentsForPost(postId);
 
    	return comments;

    }
}
