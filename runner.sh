platform=${1}

appiumPort='4725'

# Android setup
apps='wdio.apk'
deviceName='Pixel4_12'

# iOS setup
# apps='swaglabs.app'
# deviceName='iPhone_15'
# iosUdid='EC865F6C-9547-4290-9942-B2A83F91B81C'

cucumberTags='@validAccount'


export platform=${platform}
export deviceName=${deviceName}
export iosUdid=${iosUdid}
export apps=${apps}
export cucumberTags=${cucumberTags}
export appiumPort=${appiumPort}


appium --address 0.0.0.0 --port $appiumPort&
P1=$!
mvn clean test -Dcucumber.filter.tags=$cucumberTags&
P2=$!

wait $P2
pkill -9 -f $appiumPort
