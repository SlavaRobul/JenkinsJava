package db;

import java.util.Collections;
import java.util.List;

import com.geekbrains.db.dao.CategoriesMapper;
import com.geekbrains.db.dao.ProductsMapper;
import com.geekbrains.db.model.Categories;
import com.geekbrains.db.model.CategoriesExample;
import com.geekbrains.db.model.Products;
import com.geekbrains.db.model.ProductsExample;

public class TestDb {

    public static void main(String[] args) {
        long carsID = 0L;
        long boatID = 0L;
        long planeID = 0L;
        long trainID = 0L;

        DbService dbService = new DbService();
        ProductsMapper productsMapper = dbService.getProductsMapper();
        CategoriesMapper categoriesMapper = dbService.getCategoriesMapper();
        Products product = productsMapper.selectByPrimaryKey(1L);
        System.out.println(product);

        ProductsExample filterProduct = new ProductsExample();
        CategoriesExample filterCategory = new CategoriesExample();

        Categories tempCategory = new Categories();
        tempCategory.setTitle("Car");
        categoriesMapper.insert(tempCategory);

        tempCategory.setTitle("Boat");
        categoriesMapper.insert(tempCategory);

        tempCategory.setTitle("Plane");
        categoriesMapper.insert(tempCategory);

        tempCategory.setTitle("Train");
        categoriesMapper.insert(tempCategory);


        List<Categories> categories = categoriesMapper.selectByExample(filterCategory);
        for (Categories temp: categories) {
            System.out.println(temp.getTitle());
            if (temp.getTitle() == "Car") carsID = temp.getId();
            else if (temp.getTitle() == "Boat") boatID = temp.getId();
            else if (temp.getTitle() == "Plane") planeID = temp.getId();
            else if (temp.getTitle() == "Train") trainID = temp.getId();
        }

        Products forCreate = new Products();
        forCreate.setTitle("Ferrari");
        forCreate.setPrice(100000);
        forCreate.setCategoryId(carsID);
        productsMapper.insert(forCreate);

        forCreate.setTitle("Audi");
        forCreate.setPrice(50000);
        forCreate.setCategoryId(carsID);
        productsMapper.insert(forCreate);

        forCreate.setTitle("BMW");
        forCreate.setPrice(60000);
        forCreate.setCategoryId(carsID);
        productsMapper.insert(forCreate);

        forCreate.setTitle("Boat 1");
        forCreate.setPrice(1000000);
        forCreate.setCategoryId(boatID);
        productsMapper.insert(forCreate);

        forCreate.setTitle("Boat 2");
        forCreate.setPrice(2000000);
        forCreate.setCategoryId(boatID);
        productsMapper.insert(forCreate);

        forCreate.setTitle("Boat 3");
        forCreate.setPrice(3000000);
        forCreate.setCategoryId(boatID);
        productsMapper.insert(forCreate);

        forCreate.setTitle("Plane 1");
        forCreate.setPrice(1000000);
        forCreate.setCategoryId(planeID);
        productsMapper.insert(forCreate);

        forCreate.setTitle("Plane 2");
        forCreate.setPrice(1500000);
        forCreate.setCategoryId(planeID);
        productsMapper.insert(forCreate);

        forCreate.setTitle("Plane 3");
        forCreate.setPrice(2000000);
        forCreate.setCategoryId(planeID);
        productsMapper.insert(forCreate);

        forCreate.setTitle("Train 1");
        forCreate.setPrice(1000000);
        forCreate.setCategoryId(trainID);
        productsMapper.insert(forCreate);

        forCreate.setTitle("Train 2");
        forCreate.setPrice(2000000);
        forCreate.setCategoryId(trainID);
        productsMapper.insert(forCreate);

        forCreate.setTitle("Train 3");
        forCreate.setPrice(3000000);
        forCreate.setCategoryId(trainID);
        productsMapper.insert(forCreate);



        for (Categories temp: categories) {
            filterProduct.clear();
            filterProduct.createCriteria().andCategoryIdEqualTo(temp.getId());
            System.out.println(temp.getTitle() + ": " + productsMapper.selectByExample(filterProduct));
        }

        filterProduct.clear();
        for (Categories temp: categories) {
            filterProduct.clear();
            filterProduct.createCriteria().andCategoryIdEqualTo(temp.getId());
            System.out.println(temp.getTitle() + ": ");
            var products = productsMapper.selectByExample(filterProduct);
            Collections.sort(products, Products.COMPARE_BY_COUNT);
            int index = 0;
            while (index < 3){
                if (index <= products.size() - 1)
                    System.out.println(products.get(index));
                else break;
                index++;
            }
        }

        System.out.println();
        System.out.println("Most expensive:");
        filterProduct.clear();
        List<Products> productsExpensive = productsMapper.selectByExample(filterProduct);
        Collections.sort(productsExpensive, Products.COMPARE_BY_COUNT);
        for (int i = 0; i < 3; i++)
            System.out.println(productsExpensive.get(i));
        /*Products forCreate = new Products();
        forCreate.setTitle("Coca cola");
        forCreate.setPrice(50);
        forCreate.setCategoryId(1L);

        productsMapper.insert(forCreate);

        ProductsExample filter = new ProductsExample();

        List<Products> products = productsMapper.selectByExample(filter);
        System.out.println(products);

        filter.createCriteria()
                .andCategoryIdEqualTo(2L);

        System.out.println(productsMapper.selectByExample(filter));

        filter.clear();
        filter.createCriteria()
                .andPriceBetween(51, 1000);

        System.out.println(productsMapper.selectByExample(filter));

        product.setPrice(105);
        productsMapper.updateByPrimaryKey(product);

        System.out.println(productsMapper.selectByPrimaryKey(1L));*/
    }

}
