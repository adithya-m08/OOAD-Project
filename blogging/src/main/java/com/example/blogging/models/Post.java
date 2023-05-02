package com.example.blogging.models;

import com.example.blogging.services.UserService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "posts")
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(nullable = false) private String title;

    private String subtitle;

    @Transient
    private MultipartFile
        contentFile; // This field is not stored in the database

    @Column(nullable = false)
    private String contentFileName; // This field is stored in the database
    //
    @Column(nullable = false) private String fileName;

    private String contentType;

    public Post() {}

    public void setFile(MultipartFile contentFile, UserService userService)
        throws IOException {
        this.contentFile = contentFile;
        this.contentFileName = contentFile.getOriginalFilename();
        saveContentToFile(userService);
    }

    private void saveContentToFile(UserService userService) throws IOException {
        User author = userService.getUserById(this.author);
        String authorUsername = author.getUsername();

        // Generate a unique file name using a UUID and the original file name
        String uniqueFileName =
            UUID.randomUUID().toString() + "-" + contentFileName;

        // Create the directory for the user if it doesn't exist
        String userDirectory =
            "data/" + authorUsername; // Change this to your actual file path
        // Write the file contents to the file system
        Path fos = Paths.get(userDirectory + "/" + uniqueFileName);
        if (Files.exists(fos)) {
            System.out.println("File exists!");
        } else {
            System.out.println("File not found.");
            Path d = Paths.get(userDirectory);
            if (!Files.exists(d)) {
                Files.createDirectories(d);
            }
        }
        Files.write(fos, contentFile.getBytes());

        // Update the post object with the file name and content type
        this.fileName = uniqueFileName;
        this.contentType = contentFile.getContentType();
    }

    public byte[] getFile(UserService userService) throws IOException {
        User author = userService.getUserById(this.author);
        String authorUsername = author.getUsername();

        String userDirectory =
            "data/" + authorUsername; // Change this to your actual file path
        //
        // read the file from the system

        System.out.println(fileName);
        File file = new File(userDirectory + "/" + fileName);
        System.out.println(file.toPath().toString());

        // create a StandardMultipartFile object from the file
        try (FileInputStream fis = new FileInputStream(file)) {
            return Files.readAllBytes(file.toPath());
        }
    }

    public String getContentFilePath() { return contentFileName; }

    public void setContentFilePath(String contentFilePath) {
        this.contentFileName = contentFilePath;
    }

    public String getContent() throws IOException {
        Path filePath = Paths.get(fileName);
        return new String(Files.readAllBytes(filePath));
    }

    public void setContent(String content) throws IOException {
        Path filePath = Paths.get(contentFileName);
        Files.write(filePath, content.getBytes());
    }

    public void deleteFile() throws IOException {
        String userDirectory =
            "data/" + author.getUsername() + "/"; // Change this to your actual file path
        Files.delete(Paths.get(userDirectory + fileName));
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public User getAuthor() { return author; }

    public void setAuthor(User author) { this.author = author; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getSubtitle() { return subtitle; }

    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public MultipartFile getContentFile() { return contentFile; }

    public String getContentFileName() { return contentFileName; }

    public void setContentFileName(String contentFileName) {
        this.contentFileName = contentFileName;
    }

    public void setContentFile(MultipartFile contentFile) {
        this.contentFile = contentFile;
    }

    public String getFileName() { return fileName; }

    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getContentType() { return contentType; }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
