// =========================================
// AUTH.JS
// Login & Register
// =========================================

// ----------------------------
// LOGIN
// ----------------------------

const loginForm = document.getElementById("loginForm");

if (loginForm) {

    loginForm.addEventListener("submit", loginUser);

}

async function loginUser(event) {

    event.preventDefault();

    const username = document.getElementById("username").value;

    const password = document.getElementById("password").value;

    try {

        const response = await API.post("/api/auth/login", {

            username: username,

            password: password

        });

        const data = response.data;

        localStorage.setItem("token", data.token);
        localStorage.setItem("username", data.username);
        localStorage.setItem("role", data.role);

        alert(data.message);

        if (data.role === "GUEST") {

            window.location.href = "dashboard.html";

        }
        else if (data.role === "PROPERTY_OWNER") {

            window.location.href = "owner-dashboard.html";

        }
        else if (data.role === "HOSPITALITY_ADMIN") {

            window.location.href = "admin-dashboard.html";

        }
        else {

            alert("Unknown Role");

        }

    }
    catch (error) {

        console.log(error);

        alert("Invalid Username or Password");

    }

}

// ----------------------------
// REGISTER
// ----------------------------

const registerForm = document.getElementById("registerForm");

if (registerForm) {

    registerForm.addEventListener("submit", registerUser);

}

async function registerUser(event) {

    event.preventDefault();

    const user = {

        username: document.getElementById("username").value,

        password: document.getElementById("password").value,

        email: document.getElementById("email").value,

        fullName: document.getElementById("fullName").value,

        role: document.getElementById("role").value

    };

    try {

        const response = await API.post("/api/auth/register", user);

        alert(response.data.message);

        window.location.href = "login.html";

    }

    catch (error) {

        console.log(error);

        alert("Registration Failed");

    }

}

// ----------------------------
// CHECK LOGIN
// ----------------------------

function checkLogin() {

    const token = localStorage.getItem("token");

    if (!token) {

        window.location.href = "login.html";

    }

}

// ----------------------------
// GET ROLE
// ----------------------------

function getRole() {

    return localStorage.getItem("role");
if (data.role === "GUEST") {

    window.location.href = "dashboard.html";

}
else if (data.role === "PROPERTY_OWNER") {

    window.location.href = "owner-dashboard.html";

}
else if (data.role === "HOSPITALITY_ADMIN") {

    window.location.href = "admin-dashboard.html";

}
}

// ----------------------------
// GET USERNAME
// ----------------------------

function getUsername() {

    return localStorage.getItem("username");

}
