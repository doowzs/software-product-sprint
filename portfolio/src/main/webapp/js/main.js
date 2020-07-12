// Copyright 2020 Tianyun Zhang

// Fetch JSON array of comments and show then
const loadComments = () => {
  fetch('/comments')
    .then(res => res.json())
    .then(comments => {
      element = document.getElementById('comments');
      comments.forEach(comment => {
        node = document.createElement('p');
        node.innerHTML = comment.name 
                          + ' @ ' + comment.date 
                          + ': ' + comment.content;
        element.appendChild(node);
      });
    });
};
