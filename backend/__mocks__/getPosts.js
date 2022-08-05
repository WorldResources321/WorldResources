function getPosts() {
    const newPost = {
        "content": "abcde",
        "author": "joe"
    }
    const newPost2 = {
        "content": "aaaa",
        "author": "alex"
    }
    var posts = [];
    posts.push(newPost);
    posts.push(newPost2);
    return posts;
}
module.exports = getPosts;