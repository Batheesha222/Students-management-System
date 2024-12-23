package dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonDTO {
    private String nicNo;
    String name;
    String address;

    private ArrayList<String> vehicleList;
}
