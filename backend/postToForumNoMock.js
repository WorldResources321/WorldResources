var express = require("express")
var app = express()


const {MongoClient} = require("mongodb")
const uri = "mongodb://localhost:27017"
const client = new MongoClient(uri)


app.use(express.json())

app.post('/postToForum', async (req, res) => {  
        const users = client.db("users")
        const all = users.collection("all")
        const blocked = users.collection("blocked")

        const content = req.body.content
        const author = req.body.author
        const query = {"email": author}

        const newPost = {
            content: req.body.content,
            author: req.body.author
        }

    try {  
        if (content == null || author == null || content === "" || author === "") { //if content or user is not specified
            res.status(400).json({status: 400, message: "content or author unspecified"})
        }
        else {
            await all.findOne(query, (err, result) => { 
                if (err) { 
                    throw err;
                }
                if (result == null) { //author given does not exist (not a signed-in user)
                    res.status(404).json({status: 404, message: "author is not a signed-in user"})
                }
                else {
                    blocked.findOne(query, (err, result) => {
                        if (err) { 
                            throw err;
                        }
                        if (result == null) { //author is qualified to post
                            client.db("forum").collection("posts").insertOne(newPost)
                            res.status(200).json({status: 200, message: "post saved to database"})
                        }
                        else { //author is blocked from posting
                            res.status(400).json({status: 400, message: "author is blocked from posting"})
                        }
                    })
               }


            })
        }
    }
    catch(err) {
        console.log(err)
        res.status(400).json({status: err.status, message: err.message})
    }
})

app.post('/addUser', async (req,res) => {
 
    const users = client.db("users")
    const all = users.collection("all")
    const newUser = {
        "email": req.body.email
    }

    try {   
        if (req.body.email == null || req.body.email === "") { //if user is not given
            res.status(400).json({status: 400, message: "user unspecified"})
        }
        else {
            await all.findOne(newUser, (err, result) => { 
                if (err) { 
                    throw err;
                }
                if (result == null) { //valid new user
                    all.insertOne(newUser)
                    res.status(200).json({status: 200, message: "user saved to database"})
                }
                else { //user already exists in database
                    res.status(400).json({status: 400, message: "user is already in database"})
                }
            })
        }
    }
    catch(err) {
        console.log(err)
        res.status(400).json({status: err.status, message: err.message})
    }

})

app.post('/blockUser', async (req,res) => {   
    const users = client.db("users")
    const all = users.collection("all")
    const blocked = users.collection("blocked")
    const newUser = {
        "email": req.body.email
    }
    
    try { 
        if (req.body.email == null || req.body.email === "") { //if user is not given
            res.status(400).json({status: 400, message: "user unspecified"})
        }
        else {
            await all.findOne(newUser, (err, result) => { 
                if (err) { 
                    throw err;
                }
                if (result == null) { //blocked user is not a signed-in user
                    res.status(404).json({status: 404, message: "user does not exist"})
                }
                else { 
                    blocked.findOne(newUser, (err, result) => {
                        if (err) { 
                            throw err;
                        }
                        if (result == null) { //valid user
                            console.log(result)
                            blocked.insertOne(newUser)
                            res.status(200).json({status: 200, message: "user blocked"})
                        }
                        else { //user is already blocked
                            res.status(400).json({status: 400, message: "user is already blocked"})
                        }
                    })
                }
            })
        }
    }
    catch(err) {
        console.log(err)
        res.status(400).json({status: err.status, message: err.message})
    }
})

async function run() {
    try {
        await client.connect()
        console.log("Successfully connected to the database")
    }
    catch (err) {
        console.log(err)
        await client.close()
    }
}

run()


module.exports = app;