
package Repositories;

import Entities.ProductsCategories;
import java.util.List;

public interface ProductsCategoriesRepository {
    List<ProductsCategories> findAll();
    ProductsCategories findById(int id_item, int id_category);
    int addProductsCategories(ProductsCategories productsCategories);
    int updateProductsCategories(ProductsCategories productsCategories);
    int deleteProductsCategories(int id_item, int id_category);
}
