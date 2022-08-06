const reportUser = require('../reportUserMock')

jest.mock("../getAllUsers.js")
jest.mock("../getReported.js")

describe('mocking user database', () => {

    var resultInt1 = reportUser("")
    test('correct output when user given is empty', () => {
        expect (resultInt1).toMatchObject({status: 400, message: "user unspecified"});
    });

    var resultInt2 = reportUser("dne@gmail.com")
    test('correct output when user given does not exist', () => {
        expect (resultInt2).toMatchObject({status: 404, message: "user does not exist"});
    });

    var resultInt3 = reportUser("aaa@gmail.com")
    test('correct output when user given has already been reported', () => {
        expect (resultInt3).toMatchObject({status: 400, message: "user has already been reported"});
    });

    var resultInt4 = reportUser("ccc@gmail.com")
    test('correct output when user given is valid', () => {
        expect (resultInt4).toMatchObject({status: 200, message: "user reported"});
    });

});
