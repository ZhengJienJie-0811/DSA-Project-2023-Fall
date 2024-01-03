window.addEventListener('DOMContentLoaded', (event) => {
    fetch('/results').then(response => response.json()).then(data => {
        const resultsContainer = document.getElementById('resultsContainer');

        // Clear previous results
        resultsContainer.innerHTML = '';

        // Assuming 'data' is an array of WebPage objects
        data.forEach(webPage => {
            const resultDiv = document.createElement('div');
            resultDiv.className = 'result-style';

            // Create an element for the name
            const nameDiv = document.createElement('div');
            nameDiv.className = 'result-name';
            nameDiv.textContent = webPage.name;
            resultDiv.appendChild(nameDiv);

            // Create a line break
            const lineBreak = document.createElement('br');
            resultDiv.appendChild(lineBreak);

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
