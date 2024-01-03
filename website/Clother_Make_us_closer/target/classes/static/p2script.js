window.addEventListener('DOMContentLoaded', (event) => {
    fetch('/results').then(response => response.json()).then(data => {
        const resultsContainer = document.getElementById('resultsContainer');
        resultsContainer.innerHTML = ''; // Clear previous results

        const limitedData = data.slice(0, 14); // Limit the number of results to 14

        limitedData.forEach(webPage => {
            const resultDiv = document.createElement('div');
            resultDiv.className = 'result-style';

            const nameDiv = document.createElement('div');
            nameDiv.className = 'result-name';
            nameDiv.textContent = webPage.name;
            resultDiv.appendChild(nameDiv);

            const urlAnchor = document.createElement('a');
            urlAnchor.className = 'result-url';
            urlAnchor.href = webPage.url;
            urlAnchor.textContent = webPage.url;
            urlAnchor.target = "_blank";
            resultDiv.appendChild(urlAnchor);

            resultsContainer.appendChild(resultDiv);
        });
    }).catch(error => {
        console.error('Error fetching results:', error);
    });
});

