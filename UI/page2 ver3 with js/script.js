// 假設這是搜尋結果的數據
const searchResults = [
    { title: "", description: "" },
    { title: "", description: "" },
    { title: "", description: "" },
    { title: "", description: "" },
    
    // ... 其他結果 ...
];

// 獲取容器
const container = document.getElementById('resultsContainer');

// 為每個結果創建一個方塊並加入到容器中
searchResults.forEach(result => {
    const resultDiv = document.createElement('div');
    resultDiv.className = 'result-style';
    resultDiv.innerHTML = `<h2>${result.title}</h2><p>${result.description}</p>`;
    container.appendChild(resultDiv);
});

