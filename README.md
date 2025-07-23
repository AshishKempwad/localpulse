# localpulse
A scalable backend for LocalPulse — a Yelp-like local discovery platform with search, reviews, and ratings. Built with Java, Spring Boot, and Elasticsearch.

## Data setup

### docker-compose

```yaml
services:
  elastic:
    image: docker.elastic.co/elasticsearch/elasticsearch:8.18.0
    ports:
    - 9200:9200
    environment:
    - discovery.type=single-node
    - xpack.security.enabled=false
    - xpack.security.http.ssl.enabled=false
  kibana:
    image: docker.elastic.co/kibana/kibana:8.18.0
    ports:
    - 5601:5601
    environment:
    - ELASTICSEARCH_HOSTS=http://elastic:9200
  data-setup:
    image: vinsdocker/elasticsearch-business-datasetup
    profiles:
      - data-setup
    environment:
    - ELASTICSEARCH_HOST=http://elastic:9200
```

### Instructions

 - pull this image as it might take some time - `docker pull vinsdocker/elasticsearch-business-datasetup:latest`
 - Run this command `docker compose up`
 - This will start `elasticsearch` and `kibana`. NOT `data-setup`.
 - Wait for elasticsearch and kibana to be up and running.
 - Open another terminal and navigate to the path where you have this yaml file.
 - Run this command `docker compose run --rm data-setup`. This will start the data-setup service to setup the indices and add the data.

## Suggestions API Implementation



### Index Details
 
 - Index name: `suggestions`
 - Index mapping

```json
{
  "mappings": {
    "properties": {
      "search_term": {
        "type": "completion"
      }
    }
  }
}
```

- To override this mapping
```yaml
  data-setup:
    image: vinsdocker/elasticsearch-business-datasetup
    profiles:
      - data-setup
    environment:
    - ELASTICSEARCH_HOST=http://elastic:9200
    volumes:
    - ./suggestion-index-settings.json:/usr/share/app/business-search/suggestion-index-settings.json
```


### API Details

| Parameter       | Type          | Description                                           |
|------------------|---------------|-------------------------------------------------------|
| **Request Type**  | GET           | The HTTP method used for the request.                |
| **Request Endpoint** | `/api/suggestions?prefix=res&limit=10` | The API endpoint to retrieve suggestions based on a prefix and limit. |
| **Query Parameters** |  - `prefix`  | A string used to filter suggestions (Example: `res`).   |
|                    |  - `limit`   | The maximum number of suggestions to return (Example: `10`). |
| **Response**      | `List<String>` | A list of suggestions based on the provided parameters. |

