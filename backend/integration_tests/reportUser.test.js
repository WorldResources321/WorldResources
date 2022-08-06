const request = require("supertest");
const app = require("../reportUserNoMock");

describe('report user', () => {

    
    const {MongoClient} = require("mongodb")
    const uri = "mongodb://localhost:27017"
    const client = new MongoClient(uri)
    
    beforeAll(async () => {
        await client.connect()
    })
    
    afterAll(async() => {
        await client.close()    
    })

    it('empty user', async() => {
        await request(app)
            .post('/reportUser')
            .send({email: ""})
            .expect(400);

    });

    it('non-existing user', async() => {
        await request(app)
            .post('/reportUser')
            .send({email: 'merinda@gmail.com'})
            .expect(404);

    });

    it('already reported', async() => {

        await request(app)
        .post('/addUser')
        .send({email: 'andrew@gmail.com'});

        await request(app)
        .post('/reportUser')
        .send({email: 'andrew@gmail.com'});

        await request(app)
            .post('/reportUser')
            .send({email: 'andrew@gmail.com'}) //have to manually add to local database
            .expect(400);

    });

    it('valid report', async() => {

        await request(app)
            .post('/addUser')
            .send({email:'user2@gmail.com'});
            
        await request(app)
            .post('/reportUser')
            .send({email: 'user2@gmail.com'})
            .expect(200);

    });

});