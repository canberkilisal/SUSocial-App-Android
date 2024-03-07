package com.example.firsttryapp;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class TryRepo {

    public void loginOps(ExecutorService srv, Handler uiHandler, String username, String password){


        srv.execute(()->{
            try {
                URL url = new URL("http://10.0.2.2:8080/susocial/users/login");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();

                conn.setRequestMethod("POST");

                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                conn.setDoOutput(true);
                conn.setDoInput(true);

                String requestBody = "username=" + username + "&password=" + password;
                OutputStream os = conn.getOutputStream();
                os.write(requestBody.getBytes("UTF-8"));
                os.close();

                String opSys;
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    opSys = "Login Successfully!";
                }
                else {
                    opSys = "Invalid Username or Password!";
                }


                Message msg = new Message();
                msg.obj = opSys;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
    }

    public void signUpOps(ExecutorService srv, Handler uiHandler, String name, String lastName ,String username, String password){


        srv.execute(()->{
            try {
                URL url = new URL("http://10.0.2.2:8080/susocial/users");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();

                conn.setRequestMethod("POST");

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                JSONObject jsonUser = new JSONObject();
                jsonUser.put("name", name);
                jsonUser.put("lastName", lastName);
                jsonUser.put("username", username);
                jsonUser.put("password", password);

                OutputStream os = conn.getOutputStream();
                os.write(jsonUser.toString().getBytes("UTF-8"));
                os.close();

                String opSys;
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_CREATED) {
                    opSys = "User Created!";
                }
                else {
                    opSys = "Failed";
                }


                Message msg = new Message();
                msg.obj = opSys;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });
    }

    public void listAllUsers(ExecutorService srv, Handler uiHandler) {

        srv.execute(() -> {

            try {
                URL url = new URL("http://10.0.2.2:8080/susocial/users/all" );



                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedInputStream reader = new BufferedInputStream(conn.getInputStream());
                StringBuilder buffer = new StringBuilder();
                int chr = 0;
                while ((chr = reader.read()) != -1) {

                    buffer.append((char) chr);


                }


                JSONArray arr = new JSONArray(buffer.toString());
                List<User> data = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {

                    JSONObject currentObj = arr.getJSONObject(i);


                    data.add(new User(currentObj.getString("id"),
                            currentObj.getString("name"),
                            currentObj.getString("lastName"),
                            currentObj.getString("username"),
                            currentObj.getString("password"),
                            currentObj.getString("schedule"),
                            currentObj.getString("friends")));

                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void getUserByUsername(ExecutorService srv, Handler uiHandler, String id){


        srv.execute(()->{
            try {
                URL url = new URL("http://10.0.2.2:8080/susocial/users/" + id);
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONArray arr = new JSONArray(buffer.toString());
                JSONObject currentObj = arr.getJSONObject(0);
                User opSys = new User(currentObj.getString("id"),
                        currentObj.getString("name"),
                        currentObj.getString("lastName"),
                        currentObj.getString("username"),
                        currentObj.getString("password"),
                        currentObj.getString("schedule"),
                        currentObj.getString("friends"));


                Message msg = new Message();
                msg.obj = opSys;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public void listAllNews(ExecutorService srv, Handler uiHandler) {

        srv.execute(() -> {

            try {
                URL url = new URL("http://10.0.2.2:8080/susocial/social/news/all" );



                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedInputStream reader = new BufferedInputStream(conn.getInputStream());
                StringBuilder buffer = new StringBuilder();
                int chr = 0;
                while ((chr = reader.read()) != -1) {

                    buffer.append((char) chr);


                }


                JSONArray arr = new JSONArray(buffer.toString());
                List<News> data = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {

                    JSONObject currentObj = arr.getJSONObject(i);


                    data.add(new News(currentObj.getString("id"),
                            currentObj.getString("title"),
                            currentObj.getString("content"),
                            currentObj.getString("comments"),
                            currentObj.getString("sunewTime")));

                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void getNewsByTitle(ExecutorService srv, Handler uiHandler, String id){


        srv.execute(()->{
            try {
                URL url = new URL("http://10.0.2.2:8080/susocial/social/news/search?title=" + id);
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONArray arr = new JSONArray(buffer.toString());
                JSONObject currentObj = arr.getJSONObject(0);
                News opSys = new News(currentObj.getString("id"),
                        currentObj.getString("title"),
                        currentObj.getString("content"),
                        currentObj.getString("comments"),
                        currentObj.getString("sunewTime"));


                Message msg = new Message();
                msg.obj = opSys;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public void listAllPosts(ExecutorService srv, Handler uiHandler) {
        srv.execute(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/susocial/social/post/all");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedInputStream reader = new BufferedInputStream(conn.getInputStream());
                StringBuilder buffer = new StringBuilder();
                int chr;
                while ((chr = reader.read()) != -1) {
                    buffer.append((char) chr);
                }

                JSONArray arr = new JSONArray(buffer.toString());
                List<Post> data = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject currentObj = arr.getJSONObject(i);
                    JSONObject postOwnerObj = currentObj.getJSONObject("postOwner");

                    User postOwner = new User(
                            postOwnerObj.getString("id"),
                            postOwnerObj.getString("name"),
                            postOwnerObj.getString("lastName"),
                            postOwnerObj.getString("username"),
                            postOwnerObj.getString("password"),
                            postOwnerObj.getString("schedule"),
                            postOwnerObj.getString("friends")
                    );

                    List<Comment> comments = new ArrayList<>();
                    JSONArray commentsArray = currentObj.optJSONArray("comments");

                    if (commentsArray != null) {
                        for (int j = 0; j < commentsArray.length(); j++) {
                            JSONObject commentObj = commentsArray.getJSONObject(j);
                            User commentOwner = new User(
                                    commentObj.getJSONObject("commentOwner").getString("id"),
                                    commentObj.getJSONObject("commentOwner").getString("name"),
                                    commentObj.getJSONObject("commentOwner").getString("lastName"),
                                    commentObj.getJSONObject("commentOwner").getString("username"),
                                    commentObj.getJSONObject("commentOwner").getString("password"),
                                    commentObj.getJSONObject("commentOwner").getString("schedule"),
                                    commentObj.getJSONObject("commentOwner").getString("friends")
                            );
                            Comment comment = new Comment(
                                    commentObj.getString("id"),
                                    commentObj.getString("content"),
                                    commentObj.getString("commentTime"),
                                    commentOwner
                            );

                            comments.add(comment);
                        }
                    }

                    Post post = new Post(
                            currentObj.getString("id"),
                            comments,
                            currentObj.getString("postTime"),
                            currentObj.getString("content"),
                            postOwner
                    );

                    data.add(post);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void getPostById(ExecutorService srv, Handler uiHandler, String id){


        srv.execute(()-> {
            try {
                URL url = new URL("http://10.0.2.2:8080/susocial/social/post/" + id);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {

                    buffer.append(line);

                }

                JSONObject currentObj = new JSONObject(buffer.toString());

                JSONObject postOwnerObj = currentObj.getJSONObject("postOwner");
                User postOwner = new User(
                        postOwnerObj.getString("id"),
                        postOwnerObj.getString("name"),
                        postOwnerObj.getString("lastName"),
                        postOwnerObj.getString("username"),
                        postOwnerObj.getString("password"),
                        postOwnerObj.getString("schedule"),
                        postOwnerObj.getString("friends")
                );

                List<Comment> comments = new ArrayList<>();
                JSONArray commentsArray = currentObj.optJSONArray("comments");

                if (commentsArray != null) {
                    for (int j = 0; j < commentsArray.length(); j++) {
                        JSONObject commentObj = commentsArray.getJSONObject(j);

                        User commentOwner = new User(
                                commentObj.getJSONObject("commentOwner").getString("id"),
                                commentObj.getJSONObject("commentOwner").getString("name"),
                                commentObj.getJSONObject("commentOwner").getString("lastName"),
                                commentObj.getJSONObject("commentOwner").getString("username"),
                                commentObj.getJSONObject("commentOwner").getString("password"),
                                commentObj.getJSONObject("commentOwner").getString("schedule"),
                                commentObj.getJSONObject("commentOwner").getString("friends")
                        );

                        Comment comment = new Comment(
                                commentObj.getString("id"),
                                commentObj.getString("content"),
                                commentObj.getString("commentTime"),
                                commentOwner
                        );

                        comments.add(comment);
                    }
                }

                Post post = new Post(
                        currentObj.getString("id"),
                        comments,
                        currentObj.getString("postTime"),
                        currentObj.getString("content"),
                        postOwner
                );

                Message msg = new Message();
                msg.obj = post;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public void listAllFriends(ExecutorService srv, Handler uiHandler, String username) {

        srv.execute(() -> {

            try {
                URL url = new URL("http://10.0.2.2:8080/susocial/users/"+ username +"/friends" );

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedInputStream reader = new BufferedInputStream(conn.getInputStream());
                StringBuilder buffer = new StringBuilder();
                int chr = 0;
                while ((chr = reader.read()) != -1) {

                    buffer.append((char) chr);

                }

                JSONArray arr = new JSONArray(buffer.toString());
                List<User> data = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {

                    JSONObject currentObj = arr.getJSONObject(i);


                    data.add(new User(currentObj.getString("id"),
                            currentObj.getString("name"),
                            currentObj.getString("lastName"),
                            currentObj.getString("username"),
                            currentObj.getString("password"),
                            currentObj.getString("schedule"),
                            currentObj.getString("friends")));

                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void addFriend(ExecutorService srv, Handler uiHandler, String username, String friendUsername) {

        srv.execute(() -> {

            try {
                URL url = new URL("http://10.0.2.2:8080/susocial/users/"+ username +"/add-friend" );

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");

                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                conn.setDoOutput(true);
                conn.setDoInput(true);

                String requestBody = "friendToAdd=" + friendUsername;
                OutputStream os = conn.getOutputStream();
                os.write(requestBody.getBytes("UTF-8"));
                os.close();


                String opSys;
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    opSys = "Friend added successfully!";
                }
                else {
                    opSys = "You are already friends!";
                }

                Message msg = new Message();
                msg.obj = opSys;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void createPost(ExecutorService srv, Handler uiHandler, String username, String content, LocalDateTime time) {

        srv.execute(() -> {

            try {
                URL url = new URL("http://10.0.2.2:8080/susocial/social/post");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                JSONObject jsonPost = new JSONObject();


                JSONObject jsonPostOwner = new JSONObject();
                jsonPostOwner.put("id", username);


                jsonPost.put("postOwner", jsonPostOwner);
                jsonPost.put("postTime", time.toString());
                jsonPost.put("content", content);

                OutputStream os = conn.getOutputStream();
                os.write(jsonPost.toString().getBytes("UTF-8"));
                os.close();

                String opSys;
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_CREATED) {
                    opSys = "Post Created!";
                }
                else {
                    opSys = "Failed";
                }


                Message msg = new Message();
                msg.obj = opSys;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void createComment(ExecutorService srv, Handler uiHandler, String username, String content, LocalDateTime time, String id) {

        srv.execute(() -> {

            try {
                URL url = new URL("http://10.0.2.2:8080/susocial/social/post/" + id + "/add-comment");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                JSONObject jsonPost = new JSONObject();


                JSONObject jsonPostOwner = new JSONObject();
                jsonPostOwner.put("id", username);


                jsonPost.put("commentOwner", jsonPostOwner);
                jsonPost.put("commentTime", time.toString());
                jsonPost.put("content", content);

                OutputStream os = conn.getOutputStream();
                os.write(jsonPost.toString().getBytes("UTF-8"));
                os.close();

                String opSys;
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    opSys = "Comment Created!";
                }
                else {
                    opSys = "Failed";
                }


                Message msg = new Message();
                msg.obj = opSys;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
