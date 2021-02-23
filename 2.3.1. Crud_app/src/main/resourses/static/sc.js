$(document).ready(function() {
    getUsers();
});
function getUsers() {
    fetch('http://localhost:8080/admin/users', {method: "GET"}).then(
        res => {
            res.json().then(
                users => {
                    $("#tbodyID").empty();
                    let table = '';
                    for(let i = 0; i < users.length; i++) {
                        let roles = '';
                        for (let r of users[i].roles) {
                            roles += r.name + "; ";
                        }
                        table = $("<tr>").attr("id", users[i].id);
                        table.append("" +
                            "<td>" + users[i].id + "</td>" +
                            "<td>" + users[i].username + "</td>" +
                            "<td>" + users[i].password + "</td>" +
                            "<td>" + roles + "</td>" +
                            "<td><button onclick='getUser(" + users[i].id + ")' class='btn btn-info btn-sm' data-toggle='modal' data-target='#EditModal'>Edit</button></td>" +
                            "<td><button onclick='getUserDelete(" + users[i].id + ")' class='btn btn-danger btn-sm' data-toggle='modal' data-target='#DeleteModal'>Delete</button></td>"
                        );
                        $("#mainTable").append(table)
                    }
                }
            )
        }
    )
}
let allRoles = ["ROLE_ADMIN", "ROLE_USER"];
function getUser(id) {
    fetch(`http://localhost:8080/admin/user/${id}`, {method: "GET"}).then(
        res => {
            res.json().then(
                user => {
                    $(".modal-body #getId").val(user.id)
                    $(".modal-body #getUserName").val(user.username)
                    $(".modal-body #getPassword").val(user.password)
                    $(".modal-body #getRoles").empty();
                    $.each(allRoles, (i, role) => {
                        $("#getRoles").append(
                            $("<option>").text(role)
                        )
                    });
                    $("#getRoles").val(user.roles);
                    localStorage.setItem('idForUpdate', user.id)
                }
            )
        }
    )
}

function getUserDelete(id) {
    fetch(`http://localhost:8080/admin/user/${id}`, {method: "GET"}).then(
        res => {
            res.json().then(
                user => {
                    let roles = '';
                    for (let value of user.roles) {
                        roles += value.name + ";";
                    }
                    $(".modal-body #getIdDelete").val(user.id)
                    $(".modal-body #getUsernameDelete").val(user.username)
                    $(".modal-body #getPasswordDelete").val(user.password)
                    $(".modal-body #getRolesDelete").val(roles)
                    localStorage.setItem('idForDelete', user.id)
                }
            )
        }
    )
}

function updateUser(id) {
    event.preventDefault();
    let user = {
        username : document.getElementById('getUserName').value,
        password : document.getElementById('getPassword').value,
        roles : $('#getRoles').val()
    }
    fetch(`http://localhost:8080/admin/user/${id}`, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(user)
    }).then(
        () => {
            getUsers();
            $("#EditModal").modal('hide');
        }
    )
}

function deleteUser(id) {
    fetch(`http://localhost:8080/admin/user/${id}`, {method: "DELETE"}).then(
        $("#DeleteModal").modal('hide'),
        $("#tbodyID #" + id).remove()
    )
}

function addUser() {
    event.preventDefault();
    let user = {
        username : document.getElementById('addUserName').value,
        password : document.getElementById('addPassword').value,
        roles : $('#addRoles').val()
    }

    fetch('http://localhost:8080/admin/user/', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(user)
    }).then(
        () => {
            getUsers();
            $('#adminPage').tab('show');
        }
    )
}