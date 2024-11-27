package org.example.mhsd.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecodedData {
    private Long id;
    private String code;
    private String location;
    private String date;
    private String source;
    private String carrier;
    private String category;
    private String subcategory;
    private String indicator;
    private String disasterCode;
}