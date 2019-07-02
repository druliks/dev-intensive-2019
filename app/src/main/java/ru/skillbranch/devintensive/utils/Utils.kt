package ru.skillbranch.devintensive.utils

import java.lang.StringBuilder



object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        val parts:List<String>?=fullName?.split(" ")
        var firstName:String?=parts?.getOrNull(0)
        var lastName:String?=parts?.getOrNull(1)
        when(lastName){
            ""->lastName=null
            " "->lastName=null
        }
        when (firstName){
            ""->firstName=null
            " "->firstName=null
        }
        return firstName to lastName
    }

    fun transliteration(payload:String?, devider: String=" "): String {

        val parts=payload?.split(" ")
        val result= StringBuilder("")
        if (parts != null) {
            for(part in parts){
                result.append(toTranslitOld(part)).append(devider)
            }
        }
        result.deleteCharAt(result.lastIndex)
        return result.toString()
    }

    private fun toTranslitOld(text: String): String {
        val mapLetters= mapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya" ,
            "А" to "A",
            "Б" to "B",
            "В" to "V",
            "Г" to "G",
            "Д" to "D",
            "Е" to "E",
            "Ё" to "E",
            "Ж" to "Zh",
            "З" to "Z",
            "И" to "I",
            "Й" to "I",
            "К" to "K",
            "Л" to "L",
            "М" to "M",
            "Н" to "N",
            "О" to "O",
            "П" to "P",
            "Р" to "R",
            "С" to "S",
            "Т" to "T",
            "У" to "U",
            "Ф" to "F",
            "Х" to "H",
            "Ц" to "C",
            "Ч" to "Ch",
            "Ш" to "Sh",
            "Щ" to "Sh'",
            "Ъ" to "",
            "Ы" to "I",
            "Ь" to "",
            "Э" to "E",
            "Ю" to "Yu",
            "Я" to "Ya")
        val sb = StringBuffer(text.length)
        for (i in 0 until text.length) {
            val l = text.substring(i, i + 1)
            if (mapLetters.containsKey(l)) {
                sb.append(mapLetters[l])
            } else {
                sb.append(l)
            }
        }
        return sb.toString()
    }


    fun toInitials(firstName: String?, lastName: String?): String? {
        if(firstName==null&&lastName==null) return null
        var firstInitial:Char?=null
        var lastInitial:Char?=null
        if(firstName!=null&&firstName.trim().isNotEmpty()){
            firstInitial=firstName.trim()[0]
        }
        if(lastName!=null&&lastName.trim().isNotEmpty()){
            lastInitial=lastName.trim()[0]
        }
        if(firstInitial==null&&lastInitial==null) return null
        if(firstInitial==' '&&lastInitial==' ') return null
        if((firstInitial!=null||firstInitial!=' ')&&(lastInitial==null||lastInitial==' ')) return firstInitial.toString().toUpperCase()
        if((firstInitial==null||firstInitial==' ')&&(lastInitial!=null||lastInitial!=' ')) return lastInitial.toString().toUpperCase()
        val firstInit=if(firstInitial==null||firstInitial==' ') "" else firstInitial.toString()
        val lastInit=if(lastInitial==null||lastInitial==' ') "" else lastInitial.toString()
        val result=firstInit+lastInit

        return result.toUpperCase()
    }
}
