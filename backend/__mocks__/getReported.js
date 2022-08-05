//mocked module (stores reported users)

function getReported() {
    var reportedUsers = [];
    reportedUsers.push("aaa@gmail.com");
    return reportedUsers;
}
module.exports = getReported;