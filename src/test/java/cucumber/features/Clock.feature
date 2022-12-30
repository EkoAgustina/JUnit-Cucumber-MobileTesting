Feature: Alarm

  @TestOne
  Scenario: User can create Alarm
    And User click "Alarm.yml:IconAlarm"
    And User wait 2 seconds
    Then Verify element "Alarm.yml:AlarmTitle" will be displayed
    And User click "Alarm.yml:SelectTime"
#    And User click "Alarm.yml:SelectFive"
#    And User click "Alarm.yml:SelectThirty"
#    And User click "Alarm.yml:SelectAm"
#    And User click "Alarm.yml:OKButton"
#    And User wait 2 seconds
#    Then Verify value "Alarm.yml:MyAlarm" is "equal" with data "test_data.yml:dataMyAlarm"


