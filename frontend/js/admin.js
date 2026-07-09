document.addEventListener("DOMContentLoaded", () => {

    showProperties();

});

// =============================

async function showProperties() {

    try {

        const response = await API.get("/api/listings");

        let html = "";

        response.data.forEach(property => {

            html += `

            <div class="property-card">

                <h2>${property.title}</h2>

                <p>${property.address}</p>

                <p>${property.description}</p>

                <p>

                    ₹${property.basePrice}

                </p>

                <p>

                    Status :

                    <b>${property.status}</b>

                </p>

                <button onclick="approveProperty(${property.id})">

                    Approve

                </button>

                <button onclick="rejectProperty(${property.id})">

                    Reject

                </button>

            </div>

            `;

        });

        document.getElementById("adminContainer").innerHTML = html;

    }

    catch(error){

        console.log(error);

    }

}
async function approveProperty(id){

    try{

        await API.patch(

            "/api/listings/"+id+"/status?status=APPROVED"

        );

        alert("Property Approved");

        showProperties();

    }

    catch(error){

        alert("Unable to Approve");

    }

}

// ===========================

async function rejectProperty(id){

    try{

        await API.patch(

            "/api/listings/"+id+"/status?status=REJECTED"

        );

        alert("Property Rejected");

        showProperties();

    }

    catch(error){

        alert("Unable to Reject");

    }

}
// =============================
// SHOW BOOKINGS
// =============================

async function showBookings(){

    try{

        const response = await API.get("/api/bookings");

        let html = "";

        response.data.forEach(booking=>{

            html += `

            <div class="property-card">

                <h3>

                    Booking #${booking.id}

                </h3>

                <p>

                    Guest :
                    ${booking.guest.fullName}

                </p>

                <p>

                    Property :
                    ${booking.listing.title}

                </p>

                <p>

                    Check In :
                    ${booking.checkInDate}

                </p>

                <p>

                    Check Out :
                    ${booking.checkOutDate}

                </p>

                <button
                onclick="deleteBooking(${booking.id})">

                Delete

                </button>

            </div>

            `;

        });

        document.getElementById("adminContainer").innerHTML = html;

    }

    catch(error){

        console.log(error);

        alert("Unable to Load Bookings");

    }

}
// =============================
// DELETE BOOKING
// =============================

async function deleteBooking(id){

    if(!confirm("Delete Booking?")){

        return;

    }

    try{

        await API.delete("/api/bookings/"+id);

        alert("Booking Deleted");

        showBookings();

    }

    catch(error){

        console.log(error);

        alert("Unable to Delete");

    }

}