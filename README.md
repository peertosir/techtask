**HTML Page parser**

Main purpose is to count words frequency on given HTML page.


**Launch**
Just run application with 1 command line argument which will be URL for page to parse.


**Features**

- Built with Spring Boot Initializer
- Saves result to embedded H2 db
- Logging to file
- A little bit of tests
- Validation of URL
- Layers: domain -> repository -> service -> view(as well app);

*Log file name:* app.log
*Database file:* ./data_db
