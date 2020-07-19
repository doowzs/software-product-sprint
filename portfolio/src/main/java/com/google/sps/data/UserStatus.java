// Copyright 2020 Tianyun Zhang
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

package com.google.sps.data;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public final class UserStatus {

  private final String email;
  private final String loginUrl;
  private final String logoutUrl;

  public UserStatus() {
    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
      this.email = userService.getCurrentUser().getEmail();
      this.loginUrl = "";
      this.logoutUrl = userService.createLogoutURL("/");
    } else {
      this.email = "";
      this.loginUrl = userService.createLoginURL("/");
      this.logoutUrl = "";
    }
  }

  public String getEmail() {
    return email;
  }
}