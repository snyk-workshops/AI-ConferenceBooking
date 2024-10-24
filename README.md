## CoPilot Demo

**NOTE:** For the most effective demo, clone the project and import into IntelliJ fresh each time.

From IntelliJ, you can choose: `File > Manage IDE Settings > Import Settings...` and import the 
`IntelliJIdeaLiveTemplateSettings.zip` file to take advantage of the live templates as called out in the presentation
notes.

Make sure you have copilot and snyk plugins installed.

For each of the instructions below, you'll want to hit <enter> after the comment, wait for copilot to generate the
code and then hit <tab> to accept its response.

Refer to the script doc for more information. 

0 - customize the `src/main/resources/application.yml` with your own `auth.user.name` OR just leave it as `micah` 

1 - Launch the app, browse to: http://localhost:8081

2 - set up the index page: `src/main/resources/templates/index.html`

```
<!-- insert nav fragment here -->

<!-- insert eventsTable fragment here -->

```

3 - Add search functionality

In `SearchRepository.searchTalk`:

```
// lowercase the input

// create a native query 
// to search for talks 
// joined with the person table 
// with the input in the 
// description, title or speaker username
// format the code to make it more readable

// execute the query

// return the result
```

In the app: show that the search field works

4 - Demonstrate SQL injection

In the app, put this in the search field:

```sql
%'; update talk set start_date = dateadd(hour,-2,CURRENT_TIMESTAMP), end_date = dateadd(hour,1,CURRENT_TIMESTAMP) where id = 1; --
```

This moves _your_ talks to the top of the list by adjusting the start times.

NOTE: Per the code in `AiConferenceBookingApplication`, the main speaker 
(set to username `micah` by default, will always have id `1`)

5 - Fix the SQL injection

Delete the previous code in `SearchRepository.searchTalk`. Then:

```
// lowercase the input

// create a native query using named parameters 
// to search for talks 
// joined with the person table 
// with the input in the 
// description, title or speaker username
// format the code to make it more readable

// set the parameter

// execute the query

// return the result

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
http://localhost:8081/talks?username=<script>alert(1)</script>
```

13 - Update the code to provide links to the talks

Uncomment line 41 and comment line 40 in `eventsTable.html`

14 - Fix the XSS vuln

Use snyk code to scan (it will catch the XSS vuln)

Paste the code in ChatGPT and ask for analysis and fixes

Here's a conversation reference for this: https://chatgpt.com/share/53766a36-762f-4bd1-8d16-50a49b9516a6

Note that the prompt is: 

```
I am going to upload some code. Identify and fix any security vulnerabilities with minimal changes to the code.
```

Show that ChatGPT suggests the fix that is idiomatic to Spring Boot: `HtmlUtils.htmlEscape(username)`. That's great!

BUT, this is a great opportunity to show off DeepCodeAI Fix. In IntelliJ, you can click the lightning bolt and
let snyk fix the code and you'll see it comes up with the same fix: `HtmlUtils.htmlEscape(username)`

