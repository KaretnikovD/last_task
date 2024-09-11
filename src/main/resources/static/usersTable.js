var users = [];

var usersTable = document.querySelector("#users");
var userModal = document.querySelector('#userModal');
var userModalTitle = userModal.querySelector('.modal-title');

var userModalEditBtn = userModal.querySelector('.modal-footer .edit');
var userModalDeleteBtn = userModal.querySelector('.modal-footer .delete');

var addUserForm = document.getElementById('addUserForm');

var fetchRoles = () => {
    fetch("/rest/roles").then((response) => response.json()).then((data) => {
        var rolesSelects = document.querySelectorAll('select[name=roles]');
        data.forEach((r) => {
            let el = document.createElement("option");
            el.text = r.readableName;
            el.value = r.id;
            el.dataset.role = JSON.stringify(r);
            rolesSelects.forEach(sel => sel.appendChild(el.cloneNode(true)));
        })
    });
};


var fetchUsers = () => {
    fetch("/rest/users").then((response) => response.json()).then((data) => {

        users = data;
        var temp = "";
        data.forEach((u) => {
            var roles = u.roles.map(role => role.name.replace(/ROLE_/, ""));
            roles = roles.toString().replaceAll(',', ' ');


            temp += `
                <tr data-id="${u.id}" class="user">
                    <td>${u.id}</td>
                    <td>${u.firstName}</td>
                    <td>${u.lastName}</td>
                    <td>${u.age}</td>
                    <td>${u.email}</td>
                    <td>${roles}</td>
                    <td><button class='edit btn btn-info'>Edit</button></td>
                    <td><button class='delete btn btn-danger'>Delete</button></td>
                </tr>
            `;

        })
        usersTable.innerHTML = temp;
    });
};



var showUserModal = function (user_id, type) {


    const user = users.find(u => u.id == user_id);


    if (type == 'DELETE') userModalTitle.textContent = "Delete user";
    if (type == 'EDIT') userModalTitle.textContent = "Edit user";



    userModalDeleteBtn.hidden = type != 'DELETE';
    userModalEditBtn.hidden = type != 'EDIT';


    userModal.querySelectorAll('.modal-body [name]').forEach((el) => {
        el.disabled = type == 'DELETE';
    })


    Object.keys(user).forEach((key) => {
        let input = userModal.querySelector(`input[name=${key}]`);
        if (input) input.value = user[key];
    })


    userModal.querySelector('input[name=password]').value = "";

    userModal.querySelector('input[name=id]').disabled = true;
    userModal.querySelectorAll('input');
    $('#userModal').modal('show');
}


const takeRoles = function (form) {
    return [...form.querySelector('select[name="roles"]').selectedOptions].map(option => JSON.parse(option.dataset.role));
}

//                     Add user
addUserForm.addEventListener('submit', (e) => {
    e.preventDefault();
    var formData = new FormData(addUserForm);
    var data = Object.fromEntries(formData.entries());
    data["roles"] = takeRoles(addUserForm);
    fetch(`/rest/users`, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
            , Accept: "application/json"
        }

    })
        .then(response => {
            if (response.ok) {
                fetchUsers();
                document.getElementById('nav-home-tab').click();
            } else {
                alert("Add method problems");
            }
        })

});


// delete modal
userModalDeleteBtn.addEventListener('click', (e) => {
    var user_id = userModal.querySelector('[name=id]').value;
    fetch(`/rest/users/${user_id}`, { method: 'DELETE' })
        .then(response => {
            if (response.ok) {
                fetchUsers();
                $('#userModal').modal('hide');
            } else {
                alert("Delete method problems");
            }
        });
});


// edit modal
userModalEditBtn.addEventListener('click', (e) => {
    var user_id = userModal.querySelector('[name=id]').value;
    var formData = new FormData(userModal.querySelector('form'));

    formData.append('id', user_id);
    var data = Object.fromEntries(formData.entries());
    data["roles"] = takeRoles(userModal.querySelector('form'));

    fetch(`/rest/users/${user_id}`, {
        method: 'PUT',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                // fetchUsers();
                $('#userModal').modal('hide');
            } else {
                alert("Update method problems");
            }
        });
});




usersTable.addEventListener('click', (e) => {
    let user = e.target.closest('.user');
    if (!user) return;
    let user_id = user.dataset.id;

    if (e.target.classList.contains('delete')) showUserModal(user_id, 'DELETE');
    if (e.target.classList.contains('edit')) showUserModal(user_id, 'EDIT');
})


fetchRoles();
fetchUsers();