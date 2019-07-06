package ru.skillbranch.devintensive.extensions


fun String.truncate(size:Int=16):String{
    var oper=this.trim()
    if(size>=oper.length-1) return oper
    oper=oper.substring(0..size-1)
    if(oper[oper.length-1]==' ') oper=oper.substring(0..(oper.length-2))
    return "$oper..."
}

fun String.stripHtml():String{
    var oper=this.replace("\\<.*?>".toRegex(), "")
    oper=oper.replace("\\&.*?;".toRegex(), "")
    oper=oper.replace("[\\s]{2,}".toRegex(), " ")
    return oper
}