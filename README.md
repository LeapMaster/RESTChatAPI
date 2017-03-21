# RESTChatAPI

Basic chat API to push an retrieve messages.

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

* PUT message - Add a new message to the table.
Returns confirmation code.

## Third Checkpoint

* GET user - Return user info by user id.
Returns user object.

* PUT user - Add new user 

## Fourth Checkpoint

* Not sure, we'll think of something.
