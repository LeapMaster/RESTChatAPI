# RESTChatAPI

## Problem Statement
We need a basic but configurable chat API to push and retrieve messages.

## Solution
Make the thing! We'll use IntelliJ with Jersey to create a basic REST service that implements message insert/retrieval, as well as accommodating app-side user authentication and attaching users to each message.

## Tables

### Messages
ID - int(20) AUTO PRIMARY KEY
message - varchar(200)
timestamp - TIMESTAMP
userID - int(20) FOREIGN KEY (Users)

### Users
userID - int(20) AUTO PRIMARY KEY
username - varchar(20)

## First Checkpoint: 

* GET message - Return list of last 20 messages, sorted by most recent first.
Returns JSON array of message objects.

## Second Checkpoint

* POST message - Add a new message to the table.
Returns confirmation code.

## Third Checkpoint

* GET user - Return user info by user id.
Returns user object.

* PUT user - Add new user 

## Fourth Checkpoint

* Modify messages to associate with a User. Add functionality to retrieve user data if sent proper username and password.

## Fifth Checkpoint

* Any additional stretch goals
