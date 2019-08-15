## Taggable API

To run:

```
$ ./gradlew bootRun
```

### `curl`s for testing


- `/tags`

```
GET

curl -H "Content-Type: application/json" http://localhost:8080/tags
```

```
POST

curl -X POST -H "Content-Type: application/json" http://localhost:8080/tags -d '{ "id": 1, "url": "www.google.com", "sourceUser": { "handle": "miguel", "name": "Miguel Biasao" }, "targetUsers": [ { "handle": "biasao", "name": "Alberto" }] }'
```

- `/users`

```
POST

curl -v -X POST -H "Content-Type: application/json" http://localhost:8080/users -d'{ "handle": "biasao", "name": "Alberto Biasao" }'
```

- `/feed`

```
POST

curl -v -X POST -H "Content-Type: application/json" http://localhost:8080/feeds -d '{"user":{"handle":"biasao","name":"Alberto Biasao"},"tags":[{"id":"25a3294a-c352-4640-a95f-6a587eb933c4","url":"https://medium.com/swlh/branding-is-mostly-bullshit-8143b1d6ff1e","sourceUser":{"handle":"mbiasao","name":"Mateus Biasao"},"targetUsers":[{"handle":"biasao","name":"Alberto Biasao"}]},{"id":"648cbd8d-c77f-4667-a112-48236ebb221d","url":"https://www.spotify.com","sourceUser":{"handle":"mbiasao","name":"Mateus Biasao"},"targetUsers":[{"handle":"biasao","name":"Alberto Biasao"}]}]}'
```
