const request = require("supertest");
const app = require("../getPostsNoMock");

describe('get posts', () => {

    const {MongoClient} = require("mongodb")
    const uri = "mongodb://localhost:27017"
    const client = new MongoClient(uri)
    
    beforeAll(async () => {
        try {
            await client.connect()
            client.db("forum").collection("posts").drop();
        }
        catch (err) {
            console.log(err)
            await client.close()
        }
    })
    
    afterAll(async() => {
        await client.close()    
    })

    it('empty forum', async() => {
        await request(app)
            .get('/getPosts')
            .expect(404);

    });

    it('non-empty forum', async() => {

        await request(app)
            .post('/addUser')
            .send({email: 'user1@gmail.com'});

        await request(app)
            .post('/postToForum')
            .send({content: 'balahblah', author: 'user1@gmail.com'});

        await request(app)
            .get('/getPosts')
            .expect(200);

    });

});