function addNumbers() {
    var num1 = document.getElementById('num1').value;
    var num2 = document.getElementById('num2').value;

    fetch('/add?num1=' + num1 + '&num2=' + num2)
        .then(response => response.json())
        .then(data => document.getElementById('result').innerText = data.result);
}
