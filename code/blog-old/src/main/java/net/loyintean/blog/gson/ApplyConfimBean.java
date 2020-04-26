package net.loyintean.blog.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author jingwan
 * 提交终审数据
 */
public class ApplyConfimBean implements java.io.Serializable{
	private static final long serialVersionUID = 2654334646351058302L;
    @Expose
	@SerializedName("userid")
    private String userId;

	/**
	 * 银行名称
	 */
    @Expose
	@SerializedName("bank_id")
    private String bankId;

	/**
	 * 银行卡号
	 */
    @Expose
	@SerializedName("card_no")
    private String cardNo;

	/**
	 * 持卡人姓名
	 */
    @Expose
   	@SerializedName("name")
    private String accountName;

	/**
	 * 手机号码
	 */
    private String phone;

	/**
	 * 身份证号
	 */
    @Expose
	@SerializedName("id_no")
    private String idNo;

    /**
	 * 开户省份(按宜人贷提供的字典)
	 */
    private String bankProvince;

    /**
	 * 开户城市(按宜人贷提供的字典)
	 */
    private String bankCity;

    /**
	 * 支行名称
	 */
    private String bankDetailName;

	/**
	 * 宜人贷用户id
	 */
	private String yrdUserId;

	/**
	 * 借款申请的主键
	 */
	private String transportId;

	/**
	 * 手机型号
	 */
	private String model;

	/**
	 * 宜人贷applyId
	 */
	private String yrdApplyId;

    /**
	 * 产品code
	 */
	private String productCode;

	/**
	 * 借款金额
	 */
	private String applyAmount;

	/**
	 * 借款期数
	 */
	private String applyTerm;

	/**
	 * 借款费率
	 */
	private String applyRate;

	/**
	 * 淘宝抓取数据标识　1为非测试用户　4为测试用户
	 */
	private String crawlContent;

	/**
	 * 保险公司code
	 */
	private String insuranceCompanyCode;

	/**
	 * 是否需要进信审 T:需要 F：不需要
	 */
    private String cpoMark;

	/**
	 * PICC承保标签
	 */
	private String guaranteeMethod;

	/**
	 * 户籍地址
	 */
	private String householdAddress;

	/**
	 * 用户是否走了京东抓取 1是 其他都不是
	 */
	private String isJD;

	public String getIsJD() {
		return isJD;
	}

	public void setIsJD(String isJD) {
		this.isJD = isJD;
	}

	public String getHouseholdAddress() {
		return householdAddress;
	}

	public void setHouseholdAddress(String householdAddress) {
		this.householdAddress = householdAddress;
	}

	public String getGuaranteeMethod() {
		return guaranteeMethod;
	}

	public void setGuaranteeMethod(String guaranteeMethod) {
		this.guaranteeMethod = guaranteeMethod;
	}

	public String getCpoMark() {
		return cpoMark;
	}

	public void setCpoMark(String cpoMark) {
		this.cpoMark = cpoMark;
	}

	public String getInsuranceCompanyCode() {
		return insuranceCompanyCode;
	}

	public void setInsuranceCompanyCode(String insuranceCompanyCode) {
		this.insuranceCompanyCode = insuranceCompanyCode;
	}

	public String getCrawlContent() {
		return crawlContent;
	}

	public void setCrawlContent(String crawlContent) {
		this.crawlContent = crawlContent;
	}

	public String getApplyRate() {
		return applyRate;
	}

	public void setApplyRate(String applyRate) {
		this.applyRate = applyRate;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getApplyTerm() {
		return applyTerm;
	}

	public void setApplyTerm(String applyTerm) {
		this.applyTerm = applyTerm;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getYrdApplyId() {
		return yrdApplyId;
	}

	public void setYrdApplyId(String yrdApplyId) {
		this.yrdApplyId = yrdApplyId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYrdUserId() {
		return yrdUserId;
	}

	public void setYrdUserId(String yrdUserId) {
		this.yrdUserId = yrdUserId;
	}

	public String getTransportId() {
		return transportId;
	}

	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getBankProvince() {
		return bankProvince;
	}
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getBankDetailName() {
		return bankDetailName;
	}
	public void setBankDetailName(String bankDetailName) {
		this.bankDetailName = bankDetailName;
	}
}