
package Repositories;

import Entities.Categories;
import java.util.List;

public interface CategoriesRepository {
    List<Categories> findAll();
    Categories findById();
    int addCategories(Categories categories);
    int updateCategories(Categories categories);
    int deleteCategories();
}
