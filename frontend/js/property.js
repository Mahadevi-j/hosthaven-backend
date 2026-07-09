// ===============================
// PROPERTY.JS - PART 1
// ===============================

// Page Initialization
document.addEventListener("DOMContentLoaded", () => {

    // Properties Page
    if (document.getElementById("propertyContainer")) {
        loadProperties();
    }

    // Property Details Page
    if (document.getElementById("bookingForm")) {

        loadProperty();

        document
            .getElementById("bookingForm")
            .addEventListener("submit", bookProperty);
    }

    // Owner Dashboard
   if (document.getElementById("ownerProperties")) {

    const ownerId = localStorage.getItem("ownerId");

    if (ownerId) {

        loadOwnerProperties();

    }

    document
        .getElementById("propertyForm")
        .addEventListener("submit", saveProperty);

    document
        .getElementById("showFormBtn")
        .addEventListener("click", () => {

            document
                .getElementById("propertyFormContainer")
                .style.display = "block";

        });

}

});

function saveGuestId(){

    const guestId=document.getElementById("guestId").value;

    if(guestId===""){

        alert("Enter Guest ID");

        return;

    }

    localStorage.setItem("guestId",guestId);

    alert("Guest ID Saved");

    loadProperties();

}
// ===============================
// Load All Properties
// ===============================

async function loadProperties() {

    try {

        const response = await API.get("/api/listings");

        displayProperties(response.data);

    }

    catch (error) {

        console.log(error);

        alert("Unable to load properties.");

    }

}


// ===============================
// Display Property Cards
// ===============================

function displayProperties(properties) {

    const container = document.getElementById("propertyContainer");

    if (!container) return;

    container.innerHTML = "";

    properties.forEach(property => {

        container.innerHTML += `

        <div class="property-card">

            <h2>${property.title}</h2>

            <p>

                <b>Address :</b>

                ${property.address}

            </p>

            <p>

                ${property.description}

            </p>

            <p>

                <b>Benefits :</b>

                ${property.benefits}

            </p>

            <h3>

                ₹${property.basePrice}

            </h3>

            <button onclick="viewProperty(${property.id})">

                View Details

            </button>

        </div>

        `;

    });

}


// ===============================
// View Property
// ===============================

function viewProperty(id) {

    localStorage.setItem("propertyId", id);

    window.location.href = "property-details.html";

}


// ===============================
// Load Property Details
// ===============================
async function loadProperty() {

    const id = localStorage.getItem("propertyId");

    if (!id) return;

    try {

        const response = await API.get("/api/listings/" + id);

        const property = response.data;

        // Show booking form only for Guests
        const role = localStorage.getItem("role");

        if (role !== "GUEST") {

            document.getElementById("bookingForm").style.display = "none";

        }

        document.getElementById("title").innerHTML = property.title;

        document.getElementById("description").innerHTML = property.description;

        document.getElementById("address").innerHTML = property.address;

        document.getElementById("price").innerHTML = "₹ " + property.basePrice;

    }

    catch (error) {

        console.log(error);

    }

}
//==================================
// Book Property
// ===============================

async function bookProperty(event) {
    alert("Book Property Function Called");

    event.preventDefault();

    const propertyId = localStorage.getItem("propertyId");

    const guestId = localStorage.getItem("guestId");

    if (!guestId) {

        alert("Please enter and save your Guest ID first.");

        return;

    }

    const booking = {

        listingId: Number(propertyId),

        guestId: Number(guestId),

        checkInDate: document.getElementById("checkInDate").value,

        checkOutDate: document.getElementById("checkOutDate").value,

        totalPrice: Number(
            document.getElementById("price")
                .innerText
                .replace("₹", "")
                .trim()
        )

    };

    try {

        await API.post("/api/bookings", booking);

        alert("Booking Successful");

        window.location.href = "bookings.html";

    }

    catch (error) {

        console.log(error);

        alert("Booking Failed");

    }

}


// ===============================
// Save Owner ID
// ===============================

