package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.PictureData;
import ru.geekbrains.persist.repo.PictureRepository;

import java.util.Optional;

public class PictureServiceDiskImpl implements PictureService{

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceDiskImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(long id) {
        return pictureRepository.findById(id)
                .map(Picture::getContentType);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) {
        return pictureRepository.findById(id)
                .map(pic -> pic.getPictureData().getImagePath().getBytes());
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        return null;
    }
}
