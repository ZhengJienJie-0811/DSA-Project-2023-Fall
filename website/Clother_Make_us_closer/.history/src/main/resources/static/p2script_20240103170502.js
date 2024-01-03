window.addEventListener('DOMContentLoaded', (event) => {
    // This function could be triggered when the page is loaded.
    // Fetch the results from the server, e.g., using a GET request
    fetch('/results').then(response => response.json()).then(data => {
        const resultsContainer = document.getElementById('resultsContainer');
        // Assuming 'data' is an array of result strings
        data.forEach(result => {
            const resultElement = document.createElement('div');
            resultElement.className = 'result';
            resultElement.textContent = result;
            resultsContainer.appendChild(resultElement);
        });
    }).catch(error => {
        console.error('Error fetching results:', error);
    });
});
