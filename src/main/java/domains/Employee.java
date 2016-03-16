package domains;

/**
 * @author anhtuongnguyen
 *
 */
public class Employee {
    private String name;
    private String email;
    private String cellphone;

    /**
     * @return String
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return String
     */
    public String getEmail() {
	return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
	this.email = email;
    }

    /**
     * @return String
     */
    public String getCellphone() {
	return cellphone;
    }

    /**
     * @param cellphone
     */
    public void setCellphone(String cellphone) {
	this.cellphone = cellphone;
    }
}
