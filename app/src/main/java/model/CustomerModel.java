package model;

public class CustomerModel {
    private int mId;
    private String mName;
    private int mAge;
    private boolean mIsActive;


    //Constructores

    /**
     * Constructor
     */
    public CustomerModel() {
    }

    /**
     * Constructor
     * @param mName
     * @param mAge
     * @param mIsActive
     */
    public CustomerModel(String mName, int mAge, boolean mIsActive) {
        this.mName = mName;
        this.mAge = mAge;
        this.mIsActive = mIsActive;
    }

    /**
     * Constructor
     * @param mId
     * @param mName
     * @param mAge
     * @param mIsActive
     */
    public CustomerModel(int mId, String mName, int mAge, boolean mIsActive) {
        this.mId = mId;
        this.mName = mName;
        this.mAge = mAge;
        this.mIsActive = mIsActive;
    }

    //getters and setters


    /**
     *
     * @return
     */
    public int getId() {
        return this.mId;
    }

    /**
     *
     * @param mId
     */
    public void setId(int mId) {
        this.mId = mId;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.mName;
    }

    /**
     *
     * @param mName Nombre del cliente
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    public int getAge() {
        return this.mAge;
    }

    /**
     *
     * @param mAge Edad del cliente
     */
    public void setAge(int mAge) {
        this.mAge = mAge;
    }

    /**
     *
     * @return
     */
    public boolean isActive() {
        return this.mIsActive;
    }

    /**
     *
     * @param mIsActive
     */
    public void setIsActive(boolean mIsActive) {
        this.mIsActive = mIsActive;
    }

    /**
     * Imprime el contenido de la clase
     * @return
     */
    @Override
    public String toString() {
        return "CustomerModel{" +
                "Id=" + mId +
                ", Name='" + mName + '\'' +
                ", Age=" + mAge +
                ", IsActive=" + mIsActive +
                '}';
    }
}
