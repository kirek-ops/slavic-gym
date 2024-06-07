package MMM.demo.Repositories;

import MMM.demo.Entities.Images;

import java.util.List;

public interface ImagesRepository {
    List<Images> findAll();
    Integer findImageIdByItemId(Integer id_item);
}
