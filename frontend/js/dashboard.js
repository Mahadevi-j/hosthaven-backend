document.addEventListener("DOMContentLoaded",()=>{

loadProperties();

});

async function loadProperties(){

try{

const response=await API.get("/api/listings");

displayProperties(response.data);

}

catch(error){

alert("Unable to Load Properties");

}

}

function displayProperties(properties){

const container=document.getElementById("propertyContainer");

container.innerHTML="";

properties.forEach(property=>{

container.innerHTML+=`

<div class="property-card">

<h2>${property.title}</h2>

<p>

<b>Address :</b>

${property.address}

</p>

<p>

<b>Price :</b>

₹${property.basePrice}

</p>

<p>

${property.description}

</p>

<button onclick="viewProperty(${property.id})">

View Details

</button>

</div>

`;

});

}

function viewProperty(id){

localStorage.setItem("propertyId",id);

window.location.href="property-details.html";

}

async function searchProperty(){

const keyword=document.getElementById("search").value.toLowerCase();

const response=await API.get("/api/listings");

const properties=response.data.filter(p=>

p.title.toLowerCase().includes(keyword) ||

p.address.toLowerCase().includes(keyword)

);

displayProperties(properties);

}