// Copyright 2020 Tianyun Zhang

const loadComments = () => {
  fetchComments();
  fetchUserStatus();
};

// Fetch JSON array of comments and show them
const fetchComments = () => {
  fetch('/comments')
    .then(res => res.json())
    .then(comments => {
      element = document.getElementById('comments');
      comments.forEach(comment => {
        node = document.createElement('p');
        node.innerHTML = comment.name
                          + '[' + comment.email + ']'
                          + ' @ ' + comment.date 
                          + ': ' + comment.content;
        element.appendChild(node);
      });
    });
};

// Fetch user status and show buttons.
const fetchUserStatus = () => {
  fetch('/user-status')
    .then(res => res.json())
    .then(status => {
      console.log(status);
      loginLink = document.getElementById('login-link');
      logoutLink = document.getElementById('logout-link');
      emailElement = document.getElementById('user-email');
      anonymousElement = document.getElementById('new-comment-anonymous');
      loggedInElement = document.getElementById('new-comment-logged-in');
      if (status.email) {
        emailElement.innerHTML = status.email;
        logoutLink.href = status.logoutUrl;
        loggedInElement.style["display"] = "block";
      } else {
        loginLink.href = status.loginUrl;
        anonymousElement.style["display"] = "block";
      }
    });
}
