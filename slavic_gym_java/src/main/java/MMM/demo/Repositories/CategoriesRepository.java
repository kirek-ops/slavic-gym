
package MMM.demo.Repositories;

import MMM.demo.Entities.Categories;
import java.util.List;

public interface CategoriesRepository {
    List<Categories> findAll();
}
