const request = require("supertest");
const app = require("../reportUserNoMock");

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

describe('report user', () => {
    it('non-existing user', async() => {
        await request(app)
            .post('/report')
            .send({email: 'smith@gmail.com'})
            .expect(400);

    });

    it('already reported', async() => {

        await request(app)
            .post('/report')
            .send({email: 'reported@gmail.com'}) //have to manually add to local database
            .expect(400);

    });

    it('valid report', async() => {

        await request(app)
            .post('/report')
            .send({email: 'uesr123@gmail.com'})
            .expect(200);

    });

});