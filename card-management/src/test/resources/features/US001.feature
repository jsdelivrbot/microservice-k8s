# 2018-Apr-23
# Version 1

@Card-Offers
Feature: US-1 Get card offers
"""
  The existing Bank Customer, who also already has an account, wants to apply for a Debit Card
  and for that, wants to get the list of offers.
  TECH1-1: Method: GET
  TECH1-2: request: ClientID (in the Auth header); AccountID (as query parameter)
  TECH1-3: response: List<CardOffer>
  TECH1-4: business logic:
  - The Account Management API uses the "Account Type" attribute, which might be
    - mortgage (mortgage based loan),
    - sbl (security backed loan)
    - current account
    - credit account
  - When checking eligibility, the type must be "curent account".
  - There might be other attributes, known by the Account Management API only, which might prevent that
    account to be used as a Debit Card Account. Therefore, when checking the account, AM-API may return
    error easily.
    """

  Scenario: AC1.1 Get list of offers
    Given The Bank Customer is logged in
    And he has a selected account 12345678-87654321
    When he applies for the Card Offer List of cardType debit
    Then HTTP 200 returns
    And he gets back the list of Card Offers
#    And the corresponding log record is persisted.

  @ignore
  Scenario: AC1.2 See Offer objects
    Given The Bank Customer is logged in
    And he has a selected account 12345678-87654321
    When he applies for the Card Offer List of cardType debit
    Then HTTP 200 returns
    And he gets back the list of Card Offers
    And each object has the mandatory attributes of <OfferID, CardType, TermsConditionsLink, Fees>
#    And each Fee may have any combination of the Fee attributes of { YearlyFeeAmount, MonthlyFeeAmount, DebitInterestRate, CretitInterestRate }
#    And each Fee attribute that exists must not be negative.

  @ignore
  Scenario: AC1.3 Missing Account
    Given The Bank Customer is logged in
    And he did not select any Account
    When he applies for the Card Offer List of cardType debit
    Then HTTP 404 returns
#    And the returned object consists of two attributes {ErrorCode, ErrorMessage}
#    And the ErrorMessage is "Missing account number"
#    And the corresponding log record is persisted.
#

  @ignore
  Scenario: AC1.4 Existing but not owned Account
    Given The Bank Customer is logged in
    And he selects an Account 12345678-12345678 # which is not his own
    When he applies for the Card Offer List of cardType debit
    Then HTTP 404 returns
#    And no object returns
#    And the corresponding log record is persisted.
#
  Scenario: Wrong Account Type
    Given The Bank Customer is logged in
    And he selects an Account 12345678-11111111 # which type is other than current_account
    When he applies for the Card Offer List of cardType debit
    Then HTTP 400 returns
#    And the returned object consists of two attributes {ErrorCode, ErrorMessage}
#    And the ErrorMessage is "Account {accountnumber} may not support Debit Cards"
#    And the corresponding log record is persisted.
#

  Scenario: Account non eligible by Account Management e.g with wrong state
    Given The Bank Customer is logged in
    And he selects an Account 12345678-22222222 # which is not eligible in the Account Management API
    When he applies for the Card Offer List of cardType debit
    Then HTTP 400 returns
#    And the returned object consists of two attributes {ErrorCode, ErrorMessage}
#    And the ErrorMessage is "Blocked accounts may not have Debit Cards"
#    And the corresponding log record is persisted.
#
  @Component
  @ignore
  Scenario: Required service Account Management API is down
    Given The Bank Customer is logged in
    And the Account Management API is down2
    And he has a selected account 12345678-87654321
    When he applies for the Card Offer List of cardType debit
    Then HTTP 500 returns
#    And the corresponding log record is persisted.

#  LETS DEFINE THIS
#  Scenario: Misconfigured Card Management API
#    Given The Bank Customer is logged in
#    And the Card Management API is misconfigured (e.g. a config file is missing or invalid)
#    When he applies for the Card Offer list
#    Then HTTP 500 returns
#    And the corresponding log record is persisted.
