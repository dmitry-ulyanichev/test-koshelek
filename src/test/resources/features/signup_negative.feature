@signup_negative_scenarios
Feature: Negative Scenarios on the Signup Page

  @signup_negative_scenarios_01
  Scenario: All fields left empty
    Given I open page "https://koshelek.ru/authorization/signup"
    And I wait for page to finish loading (element with xpath "//div[@data-wi='footer']" is present)
    When I click on element with css "[type='submit']"
    Then I should see error message "Поле не заполнено" in "#input-125"
    And I should see error message "Поле не заполнено" in "#username"
    And I should see error message "Поле не заполнено" in "#new-password"
    And I should see user-agreement checkbox show an error

  Scenario Outline: Invalid username
    Given I open page "https://koshelek.ru/authorization/signup"
    And I wait for page to finish loading (element with xpath "//div[@data-wi='footer']" is present)
    When I type "<input>" into element with css "#input-125"
    And I click away from element with css "#input-125"
    Then I should see error message "Допустимые символы (от 6 до 32): a-z, 0-9, _. Имя должно начинаться с буквы" in "#input-125"
    And I should see error popup with message "Имя пользователя недействительно"

    Examples:
    | input                               |
    | q                                   |
    | qwert                               |
    | 1qwert                              |
    | 1qwertyuiopasdfghjkl                |
    | _qwertyuiopasdfghjkl                |
    | user-name                           |
    | qwertyuiopasdfghjklzxcvbnmqwertyu   |
    | q`~!@#$%^&*()-=+,.?[]{}             |

  Scenario Outline: Invalid email
    Given I open page "https://koshelek.ru/authorization/signup"
    And I wait for page to finish loading (element with xpath "//div[@data-wi='footer']" is present)
    When I type "<input>" into element with css "#username"
    And I click away from element with css "#username"
    Then I should see error message "Формат e-mail: username@test.ru" in "#username"

    Examples:
    | input  |
    | q      |
    | q@     |
    | q@we   |
    | q@we.  |
    | q@we.r |

  Scenario Outline: Invalid password
    Given I open page "https://koshelek.ru/authorization/signup"
    And I wait for page to finish loading (element with xpath "//div[@data-wi='footer']" is present)
    When I type "<input>" into element with css "#new-password"
    And I click away from element with css "#new-password"
    Then I should see error message "<message>" in "#new-password"

    Examples:
      | input                                                             | message                                                                      |
      | 1Qw                                                               | Пароль должен содержать минимум 8 символов                                   |
      | 1Qwerty                                                           | Пароль должен содержать минимум 8 символов                                   |
      | !@#                                                               | Пароль должен содержать минимум 8 символов                                   |
      | 1Qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopas | Пароль должен содержать от 8 до 64 символов, включая заглавные буквы и цифры |
      | qwertyuiop                                                        | Пароль должен содержать от 8 до 64 символов, включая заглавные буквы и цифры |
      | 12345678                                                          | Пароль должен содержать от 8 до 64 символов, включая заглавные буквы и цифры |
      | QWERTYUIOP                                                        | Пароль должен содержать от 8 до 64 символов, включая заглавные буквы и цифры |
      | 12QWERTYUI                                                        | Пароль должен содержать от 8 до 64 символов, включая заглавные буквы и цифры |
      | Qwertyuiop                                                        | Пароль должен содержать от 8 до 64 символов, включая заглавные буквы и цифры |
      | 1qwertyuiop                                                       | Пароль должен содержать от 8 до 64 символов, включая заглавные буквы и цифры |