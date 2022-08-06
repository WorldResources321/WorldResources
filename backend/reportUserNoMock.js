var express = require("express")
var app = express()

const {MongoClient} = require("mongodb")
const uri = "mongodb://localhost:27017"
const client = new MongoClient(uri)

app.use(express.json())

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
                if (result == null) { //reported user is not a signed-in user
                    res.status(404).json({status: 404, message: "user does not exist"})
                }
                else { 
                    reported.findOne(newUser, (err, result) => {
                        if (result == null) { //valid user
                            reported.insertOne(newUser, (err, result) => {
                                res.status(200).json({status: 200, message: "user reported"})
                            })
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

app.post('/addUser', async (req,res) => {

    try {    
        const users = client.db("users")
        const all = users.collection("all")
        const newUser = {
            "email": req.body.email
        }

        if (req.body.email == null || req.body.email === "") { //if user is not given
            res.status(400).json({status: 400, message: "user unspecified"})
        }
        else {
            await all.findOne(newUser, (err, result) => { 
                if (result == null) { //valid new user
                    all.insertOne(newUser, (err, result) => {
                        res.status(200).json({status: 200, message: "user saved to database"})
                    })
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