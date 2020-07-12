// Copyright 2020 Tianyun Zhang

// Fetch data from /data and put it into paragraph with id='data'
const loadData = () => {
  fetch('/data')
    .then(res => res.text())
    .then(text => {
      document.getElementById('data').innerHTML = text;
    });
};
