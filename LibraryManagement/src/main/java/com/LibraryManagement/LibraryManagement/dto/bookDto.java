package com.LibraryManagement.LibraryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class bookDto {
  private Long id;
  private String title;
  private String author;
  private String isbn;
  private String description;
  private double price;

}
