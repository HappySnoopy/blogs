/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.overdue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Snoopy
 */
public class XlsDto {

    private String lendId;

    private String planId;

    private String debtId;

    private int phase;

    private Date repayDate;

    private BigDecimal frozenBalance;

    private BigDecimal repayPrincipal;

    private BigDecimal repayInterest;

    private BigDecimal repayMgmtFee;

    private BigDecimal repayOtherFees;

    private BigDecimal penaltyInterest;

    private BigDecimal liquidatedFee;

    private BigDecimal balance;

    private int overdueDays;

    private Date settleDate;

    private BigDecimal deductAmount;

    private BigDecimal repaidAmount;

    private List<XlsDto> plans;

    /**
     * @return the {@link #repayPrincipal}
     */
    public BigDecimal getRepayPrincipal() {
        return this.repayPrincipal;
    }

    /**
     * @return the {@link #repayInterest}
     */
    public BigDecimal getRepayInterest() {
        return this.repayInterest;
    }

    /**
     * @return the {@link #repayMgmtFee}
     */
    public BigDecimal getRepayMgmtFee() {
        return this.repayMgmtFee;
    }

    /**
     * @return the {@link #repayOtherFees}
     */
    public BigDecimal getRepayOtherFees() {
        return this.repayOtherFees;
    }

    /**
     * @return the {@link #penaltyInterest}
     */
    public BigDecimal getPenaltyInterest() {
        return this.penaltyInterest;
    }

    /**
     * @return the {@link #liquidatedFee}
     */
    public BigDecimal getLiquidatedFee() {
        return this.liquidatedFee;
    }

    /**
     * @return the {@link #balance}
     */
    public BigDecimal getBalance() {
        return this.balance;
    }

    /**
     * @return the {@link #overdueDays}
     */
    public int getOverdueDays() {
        return this.overdueDays;
    }

    /**
     * @param repayPrincipal
     *        the {@link #repayPrincipal} to set
     */
    public void setRepayPrincipal(BigDecimal repayPrincipal) {
        this.repayPrincipal = repayPrincipal;
    }

    /**
     * @param repayInterest
     *        the {@link #repayInterest} to set
     */
    public void setRepayInterest(BigDecimal repayInterest) {
        this.repayInterest = repayInterest;
    }

    /**
     * @param repayMgmtFee
     *        the {@link #repayMgmtFee} to set
     */
    public void setRepayMgmtFee(BigDecimal repayMgmtFee) {
        this.repayMgmtFee = repayMgmtFee;
    }

    /**
     * @param repayOtherFees
     *        the {@link #repayOtherFees} to set
     */
    public void setRepayOtherFees(BigDecimal repayOtherFees) {
        this.repayOtherFees = repayOtherFees;
    }

    /**
     * @param penaltyInterest
     *        the {@link #penaltyInterest} to set
     */
    public void setPenaltyInterest(BigDecimal penaltyInterest) {
        this.penaltyInterest = penaltyInterest;
    }

    /**
     * @param liquidatedFee
     *        the {@link #liquidatedFee} to set
     */
    public void setLiquidatedFee(BigDecimal liquidatedFee) {
        this.liquidatedFee = liquidatedFee;
    }

    /**
     * @param balance
     *        the {@link #balance} to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @param overdueDays
     *        the {@link #overdueDays} to set
     */
    public void setOverdueDays(int overdueDays) {
        this.overdueDays = overdueDays;
    }

    /**
     * @return the {@link #lendId}
     */
    public String getLendId() {
        return this.lendId;
    }

