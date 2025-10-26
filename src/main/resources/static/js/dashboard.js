    function showTab(tabId, buttonID) {
    const tabs = document.querySelectorAll('.tab');
    tabs.forEach(tab => tab.classList.remove('active'));

    const buttons = document.querySelectorAll('.button');
    buttons.forEach(button => button.classList.remove('active'));

    document.getElementById(tabId).classList.add('active');
    document.getElementById(buttonID).classList.add('active');
}

    function rowClicked(value) {
    location.href = value;
}

    document.addEventListener('DOMContentLoaded', function () {
    const entrepriseSelect = document.getElementById('entrepriseSelect');
    const tuteurSelect = document.getElementById('tuteurSelect');

    entrepriseSelect.addEventListener('change', function () {
    const entrepriseId = this.value;

    tuteurSelect.innerHTML = '<option value="">-- Choisir une entreprise dabord --</option>';
    tuteurSelect.disabled = true;

    if (entrepriseId) {
    const url = `/api/tuteurs/by-entreprise/${entrepriseId}`;

    fetch(url)
    .then(response => {
    if (!response.ok) {
    throw new Error('La réponse du réseau n\'était pas valide');
}
    return response.json();
})
    .then(tuteurs => {
    // Vider le select (sauf la première option)
    tuteurSelect.innerHTML = '<option value="">Choisir un tuteur...</option>';

    // Remplir le select avec les tuteurs reçus
    tuteurs.forEach(tuteur => {
    const option = document.createElement('option');
    option.value = tuteur.id;
    option.textContent = tuteur.prenom + ' ' + tuteur.nom;
    tuteurSelect.appendChild(option);
});

    // Activer le champ tuteur
    tuteurSelect.disabled = false;
})
    .catch(error => {
    console.error('Erreur lors de la récupération des tuteurs:', error);
    tuteurSelect.innerHTML = '<option value="">Erreur de chargement</option>';
});
}
});
});

<!--    Suppression d'un apprenti -->
    document.addEventListener('DOMContentLoaded', function () {
    const confirmationModal = document.getElementById('confirmationSuppressionModal');
    if (confirmationModal) {
    confirmationModal.addEventListener('show.bs.modal', function (event) {
    // 'event.relatedTarget' est le lien <a> qui a déclenché la modale
    const button = event.relatedTarget;

    // Récupérer les données depuis les attributs data-*
    const nom = button.getAttribute('data-nom');
    const prenom = button.getAttribute('data-prenom');
    const deleteUrl = button.getAttribute('data-url');

    // Mettre à jour le texte de la modale
    const modalText = confirmationModal.querySelector('#modalText');
    modalText.textContent = `Êtes-vous certain de vouloir supprimer l'apprenti ${prenom} ${nom} ?`;

    const deleteForm = confirmationModal.querySelector('#deleteForm');
    deleteForm.setAttribute('action', deleteUrl);
});
}
});


<!-- Filtrage du tableau des apprentis -->
    document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('searchApprenti');
    const tableBody = document.querySelector('#apprenti table tbody');
    const dataRows = tableBody.querySelectorAll('tr.apprenti-row');
    const noResultsRow = document.getElementById('noResultsRow');
    let debounceTimer;

    const debounce = (func, delay) => {
    return function(...args) {
    clearTimeout(debounceTimer);
    debounceTimer = setTimeout(() => {
    func.apply(this, args);
}, delay);
};
};

    const filterTable = () => {
    const searchTerm = searchInput.value.toLowerCase();
    let visibleRowsCount = 0;

    dataRows.forEach(row => {
    const cells = row.getElementsByTagName('td');

    const nom = cells[0].textContent.toLowerCase();
    const prenom = cells[1].textContent.toLowerCase();
    const entreprise = cells[3].textContent.toLowerCase();
    const mission = cells[4].textContent.toLowerCase();

    if (nom.includes(searchTerm) ||
    prenom.includes(searchTerm) ||
    entreprise.includes(searchTerm) ||
    mission.includes(searchTerm))
{
    row.style.display = ''; // Affiche la ligne
    visibleRowsCount++;
} else {
    row.style.display = 'none'; // Cache la ligne
}
});

    if (visibleRowsCount === 0 && searchTerm !== '') {
    noResultsRow.style.display = 'table-row';
} else {
    noResultsRow.style.display = 'none';
}
};

    searchInput.addEventListener('keyup', debounce(filterTable, 300));
});