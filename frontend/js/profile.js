// ======================================
// PROFILE.JS
// ======================================

document.addEventListener("DOMContentLoaded", () => {

    loadProfile();

});

// ======================================
// Load Profile
// ======================================

function loadProfile() {

    document.getElementById("username").value =
        localStorage.getItem("username") || "";

    document.getElementById("role").value =
        localStorage.getItem("role") || "";

    document.getElementById("fullName").value =
        localStorage.getItem("fullName") || "";

    document.getElementById("email").value =
        localStorage.getItem("email") || "";

    document.getElementById("phone").value =
        localStorage.getItem("phone") || "";

}

// ======================================
// Save Profile (Local Only)
// ======================================

const profileForm = document.getElementById("profileForm");

if (profileForm) {

    profileForm.addEventListener("submit", updateProfile);

}

function updateProfile(event) {

    event.preventDefault();

    localStorage.setItem(
        "fullName",
        document.getElementById("fullName").value
    );

    localStorage.setItem(
        "email",
        document.getElementById("email").value
    );

    localStorage.setItem(
        "phone",
        document.getElementById("phone").value
    );

    alert("Profile Updated Successfully");

}