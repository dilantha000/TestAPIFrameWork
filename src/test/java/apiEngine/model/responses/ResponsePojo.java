package apiEngine.model.responses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Getter
@Setter
public class ResponsePojo {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Generated("jsonschema2pojo")
    public class Example {

        public String code;
        public String message;
        public String name;

        public Example(String code, String message, String name) {
            super();
            this.code = code;
            this.message = message;
            this.name = name;

        }
    }


}
