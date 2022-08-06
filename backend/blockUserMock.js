const getAllUsers = require('./getAllUsers')
const getBlockedUsers = require('./getBlockedUsers')

//save user to database if user is "valid" and return 0
function blockUser (user) {

    var mockAllUsers = getAllUsers();
    var mockBlockedUsers = getBlockedUsers();

    if (user === "") { //user given is empty
        return {status: 400, message: "user unspecified"};
    }
    else if (mockAllUsers.indexOf(user) == -1) { //invalid user
        return {status: 404, message: "user does not exist"};
    }
    else if (mockBlockedUsers.indexOf(user) > -1) { //user is already blocked
        return {status: 400, message: "user is already blocked"};
    }
    else { //valid user
        return {status: 200, message: "user blocked"};
    }

}
module.exports = blockUser;
