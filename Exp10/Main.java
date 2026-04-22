import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    // ── which table is currently shown ──
    private String currentTable = "MenuItem";  // default

    // ── MenuItem table columns ──
    private TableView<MenuItemModel> menuTable = new TableView<>();

    // ── Restaurant table columns ──
    private TableView<RestaurantModel> resTable = new TableView<>();

    // ── form area at the bottom ──
    private VBox formBox = new VBox(10);

    // ── status label ──
    private Label statusLabel = new Label("Ready.");

    // ─────────────────────────────────────────
    @Override
    public void start(Stage stage) {

        // ── 1. MenuBar ──────────────────────────
        MenuBar menuBar = new MenuBar();

        // Menu 1: MenuItem operations
        Menu miMenu = new Menu("MenuItem");
        javafx.scene.control.MenuItem miInsert = new javafx.scene.control.MenuItem("Insert");
        javafx.scene.control.MenuItem miSelect = new javafx.scene.control.MenuItem("Select All");
        javafx.scene.control.MenuItem miUpdate = new javafx.scene.control.MenuItem("Update");
        javafx.scene.control.MenuItem miDelete = new javafx.scene.control.MenuItem("Delete");
        miMenu.getItems().addAll(miInsert, miSelect, miUpdate, miDelete);

        // Menu 2: Restaurant operations
        Menu resMenu = new Menu("Restaurant");
        javafx.scene.control.MenuItem resInsert = new javafx.scene.control.MenuItem("Insert");
        javafx.scene.control.MenuItem resSelect = new javafx.scene.control.MenuItem("Select All");
        javafx.scene.control.MenuItem resUpdate = new javafx.scene.control.MenuItem("Update");
        javafx.scene.control.MenuItem resDelete = new javafx.scene.control.MenuItem("Delete");
        resMenu.getItems().addAll(resInsert, resSelect, resUpdate, resDelete);

        menuBar.getMenus().addAll(miMenu, resMenu);

        // ── 2. Build MenuItem TableView ──────────
        buildMenuItemTable();

        // ── 3. Build Restaurant TableView ────────
        buildRestaurantTable();

        // ── 4. Stack both tables in same spot ────
        // only one visible at a time
        StackPane tableArea = new StackPane(menuTable, resTable);
        resTable.setVisible(false);   // MenuItem shown by default

        // ── 5. Form area + status ────────────────
        formBox.setPadding(new Insets(10));
        VBox bottomBox = new VBox(10, formBox, statusLabel);
        bottomBox.setPadding(new Insets(10));

        // ── 6. Main layout ───────────────────────
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(tableArea);
        root.setBottom(bottomBox);

        // ── 7. Wire up MenuItem menu events ──────

        miInsert.setOnAction(e -> {
            currentTable = "MenuItem";
            showMenuItemInsertForm();
        });

        miSelect.setOnAction(e -> {
            currentTable = "MenuItem";
            switchToMenuItemTable(tableArea);
            loadMenuItems();
            formBox.getChildren().clear();
            statusLabel.setText("Showing all Menu Items.");
        });

        miUpdate.setOnAction(e -> {
            currentTable = "MenuItem";
            switchToMenuItemTable(tableArea);
            loadMenuItems();
            showMenuItemUpdateForm();
            statusLabel.setText("Click a row, edit fields, then click Update.");
        });

        miDelete.setOnAction(e -> {
            currentTable = "MenuItem";
            switchToMenuItemTable(tableArea);
            loadMenuItems();
            showMenuItemDeleteForm();
            statusLabel.setText("Click a row, then click Delete.");
        });

        // ── 8. Wire up Restaurant menu events ────

        resInsert.setOnAction(e -> {
            currentTable = "Restaurant";
            showRestaurantInsertForm(tableArea);
        });

        resSelect.setOnAction(e -> {
            currentTable = "Restaurant";
            switchToRestaurantTable(tableArea);
            loadRestaurants();
            formBox.getChildren().clear();
            statusLabel.setText("Showing all Restaurants.");
        });

        resUpdate.setOnAction(e -> {
            currentTable = "Restaurant";
            switchToRestaurantTable(tableArea);
            loadRestaurants();
            showRestaurantUpdateForm();
            statusLabel.setText("Click a row, edit fields, then click Update.");
        });

        resDelete.setOnAction(e -> {
            currentTable = "Restaurant";
            switchToRestaurantTable(tableArea);
            loadRestaurants();
            showRestaurantDeleteForm();
            statusLabel.setText("Click a row, then click Delete.");
        });

        // ── 9. Load default data ──────────────────
        loadMenuItems();

        // ── 10. Show window ───────────────────────
        Scene scene = new Scene(root, 750, 550);
        stage.setTitle("Restaurant JDBC Manager — Exp10");
        stage.setScene(scene);
        stage.show();
    }

    // ═══════════════════════════════════════════
    //  TABLE BUILDERS
    // ═══════════════════════════════════════════

    private void buildMenuItemTable() {
        TableColumn<MenuItemModel, Integer> idCol    = new TableColumn<>("ID");
        TableColumn<MenuItemModel, String>  nameCol  = new TableColumn<>("Name");
        TableColumn<MenuItemModel, Double>  priceCol = new TableColumn<>("Price");
        TableColumn<MenuItemModel, Integer> resIdCol = new TableColumn<>("ResId");

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        resIdCol.setCellValueFactory(new PropertyValueFactory<>("resId"));

        idCol.setPrefWidth(60);
        nameCol.setPrefWidth(200);
        priceCol.setPrefWidth(100);
        resIdCol.setPrefWidth(80);

        menuTable.getColumns().addAll(idCol, nameCol, priceCol, resIdCol);
        menuTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void buildRestaurantTable() {
        TableColumn<RestaurantModel, Integer> idCol      = new TableColumn<>("ID");
        TableColumn<RestaurantModel, String>  nameCol    = new TableColumn<>("Name");
        TableColumn<RestaurantModel, String>  addressCol = new TableColumn<>("Address");

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        idCol.setPrefWidth(60);
        nameCol.setPrefWidth(200);
        addressCol.setPrefWidth(300);

        resTable.getColumns().addAll(idCol, nameCol, addressCol);
        resTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    // ═══════════════════════════════════════════
    //  DATA LOADERS
    // ═══════════════════════════════════════════

    private void loadMenuItems() {
        ObservableList<MenuItemModel> data =
            FXCollections.observableArrayList(MenuItemDAO.selectAll());
        menuTable.setItems(data);
    }

    private void loadRestaurants() {
        ObservableList<RestaurantModel> data =
            FXCollections.observableArrayList(RestaurantDAO.selectAll());
        resTable.setItems(data);
    }

    // ═══════════════════════════════════════════
    //  TABLE SWITCHERS
    // ═══════════════════════════════════════════

    private void switchToMenuItemTable(StackPane area) {
        menuTable.setVisible(true);
        resTable.setVisible(false);
    }

    private void switchToRestaurantTable(StackPane area) {
        resTable.setVisible(true);
        menuTable.setVisible(false);
    }

    // ═══════════════════════════════════════════
    //  MENUITEM FORMS
    // ═══════════════════════════════════════════

    private void showMenuItemInsertForm() {
        formBox.getChildren().clear();

        TextField nameF  = new TextField(); nameF.setPromptText("Item Name");
        TextField priceF = new TextField(); priceF.setPromptText("Price");
        TextField resIdF = new TextField(); resIdF.setPromptText("Restaurant ID");
        Button    submitBtn = new Button("Insert");

        submitBtn.setOnAction(e -> {
            try {
                String name  = nameF.getText();
                double price = Double.parseDouble(priceF.getText());
                int    resId = Integer.parseInt(resIdF.getText());

                MenuItemDAO.insertItem(name, price, resId);
                loadMenuItems();
                formBox.getChildren().clear();
                statusLabel.setText("✅ MenuItem inserted successfully.");
            } catch (Exception ex) {
                statusLabel.setText("❌ Error: " + ex.getMessage());
            }
        });

        formBox.getChildren().addAll(
            new Label("── Insert MenuItem ──"),
            new HBox(10, new Label("Name:"), nameF),
            new HBox(10, new Label("Price:"), priceF),
            new HBox(10, new Label("ResId:"), resIdF),
            submitBtn
        );
    }

    private void showMenuItemUpdateForm() {
        formBox.getChildren().clear();

        TextField nameF  = new TextField(); nameF.setPromptText("New Name");
        TextField priceF = new TextField(); priceF.setPromptText("New Price");
        TextField resIdF = new TextField(); resIdF.setPromptText("New ResId");
        Button    submitBtn = new Button("Update");

        // auto-fill when row is clicked
        menuTable.getSelectionModel().selectedItemProperty()
            .addListener((obs, oldRow, newRow) -> {
                if (newRow != null) {
                    nameF.setText(newRow.getName());
                    priceF.setText(String.valueOf(newRow.getPrice()));
                    resIdF.setText(String.valueOf(newRow.getResId()));
                }
            });

        submitBtn.setOnAction(e -> {
            MenuItemModel selected = menuTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                statusLabel.setText("❌ Please select a row first.");
                return;
            }
            try {
                String name  = nameF.getText();
                double price = Double.parseDouble(priceF.getText());
                int    resId = Integer.parseInt(resIdF.getText());

                MenuItemDAO.updateItem(selected.getId(), name, price, resId);
                loadMenuItems();
                formBox.getChildren().clear();
                statusLabel.setText("✅ MenuItem updated successfully.");
            } catch (Exception ex) {
                statusLabel.setText("❌ Error: " + ex.getMessage());
            }
        });

        formBox.getChildren().addAll(
            new Label("── Update MenuItem (click a row first) ──"),
            new HBox(10, new Label("Name:"), nameF),
            new HBox(10, new Label("Price:"), priceF),
            new HBox(10, new Label("ResId:"), resIdF),
            submitBtn
        );
    }

    private void showMenuItemDeleteForm() {
        formBox.getChildren().clear();

        Label selectedLabel = new Label("No row selected.");
        Button deleteBtn    = new Button("Delete Selected Row");

        menuTable.getSelectionModel().selectedItemProperty()
            .addListener((obs, oldRow, newRow) -> {
                if (newRow != null) {
                    selectedLabel.setText("Selected: ID=" + newRow.getId()
                        + "  Name=" + newRow.getName());
                }
            });

        deleteBtn.setOnAction(e -> {
            MenuItemModel selected = menuTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                statusLabel.setText("❌ Please select a row first.");
                return;
            }
            MenuItemDAO.deleteItem(selected.getId());
            loadMenuItems();
            formBox.getChildren().clear();
            statusLabel.setText("✅ MenuItem deleted successfully.");
        });

        formBox.getChildren().addAll(
            new Label("── Delete MenuItem (click a row) ──"),
            selectedLabel,
            deleteBtn
        );
    }

    // ═══════════════════════════════════════════
    //  RESTAURANT FORMS
    // ═══════════════════════════════════════════

    private void showRestaurantInsertForm(StackPane tableArea) {
        switchToRestaurantTable(tableArea);
        loadRestaurants();
        formBox.getChildren().clear();

        TextField nameF    = new TextField(); nameF.setPromptText("Restaurant Name");
        TextField addressF = new TextField(); addressF.setPromptText("Address");
        Button    submitBtn = new Button("Insert");

        submitBtn.setOnAction(e -> {
            try {
                String name    = nameF.getText();
                String address = addressF.getText();

                RestaurantDAO.insertRestaurant(name, address);
                loadRestaurants();
                formBox.getChildren().clear();
                statusLabel.setText("✅ Restaurant inserted successfully.");
            } catch (Exception ex) {
                statusLabel.setText("❌ Error: " + ex.getMessage());
            }
        });

        formBox.getChildren().addAll(
            new Label("── Insert Restaurant ──"),
            new HBox(10, new Label("Name:"), nameF),
            new HBox(10, new Label("Address:"), addressF),
            submitBtn
        );
    }

    private void showRestaurantUpdateForm() {
        formBox.getChildren().clear();

        TextField nameF    = new TextField(); nameF.setPromptText("New Name");
        TextField addressF = new TextField(); addressF.setPromptText("New Address");
        Button    submitBtn = new Button("Update");

        resTable.getSelectionModel().selectedItemProperty()
            .addListener((obs, oldRow, newRow) -> {
                if (newRow != null) {
                    nameF.setText(newRow.getName());
                    addressF.setText(newRow.getAddress());
                }
            });

        submitBtn.setOnAction(e -> {
            RestaurantModel selected = resTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                statusLabel.setText("❌ Please select a row first.");
                return;
            }
            try {
                RestaurantDAO.updateRestaurant(
                    selected.getId(), nameF.getText(), addressF.getText());
                loadRestaurants();
                formBox.getChildren().clear();
                statusLabel.setText("✅ Restaurant updated successfully.");
            } catch (Exception ex) {
                statusLabel.setText("❌ Error: " + ex.getMessage());
            }
        });

        formBox.getChildren().addAll(
            new Label("── Update Restaurant (click a row first) ──"),
            new HBox(10, new Label("Name:"), nameF),
            new HBox(10, new Label("Address:"), addressF),
            submitBtn
        );
    }

    private void showRestaurantDeleteForm() {
        formBox.getChildren().clear();

        Label  selectedLabel = new Label("No row selected.");
        Button deleteBtn     = new Button("Delete Selected Row");

        resTable.getSelectionModel().selectedItemProperty()
            .addListener((obs, oldRow, newRow) -> {
                if (newRow != null) {
                    selectedLabel.setText("Selected: ID=" + newRow.getId()
                        + "  Name=" + newRow.getName());
                }
            });

        deleteBtn.setOnAction(e -> {
            RestaurantModel selected = resTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                statusLabel.setText("❌ Please select a row first.");
                return;
            }
            RestaurantDAO.deleteRestaurant(selected.getId());
            loadRestaurants();
            formBox.getChildren().clear();
            statusLabel.setText("✅ Restaurant deleted successfully.");
        });

        formBox.getChildren().addAll(
            new Label("── Delete Restaurant (click a row) ──"),
            selectedLabel,
            deleteBtn
        );
    }

    // ═══════════════════════════════════════════
    public static void main(String[] args) {
        launch(args);
    }
}
