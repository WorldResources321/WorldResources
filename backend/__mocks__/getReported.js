//mocked module (stores reported users)

function getReported() {
    var reportedUsers = [];
    reportedUsers.push("aaa@gmail.com");
    reportedUsers.push("bbb@gmail.com");
    reportedUsers.push("ccc@gmail.com");
    reportedUsers.push("ddd@gmail.com");
    reportedUsers.push("eee@gmail.com");
    reportedUsers.push("fff@gmail.com");
    reportedUsers.push("ggg@gmail.com");
    return reportedUsers;
}
module.exports = getReported;