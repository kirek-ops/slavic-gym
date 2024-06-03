
package MMM.demo.Repositories;

import MMM.demo.Entities.ProductsCategories;
import java.util.List;

public interface ProductsCategoriesRepository {
    List<ProductsCategories> findAll();
}
