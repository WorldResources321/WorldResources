//mocked (stores all users)

function getAllUsers() {
    var allUsers = [];
    allUsers.push("aaa@gmail.com");
    allUsers.push("ccc@gmail.com");
    allUsers.push("blocked111@gmail.com");
    allUsers.push("blocked222@gmail.com");
    allUsers.push("blocked333@gmail.com");
    return allUsers;
}
module.exports = getAllUsers;