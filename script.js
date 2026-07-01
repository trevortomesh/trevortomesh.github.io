document.addEventListener('DOMContentLoaded', () => {
    fetch('cards.json')
        .then(response => response.json())
        .then(data => initializePage(data))
        .catch(error => {
            console.error('Error loading JSON:', error);
            const cardContainer = document.getElementById('card-container');
            if (cardContainer) {
                cardContainer.innerHTML = '<p class="callout">Cards could not be loaded.</p>';
            }
        });
});

function initializePage(data) {
    const cardContainer = document.getElementById('card-container');
    const tagList = document.getElementById('tag-list');
    const searchInput = document.getElementById('search');

    if (!cardContainer || !tagList || !searchInput) {
        return;
    }

    const cards = Object.entries(data).map(([id, card]) => ({
        id,
        ...card,
        tags: normalizeTags(card.tags)
    }));

    const tags = [...new Set(cards.flatMap(card => card.tags))]
        .filter(tag => tag !== 'all')
        .sort((a, b) => a.localeCompare(b));

    tagList.appendChild(createTagLink('All', 'all'));
    tags.forEach(tag => tagList.appendChild(createTagLink(formatTagLabel(tag), tag)));

    tagList.addEventListener('click', event => {
        const link = event.target.closest('a[data-tag]');
        if (!link) {
            return;
        }

        event.preventDefault();
        setActiveTag(link);
        renderCards(cards, cardContainer, getActiveTag(), searchInput.value);
    });

    searchInput.addEventListener('input', () => {
        renderCards(cards, cardContainer, getActiveTag(), searchInput.value);
    });

    const allLink = tagList.querySelector('a[data-tag="all"]');
    setActiveTag(allLink);
    renderCards(cards, cardContainer, 'all', '');
}

function normalizeTags(tags = []) {
    return [...new Set(tags.map(tag => String(tag).trim().toLowerCase()).filter(Boolean))];
}

function createTagLink(label, tag) {
    const item = document.createElement('li');
    const link = document.createElement('a');
    link.href = '#card-container';
    link.dataset.tag = tag;
    link.textContent = label;
    item.appendChild(link);
    return item;
}

function formatTagLabel(tag) {
    return tag
        .split('-')
        .map(part => part.charAt(0).toUpperCase() + part.slice(1))
        .join(' ');
}

function setActiveTag(selectedLink) {
    document.querySelectorAll('#tag-list a[data-tag]').forEach(link => {
        const isActive = link === selectedLink;
        link.classList.toggle('active', isActive);
        link.setAttribute('aria-current', isActive ? 'true' : 'false');
    });
}

function getActiveTag() {
    return document.querySelector('#tag-list a.active')?.dataset.tag || 'all';
}

function renderCards(cards, cardContainer, selectedTag, searchQuery) {
    const query = searchQuery.trim().toLowerCase();
    const fragment = document.createDocumentFragment();

    cards
        .filter(card => matchesTag(card, selectedTag))
        .filter(card => matchesSearch(card, query))
        .forEach(card => fragment.appendChild(createCardElement(card)));

    cardContainer.replaceChildren(fragment);
}

function matchesTag(card, selectedTag) {
    return !selectedTag || selectedTag === 'all' || card.tags.includes(selectedTag);
}

function matchesSearch(card, query) {
    if (!query) {
        return true;
    }

    const searchableText = [
        card.title,
        stripHtml(card.text || ''),
        card.tags.join(' ')
    ].join(' ').toLowerCase();

    return searchableText.includes(query);
}

function stripHtml(html) {
    const template = document.createElement('template');
    template.innerHTML = html;
    return template.content.textContent || '';
}

function createCardElement(cardData) {
    const cardElement = document.createElement('article');
    cardElement.className = 'card';

    const primaryLink = extractPrimaryLink(cardData.text || '');
    const tags = cardData.tags.filter(tag => tag !== 'all');

    cardElement.innerHTML = `
        <div class="card-inner">
            <div class="card-header">
                <h4>${cardData.title}</h4>
                ${primaryLink ? `<a class="card-action" href="${primaryLink.href}" ${primaryLink.external ? 'target="_blank" rel="noopener noreferrer"' : ''}>Open</a>` : ''}
            </div>
            ${cardData.image ? `<img src="${cardData.image}" alt="">` : ''}
            <div class="card-text-container">
                <div class="card-text">${cardData.text || ''}</div>
                <button class="expand-button" type="button" aria-expanded="false">More</button>
            </div>
            ${tags.length ? `<div class="card-tags">${tags.map(tag => `<span class="card-tag">${formatTagLabel(tag)}</span>`).join('')}</div>` : ''}
        </div>
    `;

    const text = cardElement.querySelector('.card-text');
    const expandButton = cardElement.querySelector('.expand-button');

    requestAnimationFrame(() => {
        const isOverflowing = text.scrollHeight > text.clientHeight + 2;
        expandButton.hidden = !isOverflowing;
    });

    expandButton.addEventListener('click', () => {
        const isExpanded = cardElement.classList.toggle('expanded');
        expandButton.textContent = isExpanded ? 'Less' : 'More';
        expandButton.setAttribute('aria-expanded', String(isExpanded));
    });

    return cardElement;
}

function extractPrimaryLink(html) {
    const template = document.createElement('template');
    template.innerHTML = html;
    const anchor = template.content.querySelector('a[href]');

    if (!anchor) {
        return null;
    }

    const href = anchor.getAttribute('href');
    return {
        href,
        external: /^https?:\/\//i.test(href)
    };
}
