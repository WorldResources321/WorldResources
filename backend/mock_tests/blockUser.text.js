const blockUser = require('../blockUser')

jest.mock("../getAllUsers.js")
jest.mock("../getBlockedUsers.js")

describe('mocking user database', () => {

    var resultInt1 = blockUser("")
    test('correct output when user given is empty', () => {
        expect (resultInt1).toBe(-1);
    });

    var resultInt2 = blockUser("dne@gmail.com")
    test('correct output when user given does not exist', () => {
        expect (resultInt2).toBe(-1);
    });

    var resultInt3 = blockUser("blocked111@gmail.com")
    test('correct output when user given is already blocked', () => {
        expect (resultInt3).toBe(-1);
    });

    var resultInt4 = blockUser("aaa@gmail.com")
    test('correct output when user given is valid', () => {
        expect (resultInt4).toBe(0);
    });

});
