//mocked (stores blocked users)

function getBlockedUsers() {
    var blockedUsers = [];
    blockedUsers.push("blocked111@gmail.com");
    blockedUsers.push("blocked222@gmail.com");
    blockedUsers.push("blocked333@gmail.com");
    return blockedUsers;
}
module.exports = getBlockedUsers;