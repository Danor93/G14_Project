package querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.text.html.parser.Entity;

import Entities.Dish;
import Entities.DishType;
import Entities.Order;
import Entities.OrderType;
import Entities.User;

public class ShowDishes {
	public static ArrayList<Dish> getDishes(Object object) {
		ArrayList<Dish> dishes = new ArrayList<>();
		String ID = (String) object;
		System.out.println(ID);
		PreparedStatement stmt;
		String query = "";
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("SELECT * FROM bytemedatabase.dishes WHERE restId1=? ORDER BY dishType");
				stmt.setString(1, ID);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Dish dish = new Dish(rs.getString("dishName"), rs.getString("supplierName"),
							query, query, query, query, Float.parseFloat(rs.getString("price")), Integer.parseInt(rs.getString("inventory")),
							DishType.toDishType(rs.getString("dishType")));//need to be fix.
					dish.setRestCode(rs.getString("restId1"));
					dish.setChoiceFactor(rs.getString("choiceFactor"));
					//dish.setde(rs.getString("choiceDetails"));
					dish.setIngredients(rs.getString("ingredients"));
					dish.setExtra(rs.getString("extra"));
					dishes.add(dish);
				}
				rs.close();
			} else {
				System.out.println("Conn is null");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dishes;
	}
}