// Copyright 2020 Tianyun Zhang, 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.sps.data.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/comments")
public class CommentsServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Gson gson = new Gson();
    ArrayList<Comment> comments = new ArrayList<Comment>();
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    Query query = new Query("Comment").addSort("date", SortDirection.ASCENDING);
    PreparedQuery results = datastore.prepare(query);
    for (Entity entity : results.asIterable()) {
      String name = (String) entity.getProperty("name");
      String email = (String) entity.getProperty("email");
      Date date = (Date) entity.getProperty("date");
      String content = (String) entity.getProperty("content");
      comments.add(new Comment(name, email, date, content));
    }

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(comments));
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
      String name = request.getParameter("name");
      String email = userService.getCurrentUser().getEmail();
      String content = request.getParameter("content");

      Entity commentEntity = new Entity("Comment");
      commentEntity.setProperty("name", name);
      commentEntity.setProperty("email", email);
      commentEntity.setProperty("date", new Date());
      commentEntity.setProperty("content", content);

      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      datastore.put(commentEntity);
    }

    response.sendRedirect("/");
  }
}
