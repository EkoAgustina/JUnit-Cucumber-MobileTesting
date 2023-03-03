Feature: Alarm

  @TestPass
  Scenario: User can create Alarm
    And User click "Alarm:IconAlarm"
    And Wait 2 seconds
    Then Element "Alarm:AlarmTitle" will be displayed
    And User click "Alarm:SelectTime"
    And User click "Alarm:SelectFive"
    And User click "Alarm:SelectThirty"
    And User click "Alarm:SelectAm"
    And User click "Alarm:OKButton"
    And Wait 2 seconds
    Then Element "Alarm:MyAlarm" is equal with data "test_data:dataMyAlarm"
    Then User take screenshot with file name "Myscreenshoot"

  @TestFail
  Scenario: User fail run test
    And User click "Alarm:IconAlarm"
    And Wait 2 seconds
    Then Element "Alarm:AlarmTitle" will be displayed
    And User click "Alarm:SelectTime"
    And User click "Alarm:SelectFive"
    And User click "Alarm:SelectThirty"
    And User click "Alarm:SelectAm"
    And User click "Alarm:OKButton"
    And Wait 2 seconds
    Then Element "Alarm:MyAlarm" is equal with data "test_data:dataMyAlar"
    Then User take screenshot with file name "One"