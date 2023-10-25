document.addEventListener('DOMContentLoaded', function() {
    // Get all <code> elements
    var codeElements = document.querySelectorAll('pre > code');

    codeElements.forEach(function(codeElem) {
        var languageClass = codeElem.className.replace("language-", ""); // Remove the "language-" prefix
        codeElem.className = languageClass + " hljs"; // Add "hljs" class
    });

    codeElements.forEach(function(codeElem) {
        var languageClass = codeElem.className.replace("language-", ""); // Remove the "language-" prefix

        // Create the new HTML structure
        var container = document.createElement('div');
        container.className = 'code-container';

        var header = document.createElement('div');
        header.className = 'code-header';
        container.appendChild(header);

        var langLabel = document.createElement('span');
        langLabel.className = 'language-label';
        langLabel.textContent = languageClass.replace("hljs", "").trim();
        header.appendChild(langLabel);

        var copyBtn = document.createElement('button');
        copyBtn.textContent = 'Copy';
        copyBtn.className = 'copy-btn';
        header.appendChild(copyBtn);

        container.appendChild(codeElem.cloneNode(true));

        codeElem.parentNode.replaceChild(container, codeElem);

        // Add the copy functionality
        copyBtn.addEventListener('click', function() {
            var textarea = document.createElement('textarea');
            textarea.textContent = codeElem.textContent;
            document.body.appendChild(textarea);

            textarea.select();
            document.execCommand('copy');
            document.body.removeChild(textarea);
            copyBtn.textContent = 'copied';
            // alert('Code copied to clipboard!');
        });
    });
    hljs.highlightAll();
});
