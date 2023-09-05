package javaBootcamp.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Car {

    @NonNull
    private int id;
    @NonNull
    private String model;
}
