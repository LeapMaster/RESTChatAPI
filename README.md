# RESTChatAPI

## Problem Statement
We need a basic but configurable chat API to push and retrieve messages input in real time by the user.

## Solution
Make the thing! We'll use IntelliJ with Jersey to create a basic REST service that implements message insert/retrieval, as well as accommodating app-side user authentication and attaching users to each message.

## Usage Guide
Below are the 5 available endpoints for the API; use these to provide persistence and back-end functionality for a chat program.

### /recent
* allows GET request
* No parameters
* Returns JSON of GSON-serialized messages, newest first, limited to 20 (default, configurable in API's properties)

### /all
* allows GET request
* No parameters
* Returns JSON of GSON-serialized messages, newest first, limited to 50 (default, configurable in API's properties)

### /add
* allows POST request
* One parameter: String _jsonData_ with two key-value pairs. _message_ holds the text body for the message, _userID_ holds the ID for the associated user.
    * For example: _"{\\"message\\": \\"This is a message!\\", \\"userID\\": \\"3\\"}"_
* Returns a Response confirming whether insert was successful or not

### /delete
* allows GET request (as to allow ease of access through DHTML)
* One parameter: @QueryParam String _messageID_; holds the ID for the message to be deleted.
* Returns a Response confirming whether delete was successful or not

### /messagesbyuser
* allows GET request
* One parameter: @QueryParam String _userID_; ID of user to select by
* Returns JSON of GSON-serialized messages associated with selected userID, newest first, no explicit limit

## Technologies

 Database - MySql
    Store tables for Users and Messages
 Api
     /All
     /Delete - removes message from DB/display
     /Recent - shows all message "history" up to 50 messages
     
 Logging
        Log4J
        
 Unit Testing
        Junit
        
 Database CRUD
        Hibernate
        
 Return formats
        JSON
        text
        HTML
        jQuery


## Database:
Chat

## Tables:

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

* GET message - Return list of all 50 messages
* update PUT to trim oldest messages if 50+ exist
* add userID parameter to PUT message insert, and GET to get all messages by userID

## Fourth Checkpoint

* ~GET - most recent message, listens and sends only on new message insert~

## Fifth Checkpoint
* POST - Delete message by ID* 
* Finish any other API changes

## Sixth Checkpoint
* Make sure all API calls work using Soap UI
* Write Java code to call the endpoints

## Seventh Checkpoint
* Build a webapp that uses the Java code to leverage the API by calling its endpoints



