# RESTChatAPI

## Problem Statement
We need a basic but configurable chat API to push and retrieve messages input in real time by the user.

## Solution
Make the thing! We'll use IntelliJ with Jersey to create a basic REST service that implements message insert/retrieval, as well as accommodating app-side user authentication and attaching users to each message.

## Usage Guide
This single use api allows you to send messages and store them in a database. We created this service to be implemented as an instant messenger. There is no server attached but this Chat Service updates and retrieves database records to allow users to view their messages. Requires a MySQL database for functionality: Message and User.

API End Point: http://52.14.153.185:8080/chatapi/recent
Basic Implementation of API: http://52.14.153.185:8080/chatproject/

### Endpoint URL host
* [Tack the following onto this URL to make API calls.](http://52.14.153.185:8080/chatapi/)

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

### Example Webapp
* [Example of a Webapp leveraging three of the five API calls.](http://52.14.153.185:8080/chatproject/)

### Errors

* 500 Error: You have reached this page in error or the server may be down.

### Example Output

* _host url_ + /chatapi/recent

[{"ID":50,"message":"message body","timestamp":"Thu, 13 Apr 2017 20:44:22 UTC","userID":0},{"ID":49,"message":"so happy","timestamp":"Thu, 13 Apr 2017 20:44:04 UTC","userID":0},{"ID":48,"message":"abc","timestamp":"Thu, 13 Apr 2017 19:21:55 UTC","userID":0},{"ID":46,"message":"Wow look at that message","timestamp":"Thu, 13 Apr 2017 19:18:13 UTC","userID":0},{"ID":44,"message":"Testing","timestamp":"Thu, 13 Apr 2017 19:10:10 UTC","userID":0},{"ID":40,"message":"Potato Tomato","timestamp":"Thu, 13 Apr 2017 18:47:13 UTC","userID":0},{"ID":39,"message":"This is a message body.","timestamp":"Thu, 13 Apr 2017 18:46:37 UTC","userID":0},{"ID":38,"message":"abc","timestamp":"Thu, 13 Apr 2017 18:46:19 UTC","userID":0},{"ID":37,"message":"Message bodies can be 200 characters long by default.","timestamp":"Thu, 13 Apr 2017 18:46:13 UTC","userID":0},{"ID":33,"message":"Hello!","timestamp":"Thu, 13 Apr 2017 18:26:39 UTC","userID":0},{"ID":32,"message":"What a nice day!","timestamp":"Thu, 13 Apr 2017 18:26:34 UTC","userID":0},{"ID":31,"message":"Who wants lunch?","timestamp":"Thu, 13 Apr 2017 17:39:52 UTC","userID":0},{"ID":30,"message":"The sun is shining.","timestamp":"Thu, 13 Apr 2017 17:38:38 UTC","userID":0},{"ID":29,"message":"Donde esta la biblioteca?","timestamp":"Thu, 13 Apr 2017 17:34:52 UTC","userID":0},{"ID":17,"message":"Seventeenth. 99% of all asphalt is recycled and reused to make new roads.","timestamp":"Mon, 06 Mar 2017 17:37:49 UTC","userID":2},{"ID":15,"message":"Fifteenth message. Did you know that the classiication of oranges as a berry has been confusing and controversial?","timestamp":"Mon, 06 Mar 2017 17:36:05 UTC","userID":3},{"ID":14,"message":"Fourteenth message, we\u0027re getting there.","timestamp":"Mon, 06 Mar 2017 17:33:14 UTC","userID":2},{"ID":13,"message":"Thirteenth message. Spoooooky.","timestamp":"Mon, 06 Mar 2017 17:33:00 UTC","userID":1},{"ID":12,"message":"Twelfth message. Because twelvth would make too much sense.","timestamp":"Mon, 06 Mar 2017 17:32:03 UTC","userID":3},{"ID":11,"message":"Why do we need multiple compound vowels to make the same sound?","timestamp":"Mon, 06 Mar 2017 17:30:51 UTC","userID":2}]

## Technologies

 * Database - MySql
      * Store tables for Users and Messages
     
 * Logging
      * Log4J
        
 * Unit Testing
      * Junit
        
 * Database CRUD
      * Hibernate
        
 * Return formats
      * JSON
      * text
      * HTML
      * jQuery


## Database:
* Chat

## Tables:

### Messages
* ID - int(20) AUTO PRIMARY KEY
* message - varchar(200)
* timestamp - TIMESTAMP
* userID - int(20) FOREIGN KEY (Users)

### Users
* userID - int(20) AUTO PRIMARY KEY
* username - varchar(20)


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



