/**
 *
 */
package ua.store.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;
import java.util.TreeSet;

import ua.store.model.instances.products.Armchair;
import ua.store.model.instances.products.Cabinet;
import ua.store.model.instances.products.Product;
import ua.store.model.instances.products.ProductFactory;
import ua.store.model.instances.products.ProductType;
import ua.store.model.instances.products.Sofa;
import ua.store.model.instances.products.Table;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class ProductDAO implements MyLogger {

	/* (non-Javadoc)
	 * @see ua.shop.dao.DAO#create(java.lang.Object)
	 */
	public boolean create(Product product) {

		String additionalPropertyName = null;
		String additionalPropertyValue = null;

		switch (product.getProductType()) {
		case CABINET:
			additionalPropertyName = "internalVolume";
			additionalPropertyValue = String.valueOf(((Cabinet) product).getInternalVolume());
			break;
		case SOFA:
			additionalPropertyName = "doubleSize";
			additionalPropertyValue = String.valueOf(((Sofa) product).isDoubleSize());
			break;
		case ARMCHAIR:
			additionalPropertyName = "transformer";
			additionalPropertyValue = String.valueOf(((Armchair) product).isTransformer());
			break;
		case TABLE:
			additionalPropertyName = "surfaceType";
			additionalPropertyValue = String.valueOf(((Table) product).getSurfaceType());
			break;
		default:
			break;
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// send data to the table "products"
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.create);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setString(2, product.getProductType().toString());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setDouble(4, product.getPrice());
			preparedStatement.setInt(5, product.getQuantityInStock());
			preparedStatement.execute();
			preparedStatement.close();

			// get productId
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.create2);
			preparedStatement.setString(1, product.getProductName());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int productId = resultSet.getInt(1);
			preparedStatement.close();

			// send data to the table "add_prod_properties"
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.create3);
			preparedStatement.setInt(1, productId);
			preparedStatement.setString(2, additionalPropertyName);
			preparedStatement.setString(3, additionalPropertyValue);
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
			return false;
		} finally {
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a ResultSet", e);
			}
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

		return true;
	}

	/* (non-Javadoc)
	 * @see ua.shop.dao.DAO#update(java.lang.Object)
	 */
	public boolean update(Product product) {

		String additionalPropertyName = null;
		String additionalPropertyValue = null;

		switch (product.getProductType()) {
		case CABINET:
			additionalPropertyName = "internalVolume";
			additionalPropertyValue = String.valueOf(((Cabinet) product).getInternalVolume());
			break;
		case SOFA:
			additionalPropertyName = "doubleSize";
			additionalPropertyValue = String.valueOf(((Sofa) product).isDoubleSize());
			break;
		case ARMCHAIR:
			additionalPropertyName = "transformer";
			additionalPropertyValue = String.valueOf(((Armchair) product).isTransformer());
			break;
		case TABLE:
			additionalPropertyName = "surfaceType";
			additionalPropertyValue = String.valueOf(((Table) product).getSurfaceType());
			break;
		default:
			break;
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// send data to the table "products"
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.update);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setString(2, product.getProductType().toString());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setDouble(4, product.getPrice());
			preparedStatement.setInt(5, product.getQuantityInStock());
			preparedStatement.setInt(6, product.getProductId());
			preparedStatement.executeUpdate();
			preparedStatement.close();

			// send data to the table "add_prod_properties"
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.update2);
			preparedStatement.setString(1, additionalPropertyName);
			preparedStatement.setString(2, additionalPropertyValue);
			preparedStatement.setInt(3, product.getProductId());
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
			return false;
		} finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
				return true;
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
				return true;
			}
		}

		return true;
	}

	/* (non-Javadoc)
	 * @see ua.shop.dao.DAO#getAll()
	 */
	public TreeSet<Product> getAllProducts() {
		TreeSet<Product> productList = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = DAOFactory.createConnection();
		try {
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.getAllProducts);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				productList = createProductList(resultSet);
				if (logger.isDebugEnabled()) {
					logger.debug("Products list is successfully gotten from DB");
				}
				return productList;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Error. Products list is empty");
				}
				return null;
			}
		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
		} finally {
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a ResultSet", e);
			}
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

		return productList;
	}

	/**
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private TreeSet<Product> createProductList(ResultSet resultSet) throws SQLException {
		TreeSet<Product> productList = new TreeSet<>();
		resultSet.beforeFirst();
		while (resultSet.next()) {
			Product product = createProductFromResultSet(resultSet);
			productList.add(product);
		}
		return productList;
	}

	/* (non-Javadoc)
	 * @see ua.shop.dao.DAO#getByEntity(java.lang.Object)
	 */
	public Product getByName(String productName) {

		Product productSql = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// send data to the table "products"
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.getByName);
			preparedStatement.setString(1, productName);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				productSql = createProductFromResultSet(resultSet);
				if (logger.isDebugEnabled()) {
					logger.debug("Product \"" + productName + "\" is obtained");
				}
				return productSql;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Product \"" + productName + "\" is absent in DB");
				}
				return null;
			}
		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
			return null;
		} finally {
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a ResultSet", e);
			}
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

	}

	/**
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private Product createProductFromResultSet(ResultSet resultSet) throws SQLException {
		Product product = ProductFactory.createProduct(resultSet.getString("type"));
		product.setProductId(resultSet.getInt("product_id"));
		product.setProductName(resultSet.getString("name"));
		product.setPrice(resultSet.getDouble("price"));
		product.setDescription(resultSet.getString("description"));
		product.setQuantityInStock(resultSet.getInt("quantity_in_stock"));

		switch (product.getProductType()) {
		case CABINET:
			((Cabinet) product).setInternalVolume(Integer.valueOf(resultSet.getString("property_value")));
			break;
		case SOFA:
			((Sofa) product).setDoubleSize(Boolean.valueOf(resultSet.getString("property_value")));
			break;
		case ARMCHAIR:
			((Armchair) product).setTransformer(Boolean.valueOf(resultSet.getString("property_value")));
			break;
		case TABLE:
			((Table) product).setSurfaceType(String.valueOf(resultSet.getString("property_value")));
			break;
		default:
			break;
		}

		return product;
	}

	/**
	 * @param productName
	 * @return
	 */
	public boolean doesProductExist(String productName) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.doesProductExist);
			preparedStatement.setString(1, productName);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("An error occurred while executing a PreparedStatement", e);
			return false;
		} finally {
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a ResultSet", e);
			}
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

		return false;
	}

	/**
	 * @param productName
	 * @return
	 */
	public Product getByProductName(String productName) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Product productSql = null;

		try {
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.getByProductName);
			preparedStatement.setString(1, productName);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				productSql = createProductFromResultSet(resultSet);
				if (logger.isDebugEnabled()) {
					logger.debug("Product \"" + productName + "\" is obtained");
				}
				return productSql;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Product \"" + productName + "\" is absent in DB");
				}
				return null;
			}
		} catch (SQLException e) {
			logger.error(
					"An error occurred while executing the PreparedStatement", e);
			return null;
		} finally {
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a ResultSet", e);
			}
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

	}

	/**
	 * @param newProduct
	 * @return
	 */
	public boolean deleteByProduct(Product product) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOFactory.createConnection();

			// delete product from tables "products" and "add_prod_properties"
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.deleteByProduct);
			preparedStatement.setInt(1, product.getProductId());
			preparedStatement.executeUpdate();
			preparedStatement.close();

			if (logger.isDebugEnabled()) {
				logger.debug("Product \"" + product.getProductName() + "\" is deleted");
			}

			return true;

		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
			return false;
		} finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

	}

	/**
	 * @return
	 */
	public TreeSet<Product> getProductsByType(ProductType productType) {

		TreeSet<Product> productList = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = DAOFactory.createConnection();
		try {
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.getProductsByType);
			preparedStatement.setString(1, productType.toString());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				productList = createProductList(resultSet);
				if (logger.isDebugEnabled()) {
					logger.debug("Products list is successfully gotten from DB");
				}
				return productList;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Error. Products list is empty");
				}
				return null;
			}
		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
		} finally {
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a ResultSet", e);
			}
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

		return productList;
	}

	/**
	 * @return
	 */
	public TreeMap<Product, Integer> getAllProductsMap() {
		TreeMap<Product, Integer> productList = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = DAOFactory.createConnection();
		try {
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.getAllProductsMap);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				productList = createProductMap(resultSet);
				if (logger.isDebugEnabled()) {
					logger.debug("Products list is successfully gotten from DB");
				}
				return productList;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Error. Products list is empty");
				}
				return null;
			}
		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
		} finally {
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a ResultSet", e);
			}
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

		return productList;
	}

	/**
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private TreeMap<Product, Integer> createProductMap(ResultSet resultSet) throws SQLException {
		TreeMap<Product, Integer> productMap = new TreeMap<>();
		resultSet.beforeFirst();
		while (resultSet.next()) {
			Product product = createProductFromResultSet(resultSet);
			productMap.put(product, product.getQuantityInStock());
		}
		return productMap;
	}

	/**
	 * @param productType
	 * @return
	 */
	public TreeMap<Product, Integer> getProductsMapByType(ProductType productType) {
		TreeMap<Product, Integer> productMap = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = DAOFactory.createConnection();
		try {
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.getProductsMapByType);
			preparedStatement.setString(1, productType.toString());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				productMap = createProductMap(resultSet);
				if (logger.isDebugEnabled()) {
					logger.debug("Products list is successfully gotten from DB");
				}
				return productMap;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Error. Products list is empty");
				}
				return null;
			}
		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
		} finally {
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a ResultSet", e);
			}
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

		return productMap;
	}

	/**
	 *
	 */
	public void deleteAllProducts() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOFactory.createConnection();

			// delete products from tables "products" and "add_prod_properties"
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.deleteAllProducts);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			if (logger.isDebugEnabled()) {
				logger.debug("All products are deleted");
			}

		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
		} finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}
	}

	/**
	 *
	 */
	public void dropTables() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOFactory.createConnection();

			// drop tables "products" and "add_prod_properties"
			preparedStatement = connection.prepareStatement(ProductDAOSqlCommands.dropTables);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			if (logger.isDebugEnabled()) {
				logger.debug("All product tables are successfully dropped");
			}
		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
		} finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

	}



	/**
	 *
	 */
	public void createTables() {

		Connection connection = null;
		Statement statement = null;

		try {
			connection = DAOFactory.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();

			// create table "products"
			statement.addBatch(ProductDAOSqlCommands.createTableProducts);

			// create table "add_prod_properties"
			statement.addBatch(ProductDAOSqlCommands.createTableAddProdProperties);

			statement.executeBatch();
			connection.commit();
			connection.setAutoCommit(true);

			if (logger.isDebugEnabled()) {
				logger.debug("All products tables are successfully created");
			}
		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
		} finally {
			try {
				if (statement != null) statement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Statement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}
	}

}
