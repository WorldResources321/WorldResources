const getAllUsers = require('./getAllUsers')
const getReported= require('./getReported')

//save user to database if user is "valid" and return 0
function reportUser (user) {

    var mockAllUsers = getAllUsers();
    var mockReported = getReported();

    if (user === "") { //user given is empty
        return {status: 400, message: "user unspecified"};
    }
    else if (mockAllUsers.indexOf(user) == -1) { //invalid user
        return {status: 400, message: "user does not exist"};
    }
    else if (mockReported.indexOf(user) > -1) { //user has already been reported
        return {status: 400, message: "user has already been reported"};
    }
    else { //valid user
        return {status: 200, message: "user reported"};
    }

}
module.exports = reportUser;