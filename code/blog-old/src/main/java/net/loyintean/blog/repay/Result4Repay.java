/**
 *
 * All Rights Reserved
 */
package net.loyintean.blog.repay;

import java.math.BigDecimal;

/**
 * 试算结果
 * <p>
 * 默认结果全部为0
 *
 * @author Snoopy
 */
public class Result4Repay {

    /**
     * 账户余额
     */

    private BigDecimal balance = BigDecimal.ZERO;

    /**
     * 账户冻结余额
     */

    private BigDecimal frozenBalance = BigDecimal.ZERO;

    /**
     * 逾期数据
     */

    private Result4Calculate overdue = new Result4Calculate();

    /**
     * 当期数据
     */

    private Result4Calculate current = new Result4Calculate();

    /**
     * 提前结清数据
     */

    private Result4Calculate advance = new Result4Calculate();

    private Result4Calculate canDoAdvanceA = new Result4Calculate();

    private BigDecimal total;

    /**
     * @return the {@link #overdue}
     */
    public Result4Calculate getOverdue() {
        return this.overdue;
    }

    /**
     * @param overdue
     *        the {@link #overdue} to set
     */
    public void setOverdue(Result4Calculate overdue) {
        this.overdue = overdue;
    }

    /**
     * @return the {@link #current}
     */
    public Result4Calculate getCurrent() {
        return this.current;
    }

    /**
     * @param current
     *        the {@link #current} to set
     */
    public void setCurrent(Result4Calculate current) {
        this.current = current;
    }

    /**
     * @return the {@link #advance}
     */
    public Result4Calculate getAdvance() {
        return this.advance;
    }

    /**
     * @param advance
     *        the {@link #advance} to set
     */
    public void setAdvance(Result4Calculate advance) {
        this.advance = advance;
    }

    /**
     * @return the {@link #balance}
     */
    public BigDecimal getBalance() {
        return this.balance;
    }

    /**
     * @param balance
     *        the {@link #balance} to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @return the {@link #frozenBalance}
     */
    public BigDecimal getFrozenBalance() {
        return this.frozenBalance;
    }

    /**
     * @param frozenBalance
     *        the {@link #frozenBalance} to set
     */
    public void setFrozenBalance(BigDecimal frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Result4Repay [");
        if (this.balance != null) {
            builder.append("balance=");
            builder.append(this.balance);
            builder.append(", ");
        }
        if (this.frozenBalance != null) {
            builder.append("frozenBalance=");
            builder.append(this.frozenBalance);
            builder.append(", ");
        }
        if (this.overdue != null) {
            builder.append("overdue=");
            builder.append(this.overdue);
            builder.append(", ");
        }
        if (this.current != null) {
            builder.append("current=");
            builder.append(this.current);
            builder.append(", ");
        }
        if (this.advance != null) {
            builder.append("advance=");
            builder.append(this.advance);
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * @return the {@link #total}
     */
    public BigDecimal getTotal() {
        return this.total;
    }

    /**
     * @param total
     *        the {@link #total} to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the {@link #canDoAdvanceA}
     */
    public Result4Calculate getCanDoAdvanceA() {
        return this.canDoAdvanceA;
    }

    /**
     * @param canDoAdvanceA
     *        the {@link #canDoAdvanceA} to set
     */
    public void setCanDoAdvanceA(Result4Calculate canDoAdvanceA) {
        this.canDoAdvanceA = canDoAdvanceA;
    }
}
