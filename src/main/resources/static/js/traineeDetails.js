<!--snackbar -->
    document.addEventListener('DOMContentLoaded', function() {
    const snackbar = document.getElementById('snackbar');

    if (snackbar) {
    snackbar.className = 'show';

    setTimeout(function() {
    snackbar.className = snackbar.className.replace('show', '');
}, 5000);
}
});