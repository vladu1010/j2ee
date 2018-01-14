package net.homecredit.comp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class UrlDto {
    @JsonProperty("complain")
    private String complain;

    @JsonProperty("boss")
    private String boss;

    @JsonProperty("logout")
    private String logout;
}
