/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.overdue;

/**
 * @author Snoopy
 */
public class RestResult4OverdueCalculate {

    private boolean success;

    private XlsDto data;

    /**
     * @return the {@link #success}
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * @return the {@link #data}
     */
    public XlsDto getData() {
        return this.data;
    }

    /**
     * @param success
     *        the {@link #success} to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @param data
     *        the {@link #data} to set
     */
    public void setData(XlsDto data) {
        this.data = data;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RestResult4OverdueCalculate [success=");
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
