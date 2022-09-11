# AlarmTest

* AlarmManager
* ELAPSED_REALTIME_WAKEUP: interval 3 minutes
* Show notification on alarm
* Show notification on boot

## 説明

* ブート時に intent を受信して notification を表示する
  * その intent が有効になるのはアプリ起動後(Activityを表示させた後)
  * ただし 3分間隔のアラームは開始していないため、ブートの notification を表示するだけ
