package at.ac.tuwien.sepm.groupphase.backend.service;


import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface NewsService {

    /**
     * Creates a news entry
     * @param news the news entry to be created
     * @return the news entry created in the database
     * @throws NotCreatedException in case something goes wrong while accessing the database
     */
    News save(News news) throws NotCreatedException;

    /**
     * Gets a news entry by its id
     * @param id for which a news entry is to be returned
     * @return the news entry with the given @param id
     * @throws NotFoundException in case no news with @param id is found or the database access fails
     */
    News findById(Long id) throws NotFoundException;

    News updateWithImage(Long id, MultipartFile image) throws NotFoundException;
}
