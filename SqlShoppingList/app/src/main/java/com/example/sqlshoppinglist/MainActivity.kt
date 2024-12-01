package com.example.sqlshoppinglist

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlshoppinglist.adapters.ProductAdapter
import com.example.sqlshoppinglist.models.Product
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val executor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setupAddButton()
        fetchProducts()
    }

    private fun setupRecyclerView() {
        productRecyclerView = findViewById(R.id.productRecyclerView)
        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(
            emptyList(),
            onEdit = { product -> showEditDialog(product) },
            onDelete = { product -> deleteProduct(product) }
        )
        productRecyclerView.adapter = productAdapter
    }

    private fun setupAddButton() {
        findViewById<Button>(R.id.addProductButton).setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_product, null)

        AlertDialog.Builder(this)
            .setTitle("Add Product")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = dialogView.findViewById<EditText>(R.id.productNameInput).text.toString()
                val count = dialogView.findViewById<EditText>(R.id.productCountInput).text.toString().toIntOrNull() ?: 0
                if (name.isNotBlank()) {
                    addProduct(name, count)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditDialog(product: Product) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_product, null)

        dialogView.findViewById<EditText>(R.id.productNameInput).setText(product.name)
        dialogView.findViewById<EditText>(R.id.productCountInput).setText(product.count.toString())

        AlertDialog.Builder(this)
            .setTitle("Edit Product")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val name = dialogView.findViewById<EditText>(R.id.productNameInput).text.toString()
                val count = dialogView.findViewById<EditText>(R.id.productCountInput).text.toString().toIntOrNull() ?: 0
                if (name.isNotBlank()) {
                    updateProduct(product.copy(name = name, count = count))
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun addProduct(name: String, count: Int) {
        executor.execute {
            try {
                val connection = connectToDatabase()
                val query = "INSERT INTO Product (name, count, list_id, checked, count_type) VALUES (?, ?, 1, 0, 1)"
                connection.prepareStatement(query).use { statement ->
                    statement.setString(1, name)
                    statement.setInt(2, count)
                    statement.executeUpdate()
                }
                connection.close()
                fetchProducts()
            } catch (e: Exception) {
                showError("Failed to add product: ${e.message}")
            }
        }
    }

    private fun updateProduct(product: Product) {
        executor.execute {
            try {
                val connection = connectToDatabase()
                val query = "UPDATE Product SET name = ?, count = ? WHERE _id = ?"
                connection.prepareStatement(query).use { statement ->
                    statement.setString(1, product.name)
                    statement.setInt(2, product.count)
                    statement.setInt(3, product._id)
                    statement.executeUpdate()
                }
                connection.close()
                fetchProducts()
            } catch (e: Exception) {
                showError("Failed to update product: ${e.message}")
            }
        }
    }

    private fun deleteProduct(product: Product) {
        AlertDialog.Builder(this)
            .setTitle("Delete Product")
            .setMessage("Are you sure you want to delete ${product.name}?")
            .setPositiveButton("Delete") { _, _ ->
                executor.execute {
                    try {
                        val connection = connectToDatabase()
                        val query = "DELETE FROM Product WHERE _id = ?"
                        connection.prepareStatement(query).use { statement ->
                            statement.setInt(1, product._id)
                            statement.executeUpdate()
                        }
                        connection.close()
                        fetchProducts()
                    } catch (e: Exception) {
                        showError("Failed to delete product: ${e.message}")
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showError(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun fetchProducts() {
        executor.execute {
            try {
                val connection = connectToDatabase()
                val products = queryProducts(connection)
                runOnUiThread {
                    if (products.isEmpty()) {
                        Toast.makeText(this, "No products found.", Toast.LENGTH_LONG).show()
                    } else {
                        productAdapter.updateProducts(products)
                    }
                }
            } catch (e: Exception) {
                val errorMessage = "Database error: ${e.message}"
                Log.e("MainActivity", errorMessage, e)
                runOnUiThread {
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun connectToDatabase(): Connection {
        val jdbcUrl = "jdbc:mysql://192.168.1.192:3306/ShoppingApp"
        val jdbcUser = "netroot"
        val jdbcPassword = "0000"

        try {
            val driverClass = Class.forName("com.mysql.jdbc.Driver").newInstance()
            DriverManager.registerDriver(driverClass as java.sql.Driver)

            return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)
        } catch (e: Exception) {
            Log.e("Database", "Connection error", e)
            throw Exception("Failed to connect to database. Ensure the server is running, and IP, port, and credentials are correct.", e)
        }
    }


    private fun queryProducts(connection: Connection): List<Product> {
        val query = "SELECT _id, name, count, list_id, checked, count_type FROM Product"
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(query)

        val products = mutableListOf<Product>()
        while (resultSet.next()) {
            products.add(
                Product(
                    _id = resultSet.getInt("_id"),
                    name = resultSet.getString("name"),
                    count = resultSet.getInt("count"),
                    list_id = resultSet.getInt("list_id"),
                    checked = resultSet.getBoolean("checked"),
                    count_type = resultSet.getInt("count_type")
                )
            )
        }
        resultSet.close()
        statement.close()
        connection.close()
        return products
    }
}