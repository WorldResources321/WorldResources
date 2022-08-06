const request = require("supertest");
const app = require("../postToForumNoMock");

describe('post to forum', () => {

    it('empty content', async() => {

        await request(app)
            .post('/postToForum')
            .send({content: '', author: 'smith@gmail.com'})
            .expect(400);

    });

    it('empty author', async() => {

        await request(app)
            .post('/postToForum')
            .send({content: 'blahblah', author: ''})
            .expect(400);
    });

    
    it('blocked author', async() => {

        await request(app)
            .post('/addUser')
            .send({email: 'andrew@gmail.com'});

        await request(app)
            .post('/blockUser')
            .send({email: 'andrew@gmail.com'});

        await request(app)
            .post('/postToForum')
            .send({content: 'blahbalh', author: 'andrew@gmail.com'})
            .expect(400);

    });

    it('non-existing user', async() => {

        await request(app)
            .post('/postToForum')
            .send({content: 'blahbalh', author: 'dneuser@gmail.com'}) 
            .expect(404);

    });

    it('valid post', async() => {

        await request(app)
            .post('/addUser')
            .send({email: 'user1@gmail.com'})

        await request(app)
            .post('/postToForum')
            .send({content: 'blahbalh', author: 'user1@gmail.com'}) 
            .expect(200);

    });
    

});