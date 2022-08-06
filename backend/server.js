var express = require("express")
var app = express()

const {MongoClient} = require("mongodb")
const uri = "mongodb://localhost:27017"
const client = new MongoClient(uri)

app.use(express.json())

app.get('/', (req,res) => {
    res.send("Hello World\n")
})

app.post('/', async (req,res) => {
    await client.db("test").collection("testcollection").insertOne(req.body)
    res.send(req.body.text) //store content under category "text"
})

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
                    throw(err);
                }
                if (result == null) { //blocked user is not a signed-in user
                    res.status(404).json({status: 404, message: "user does not exist"})
                }
                else { 
                    blocked.findOne(newUser, (err, result) => {
                        if (err) {
                            throw(err);
                        }
                        if (result == null) { //valid user
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

app.post('/reportUser', async (req,res) => {   
    const users = client.db("users")
    const all = users.collection("all")
    const reported = users.collection("reported")
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
                    throw(err);
                }
                if (result == null) { //reported user is not a signed-in user
                    res.status(404).json({status: 404, message: "user does not exist"})
                }
                else { 
                    reported.findOne(newUser, (err, result) => {
                        if (err) {
                            throw(err);
                        }
                        if (result == null) { //valid user
                            reported.insertOne(newUser)
                            res.status(200).json({status: 200, message: "user reported"})
                        }
                        else { //user is already blocked
                            res.status(400).json({status: 400, message: "user has already been reported"})
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

MongoClient.connect(uri, (err, db) => {

    if (err) {
        console.log("Error while connecting mongo client")
    } else {

        const myDB = db.db('myDB')
        const scoreCollection = myDB.collection('myScores')
        
        app.post('/getQuiz', (req, res) => { 
            const query = {
                email: req.body.email,
            }
            scoreCollection.findOne(query, (err, result) => {
                if (err) {
                    throw(err);
                }
                if (result != null) {
                    const objToSend = {
                        score: result.score
                    }
                    res.status(200).send(JSON.stringify(objToSend))
                } else {
                    res.status(404).send()
                }
            })

        })

        app.post('/storeQuiz', (req, res) => {
            console.log("in storeQuiz")
            const newScore = {
                email : req.body.email,
                score : req.body.score
            }
            const query = {email : newScore.email}

            scoreCollection.findOne(query, (err, result) => {
                if (err) {
                    throw(err);
                }
                if (result == null) {
                    scoreCollection.insertOne(newScore)
                        res.status(200).send()
                        console.log("logged quiz (null)")
                    
                } else if (newScore.score > result.score){
                    scoreCollection.findOneAndReplace(query,newScore)
                        res.status(200).send()
                        console.log("logged quiz (new high score)")

                } else if(newScore.score === 10){
                    scoreCollection.findOneAndReplace(query,newScore)
                        res.status(200).send()
                        console.log("logged quiz (full marks)")

                }
                else {
                    res.status(400).send()
                }

            })
        })
    }
    })


async function run() {
    try {
        await client.connect()
        console.log("Successfully connected to the database")
        var server = app.listen(3000, function () {
            //var host = server.address().address
            var port = server.address().port
            console.log("Server running at port %s", port)
        })
    }
    catch (err) {
        console.log(err)
        await client.close()
    }
}

run()