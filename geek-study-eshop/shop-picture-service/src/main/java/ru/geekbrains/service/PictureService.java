package ru.geekbrains.service;

import ru.geekbrains.controller.repr.PictureRepr;
import ru.geekbrains.persist.model.PictureData;
import ru.geekbrains.persist.model.Product;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id) throws IOException;

    PictureData createPictureData(byte[] picture);

//    List<PictureRepr> getPictures (Product product);

    // TODO перенести сюда функционал получения списка картинок

    // TODO перенести сюда функционал для удаления картинок

}
