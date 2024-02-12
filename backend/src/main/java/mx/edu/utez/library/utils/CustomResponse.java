package mx.edu.utez.library.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data  @AllArgsConstructor @NoArgsConstructor
public class CustomResponse <T>{
    T data;
    boolean error;
    int status;
    String message;
}
