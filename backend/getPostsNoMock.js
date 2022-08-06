var express = require("express")
var app = express()

const {MongoClient} = require("mongodb")
const uri = "mongodb://localhost:27017"
const client = new MongoClient(uri)

app.use(express.json())

app.get('/getPosts', async (req,res) => {
    try {
        const posts = await client.db("forum").collection("posts").find().sort({ _id: -1 }).limit(10).toArray()
        if (posts == null || posts.length == 0) { //reported user is not a signed-in user
            res.status(404).json({status: 404, message: "no posts yet"})
        }
        else {
            res.status(200).json(posts)
        }
    }
    catch(err) {
        console.log(err)
        res.status(400).json({status: err.status, message: err.message})
    }
})

app.post('/postToForum', async (req, res) => {
    try {    
        const users = client.db("users")
        const all = users.collection("all")
        const blocked = users.collection("blocked")

        const content = req.body.content
        const author = req.body.author
        const query = {"email": author}

        const newPost = {
            content: content,
            author: author
        }

        if (content == null || author == null || content === "" || author === "") { //if content or user is not specified
            res.status(400).json({status: 400, message: "content or author unspecified"})
        }
        else {
            await all.findOne(query, (err, result) => { 
                if (result == null) { //author given does not exist (not a signed-in user)
                    res.status(404).json({status: 404, message: "author is not a signed-in user"})
                }
                else {
                    blocked.findOne(query, (err, result) => {
                        if (result == null) { //author is qualified to post
                            client.db("forum").collection("posts").insertOne(newPost, (err, result) => {
                                res.status(200).json({status: 200, message: "post saved to database"})
                            })
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

module.exports =app;