
window.addEventListener('DOMContentLoaded', (event) => {
    fetch('/results').then(response => response.json()).then(data => {
        const resultsContainer = document.getElementById('resultsContainer');

        // Clear previous results
        resultsContainer.innerHTML = '';

        // Assuming 'data' is an array of result strings
        data.forEach(resultText => {
    const resultDiv = document.createElement('div');
    resultDiv.className = 'result-style';
    const resultInnerDiv = document.createElement('div');
    resultInnerDiv.className = 'result-inner'; // This class is used for styling the inner content
    resultInnerDiv.textContent = resultText;
    resultDiv.appendChild(resultInnerDiv);
    resultsContainer.appendChild(resultDiv);
});
    }).catch(error => {
        console.error('Error fetching results:', error);
    });
});



