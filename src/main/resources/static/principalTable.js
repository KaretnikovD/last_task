
var headerBar = document.querySelector('#headerBar');
var principalTable = document.querySelector('#principalTab');


var getPrincipal = () => {
    fetch("/rest/user").then((response) => response.json()).then((data) => {


        principal = data;
        // var roles = [];
        var roles = principal.roles.map(role => role.name.replace(/ROLE_/, ""));
        roles = roles.toString().replaceAll(',', ' ');


        var tableTemp = "";
        headerBar.innerHTML = `${principal.email} with roles: ${roles}`;

        tableTemp += `
<tr data-id="${principal.id}" class="principal">
<td>${principal.id}</td>
<td>${principal.firstName}</td>
<td>${principal.lastName}</td>
<td>${principal.age}</td>
<td>${principal.email}</td>
<td>${roles}</td>
</tr>
`;
        principalTable.innerHTML = tableTemp;
    })
}

getPrincipal();


