package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND=1000L
const val MINUTE=SECOND * 60
const val HOUR=MINUTE * 60
const val DAY= HOUR * 24

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat=SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, unit: TimeUnits= TimeUnits.SECOND):Date{
    var time=this.time

    time+=when (unit){
        TimeUnits.SECOND->value * SECOND
        TimeUnits.MINUTE->value * MINUTE
        TimeUnits.HOUR->value * HOUR
        TimeUnits.DAY->value * DAY
    }
    this.time=time
    return this
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(x: Int): String {
        when(this){
            SECOND->return "$x ${when(x%10){
                1->"секунду"
                in 2..4->"секунды"
                in 5..9->"секунд"
                else->"секунд"
            }
            }"
            MINUTE->return "$x ${when(x%10){
                1->"минуту"
                in 2..4->"минуты"
                in 5..9->"минут"
                else->"минут"
            }
            }"
            HOUR->return "$x ${when(x%10){
                1->"час"
                in 2..4->"часа"
                in 5..9->"часов"
                else->"часов"
            }
            }"
            DAY->return "$x ${when(x%10){
                1->"день"
                in 2..4->"дня"
                in 5..9->"дней"
                else->"дней"
            }
            }"
        }
    }
}

fun Date.humanizeDiff(date: Date= Date()): String {
    val mili=1000L

    val time1:Long=this.time
    val diffTime=time1-date.time
    var second=diffTime/mili
    if(this.after(date)){
        when(second){
            in 0..1->return "только что"
            in 2..45->return "через несколько секунд"
            in 46..75->return "через минуту"
            in 76..2700->return "через ${second/60} ${when((second/60)%10){
                1L->"минуту"
                in 2..4->"минуты"
                in 5..9->"минут"
                else->"минут"
            }
            }"
            in 2701..4500->return "через час"
            in 4501..79200->return "через ${second/60/60} ${when((second/60/60)%10){
                1L->"час"
                in 2..4->"часа"
                in 5..9->"часов"
                else->"часов"
            }
            }"
            in 79201..93600->return "через день"
            in 93601..31104000->return "через ${second/60/60/24} ${when((second/60/60/24)%10){
                1L->"день"
                in 2..4->"дня"
                in 5..9->"дней"
                else->"дней"
            }
            }"
            else -> return "более чем через год"
        }
    }else{
        second*= (-1)
        when(second){
            in 0..1-> return "только что"
            in 2..45->return "несколько секунд назад"
            in 46..75->return "минуту назад"
            in 76..2700->return "${second/60} ${when((second/60)%10){
                1L->"минуту"
                in 2..4->"минуты"
                in 5..9->"минут"
                else->"минут"
            }
            } назад"
            in 2701..4500->return "час назад"
            in 4501..79200->return "${second/60/60} ${when((second/60/60)%10){
                1L->"час"
                in 2..4->"часа"
                in 5..9->"часов"
                else->"часов"
            }
            } назад"
            in 79201..93600->return "день назад"
            in 93601..31104000->return "${second/60/60/24} ${when((second/60/60/24)%10){
                1L->"день"
                in 2..4->"дня"
                in 5..9->"дней"
                else->"дней"
            }
            } назад"
            else -> return "более года назад"
        }
    }
}