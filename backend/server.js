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

app.get('/separateContent', (req,res) => {
    res.send(req.body.content)
})

app.get('/separateAuthor', (req,res) => {
    res.send(req.body.author)
})


app.post('/posttoforum', async (req, res) => {
    try {
        await client.db("storage").collection("posts").insertOne(req.body)
        //await client.db("storage").collection("authors").insertOne(req.body.author)
        //const result =  client.db("data").collection("forum").find(req.body)
        res.send("stored to database")
        //check if the author is blocked from posting or not
    }
    catch(err) {
        console.log(err)
        res.status(400).send(err)
    }
})

app.get('/getposts', async (req,res) => {
    try {
        const content = await client.db("storage").collection("posts").find().sort({ _id: -1 }).limit(10).toArray() //get most recent posts
        console.log(content)
        res.send(content)
    }
    catch(err) {
        console.log(err)
        res.status(400).send(err)
    }
})

app.get('/getauthors', async (req,res) => {
    try {
        const authors = await client.db("storage").collection("authors").find().sort({ _id: -1 }).limit(10).toArray() //get most recent posts
        res.send(JSON.stringify(authors))
    }
    catch(err) {
        console.log(err)
        res.status(400).send(err)
    }
})


app.post('/report', async (req,res) => {
    try {
        await client.db("storage").collection("reported").insertOne(req.body)
        res.status(200).send("Database has stored the reported user\n")
    }
    catch(err) {
        console.log(err)
        res.status(400).send(err)
    }
})

app.get('/getreported', async (req,res) => {
    try {
        const content = await client.db("storage").collection("reported").find().sort({ _id: -1 }).toArray() 
        res.send(JSON.stringify(content))
    }
    catch(err) {
        console.log(err)
        res.status(400).send(err)
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

