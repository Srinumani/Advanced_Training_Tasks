// login.js

document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();

    // Get user input from the form
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Make the login API request
    fetch('/api/auth/signin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password })
    })
    .then(response => response.json())
    .then(data => {
        // Check if login was successful
        if (data.jwt) {
            // Store JWT token in localStorage
            localStorage.setItem('token', data.jwt);

            // Redirect to the dashboard page
            window.location.href = '/dashboard.html';
        } else {
            alert('Login failed! Please check your credentials.');
        }
    })
    .catch(error => {
        console.error('Error during login:', error);
        alert('An error occurred. Please try again.');
    });
});
