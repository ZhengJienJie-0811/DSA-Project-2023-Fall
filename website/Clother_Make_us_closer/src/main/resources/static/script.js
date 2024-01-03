document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form');
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        // Create an object to hold the input data
        const searchData = {
            search: document.querySelector('.search-input').value,
            condition1: document.querySelector('input[placeholder="condition1"]').value,
            condition2: document.querySelector('input[placeholder="condition2"]').value,
            condition3: document.querySelector('input[placeholder="condition3"]').value
        };

        // Send the search data to the server
        fetch('/search', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(searchData)
        })
        .then(response => response.json())
        .then(data => {
            // Handle the response data
            // Redirect to page2.html or display results on the page
            window.location.href = '/page2.html';
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
});
