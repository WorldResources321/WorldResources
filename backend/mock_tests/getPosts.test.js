const getPosts = require('../getPostsMock')

jest.mock("../getPosts.js")

const getPostsNonEmpty = require('../__mocks__/getPosts')

describe('mocking posts database', () => {
    var resultInt1 = getPosts(0)
    test('correct output when there are no posts', () => {
        expect (resultInt1).toMatchObject({status: 400, message: "no posts yet"});
    });

    var mockPosts = getPostsNonEmpty();
    var resultInt2 = getPosts(1)
    test('correct output when there are posts', () => {
        expect (resultInt2).toMatchObject(mockPosts);
    });
});