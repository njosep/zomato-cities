#Author: nidhinjos@gmail.com
Feature: Test Cases for the Zomato Cities GET API

  @zomato
  Scenario Outline: Positive Tests for the GET Cities API
    Given I set the parameters as below and provide a valid API key
      | q   | lat   | lon   | city_ids   | count   |
      | <q> | <lat> | <lon> | <city_ids> | <count> |
    When I execute GET for cities
    Then The Response Status Code should be 200
    And The Status returned in the Response should be "success"
    And The count of location_suggestions should match the count requested
    And The response time should be less than 3 seconds
    And The Response schema should be validated

    @cities
    Examples: 
      | q      | lat              | lon              | city_ids | count |
      | Dublin |                  |                  |          |     3 |
      |        |        53.350140 |        -6.266155 |          |     1 |
      |        |                  |                  |       91 |     1 |
      |        |                  |                  |    91,92 |     2 |
      |        |                  |        -6.266155 |          |     0 |
      |        |                  |                  |      141 |     1 |
      |        | -6.3572375290155 | -6.3572375290155 |          |     0 |

  @zomato
  Scenario Outline: Negative Tests for GET Cities
    Given I set the parameters as below and provide an invalid API key
      | q   | lat   | lon   | city_ids   | count   |
      | <q> | <lat> | <lon> | <city_ids> | <count> |
    When I execute GET for cities
    Then The Response Status Code should be 403
    And The Status returned in the Response should be "Forbidden"

    @cities
    Examples: 
      | q      | lat | lon | city_ids | count |
      | Dublin |     |     |          |     3 |
