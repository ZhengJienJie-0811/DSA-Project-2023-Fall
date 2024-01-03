
window.addEventListener('DOMContentLoaded', (event) => {
    fetch('/results').then(response => response.json()).then(data => {
        const resultsContainer = document.getElementById('resultsContainer');

        // Clear previous results
        resultsContainer.innerHTML = '';

        // Filter out empty or null results
        const filteredData = data.filter(resultText => resultText.trim() !== '');

        // Assuming 'data' is an array of result strings
        filteredData.forEach(resultText => {
            const resultDiv = document.createElement('div');
            resultDiv.className = 'result-style'; // Ensure this class matches your CSS for result boxes
            // Only create and append the div if the resultText is not empty
            if (resultText) {
                resultDiv.innerHTML = `<div class="result-inner">${resultText}</div>`;
                resultsContainer.appendChild(resultDiv);
            }
        });
    }).catch(error => {
        console.error('Error fetching results:', error);
    });
});




