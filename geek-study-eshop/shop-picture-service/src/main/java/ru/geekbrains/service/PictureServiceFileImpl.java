package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.repr.PictureRepr;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.PictureData;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.PictureRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "picture.storage.type", havingValue = "file")
public class PictureServiceFileImpl implements PictureService {

    private static final Logger logger = LoggerFactory.getLogger(PictureServiceFileImpl.class);

    @Value("${picture.storage.path}")
    private String storagePath;

    private final PictureRepository repository;

    @Autowired
    public PictureServiceFileImpl(PictureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(long id) {

        return repository.findPictureByIdAndPictureData_FileNameIsNotNull(id)
                .map(Picture::getContentType);

    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) {

        return repository.findPictureByIdAndPictureData_FileNameIsNotNull(id)
                .map(pic -> Path.of(storagePath, pic.getPictureData().getFileName()))
                .filter(Files::exists)
                .map(path -> {
                    try {
                        return Files.readAllBytes(path);
                    } catch (IOException ex) {
                        logger.error("Can't open picture file", ex);
                        throw new RuntimeException(ex);
                    }
                });

    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        String fileName = UUID.randomUUID().toString();
        try (OutputStream os = Files.newOutputStream(Path.of(storagePath, fileName))) {
            os.write(picture);
        } catch (IOException ex) {
            logger.error("Can't create picture file", ex);
            throw new RuntimeException(ex);
        }
        return new PictureData(fileName);
    }

    @Override
    public List<PictureRepr> getPictures(Product product) {
        return product.getPictures().stream()
                .map(PictureRepr::new)
                .collect(Collectors.toList());
    }
}

