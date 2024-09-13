package dog.example.demo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogFactController {

    @GetMapping("/dogfact")
    public DogFact getDogFact() {
        String url = "https://dog-api.kinduff.com/api/facts";
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Fetching the response from the API
            String response = restTemplate.getForObject(url, String.class);

            // Parsing JSON response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            // Assuming response is an array of facts
            String fact = root.path("facts").get(0).asText();

            return new DogFact(fact);
        } catch (Exception e) {
            e.printStackTrace();
            return new DogFact("Error fetching dog fact.");
        }
    }

}
