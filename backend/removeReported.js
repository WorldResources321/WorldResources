const getReported = require('./__mock__/getReported');
 
function removeReported(toBeRemoved) {
    var mockReported = [];
    mockReported = getReported();
    if (toBeRemoved == null) { //no email specified
        return mockReported;
    }
    var indexToBeRemoved = mockReported.indexOf(toBeRemoved);
    console.log(indexToBeRemoved);
    if (indexToBeRemoved > -1) { //if given gmail found within reported list
        mockReported.splice(indexToBeRemoved, 1); //remove the reported user with the given email from reported list
    }
    console.log(mockReported);
    return mockReported;
}
module.exports = removeReported;
