const getPostsNonEmpty = require('./getPosts')

function getPosts (number) {

    var mockPosts = getPostsNonEmpty();

    if (number == 0) { //no posts
        return  {status: 400, message: "no posts yet"};
    }
    else { //
        return mockPosts;
    }

}
module.exports = getPosts;