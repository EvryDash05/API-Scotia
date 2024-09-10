package com.example.api_scotia.commons.constants;

public class ErrorConstant {

    /** Generic server error code. */
    public static final Integer GENERIC_ERROR_CODE = 500;

    /** Generic server error message. */
    public static final String GENERIC_ERROR_MESSAGE = "An unknown error occurred";

    /** Bad request error code. */
    public static final Integer BAD_REQUEST_CODE = 400;

    /** Not found error code. */
    public static final Integer NOT_FOUND_CODE = 404;

    /** Unauthorized error code. */
    public static final Integer UNAUTHORIZED_CODE = 401;

    /** Forbidden error code. */
    public static final Integer FORBIDDEN_CODE = 403;

    /** Conflict error code (e.g., data already exists). */
    public static final Integer CONFLICT_CODE = 409;

    /** Error message for record not found. */
    public static final String RECORD_NOT_FOUND_MESSAGE = "Record not found";

    /** Error message for invalid credentials. */
    public static final String INVALID_CREDENTIALS_MESSAGE = "Invalid username or password";

    /** Error message for unauthorized access. */
    public static final String UNAUTHORIZED_ACCESS_MESSAGE = "Unauthorized access";

    /** Error message for forbidden access. */
    public static final String FORBIDDEN_ACCESS_MESSAGE = "Access forbidden";

    /** Error message for data conflict (e.g., already exists). */
    public static final String DATA_CONFLICT_MESSAGE = "Data conflict, already exists";

    /** Error message for non-existent customer. */
    public static final String CUSTOMER_NOT_FOUND = "Customer does not exist";

    /** Error message for non-existent payment. */
    public static final String PAYMENT_NOT_FOUND = "Payment record not found";

    /** Client not found error message. */
    public static final String CLIENT_NOT_FOUND_MESSAGE = "Client does not exist";

    /** Loan request not found error message. */
    public static final String LOAN_NOT_FOUND_MESSAGE = "Loan request does not exist";

    /** Credit card not found error message. */
    public static final String CARD_NOT_FOUND_MESSAGE = "Credit card does not exist";

    /** Invalid credit card type error message. */
    public static final String INVALID_CARD_TYPE_MESSAGE = "Invalid credit card type provided";

    /** Payment not found error message. */
    public static final String PAYMENT_NOT_FOUND_MESSAGE = "Payment record not found";

    /** Transaction not found error message. */
    public static final String TRANSACTION_NOT_FOUND_MESSAGE = "Transaction record not found";

    /** Unauthorized loan amount error message. */
    public static final String UNAUTHORIZED_LOAN_AMOUNT_MESSAGE = "Requested loan amount exceeds authorized limit";

    /** Insufficient credit card limit error message. */
    public static final String INSUFFICIENT_CREDIT_LIMIT_MESSAGE = "Insufficient credit card limit for the transaction";

    /** Loan installment error message. */
    public static final String INVALID_INSTALLMENT_COUNT_MESSAGE = "Invalid number of loan installments";

    /** Client financial info not found error message. */
    public static final String CLIENT_FINANCIAL_INFO_NOT_FOUND_MESSAGE = "Client financial information not found";

    private ErrorConstant(){}
}
