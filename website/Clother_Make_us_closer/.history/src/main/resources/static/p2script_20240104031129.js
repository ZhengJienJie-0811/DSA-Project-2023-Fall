window.addEventListener('DOMContentLoaded', (event) => {
    fetch('/results').then(response => response.json()).then(data => {
        const resultsContainer = document.getElementById('resultsContainer');

        // Clear previous results
        resultsContainer.innerHTML = '';

        // Limit the number of results to 14
        const limitedData = data.slice(0, 14);

        // Assuming 'limitedData' is an array of WebPage objects
        limitedData.forEach(webPage => {
            const resultDiv = document.createElement('div');
            resultDiv.className = 'result-style';
            
            // Create an element for the name
            const nameDiv = document.createElement('div');
            nameDiv.className = 'result-name';
            nameDiv.textContent = webPage.name;
            resultDiv.appendChild(nameDiv);

            // Create a line break
            resultDiv.appendChild(document.createElement('br'));

            // Create an element for the URL as a clickable link
            const urlAnchor = document.createElement('a');
            urlAnchor.className = 'result-url';
            urlAnchor.href = webPage.url;
            urlAnchor.textContent = webPage.url;
            urlAnchor.target = "_blank"; // Open in new tab
            resultDiv.appendChild(urlAnchor);

            // Append the resultDiv to the container
            resultsContainer.appendChild(resultDiv);
        });
    }).catch(error => {
        console.error('Error fetching results:', error);
    });
});
