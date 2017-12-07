package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.model.Person;

public class PersonOverviewController {

	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private Label streetLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label phoneLabel;

	// Ссылка на главное приложение.
	private Main mainApp;

	/**
	 * Конструктор.
	 * Конструктор вызывается раньше метода initialize().
	 */
	public PersonOverviewController() {
	}

	/**
	 * Инициализация класса-контроллера. Этот метод вызывается автоматически
	 * после того, как fxml-файл будет загружен.
	 */
	@FXML
	private void initialize() {
		// Инициализация таблицы адресатов с двумя столбцами.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
		// Очистка дополнительной информации об адресате.
		showPersonDetails(null);

		// Слушаем изменения выбора, и при изменении отображаем
		// дополнительную информацию об адресате.
		personTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	/**
	 * Вызывается, когда пользователь кликает по кнопке удаления.
	 */
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
		} else {
			// Ничего не выбрано.
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	/**
	 * Вызывается, когда пользователь кликает по кнопке New...
	 * Открывает диалоговое окно с дополнительной информацией нового адресата.
	 */
	@FXML
	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if (okClicked) {
			mainApp.getPersonData().add(tempPerson);
		}
	}

	/**
	 * Вызывается, когда пользователь кликает по кнопка Edit...
	 * Открывает диалоговое окно для изменения выбранного адресата.
	 */
	@FXML
	private void handleEditPerson() {
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				showPersonDetails(selectedPerson);
			}

		} else {
			// Ничего не выбрано.
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	/**
	 * Вызывается главным приложением, которое даёт на себя ссылку.
	 *
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

		// Добавление в таблицу данных из наблюдаемого списка
		personTable.setItems(mainApp.getPersonData());
	}

	/**
	 * Заполняет все текстовые поля, отображая подробности об адресате.
	 * Если указанный адресат = null, то все текстовые поля очищаются.
	 *
	 * @param person — адресат типа Person или null
	 */
	private void showPersonDetails(Person person) {
		if (person != null) {
			// Заполняем метки информацией из объекта person.
			streetLabel.setText(person.getStreet());
			phoneLabel.setText(Long.toString(person.getPhone()));
			cityLabel.setText(person.getCity());
		} else {
			// Если Person = null, то убираем весь текст.
			streetLabel.setText("");
			phoneLabel.setText("");
			cityLabel.setText("");
		}
	}

}
