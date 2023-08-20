Feature: User Registration on MailChimp

  Scenario Outline: Create users
    Given User is on the registration page
    When User fills in valid "<email>", "<username>", and "<password>"
    And User submits the registration form
    Then User should see a confirmation or an error "<message>"

    Examples:
      |     email               | username                      | password     |  message
      | userjijilala@mail.com   | Validusername7897896785       | Password123! |  signup-success
      | user24567@mail.com      | ThisIsAVeryLongUsernameThatExceeds100Characters123ThisIsAVeryLongUsernameThatExceeds100Characters-101   | Password123! | invalid-error
      | user@mail.com           | user                          | Password123! |  invalid-error
      |                         | validUsernameGreatUser        | Password123! |  invalid-error