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

package com.google.sps.data;

import java.util.Date;

public final class Comment {
    
    private final String name;
    private final String email;
    private final Date date;
    private final String content;

    public Comment(String name, String email, Date date, String content) {
        this.name = name;
        this.email = email;
        this.date = date;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}
