/**
 *
 * All Rights Reserved
 */
package net.loyintean.blog.repay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 提前、逾期、结清试算使用的Rest接口参数
 * <p>
 * 字段是否返回视JsonView而定
 *
 * @author Snoopy
 */
public class Result4Calculate {

    // [start] Snoopy 2016-11-4 标志信息
    /*
     * 这部分信息可以用于标志一期还款计划表
     */
    /**
     * 进件id
     */

    private int lendId;

    /**
     * 还款计划表id
     */

    private int planId;

    /**
     * 债务id
     */

    private int debtId;

    /**
     * 当前期数
     */
    private int phase;

    /**
     * 还款日
     */

    private Date repayDate;
    // [end] Snoopy 2016-11-4 标志信息

    // [start] Snoopy 2016-11-4 金额数据
    /**
     * 账户余额
     */

    private BigDecimal balance = BigDecimal.ZERO;

    /**
     * 账户冻结余额
     */

    private BigDecimal frozenBalance = BigDecimal.ZERO;

    /**
     * 当期应还本金（个贷应还本息）
     */

    private BigDecimal repayPrincipal = BigDecimal.ZERO;

    /**
     * 当期应还利息
     */

    private BigDecimal repayInterest = BigDecimal.ZERO;

    /**
     * 应还服务费
     */

    private BigDecimal repayMgmtFee = BigDecimal.ZERO;

    /**
     * 应还其它费用
     */

    private BigDecimal repayOtherFees = BigDecimal.ZERO;

    /**
     * 减免金额
     */

    private BigDecimal deductAmount = BigDecimal.ZERO;

    /**
     * 已还金额
     * <p>
     * 青岛银行专用。非青岛银行返回0
     */

    private BigDecimal repaidAmount = BigDecimal.ZERO;

    /**
     * 当期应还逾期罚息
     * 默认为0。按日更新
     */
    private BigDecimal penaltyInterest = BigDecimal.ZERO;
    /**
     * 当期应还逾期违约金
     * 默认为0。目前不需按日更新
     */
    private BigDecimal liquidatedFee = BigDecimal.ZERO;

    /**
     * 当期期初金额
     * 默认为0
     */
    private BigDecimal originPrincipal = BigDecimal.ZERO;

    /**
     * 当期应付提前还款手续费返还
     * 默认为0
     */
    private BigDecimal inAdvanceBonus = BigDecimal.ZERO;

    /**
     * 当期应还提前还款违约金
     * 默认为0
     */
    private BigDecimal inAdvanceLiquidated = BigDecimal.ZERO;

    // [end] Snoopy 2016-11-4 金额数据

    // [start] Snoopy 2016-4-5 其它补充数据
    /*
     * 个别试算中需要补充的数据
     */

    /**
     * 逾期天数
     * <p>
     * 默认为0.
     */
    private int overdueDays;

    /**
     * 结清日
     */
    private Date settleDate;
    // [end] Snoopy 2016-4-5 其它补充数据

    /**
     * 分期信息
     */
    private List<Result4Calculate> plans;

    private BigDecimal total;

    private BigDecimal normalTotal;

    private BigDecimal normalMgmtTotal;

    /**
     * 默认构造方法
     */
    public Result4Calculate() {
        super();
    }


    /**
     * @return the {@link #lendId}
     */
    public int getLendId() {
        return this.lendId;
    }

    /**
     * @param lendId
     *        the {@link #lendId} to set
     */
    public void setLendId(Integer lendId) {
        this.lendId = lendId == null ? 0 : lendId;
    }

    /**
     * @return the {@link #planId}
     */
    public int getPlanId() {
        return this.planId;
    }

    /**
     * @param planId
     *        the {@link #planId} to set
     */
    public void setPlanId(Integer planId) {
        this.planId = planId == null ? 0 : planId;
    }

    /**
     * @return the {@link #debtId}
     */
    public int getDebtId() {
        return this.debtId;
    }

    /**
     * @param debtId
     *        the {@link #debtId} to set
     */
    public void setDebtId(Integer debtId) {
        this.debtId = debtId == null ? 0 : debtId;
    }

    /**
     * @return the {@link #phase}
     */
    public int getPhase() {
        return this.phase;
    }

    /**
     * @param currentPhase
     *        the {@link #phase} to set
     */
    public void setPhase(Integer currentPhase) {
        this.phase = currentPhase == null ? 0 : currentPhase;
    }

    /**
     * @return the {@link #repayDate}
     */
    public Date getRepayDate() {
        return this.repayDate;
    }

