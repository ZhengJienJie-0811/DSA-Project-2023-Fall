document.addEventListener("DOMContentLoaded", function() {
    
    const form = document.querySelector('form');

    form.addEventListener('submit', function(event) {
        // 防止表單默認提交行為
        event.preventDefault();

        // 搜尋框的值
        const searchTerm = document.querySelector('.search-input').value;

        // 其他條件的值
        const conditions = [
            document.querySelector('input[placeholder="condition1"]').value,
            document.querySelector('input[placeholder="condition2"]').value,
            document.querySelector('input[placeholder="condition3"]').value
        ];

        
        console.log("Search Term:", searchTerm);
        console.log("Conditions:", conditions);

    });
});
