/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.repay;

/**
 * @author Snoopy
 */
public class RestResult4Repay {
    private boolean success;

    private Result4Repay data;

    /**
     * @return the {@link #success}
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * @return the {@link #data}
     */
    public Result4Repay getData() {
        return this.data;
    }

    /**
     * @param success
     *        the {@link #success} to set
     */
    /**
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @param data
     *        the {@link #data} to set
     */
    public void setData(Result4Repay data) {
        this.data = data;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RestResult4Repay [success=");
        builder.append(this.success);
        builder.append(", ");
        if (this.data != null) {
            builder.append("data=");
            builder.append(this.data);
        }
        builder.append("]");
        return builder.toString();
    }

}
