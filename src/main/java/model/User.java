package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import connection.SupaBaseDB;

public class User {
	private long id;
	private String username;
	private String password;

	public User(long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void add(User user) {
		String query = "INSERT INTO users (user_name, password) values(?, ?)";
		PreparedStatement prepareStatement = SupaBaseDB.getStatement(query);
		try {
			prepareStatement.setString(1, user.getUsername());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static User getUserByUserName(String userName) {
		for (User u : getUsers()) {
			if (u.getUsername().equals(userName))
				return u;
		}
		return null;
	}

	public static List<User> getUsers() {
		List<User> users = new ArrayList<>();
		try {
			String query = "SELECT * FROM users";
			PreparedStatement preparedStatement = SupaBaseDB.getStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			User user;
			while (resultSet.next()) {

				user = new User(resultSet.getInt("id"), resultSet.getString("user_name"),
						resultSet.getString("password"));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id && Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

}