    /**
     * @param lendId
     *        the {@link #lendId} to set
     */
    public void setLendId(String lendId) {
        this.lendId = lendId;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("XlsDto [");
        if (this.lendId != null) {
            builder.append("lendId=");
            builder.append(this.lendId);
            builder.append(", ");
        }
        if (this.planId != null) {
            builder.append("planId=");
            builder.append(this.planId);
            builder.append(", ");
        }
        if (this.debtId != null) {
            builder.append("debtId=");
            builder.append(this.debtId);
            builder.append(", ");
        }
        builder.append("phase=");
        builder.append(this.phase);
        builder.append(", ");
        if (this.repayDate != null) {
            builder.append("repayDate=");
            builder.append(this.repayDate);
            builder.append(", ");
        }
        if (this.frozenBalance != null) {
            builder.append("frozenBalance=");
            builder.append(this.frozenBalance);
            builder.append(", ");
        }
        if (this.repayPrincipal != null) {
            builder.append("repayPrincipal=");
            builder.append(this.repayPrincipal);
            builder.append(", ");
        }
        if (this.repayInterest != null) {
            builder.append("repayInterest=");
            builder.append(this.repayInterest);
            builder.append(", ");
        }
        if (this.repayMgmtFee != null) {
            builder.append("repayMgmtFee=");
            builder.append(this.repayMgmtFee);
            builder.append(", ");
        }
        if (this.repayOtherFees != null) {
            builder.append("repayOtherFees=");
            builder.append(this.repayOtherFees);
            builder.append(", ");
        }
        if (this.penaltyInterest != null) {
            builder.append("penaltyInterest=");
            builder.append(this.penaltyInterest);
            builder.append(", ");
        }
        if (this.liquidatedFee != null) {
            builder.append("liquidatedFee=");
            builder.append(this.liquidatedFee);
            builder.append(", ");
        }
        if (this.balance != null) {
            builder.append("balance=");
            builder.append(this.balance);
            builder.append(", ");
        }
        builder.append("overdueDays=");
        builder.append(this.overdueDays);
        builder.append(", ");
        if (this.settleDate != null) {
            builder.append("settleDate=");
            builder.append(this.settleDate);
            builder.append(", ");
        }
        if (this.deductAmount != null) {
            builder.append("deductAmount=");
            builder.append(this.deductAmount);
            builder.append(", ");
        }
        if (this.repaidAmount != null) {
            builder.append("repaidAmount=");
            builder.append(this.repaidAmount);
            builder.append(", ");
        }
        if (this.plans != null) {
            builder.append("plans=");
            builder.append(this.plans);
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * @return the {@link #planId}
     */
    public String getPlanId() {
        return this.planId;
    }

    /**
     * @return the {@link #debtId}
     */
    public String getDebtId() {
        return this.debtId;
    }

    /**
     * @return the {@link #phase}
     */
    public int getPhase() {
        return this.phase;
    }

    /**
     * @return the {@link #repayDate}
     */
    public Date getRepayDate() {
        return this.repayDate;
    }

    /**
     * @return the {@link #frozenBalance}
     */
    public BigDecimal getFrozenBalance() {
        return this.frozenBalance;
    }

    /**
     * @return the {@link #settleDate}
     */
    public Date getSettleDate() {
        return this.settleDate;
    }

    /**
     * @return the {@link #deductAmount}
     */
    public BigDecimal getDeductAmount() {
        return this.deductAmount;
    }

    /**
     * @return the {@link #repaidAmount}
     */
    public BigDecimal getRepaidAmount() {
        return this.repaidAmount;
    }

    /**
     * @param planId
     *        the {@link #planId} to set
     */
    public void setPlanId(String planId) {
        this.planId = planId;
    }

    /**
     * @param debtId
     *        the {@link #debtId} to set
     */
    public void setDebtId(String debtId) {
        this.debtId = debtId;
    }

    /**
     * @param phase
     *        the {@link #phase} to set
     */
    public void setPhase(int phase) {
        this.phase = phase;
    }

    /**
     * @param repayDate
     *        the {@link #repayDate} to set
     */
    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    /**
     * @param frozenBalance
     *        the {@link #frozenBalance} to set
     */
    public void setFrozenBalance(BigDecimal frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    /**
     * @param settleDate
     *        the {@link #settleDate} to set
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * @param deductAmount
     *        the {@link #deductAmount} to set
     */
    public void setDeductAmount(BigDecimal deductAmount) {
        this.deductAmount = deductAmount;
    }

    /**
     * @param repaidAmount
     *        the {@link #repaidAmount} to set
     */
    public void setRepaidAmount(BigDecimal repaidAmount) {
        this.repaidAmount = repaidAmount;
    }

    /**
     * @return the {@link #plans}
     */
    public List<XlsDto> getPlans() {
        return this.plans;
    }

    /**
     * @param plans
     *        the {@link #plans} to set
     */
    public void setPlans(List<XlsDto> plans) {
        this.plans = plans;
    }
}
