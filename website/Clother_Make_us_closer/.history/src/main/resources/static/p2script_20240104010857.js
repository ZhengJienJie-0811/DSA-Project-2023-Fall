
window.addEventListener('DOMContentLoaded', (event) => {
    fetch('/results').then(response => response.json()).then(data => {
        const resultsContainer = document.getElementById('resultsContainer');

        // Clear previous results
        resultsContainer.innerHTML = '';

        // Assuming 'data' is an array of result strings
        data.forEach(resultText => {
            const resultDiv = document.createElement('div');
            resultDiv.className = 'result-style'; // Use the class that your CSS has defined for the boxes
            resultDiv.innerHTML = `<div class="result-inner">${resultText}</div>`; // 'result-inner' is a suggestion for another class to style the inner content
            resultsContainer.appendChild(resultDiv);
        });
    }).catch(error => {
        console.error('Error fetching results:', error);
    });
});



