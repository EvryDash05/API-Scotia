package com.example.api_scotia.commons.constants;

public abstract class DataBaseConstants {

    /**
     * <h1>SECURITY TABLES</h1>
     * <h2>Authority table 🛢</h2>
     */
    public static final String AUTHORITIES_TABLE = "tb_authorities";
    public static final String AUTHORITIES_ID = "authority_id";
    public static final String AUTHORITIES_NAME = "authority";

    /**
     * <h2>Role table 🛢</h2>
     */
    public static final String ROLES_TABLE = "tb_roles";
    public static final String ROLES_ID = "role_id";
    public static final String ROLES_NAME = "role_name";

    /**
     * <h1>BUSINESS TABLES</h1>
     * <h2>Client table 🛢</h2>
     */
    public static final String CLIENT_TABLE = "tb_client";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_NAME = "name";
    public static final String CLIENT_LAST_NAME = "last_name";
    public static final String CLIENT_PHONE = "phone";
    public static final String CLIENT_ADDRESS = "address";
    public static final String CLIENT_EMAIL = "email";
    public static final String CLIENT_PASSWORD = "password";

    /**
     * <h2>Customer Financial Info table 🛢</h2>
     */
    public static final String CUSTOMER_FINANCIAL_INFO_TABLE = "tb_customer_financial_info";
    public static final String CUSTOMER_FINANCIAL_INFO_ID = "info_id";
    public static final String CUSTOMER_CLIENT_ID = "client_id";
    public static final String CUSTOMER_JOB_TYPE = "job_type";
    public static final String CUSTOMER_MONTHLY_INCOME = "monthly_income";
    public static final String CUSTOMER_FIXED_EXPENSES = "fixed_expenses";
    public static final String CUSTOMER_EXCESS_PAYMENT = "excess_payment";

    /**
     * <h2>Loan table 🛢</h2>
     */
    public static final String LOAN_TABLE = "tb_loan";
    public static final String LOAN_ID = "loan_id";
    public static final String LOAN_CLIENT_ID = "client_id";
    public static final String LOAN_REQUEST_TYPE = "request_type";
    public static final String LOAN_TOTAL_AMOUNT = "total_amount";
    public static final String LOAN_STATUS = "request_status";
    public static final String LOAN_DATE = "request_date";
    public static final String LOAN_INSTALLMENTS = "installments";

    /**
     * <h2>Client Payment table 🛢</h2>
     */
    public static final String CLIENT_PAYMENT_TABLE = "tb_client_payment";
    public static final String CLIENT_PAYMENT_ID = "payment_id";
    public static final String CLIENT_PAYMENT_DATE = "payment_date";
    public static final String CLIENT_PAYMENT_STATUS = "payment_status";
    public static final String CLIENT_PAYMENT_AMOUNT = "amount";

    /**
     * <h2>Credit Cards table 🛢</h2>
     */
    public static final String CREDIT_CARDS_TABLE = "tb_credit_cards";
    public static final String CREDIT_CARDS_ID = "card_id";
    public static final String CREDIT_CARDS_CLIENT_ID = "client_id";
    public static final String CREDIT_CARDS_NUMBER = "card_number";
    public static final String CREDIT_CARDS_LIMIT = "card_limit";
    public static final String CREDIT_CARDS_EXPIRATION_DATE = "expiration_date";
    public static final String CREDIT_CARDS_TYPE = "card_type";

    /**
     * <h2>Card Type table 🛢</h2>
     */
    public static final String CARD_TYPE_TABLE = "tb_card_type";
    public static final String CARD_TYPE_ID = "type_id";
    public static final String CARD_TYPE_NAME = "name";

    /**
     * <h2>Transaction table 🛢</h2>
     */
    public static final String TRANSACTION_TABLE = "tb_transaction";
    public static final String TRANSACTION_ID = "transaction_id";
    public static final String TRANSACTION_CARD_ID = "card_id";
    public static final String TRANSACTION_DATE = "transaction_date";
    public static final String TRANSACTION_AMOUNT = "amount";

    /**
     * <h1>Intermediates tables</h1>
     * <h2>Role-Authority table 🛢</h2>
     */
    public static final String ROLE_AUTHORITY_TABLE = "role_authority";
    public static final String ROLE_AUTHORITY_ROLE_ID = "role_id";
    public static final String ROLE_AUTHORITY_AUTHORITY_ID = "authority_id";

    /**
     * <h2>Customer Role table 🛢</h2>
     */
    public static final String CUSTOMER_ROLE_TABLE = "customer_role";
    public static final String CUSTOMER_ROLE_CLIENT_ID = "client_id";
    public static final String CUSTOMER_ROLE_ROLE_ID = "role_id";

    /**
     * <h2>Card Type-Card table 🛢</h2>
     */
    public static final String CARD_TYPE_CARD_TABLE = "card_type";
    public static final String CARD_TYPE_CARD_CARD_ID = "card_id";
    public static final String CARD_TYPE_CARD_TYPE_ID = "type_id";

    /**
     * <h2>Loan Payment table 🛢</h2>
     */
    public static final String LOAN_PAYMENT_TABLE = "loan_payment";
    public static final String LOAN_PAYMENT_LOAN_ID = "loan_id";
    public static final String LOAN_PAYMENT_PAYMENT_ID = "payment_id";

    /**
     * <h2>Liquidity Transaction Table 🛢</h2>
     */
    public static final String LIQUIDITY_TABLE = "tb_liquidity_transaction";
    public static final String LIQUIDITY_ID = "liquidity_id";
    public static final String LIQUIDITY_CARD_ID = "card_id";
    public static final String LIQUIDITY_TRANSACTION_DATE = "transaction_date";
    public static final String LIQUIDITY_AMOUNT = "amount";
    public static final String LIQUIDITY_STATUS = "status";

    private DataBaseConstants(){}
}
