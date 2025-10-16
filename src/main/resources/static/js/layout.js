document.addEventListener('DOMContentLoaded', function () {
    const toggleBtn = document.getElementById('toggle-btn');
    const sidebar = document.getElementById('sidebar');
    const mainContent = document.getElementById('main-content');

    toggleBtn.addEventListener('click', function () {
        sidebar.classList.toggle('retracted');
        mainContent.classList.toggle('retracted');
    });
});