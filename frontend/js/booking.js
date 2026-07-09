// =========================================
// BOOKING.JS
// =========================================

// Load bookings when page opens
document.addEventListener("DOMContentLoaded", () => {

    if (document.getElementById("bookingContainer")) {

        loadBookings();

    }

});

// =========================================
// Load All Bookings
// =========================================

async function loadBookings() {

    try {

        const response = await API.get("/api/bookings");

        displayBookings(response.data);

    }

    catch (error) {

        console.log(error);

        alert("Unable to Load Bookings");

    }

}

// =========================================
// Display Bookings
// =========================================

function displayBookings(bookings) {

    const container = document.getElementById("bookingContainer");

    container.innerHTML = "";

    if (bookings.length === 0) {

        container.innerHTML = `

            <div class="property-card">

                <h2>No Bookings Available</h2>

            </div>

        `;

        return;

    }

    bookings.forEach(booking => {

        container.innerHTML += `

        <div class="property-card">

            <h2>${booking.listing.title}</h2>

            <p>

                <strong>Guest :</strong>

                ${booking.guest.fullName}

            </p>

            <p>

                <strong>Check In :</strong>

                ${booking.checkInDate}

            </p>

            <p>

                <strong>Check Out :</strong>

                ${booking.checkOutDate}

            </p>

            <p>

                <strong>Total Price :</strong>

                ₹${booking.totalPrice}

            </p>

            <p>

                <strong>Status :</strong>

                ${booking.status}

            </p>

            <button onclick="cancelBooking(${booking.id})">

                Cancel Booking

            </button>

        </div>

        `;

    });

}

// =========================================
// Cancel Booking
// =========================================

async function cancelBooking(id) {

    const confirmCancel = confirm("Are you sure you want to cancel this booking?");

    if (!confirmCancel) {

        return;

    }

    try {

        await API.post("/api/bookings/" + id + "/cancel");

        alert("Booking Cancelled Successfully");

        loadBookings();

    }

    catch (error) {

        console.log(error);

        alert("Unable to Cancel Booking");

    }

}

// =========================================
// Get Booking By ID
// =========================================

async function getBookingById(id) {

    try {

        const response = await API.get("/api/bookings/" + id);

        return response.data;

    }

    catch (error) {

        console.log(error);

    }

}

// =========================================
// Update Booking
// =========================================

async function updateBooking(id, bookingData) {

    try {

        await API.put("/api/bookings/" + id, bookingData);

        alert("Booking Updated Successfully");

        loadBookings();

    }

    catch (error) {

        console.log(error);

        alert("Unable to Update Booking");

    }

}

// =========================================
// Confirm Booking
// (Property Owner)
// =========================================

async function confirmBooking(id) {

    try {

        await API.post("/api/bookings/" + id + "/confirm");

        alert("Booking Confirmed");

        loadBookings();

    }

    catch (error) {

        console.log(error);

        alert("Unable to Confirm Booking");

    }

}

// =========================================
// Check In
// =========================================

async function checkIn(id) {

    try {

        await API.post("/api/bookings/" + id + "/check-in");

        alert("Guest Checked In");

        loadBookings();

    }

    catch (error) {

        console.log(error);

        alert("Unable to Check In");

    }

}

// =========================================
// Check Out
// =========================================

async function checkOut(id) {

    try {

        await API.post("/api/bookings/" + id + "/check-out");

        alert("Guest Checked Out");

        loadBookings();

    }

    catch (error) {

        console.log(error);

        alert("Unable to Check Out");

    }

}

// =========================================
// Delete Booking
// (Hospitality Admin)
// =========================================

async function deleteBooking(id) {

    const confirmDelete = confirm("Delete this booking?");

    if (!confirmDelete) {

        return;

    }

    try {

        await API.delete("/api/bookings/" + id);

        alert("Booking Deleted Successfully");

        loadBookings();

    }

    catch (error) {

        console.log(error);

        alert("Unable to Delete Booking");

    }

}