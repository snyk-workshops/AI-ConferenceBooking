## CoPilot Demo

1 - Launch the app, browse to: http://localhost:8080

2 - set up the index page

```
<!-- insert nav fragment here -->
<!-- insert eventsTable fragment here -->
```

3 - Add search functionality

In `SearchRepository.searchTalk`:

```
// lowercase the input
// create a query to search for talks with the input in the title, description or speaker username
// return the result of the query
```

In the app: show that the search field works

4 - Demonstrate SQL injection

In the app, put this in the search field:

```sql
%'; update talk set start_date = dateadd(hour,-2,CURRENT_TIMESTAMP), end_date = dateadd(hour,1,CURRENT_TIMESTAMP) where id = 1; --
```

This moves _your_ talk to the top of the list

NOTE: Per the code in `AiConferenceBookingApplication`, the main speaker 
(set to username `micah` by default, will always have `talk` id 1, 2 & 3)

OR

```sql
%'; update talk set start_date = CURRENT_TIMESTAMP, end_date = dateadd(hour,1,CURRENT_TIMESTAMP) --
```

This sets everyone's talk to the same time

5 - Fix the SQL injection

Delete the previous code in `SearchRepository.searchTalk`. Then:

```
// lowercase the input
// use a query with named parameters
// set the parameter
// return the result of the query
```

6 - show that the sql injection no longer works using one of the above queries

7 - Add code to support a profile picture

In `UploadController.uploadImage`:

```
// get file name
// get path with file separator
// save file to the path
// get the person
// set the image name
// save the person
// add the message
```

8 - Show that upload of profile picture works

9 - Demonstrate a path traversal attack

Use burpsuite to capture profile pic upload POST
use burpsuite to replay pic upload, BUT change the file name to `../image/snyklogo.png`

10 - Fix the path traversal attack

Delete the previous code in `UploadController.uploadImage`. Then:

```
// get file name
// get path with file separator
// validate that there is not a path traversal by using the normalize method
// check for a path traversal
// save file to the path
// get the person
// set the image name
// save the person
// add the message
```

11 - Demonstrate that the path traversal attack no longer works

12 - Demonstrate XSS vuln

Use one of the links from the server output to show a speaker's talks listing

Replace the param with XSS:

```
http://localhost:8080/talks?username=<script>alert(1)</script>
```

13 - Update the code to provide links to the talks

Uncomment line 41 and comment line 40 in `eventsTable.html`

14 - Fix the XSS vuln

Use snyk code to scan (it will catch the XSS vuln)

Paste the code in ChatGPT and ask for analysis and fixes

Here's a conversation reference for this: https://chat.openai.com/share/0057868b-af09-461a-b3bd-26e451beea81

Note that the prompt is: 

```
I am going to upload some code. Identify and fix any security vulnerabilities with minimal changes to the code.
```

Note that while ChatGPT recognizes the XSS vuln, it fixes it using a "homegrown" solution rather than something like
`HTMLUtils.htmlEscape`


