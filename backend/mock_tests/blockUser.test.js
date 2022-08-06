const blockUser = require('../blockUserMock')

jest.mock("../getAllUsers.js")
jest.mock("../getBlockedUsers.js")

describe('mocking user database', () => {

    var resultInt1 = blockUser("")
    test('correct output when user given is empty', () => {
        expect (resultInt1).toMatchObject({status: 400, message: "user unspecified"});
    });

    var resultInt2 = blockUser("dne@gmail.com")
    test('correct output when user given does not exist', () => {
        expect (resultInt2).toMatchObject({status: 404, message: "user does not exist"});
    });

    var resultInt3 = blockUser("blocked111@gmail.com")
    test('correct output when user given is already blocked', () => {
        expect (resultInt3).toMatchObject({status: 400, message: "user is already blocked"});
    });

    var resultInt4 = blockUser("aaa@gmail.com")
    test('correct output when user given is valid', () => {
        expect (resultInt4).toMatchObject({status: 200, message: "user blocked"});
    });

});
