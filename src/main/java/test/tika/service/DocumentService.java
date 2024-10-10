package test.tika.service;


import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import test.tika.entity.Document;
import test.tika.mapper.DocumentMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentService {
    private final DocumentMapper documentMapper;
    private final Tika tika = new Tika();
    
    
    public void saveDocument(List<MultipartFile> files, String title) throws IOException, TikaException {
        List<Document> documents = new ArrayList<>();
        for (MultipartFile file : files) {
            String content = tika.parseToString(file.getInputStream());
            
            Document document = new Document();
            document.setTitle(title);
            document.setContent(content);
            documents.add(document);
        }
        
       
        for (Document document : documents) {
            documentMapper.insertDocument(document);
        }
    }
    public List<Document> searchDocuments(String keyword) {
        return documentMapper.searchDocuments(keyword);
    }
}

