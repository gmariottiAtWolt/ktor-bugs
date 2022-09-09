The bug shows different behaviours when running the application locally vs when running the tests. Specifically, sending
a request `http localhost:8080 field=test` gets the following responses:
- When the application is running from main, both normal run and debug mode, the response is `Got 0 BadRequestExceptions`.
- When running [AppTest](src/test/kotlin/com/wolt/AppTest.kt), the tests fail with `Got 2 BadRequestExceptions`.
- When running the same tests but in debug mode, the tests fail with `Got 1 BadRequestExceptions`.