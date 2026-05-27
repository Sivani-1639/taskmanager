const API_URL = "http://localhost:8080";

async function login() {

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {

        const response = await fetch(
            `${API_URL}/auth/login`,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                })
            }
        );

        const text = await response.text();

        if (response.ok) {
            alert("Login successful");
            window.location.href = "dashboard.html";
        } else {
            alert(text);
        }

    } catch(error) {
        console.log(error);
        alert("Backend server not running");
    }
}