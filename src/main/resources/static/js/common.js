async function logout() {
    try {
        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeaderName = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        const response = await fetch('/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                [csrfHeaderName]: csrfToken
            }
        });

        if (response.ok) {
            window.location.href = '/login';
        } else {
            alert('Logout failed');
            console.error('Logout failed:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}