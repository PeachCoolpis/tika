package test.tika.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import test.tika.entity.Document;
import test.tika.service.DocumentService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {
    
    
    private final DocumentService documentService;
    
    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload"; // upload.html 템플릿을 렌더링
    }
    
    @PostMapping
    public ResponseEntity<String> handleFileUpload(@RequestParam("files") List<MultipartFile> files,
                                                   @RequestParam("title") String title) {
        try {
            documentService.saveDocument(files, title);
            return ResponseEntity.ok("Files uploaded successfully!");
        } catch (IOException | TikaException e) {
            return ResponseEntity.badRequest().body("Failed to upload files: " + e.getMessage());
        }
    }
    @GetMapping("/search")
    public String searchDocuments(@RequestParam(value = "keyword", required = false)  String keyword, Model model) {
        List<Document> documents = documentService.searchDocuments(keyword);
        model.addAttribute("documents", documents);
        return "search"; // search.html 템플릿을 렌더링
    }
}