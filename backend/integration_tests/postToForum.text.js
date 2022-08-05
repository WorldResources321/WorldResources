const request = require("supertest");
const app = require("../postToForumNoMock");

const {MongoClient} = require("mongodb")
const uri = "mongodb://localhost:27017"
const client = new MongoClient(uri)

beforeAll(async () => {
    try {
        await client.connect()
    }
    catch (err) {
        console.log(err)
        await client.close()
    }
})

afterAll(async () => {
    await client.close()
})

describe('post to forum', () => {

    it('empty content', async() => {

        await request(app)
            .post('/posttoforum')
            .send({content: '', author: 'smith@gmail.com'})
            .expect(400);

    });

    it('blocked author', async() => {

        await request(app)
            .post('/posttoforum')
            .send({content: 'blahbalh', author: 'block@gmail.com'}) //have to manually add blocked user to local database
            .expect(400);

    });

    it('non-existing user', async() => {

        await request(app)
            .post('/posttoforum')
            .send({content: 'blahbalh', author: 'dneuser@gmail.com'})
            .expect(400);

    });

    it('valid post', async() => {

        await request(app)
            .post('/posttoforum')
            .send({content: 'blahbalh', author: 'uesr123@gmail.com'}) //have to manually add user to local database
            .expect(200);

    });

});