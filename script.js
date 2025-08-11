

// OLD CODE WITHOUT SEARCH BAR
/**
 * Fetches data from a JSON file and renders it on a web page with tag filtering functionality.
 
fetch('cards.json')
    .then(response => response.json())
    .then(data => {
        // Get references to the card container and tag list elements in the HTML
        const cardContainer = document.getElementById('card-container');
        const tagList = document.getElementById('tag-list');

        // Create a set to store unique tags
        const tags = new Set();

        // Add a class name for controlling animations
        const animateClassName = 'animate';

        // Add "all" tag as default and render it at the top of the tag list
        tags.add("all");
        const allTagLink = document.createElement('li');
        allTagLink.innerHTML = `<a href="#" data-tag="all">All</a>`;
        tagList.appendChild(allTagLink);

        // Iterate through the data and extract tags
        Object.keys(data).forEach(id => {
            const cardData = data[id];
            if (cardData.tags) {
                cardData.tags.forEach(tag => {
                    tags.add(tag.toLowerCase()); // Convert tag to lowercase before adding
                });
            }
        });

        // Render individual tags in the tag list
        tags.forEach(tag => {
            if (tag !== "all") {
                const tagLink = document.createElement('li');
                tagLink.innerHTML = `<a href="#" data-tag="${tag}">${tag}</a>`;
                tagList.appendChild(tagLink);
            }
        });

        // Get all tag links with data attributes
        const tagLinks = document.querySelectorAll('#tag-list a[data-tag]');

        // Add click event listeners to tag links
        tagLinks.forEach(link => {
            link.addEventListener('click', () => {
                const selectedTag = link.getAttribute('data-tag');
                cardContainer.classList.add(animateClassName); // Add animation class
                cardContainer.innerHTML = '';

                // Iterate through the data and render cards based on the selected tag
                Object.keys(data).forEach(id => {
                    const cardData = data[id];
                    if (!selectedTag || selectedTag === "all" || (cardData.tags && cardData.tags.includes(selectedTag))) {
                        const cardElement = document.createElement('div');
                        cardElement.className = 'card';
                        cardElement.innerHTML = `
                            <div class="container">
                                <h4>${cardData.title}</h4>
                                ${cardData.image ? `<img src="${cardData.image}" alt="Profile Picture">` : ''}
                                <p>${cardData.text}</p>
                            </div>
                        `;
                        cardContainer.appendChild(cardElement);
                    }
                });

                // Remove the animation class after a delay
                setTimeout(() => {
                    cardContainer.classList.remove(animateClassName);
                }, 300); // Adjust the delay as needed
            });
        });

        // Select "all" tag by default when the page loads
        tagLinks[0].click();
    })
    .catch(error => console.error('Error loading JSON:', error));
*/

//------------------------------------------------------------//

document.addEventListener('DOMContentLoaded', () => {
    // Fetch data from JSON file
    fetch('cards.json')
        .then(response => response.json())
        .then(data => initializePage(data))
        .catch(error => console.error('Error loading JSON:', error));

    // Function to initialize the page with data
    function initializePage(data) {
        const cardContainer = document.getElementById('card-container');
        const tagList = document.getElementById('tag-list');
        const searchInput = document.getElementById('search');
        const animateClassName = 'animate';

        // Create a set to store unique tags
        const tags = new Set();

        // Add "all" tag as default and render it at the top of the tag list
        tags.add('all');
        const allTagLink = createTagLink('All', 'all');
        tagList.appendChild(allTagLink);

        // Extract tags from the data
        Object.keys(data).forEach(id => {
            const cardData = data[id];
            if (cardData.tags) {
                cardData.tags.forEach(tag => {
                    tags.add(tag.toLowerCase());
                });
            }
        });

        // Render individual tags in the tag list
        tags.forEach(tag => {
            if (tag !== 'all') {
                const tagLink = createTagLink(tag, tag);
                tagList.appendChild(tagLink);
            }
        });

        // Add click event listeners to tag links
        const tagLinks = document.querySelectorAll('#tag-list a[data-tag]');
        tagLinks.forEach(link => {
            link.addEventListener('click', () => {
                const selectedTag = link.getAttribute('data-tag');
                updateActiveTag(link);
                renderCards(data, cardContainer, selectedTag, searchInput.value.trim(), animateClassName);
            });
        });

        // Add input event listener to the search input for real-time filtering
        searchInput.addEventListener('input', () => {
            const selectedTag = getActiveTag();
            renderCards(data, cardContainer, selectedTag, searchInput.value.trim(), animateClassName);
        });

        // Select "all" tag by default when the page loads
        tagLinks[0].click();
    }

    // Function to create a tag link element
    function createTagLink(label, tag) {
        const tagLink = document.createElement('li');
        tagLink.innerHTML = `<a href="#" data-tag="${tag}">${label}</a>`;
        return tagLink;
    }

    // Function to update the active tag styling
    function updateActiveTag(selectedLink) {
        const tagLinks = document.querySelectorAll('#tag-list a[data-tag]');
        tagLinks.forEach(link => link.classList.remove('active'));
        selectedLink.classList.add('active');
    }

    // Function to get the currently active tag
    function getActiveTag() {
        const activeTagLink = document.querySelector('#tag-list a.active');
        return activeTagLink ? activeTagLink.getAttribute('data-tag') : 'all';
    }

    // Function to render cards based on the selected tag or search query
    function renderCards(data, cardContainer, selectedTag, searchQuery, animateClassName) {
        cardContainer.classList.add(animateClassName);
        cardContainer.innerHTML = '';

        Object.keys(data).forEach(id => {
            const cardData = data[id];
            const isMatchingTag = !selectedTag || selectedTag === 'all' || (cardData.tags && cardData.tags.includes(selectedTag));
            const isMatchingSearch = !searchQuery || cardData.title.toLowerCase().includes(searchQuery.toLowerCase());

            if (isMatchingTag && isMatchingSearch) {
                const cardElement = createCardElement(cardData);
                cardContainer.appendChild(cardElement);
            }
        });

        setTimeout(() => {
            cardContainer.classList.remove(animateClassName);
        }, 300);
    }

    // Function to create a card element
    function createCardElement(cardData) {
        const cardElement = document.createElement('div');
        cardElement.className = 'card';
        cardElement.innerHTML = `
            <div class="card-inner">
                <h4>${cardData.title}</h4>
                ${cardData.image ? `<img src="${cardData.image}" alt="Profile Picture">` : ''}
                <div class="card-text">${cardData.text}</div>
            </div>
        `;
        return cardElement;
    }
});