    /**
     * @param repayDate
     *        the {@link #repayDate} to set
     */
    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
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
     * @return the {@link #repayPrincipal}
     */
    public BigDecimal getRepayPrincipal() {
        return this.repayPrincipal;
    }

    /**
     * @param repayPrincipal
     *        the {@link #repayPrincipal} to set
     */
    public void setRepayPrincipal(BigDecimal repayPrincipal) {
        this.repayPrincipal = repayPrincipal;
    }

    /**
     * @return the {@link #repayInterest}
     */
    public BigDecimal getRepayInterest() {
        return this.repayInterest;
    }

    /**
     * @param repayInterest
     *        the {@link #repayInterest} to set
     */
    public void setRepayInterest(BigDecimal repayInterest) {
        this.repayInterest = repayInterest;
    }

    /**
     * @return the {@link #repayMgmtFee}
     */
    public BigDecimal getRepayMgmtFee() {
        return this.repayMgmtFee;
    }

    /**
     * @param repayMgmtFee
     *        the {@link #repayMgmtFee} to set
     */
    public void setRepayMgmtFee(BigDecimal repayMgmtFee) {
        this.repayMgmtFee = repayMgmtFee;
    }

    /**
     * @return the {@link #repayOtherFees}
     */
    public BigDecimal getRepayOtherFees() {
        return this.repayOtherFees;
    }

    /**
     * @param repayOtherFees
     *        the {@link #repayOtherFees} to set
     */
    public void setRepayOtherFees(BigDecimal repayOtherFees) {
        this.repayOtherFees = repayOtherFees;
    }

    /**
     * @return the {@link #penaltyInterest}
     */
    public BigDecimal getPenaltyInterest() {
        return this.penaltyInterest;
    }

    /**
     * @param penaltyInterest
     *        the {@link #penaltyInterest} to set
     */
    public void setPenaltyInterest(BigDecimal penaltyInterest) {
        this.penaltyInterest = penaltyInterest;
    }

    /**
     * @return the {@link #liquidatedFee}
     */
    public BigDecimal getLiquidatedFee() {
        return this.liquidatedFee;
    }

    /**
     * @param liquidatedFee
     *        the {@link #liquidatedFee} to set
     */
    public void setLiquidatedFee(BigDecimal liquidatedFee) {
        this.liquidatedFee = liquidatedFee;
    }

    /**
     * @return the {@link #inAdvanceBonus}
     */
    public BigDecimal getInAdvanceBonus() {
        return this.inAdvanceBonus;
    }

    /**
     * @param inAdvanceBonus
     *        the {@link #inAdvanceBonus} to set
     */
    public void setInAdvanceBonus(BigDecimal inAdvanceBonus) {
        this.inAdvanceBonus = inAdvanceBonus;
    }

    /**
     * @return the {@link #inAdvanceLiquidated}
     */
    public BigDecimal getInAdvanceLiquidated() {
        return this.inAdvanceLiquidated;
    }

    /**
     * @param inAdvanceLiquidated
     *        the {@link #inAdvanceLiquidated} to set
     */
    public void setInAdvanceLiquidated(BigDecimal inAdvanceLiquidated) {
        this.inAdvanceLiquidated = inAdvanceLiquidated;
    }

    /**
     * @return the {@link #deductAmount}
     */
    public BigDecimal getDeductAmount() {
        return this.deductAmount;
    }

    /**
     * @param deductAmount
     *        the {@link #deductAmount} to set
     */
    public void setDeductAmount(BigDecimal deductAmount) {
        this.deductAmount = deductAmount;
    }

    /**
     * @return the {@link #repaidAmount}
     */
    public BigDecimal getRepaidAmount() {
        return this.repaidAmount;
    }

    /**
     * @param repaidAmount
     *        the {@link #repaidAmount} to set
     */
    public void setRepaidAmount(BigDecimal repaidAmount) {
        this.repaidAmount = repaidAmount;
    }

    /**
     * @return the {@link #overdueDays}
     */
    public int getOverdueDays() {
        return this.overdueDays;
    }

    /**
     * @param overdueDays
     *        the {@link #overdueDays} to set
     */
    public void setOverdueDays(int overdueDays) {
        this.overdueDays = overdueDays;
    }

    /**
     * @return the {@link #settleDate}
     */
    public Date getSettleDate() {
        return this.settleDate;
    }

    /**
     * @param settleDate
     *        the {@link #settleDate} to set
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * @return the {@link #plans}
     */
    public List<Result4Calculate> getPlans() {
        return this.plans;
    }

    /**
     * @param plans
     *        the {@link #plans} to set
     */
    public void setPlans(List<Result4Calculate> plans) {
        this.plans = plans;
    }

    /**
     * @return the {@link #originPrincipal}
     */
    public BigDecimal getOriginPrincipal() {
        return this.originPrincipal;
    }

    /**
     * @param originPrincipal
     *        the {@link #originPrincipal} to set
     */
    public void setOriginPrincipal(BigDecimal originPrincipal) {
        this.originPrincipal = originPrincipal;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Result4Calculate [lendId=");
        builder.append(this.lendId);
        builder.append(", planId=");
        builder.append(this.planId);
        builder.append(", debtId=");
        builder.append(this.debtId);
        builder.append(", phase=");
        builder.append(this.phase);
        builder.append(", ");
        if (this.repayDate != null) {
            builder.append("repayDate=");
            builder.append(this.repayDate);
            builder.append(", ");
        }
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
        if (this.originPrincipal != null) {
            builder.append("originPrincipal=");
            builder.append(this.originPrincipal);
            builder.append(", ");
        }
        if (this.inAdvanceBonus != null) {
            builder.append("inAdvanceBonus=");
            builder.append(this.inAdvanceBonus);
            builder.append(", ");
        }
        if (this.inAdvanceLiquidated != null) {
            builder.append("inAdvanceLiquidated=");
            builder.append(this.inAdvanceLiquidated);
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
        if (this.plans != null) {
            builder.append("plans=");
            builder.append(this.plans);
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
     * @return the {@link #normalTotal}
     */
    public BigDecimal getNormalTotal() {
        return this.normalTotal;
    }

    /**
     * @param normalTotal
     *        the {@link #normalTotal} to set
     */
    public void setNormalTotal(BigDecimal normalTotal) {
        this.normalTotal = normalTotal;
    }

    /**
     * @return the {@link #normalMgmtTotal}
     */
    public BigDecimal getNormalMgmtTotal() {
        return this.normalMgmtTotal;
    }

    /**
     * @param normalMgmtTotal
     *        the {@link #normalMgmtTotal} to set
     */
    public void setNormalMgmtTotal(BigDecimal normalMgmtTotal) {
        this.normalMgmtTotal = normalMgmtTotal;
    }

}
