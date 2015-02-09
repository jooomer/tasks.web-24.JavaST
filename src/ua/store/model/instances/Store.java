/**
 *
 */
package ua.store.model.instances;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import ua.store.model.dao.DAOFactory;
import ua.store.model.dao.OrderDAO;
import ua.store.model.dao.ProductDAO;
import ua.store.model.dao.UserDAO;
import ua.store.model.instances.orders.Order;
import ua.store.model.instances.products.Product;
import ua.store.model.instances.products.ProductFactory;
import ua.store.model.instances.products.ProductType;
import ua.store.model.instances.users.User;
import ua.store.model.instances.users.UserFactory;
import ua.store.model.instances.users.UserType;

/**
 * @author Sergey
 *
 */
public class Store {
	private List<User> users = new LinkedList<>();
	private List<Product> products = new LinkedList<>();

	public static void initStore() {

		initializeDB();
		initializeUsers();
		initializeProducts();
//		initializeOrders();

	}

	/**
	 *
	 */
	private static void initializeDB() {
		UserDAO userDAO = DAOFactory.getUserDAO();
		ProductDAO productDAO = DAOFactory.getProductDAO();
		OrderDAO orderDAO = DAOFactory.getOrderDAO();

		// initialize schema
		userDAO.createSchema();

		// drop tables, sequence of dropping is important
		orderDAO.dropTables();
		productDAO.dropTables();
		userDAO.dropTables();

		// create tables
		userDAO.createTables();
		productDAO.createTables();
		orderDAO.createTables();

	}


	/**
	 *
	 */
	private static void initializeUsers() {
		User user1 = UserFactory.createUser(UserType.ADMIN);
		user1.setUserName("admin");
		user1.setPassword("a");
		user1.setFirstName("Freddy");
		user1.setLastName("Krugger");
		user1.setEmail("freddy@krugger.com");
		user1.setPhone("(103) 501-23-92");
		user1.setAddress("45, First str, New York, USA");
		user1.setComments("This user is an administrator");
		user1.setInBlackList(false);

		User user2 = UserFactory.createUser(UserType.CLIENT);
		user2.setUserName("client");
		user2.setPassword("c");
		user2.setFirstName("Jeremy");
		user2.setLastName("Smith");
		user2.setEmail("jeremy@smith.com");
		user2.setPhone("(103) 694-03-85");
		user2.setAddress("196, Second str, San Francisco, USA");
		user2.setComments("This user is a customer");
		user2.setInBlackList(false);

		User user3 = UserFactory.createUser(UserType.CLIENT);
		user3.setUserName("king");
		user3.setPassword("k");
		user3.setFirstName("Mike");
		user3.setLastName("Tyson");
		user3.setEmail("mike@tyson.com");
		user3.setPhone("(103) 343-98-34");
		user3.setAddress("196, Third str, Miami, USA");
		user3.setComments("This user is a bad boy");
		user3.setInBlackList(true);

		UserDAO userDao = DAOFactory.getUserDAO();
		userDao.create(user1);
		userDao.create(user2);
		userDao.create(user3);

	}

	/**
	 *
	 */
	private static void initializeProducts() {
		Product product1 = ProductFactory.createProduct(ProductType.ARMCHAIR);
		product1.setProductName("Armchair");
		product1.setPrice(300);
		product1.setDescription("This armchair has a good price");
		product1.setQuantityInStock(5);

		Product product2 = ProductFactory.createProduct(ProductType.CABINET);
		product2.setProductName("Cabinet");
		product2.setPrice(450);
		product2.setDescription("This cabinet has a modern design");
		product2.setQuantityInStock(12);

		Product product3 = ProductFactory.createProduct(ProductType.SOFA);
		product3.setProductName("Sofa");
		product3.setPrice(250);
		product3.setDescription("This sofa is very soft");
		product3.setQuantityInStock(8);

		Product product4 = ProductFactory.createProduct(ProductType.TABLE);
		product4.setProductName("Table");
		product4.setPrice(150);
		product4.setDescription("This table has a circle surface");
		product4.setQuantityInStock(8);

		ProductDAO productDao = DAOFactory.getProductDAO();
		productDao.create(product1);
		productDao.create(product2);
		productDao.create(product3);
		productDao.create(product4);

	}



	/**
	 *
	 */
	private static void initializeOrders() {

		UserDAO userDao = DAOFactory.getUserDAO();
		TreeSet<User> users = userDao.getAllUsers();

		ProductDAO productDAO = DAOFactory.getProductDAO();
		TreeSet<Product> products = productDAO.getAllProducts();

		// initial order for 1st user
		int userId1 = users.pollFirst().getUserId();
		Order order1 = new Order();
		order1.setUserId(userId1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String orderDate = sdf.format(new Date());
		order1.setOrderDate(orderDate);
		Product product1 = products.pollFirst();

		// this method is not finished yet


	}


}
