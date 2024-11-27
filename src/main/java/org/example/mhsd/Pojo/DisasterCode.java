package org.example.mhsd.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisasterCode {
    private String code;
    private String disaster;
    private String category;
    private String subcategory;
    private String indicator;
}