function saveOwnerId() {

    const ownerId = document.getElementById("ownerId").value;

    if (ownerId === "") {

        alert("Please Enter Owner ID");

        return;

    }

    localStorage.setItem("ownerId", ownerId);

    alert("Owner ID Saved Successfully");

    loadOwnerProperties();

}



// ===============================
// Load Owner Properties
// ===============================

async function loadOwnerProperties() {

    const ownerId = localStorage.getItem("ownerId");

    if (ownerId == null) {

        return;

    }

    try {

        const response = await API.get(

            "/api/listings/owner/" + ownerId

        );

        displayOwnerProperties(response.data);

    }

    catch (error) {

        console.log(error);

    }

}
// ===============================
// PROPERTY.JS - PART 3
// Owner Property Management
// ===============================

let editingId = null;

// ===============================
// Save Property
// ===============================

async function saveProperty(event) {

    event.preventDefault();

    const ownerId = localStorage.getItem("ownerId");

    if (!ownerId) {

        alert("Please save your Owner ID first.");

        return;

    }

    const property = {

        ownerId: Number(ownerId),

        title: document.getElementById("title").value,

        description: document.getElementById("description").value,

        address: document.getElementById("address").value,

        basePrice: Number(document.getElementById("basePrice").value),

        benefits: document.getElementById("benefits").value

    };

    try {

        if (editingId == null) {

            await API.post("/api/listings", property);

            alert("Property Added Successfully");

        } else {

            await API.put("/api/listings/" + editingId, property);

            alert("Property Updated Successfully");

            editingId = null;

        }

        document.getElementById("propertyForm").reset();

        document.getElementById("propertyFormContainer").style.display = "none";

        loadOwnerProperties();

    }

    catch (error) {

        console.log(error);

        alert("Unable to Save Property");

    }

}

// ===============================
// Display Owner Properties
// ===============================

function displayOwnerProperties(properties) {

    const container = document.getElementById("ownerProperties");

    if (!container) return;

    container.innerHTML = "";

    if (properties.length === 0) {

        container.innerHTML = "<h3>No Properties Found</h3>";

        return;

    }

    properties.forEach(property => {

        container.innerHTML += `

        <div class="property-card">

            <h2>${property.title}</h2>

            <p><b>Address:</b> ${property.address}</p>

            <p><b>Description:</b> ${property.description}</p>

            <p><b>Benefits:</b> ${property.benefits}</p>

            <p><b>Price:</b> ₹${property.basePrice}</p>

            <p><b>Status:</b> ${property.status}</p>

            <button onclick="editProperty(${property.id})">

                Edit

            </button>

            <button onclick="deleteProperty(${property.id})">

                Delete

            </button>

        </div>

        `;

    });

}

// ===============================
// Edit Property
// ===============================

async function editProperty(id) {

    try {

        const response = await API.get("/api/listings/" + id);

        const property = response.data;

        editingId = property.id;

        document.getElementById("propertyFormContainer").style.display = "block";

        document.getElementById("title").value = property.title;

        document.getElementById("description").value = property.description;

        document.getElementById("address").value = property.address;

        document.getElementById("basePrice").value = property.basePrice;

        document.getElementById("benefits").value = property.benefits;

        window.scrollTo({

            top: 0,

            behavior: "smooth"

        });

    }

    catch (error) {

        console.log(error);

        alert("Unable to Load Property");

    }

}

// ===============================
// Delete Property
// ===============================

async function deleteProperty(id) {

    const confirmDelete = confirm("Do you want to delete this property?");

    if (!confirmDelete) {

        return;

    }

    try {

        await API.delete("/api/listings/" + id);

        alert("Property Deleted Successfully");

        loadOwnerProperties();

    }

    catch (error) {

        console.log(error);

        alert("Unable to Delete Property");

    }

}
// ===============================
// Search Property
// ===============================

async function searchProperty(){

    const keyword = document
        .getElementById("searchInput")
        .value
        .toLowerCase();

    try{

        const response = await API.get("/api/listings");

        const result = response.data.filter(property =>

            property.title.toLowerCase().includes(keyword)

            ||

            property.address.toLowerCase().includes(keyword)

        );

        displayProperties(result);

    }

    catch(error){

        console.log(error);

    }

}