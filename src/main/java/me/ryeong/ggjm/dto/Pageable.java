package me.ryeong.ggjm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pageable {
    private long page = 1; // 1 is the first page
    private int size = 10;
    private String keyField;
    private String keyWord;

    public Long getOffset() {
        return (page - 1) * size;
    }

    public String getListLink() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("keyField", keyField)
                .queryParam("keyWord", keyWord);

        return builder.toUriString();
    }
}
