const request = require("supertest");
const app = require("../reportUserNoMock");

describe('report user', () => {

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
            .post('/reportUser')
            .send({email: 'andrew@gmail.com'}) //have to manually add to local database
            .expect(400);

    });

    it('valid report', async() => {

        await request(app)
            .post('/reportUser')
            .send({email: 'user2@gmail.com'})
            .expect(200);

    });

});