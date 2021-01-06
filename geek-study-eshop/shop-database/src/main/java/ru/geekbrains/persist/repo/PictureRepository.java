package ru.geekbrains.persist.repo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.persist.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query ("SELECT p FROM Picture p JOIN p.pictureData pD WHERE pD.data  IS NOT NULL ")
    String findById ();
}
