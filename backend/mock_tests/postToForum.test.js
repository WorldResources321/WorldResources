const postToForum = require('../postToForumMock')

jest.mock("../getAllUsers.js")
jest.mock("../getBlockedUsers.js")

describe('mocking user database', () => {

    var resultInt1 = postToForum("", "aaa@gmail.com")
    test('correct output when content is empty', () => {
        expect (resultInt1).toMatchObject({status: 400, message: "content or author unspecified"});
    });

    var resultInt2 = postToForum("blahblahblah", "")
    test('correct output when author is empty', () => {
        expect (resultInt2).toMatchObject({status: 400, message: "content or author unspecified"});
    });

    var resultInt3 = postToForum("blahblah", "blocked222@gmail.com")
    test('correct output when author is blocked from posting', () => {
        expect (resultInt3).toMatchObject({status: 400, message: "author is blocked from posting"});
    });

    var resultInt4 = postToForum("blahhhh", "notrealuser@gmail.com")
    test('correct output when author is not registered in database', () => {
        expect (resultInt4).toStrictEqual({status: 404, message: "author is not a signed-in user"});
    });

    var resultInt5 = postToForum("blahhhh", "ccc@gmail.com")
    test('correct output when content and author are valid', () => {
        expect (resultInt5).toMatchObject({status: 200, message: "post saved to database"});
    });

});
