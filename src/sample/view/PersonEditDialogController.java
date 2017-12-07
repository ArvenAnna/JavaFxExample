package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Person;

/**
 * Created by hanna on 9/8/17.
 */
public class PersonEditDialogController {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField cityField;

	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;

	/**
	 * Инициализирует класс-контроллер. Этот метод вызывается автоматически
	 * после того, как fxml-файл будет загружен.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Устанавливает сцену для этого окна.
	 *
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Задаёт адресата, информацию о котором будем менять.
	 *
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;

		firstNameField.setText(person.getName());
		lastNameField.setText(person.getSurname());
		streetField.setText(person.getStreet());
		if (person.getPhone() != null) {
			phoneField.setText(Long.toString(person.getPhone()));
		} else {
			phoneField.setText(null);
		}
		cityField.setText(person.getCity());
	}

	/**
	 * Returns true, если пользователь кликнул OK, в другом случае false.
	 *
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Вызывается, когда пользователь кликнул по кнопке OK.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			person.setName(firstNameField.getText());
			person.setSurname(lastNameField.getText());
			person.setStreet(streetField.getText());
			person.setPhone(Long.parseLong(phoneField.getText()));
			person.setCity(cityField.getText());

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Вызывается, когда пользователь кликнул по кнопке Cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Проверяет пользовательский ввод в текстовых полях.
	 *
	 * @return true, если пользовательский ввод корректен
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (streetField.getText() == null || streetField.getText().length() == 0) {
			errorMessage += "No valid street!\n";
		}

		if (phoneField.getText() == null || phoneField.getText().length() == 0) {
			errorMessage += "No valid phone!\n";
		} else {
			try {
				Long.parseLong(phoneField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}

		if (cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Показываем сообщение об ошибке.
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
}
