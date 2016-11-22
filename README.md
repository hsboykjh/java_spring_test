# Java Spring Simple RESTful service
This project's purpose is to build a simple RESTful service.

There are two main RESTful services ( wordCount , Ranks ).

And the services is working with Spring Basic Authentication(Security).

To implement this application, Spring Boot ( Spring light version ) is used.

## wordCount
There is a sample paragraphs file, which is including several words.

When application is running, a hashmap is created.

The hashmap's type is <String (word name),  Integer (word count) >, which has count information of all words.

And the hashmap is sorted according to the count Number of the words.


If a request of client has some word names, this module returns the word names and counts of the names in the row data file.

####Sample Request
<pre><code>curl http://host/counter-api/search -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" - d'{"searchText":["Duis", "Sed", "Donec", "Augue", "Pellentesque", "123"]}' -H"Content- Type: application/json" –X POST</code></pre>

####Result in JSON:
<pre><code>{"counts": [{"Duis": 11}, {"Sed": 16}, {"Donec": 8}, {"Augue": 7}, {"Pellentesque": 6},{"123": 0}]}</code></pre>


## Ranks
This uses same Hashmap, which is created when application is running. 

Since the hashmap has sorted information, it could return the result of the hashmap iterator in order to make top ranks response. 


####Sample Request
<pre><code>curl http://host/counter-api/top/20 -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" - H"Accept: text/csv"</code></pre>

####As an example of the result if I put /top/5: 
<pre><code>text1|100
text2|91 
text3|80 
text4|70 
text5|60
</code></pre>

## Data Preprocessing
To parse the sample RowData file, it needs several cleansing process before making the hashmap.
- transform lowercase
- remove ',' and '.'


## Security ( Basic Authentication )
This application uses Basic Authentication, which is provided by Spring ( Boot ).

To use -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==", b3B0dXM6Y2FuZGlkYXRlcw== is tested to decode in unit test.

And the decoded id and password are using to set Authentication as a 'USER'.
 
