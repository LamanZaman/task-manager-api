package com.example.taskmanagment.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDto<T> {
private List<T> content;
private int pageNumber;
private int pageSize;
private long totalElements;
private int totalPages;
private boolean hasNext;
}
