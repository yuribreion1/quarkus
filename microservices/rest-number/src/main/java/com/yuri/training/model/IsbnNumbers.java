package com.yuri.training.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.time.Instant;

@Schema(description = "Several ISBN numbers for books")
public class IsbnNumbers {

    @JsonbProperty("isbn_10")
    @Schema(required = true)
    public String isbn10;

    @JsonbProperty("isbn_13")
    @Schema(required = true)
    public String isbn13;
    @JsonbTransient
    public Instant generationDate;

    @Override
    public String toString() {
        return "IsbnNumbers{" +
                "isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", generationDate=" + generationDate +
                '}';
    }
}
