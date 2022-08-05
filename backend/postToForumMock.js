const getAllUsers = require('./getAllUsers')
const getBlockedUsers = require('./getBlockedUsers')

function postToForum (content, author) {

    var mockAllUsers = getAllUsers();
    var mockBlockedUsers = getBlockedUsers();

    if (content === "" || author === "") { //content or author is empty
        return  {status: 400, message: "content or author unspecified"};
    }
    else if (mockBlockedUsers.indexOf(author) > -1) { //author is blocked
        return {status: 400, message: "author is blocked from posting"};
    }
    else if (mockAllUsers.indexOf(author) == -1) { //invalid user
        return {status: 400, message: "author is not a signed-in user"};
    }
    else { //valid post
        return {status: 200, message: "post saved to database"};
    }

}
module.exports = postToForum;