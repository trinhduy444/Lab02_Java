package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class ProductDAO implements Repository<Product, Integer>{
    public static ProductDAO getInstance(String connect) {
        return new ProductDAO() {
            @Override
            public Integer add(Product item) {
            Connection connection = JDBCUltil.getConnection(connect);
                try {
                    String sql = "INSERT INTO PRODUCT(NAME, PRICE, COLOR) VALUES(?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, item.getName());
                    preparedStatement.setInt(2, item.getPrice());
                    preparedStatement.setString(3, item.getColor());

                    int result = preparedStatement.executeUpdate();
                    if (result > 0) {
                        System.out.println("Successfully");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    if (connection != null) {
                        JDBCUltil.closeConnection(connection);
                    }
                }
                return null;
            }

            @Override
            public List<Product> readAll() {
                List<Product> products = new ArrayList<>();
                Connection connection = JDBCUltil.getConnection(connect);
                try {
                    String sql = "Select * from product";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int price = resultSet.getInt("price");
                        String color = resultSet.getString("color");

                        products.add(new Product(id, name, price, color));
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    if (connection != null) {
                        JDBCUltil.closeConnection(connection);
                    }
                }
                return products;
            }

            @Override
            public Product read(Integer id) {
                Connection connection = JDBCUltil.getConnection(connect);
                Product product = null;
                try {
                    String sql = "SELECT * FROM PRODUCT WHERE ID = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, id);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    while(resultSet.next()) {
                        int ID = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int price = resultSet.getInt("price");
                        String color = resultSet.getString("color");
                        
                        product = new Product(ID, name, price, color);
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    if (connection != null) {
                        JDBCUltil.closeConnection(connection);
                    }
                }
                return product;
            }

            @Override
            public boolean update(Product item) {
                Connection connection = JDBCUltil.getConnection(connect);
                try {
                    String sql = "UPDATE PRODUCT " +
                            "SET NAME=?, PRICE=?, COLOR=?" +
                            "WHERE ID = ?";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, item.getName());
                    preparedStatement.setInt(2, item.getPrice());
                    preparedStatement.setString(3, item.getColor());
                    preparedStatement.setInt(4, item.getId());

                    preparedStatement.executeUpdate();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    if (connection != null) {
                        JDBCUltil.closeConnection(connection);
                    }
                }
                return true;
            }

            @Override
            public boolean delete(Integer id) {
                Connection connection = JDBCUltil.getConnection(connect);
                try {
                    String sql = "DELETE FROM PRODUCT WHERE ID = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, id);

                   preparedStatement.executeUpdate();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    if (connection != null) {
                        JDBCUltil.closeConnection(connection);
                    }
                }
                return false;
            }
        };
    }
}
