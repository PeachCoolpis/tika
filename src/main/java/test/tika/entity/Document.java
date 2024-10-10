package test.tika.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Document {
    
    private Long id;
    private String title;
    private String content;
}
