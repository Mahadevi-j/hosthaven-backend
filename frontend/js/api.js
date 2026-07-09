// ===============================
// API Configuration
// ===============================

const BASE_URL = "http://localhost:8080";

const API = axios.create({

    baseURL: BASE_URL,

    headers: {
        "Content-Type": "application/json"
    }

});

// ===============================
// Automatically attach JWT Token
// ===============================

API.interceptors.request.use(

    function (config) {

        const token = localStorage.getItem("token");

        if (token) {

            config.headers.Authorization = "Bearer " + token;

        }

        return config;

    },

    function (error) {

        return Promise.reject(error);

    }

);

// ===============================
// Logout if Token Expired
// ===============================

API.interceptors.response.use(

    function (response) {

        return response;

    },

    function (error) {

        if (error.response) {

            if (error.response.status === 401) {

                alert("Session Expired");

                localStorage.clear();

                window.location.href = "login.html";

            }

            if (error.response.status === 403) {

                alert("Access Denied");

            }

        }

        return Promise.reject(error);

    }

);

// ===============================
// Logout Function
// ===============================

function logout() {

    localStorage.clear();

    alert("Logged Out Successfully");

    window.location.href = "login.html";

}