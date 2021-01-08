package ru.geekbrains.service;

import ru.geekbrains.persist.model.PictureData;

import java.io.IOException;
import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id) throws IOException;

    PictureData createPictureData(byte[] picture);

    // TODO перенести сюда функционал получения списка картинок

    // TODO перенести сюда функционал для удаления картинок

}
