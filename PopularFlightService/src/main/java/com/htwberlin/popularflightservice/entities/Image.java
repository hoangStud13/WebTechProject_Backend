package com.htwberlin.popularflightservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "ImageService")
@Data @AllArgsConstructor @NoArgsConstructor

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "imageService_Id")
    private long imageServiceId;

    @Column(name= "imageTitle")
    private String imageTitle;

    @Column(name= "description")
    private String description;

    @Column(name= "imageUrl")
    private String imageUrl;


}
