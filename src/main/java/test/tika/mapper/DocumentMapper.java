package test.tika.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import test.tika.entity.Document;

import java.util.List;

@Mapper
public interface DocumentMapper {
    void insertDocument(Document document);
    List<Document> searchDocuments(String keyword);
}
