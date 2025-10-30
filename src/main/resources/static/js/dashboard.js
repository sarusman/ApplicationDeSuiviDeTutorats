function showTab(tabId, buttonID) {
    const tabs = document.querySelectorAll('.tab');
    tabs.forEach(tab => tab.classList.remove('active'));

    const buttons = document.querySelectorAll('.button');
    buttons.forEach(button => button.classList.remove('active'));

    const targetTab = document.getElementById(tabId);
    if (targetTab) {
        targetTab.classList.add('active');
    }

    const targetButton = document.getElementById(buttonID);
    if (targetButton) {
        targetButton.classList.add('active');
    }
}

function rowClicked(value) {
    location.href = value;
}

document.addEventListener('DOMContentLoaded', function () {
    const entrepriseSelect = document.getElementById('entrepriseSelect');
    const tuteurSelect = document.getElementById('tuteurSelect');

    if (entrepriseSelect && tuteurSelect) {
        entrepriseSelect.addEventListener('change', function () {
            const entrepriseId = this.value;

            tuteurSelect.innerHTML = '<option value="">-- Choisir une entreprise d\'abord --</option>';
            tuteurSelect.disabled = true;

            if (entrepriseId) {
                const url = `/api/tuteurs/by-entreprise/${entrepriseId}`;

                fetch(url)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error("Réponse réseau invalide");
                        }
                        return response.json();
                    })
                    .then(tuteurs => {
                        tuteurSelect.innerHTML = '<option value="">Choisir un tuteur...</option>';

                        tuteurs.forEach(tuteur => {
                            const option = document.createElement('option');
                            option.value = tuteur.id;
                            option.textContent = tuteur.prenom + ' ' + tuteur.nom;
                            tuteurSelect.appendChild(option);
                        });

                        tuteurSelect.disabled = false;
                    })
                    .catch(error => {
                        console.error('Erreur lors de la récupération des tuteurs:', error);
                        tuteurSelect.innerHTML = '<option value="">Erreur de chargement</option>';
                    });
            }
        });
    }

    const confirmationModal = document.getElementById('confirmationSuppressionModal');
    if (confirmationModal) {
        confirmationModal.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;

            const nom = button.getAttribute('data-nom');
            const prenom = button.getAttribute('data-prenom');
            const deleteUrl = button.getAttribute('data-url');

            const modalText = confirmationModal.querySelector('#modalText');
            modalText.textContent = `Êtes-vous certain de vouloir supprimer l'apprenti ${prenom} ${nom} ?`;

            const deleteForm = confirmationModal.querySelector('#deleteForm');
            deleteForm.setAttribute('action', deleteUrl);
        });
    }

    const inputNomPrenom = document.getElementById('filterNomPrenom');
    const inputEntreprise = document.getElementById('filterEntreprise');
    const inputMission = document.getElementById('filterMission');
    const inputAnnee = document.getElementById('filterAnnee');
    const resetBtn = document.getElementById('resetFiltersBtn');

    const dataRows = Array.from(document.querySelectorAll('.apprenti-row'));
    const noResultsRow = document.getElementById('noResultsRow');

    function normalise(str) {
        return (str || '')
            .toString()
            .trim()
            .toLowerCase();
    }

    function applyFilters() {
        const fNom = inputNomPrenom ? normalise(inputNomPrenom.value) : '';
        const fEnt = inputEntreprise ? normalise(inputEntreprise.value) : '';
        const fMission = inputMission ? normalise(inputMission.value) : '';
        const fAnnee = inputAnnee ? normalise(inputAnnee.value) : '';

        let visibleCount = 0;

        dataRows.forEach(row => {
            const valueNomPrenom = normalise(row.getAttribute('data-fullname'));
            const valueEntreprise = normalise(row.getAttribute('data-entreprise'));
            const valueMission = normalise(row.getAttribute('data-mission'));
            const valueAnnee = normalise(row.getAttribute('data-annee'));

            const matchNom = !fNom || valueNomPrenom.includes(fNom);
            const matchEnt = !fEnt || valueEntreprise.includes(fEnt);
            const matchMissionOk = !fMission || valueMission.includes(fMission);
            const matchAnnee = !fAnnee || valueAnnee.includes(fAnnee);

            const matchAll = matchNom && matchEnt && matchMissionOk && matchAnnee;

            row.style.display = matchAll ? '' : 'none';

            if (matchAll) {
                visibleCount++;
            }
        });

        if (noResultsRow) {
            noResultsRow.style.display = (visibleCount === 0 ? '' : 'none');
        }
    }

    function resetFilters() {
        if (inputNomPrenom) inputNomPrenom.value = '';
        if (inputEntreprise) inputEntreprise.value = '';
        if (inputMission) inputMission.value = '';
        if (inputAnnee) inputAnnee.value = '';
        applyFilters();
    }

    if (inputNomPrenom) inputNomPrenom.addEventListener('input', applyFilters);
    if (inputEntreprise) inputEntreprise.addEventListener('input', applyFilters);
    if (inputMission) inputMission.addEventListener('input', applyFilters);
    if (inputAnnee) inputAnnee.addEventListener('input', applyFilters);
    if (resetBtn) resetBtn.addEventListener('click', resetFilters);

    const searchInput = document.getElementById('searchApprenti');
    if (searchInput) {
        let debounceTimer;

        const debounce = (func, delay) => {
            return function (...args) {
                clearTimeout(debounceTimer);
                debounceTimer = setTimeout(() => {
                    func.apply(this, args);
                }, delay);
            };
        };

        const filterLegacy = () => {
            const term = searchInput.value.toLowerCase();
            let visibleRowsCount = 0;

            dataRows.forEach(row => {
                const cells = row.getElementsByTagName('td');
                const nom = (cells[0]?.textContent || '').toLowerCase();
                const prenom = (cells[1]?.textContent || '').toLowerCase();
                const entreprise = (cells[3]?.textContent || '').toLowerCase();
                const mission = (cells[4]?.textContent || '').toLowerCase();

                const match =
                    nom.includes(term) ||
                    prenom.includes(term) ||
                    entreprise.includes(term) ||
                    mission.includes(term);

                row.style.display = match ? '' : 'none';
                if (match) visibleRowsCount++;
            });

            if (noResultsRow) {
                noResultsRow.style.display = (visibleRowsCount === 0 && term !== '') ? '' : 'none';
            }
        };

        searchInput.addEventListener('keyup', debounce(filterLegacy, 300));
    }

    applyFilters();
});
