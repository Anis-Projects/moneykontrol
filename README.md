# Budget Management Terminal Application

This is a budget management terminal application designed to help you track and manage your income, expenses, and savings. It provides a simple interface for managing your financial data through a terminal interface.

## Steps to Use

### 1. Set Up the Database

1. **Import the Seeder File**
    - Open MySQL Workbench.
    - Connect to your MySQL server and select the database where you want to create the tables.
    - Import the seeder file (`setup_and_seed_september.sql`) to create tables and populate them with initial data. You can do this by opening the file in MySQL Workbench and running the script.

### 2. Configure the Application

1. **Set Up Database Connection**

- Open the configuration file (`DatabaseUtil`) in your terminal application.
- Configure the database connection settings, including the URL, username, and password to match your MySQL setup.

2. **Build and Run the Application Using IntelliJ IDEA**

- **Open Your Project**:
    - Launch IntelliJ IDEA and open your project.
- **Build the Project**:
    - Go to `Build` > `Build Project` or use the shortcut `Cmd + F9` to compile your code and build the application.
- **Run the Application**:
    - To run the application, you can create a Run Configuration:
        - Go to `Run` > `Edit Configurations`.
        - Click the `+` icon and select `Application`.
        - Set the `Main class` to the class containing the `public static void main(String[] args)` method.
        - Apply and save the configuration.
    - Click the `Run` button (green triangle) or use the shortcut `Shift + F10` to execute the application within IntelliJ IDEA.

### 3. Using the Application

1. **Index Page**

    - After launching the application, you will be presented with a menu that includes options for log in, sign up, and exit.
2. **Main Menu**

    - After logging in, you will be presented with a main menu that includes options for managing income, expenses, and savings.
3. **Manage Income**

    - Choose the option to add, update, or delete income records.
3. **Manage Expenses**

    - Choose the option to add, update, or delete expense records.
4. **Manage Savings**

    - Choose the option to add, update, or delete savings records.
5. **Generate Reports**

    - Generate and view financial reports based on your data.

### 4. Additional Features

- **Monthly Reports**
    - The application can generate monthly financial reports for analysis.
- **Data Export**
    - Export your data to CSV or other formats for external analysis.

### 5. Troubleshooting

- **Database Connection Issues**

    - Verify that the database connection settings in your configuration file are correct.
    - Ensure that your MySQL server is running and accessible.
- **Application Errors**

    - Check the application logs for detailed error messages.
    - Review the console output for any runtime errors or issues.
