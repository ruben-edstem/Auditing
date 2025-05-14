package com.edstem.auditing.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Double price;
    private String createdBy;
    private LocalDate createdDate;
    private String lastModifiedBy;
    private LocalDate lastModifiedDate;
}
