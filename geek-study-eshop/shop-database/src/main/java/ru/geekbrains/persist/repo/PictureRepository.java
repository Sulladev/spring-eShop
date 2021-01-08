package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.persist.model.Picture;

import java.util.List;
import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {

//    @Query ("SELECT p FROM Picture p WHERE p.pictureData.fileName IS NOT NULL ")
//    Optional<Picture> filterPictureWherePictureFileNameIsNotNull(Optional<Picture> picture);

    Optional<Picture> findPictureByIdAndPictureData_FileNameIsNotNull(Long id);

    Optional<Picture> findPictureByIdAndPictureData_DataIsNotNull(long id);


}
