## CoPilot Demo

1. Launch the app, browse to: http://localhost:8080
2. set up the index page

// insert a header fragment
// insert the eventsTable fragment

3. Add search functionality

In `SearchRepository.searchTalk`:

// lowercase the input
// create a query to search for the input in the description and title of the talk
// return the result of the query

In the app: show that the search field works

4. Demonstrate SQL injection

In the app, put this in the search field:

```sql
%'; update talk set start_date = CURRENT_TIMESTAMP, end_date = dateadd(hour,1,CURRENT_TIMESTAMP) where id = 1; --
```

NOTE: replace the talk id with the one for the presenter

This moves _your_ talk to the top of the list

OR

```sql
%'; update talk set start_date = CURRENT_TIMESTAMP, end_date = dateadd(hour,1,CURRENT_TIMESTAMP) --
```

This sets everyone's talk to the same time

5. Fix the SQL injection

Delete the previous code in `SearchRepository.searchTalk`. Then:

// lowercase the input
// use a query with named parameters
// set the parameter
// return the result of the query

6. show that the sql injection no longer works using one of the above queries

7. Add code to support a profile picture

In `UploadController.uploadImage`:

// get file name
// get path with file separator
// save file to the path
// get the person
// set the image name
// save the person
// add the message

8. Show that upload of profile picture works

9. Demonstrate a path traversal attack

Use burpsuite to capture profile pic upload POST
use burpsuite to replay pic upload, BUT change the file name to `../image/snyklogo.png`

10. Fix the path traversal attack

Delete the previous code in `UploadController.uploadImage`. Then:

// get file name
// get path with file separator
// validate that there is not a path traversal by using the normalize method
// check for a path traversal
// save file to the path
// get the person
// set the image name
// save the person
// add the message

11. Demonstrate that the path traversal attack no longer works