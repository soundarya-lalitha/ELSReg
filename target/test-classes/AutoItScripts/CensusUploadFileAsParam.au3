ControlFocus("Choose File to Upload","","Edit1")
Sleep(2000)
$pm1=$cmdLine[1]
$pathmod=StringReplace($cmdLine[1],"\\", "\")
ControlSetText("Choose File to Upload","","Edit1",$pathmod)
Sleep(2000)
ControlClick("Choose File to Upload","","Button1")

