/**
 *
 */
package ua.store.model.dao;

/**
 * @author Sergey
 *
 */
public class ProductDAOSqlCommands {

	// create()
	final static String create = "INSERT INTO products"
			+ " (name, type, description, price, quantity_in_stock)"
			+ " VALUES (?, ?, ?, ?, ?)";

	final static String create2 = "SELECT product_id FROM products WHERE name = ?";

	final static String create3 = "INSERT INTO add_prod_properties"
			+ " (product_id, property_name, property_value)"
			+ " VALUES (?, ?, ?)";

	// update()
	final static String update = "UPDATE products"
			+ " SET name = ?,"
			+ " type = ?,"
			+ " description = ?,"
			+ " price = ?,"
			+ " quantity_in_stock = ?"
			+ " WHERE product_id = ?";

	final static String update2 = "UPDATE add_prod_properties"
			+ " SET property_name = ?,"
			+ " property_value = ?"
			+ "	WHERE product_id = ?";

	//  getAllProducts()
	final static String getAllProducts = "SELECT "
			+ "products.product_id, "
			+ "products.type, "
			+ "products.name, "
			+ "products.price, "
			+ "products.quantity_in_stock, "
			+ "products.description, "
			+ "add_prod_properties.property_name, "
			+ "add_prod_properties.property_value "
			+ "FROM products "
			+ "JOIN add_prod_properties "
			+ "ON add_prod_properties.product_id = products.product_id";

	//  getByName()
	final static String getByName = "SELECT * FROM products "
			+ "JOIN add_prod_properties "
			+ "ON products.product_id = add_prod_properties.product_id "
			+ "AND products.name = ?";

	//  doesProductExist()
	final static String doesProductExist = "SELECT name FROM products WHERE name = ?";

	//  getByProductName()
	final static String getByProductName = "SELECT "
			+ "products.product_id, "
			+ "products.type, "
			+ "products.name, "
			+ "products.price, "
			+ "products.quantity_in_stock, "
			+ "products.description, "
			+ "add_prod_properties.property_name, "
			+ "add_prod_properties.property_value "
			+ "FROM products "
			+ "JOIN add_prod_properties "
			+ "ON add_prod_properties.product_id = products.product_id "
			+ "AND products.name = ?";

	//  deleteByProduct()
	final static String deleteByProduct = "DELETE FROM products WHERE product_id = ?";

	//  getProductsByType()
	final static String getProductsByType = "SELECT "
			+ "products.product_id, "
			+ "products.type, "
			+ "products.name, "
			+ "products.price, "
			+ "products.quantity_in_stock, "
			+ "products.description, "
			+ "add_prod_properties.property_name, "
			+ "add_prod_properties.property_value "
			+ "FROM products "
			+ "JOIN add_prod_properties "
			+ "ON add_prod_properties.product_id = products.product_id "
			+ "AND products.type = ?";

	//  getAllProductsMap()
	final static String getAllProductsMap = "SELECT "
			+ "products.product_id, "
			+ "products.type, "
			+ "products.name, "
			+ "products.price, "
			+ "products.quantity_in_stock, "
			+ "products.description, "
			+ "add_prod_properties.property_name, "
			+ "add_prod_properties.property_value "
			+ "FROM products "
			+ "JOIN add_prod_properties "
			+ "ON add_prod_properties.product_id = products.product_id";

	//  getProductsMapByType()
	final static String getProductsMapByType = "SELECT "
			+ "products.product_id, "
			+ "products.type, "
			+ "products.name, "
			+ "products.price, "
			+ "products.quantity_in_stock, "
			+ "products.description, "
			+ "add_prod_properties.property_name, "
			+ "add_prod_properties.property_value "
			+ "FROM products "
			+ "JOIN add_prod_properties "
			+ "ON add_prod_properties.product_id = products.product_id "
			+ "AND products.type = ?";

	//  deleteAllProducts()
	final static String deleteAllProducts = "DELETE FROM products WHERE product_id > 0";

	//  dropTables()
	final static String dropTables = "DROP TABLE IF EXISTS add_prod_properties, products";

	//  createTables()
	final static String createTableProducts = "CREATE TABLE IF NOT EXISTS products ("
			+ "product_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, "
			+ "name VARCHAR(100) NULL DEFAULT NULL, "
			+ "type VARCHAR(20) NOT NULL, "
			+ "description TEXT NULL DEFAULT NULL, "
			+ "price DOUBLE UNSIGNED NULL DEFAULT NULL, "
			+ "quantity_in_stock INT(10) UNSIGNED NULL DEFAULT NULL, "
			+ "PRIMARY KEY (product_id)) "
			+ "ENGINE = InnoDB "
			+ "DEFAULT CHARACTER "
			+ "SET = utf8 "
			+ "COLLATE = utf8_general_ci";

	final static String createTableAddProdProperties = "CREATE TABLE IF NOT EXISTS add_prod_properties ("
			+ "id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, "
			+ "product_id INT(10) UNSIGNED NOT NULL, "
			+ "property_name VARCHAR(20) NULL DEFAULT NULL, "
			+ "property_value VARCHAR(20) NULL DEFAULT NULL, "
			+ "PRIMARY KEY (id), "
			+ "INDEX FK_add_prod_properties (product_id ASC), "
			+ "CONSTRAINT FK_add_prod_properties "
			+ "FOREIGN KEY (product_id) "
			+ "REFERENCES products (product_id) "
			+ "ON DELETE CASCADE "
			+ "ON UPDATE CASCADE) "
			+ "ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci";


}
