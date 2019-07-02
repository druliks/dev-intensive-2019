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
        val abcCyr = charArrayOf(
            'а',
            'б',
            'в',
            'г',
            'д',
            'е',
            'ё',
            'ж',
            'з',
            'и',
            'й',
            'к',
            'л',
            'м',
            'н',
            'о',
            'п',
            'р',
            'с',
            'т',
            'у',
            'ф',
            'х',
            'ц',
            'ч',
            'ш',
            'щ',
            'ъ',
            'ы',
            'ь',
            'э',
            'ю',
            'я',
            'А',
            'Б',
            'В',
            'Г',
            'Д',
            'Е',
            'Ё',
            'Ж',
            'З',
            'И',
            'Й',
            'К',
            'Л',
            'М',
            'Н',
            'О',
            'П',
            'Р',
            'С',
            'Т',
            'У',
            'Ф',
            'Х',
            'Ц',
            'Ч',
            'Ш',
            'Щ',
            'Ъ',
            'Ы',
            'Ь',
            'Э',
            'Ю',
            'Я',
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'o',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z',
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'I',
            'J',
            'K',
            'L',
            'M',
            'N',
            'O',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'U',
            'V',
            'W',
            'X',
            'Y',
            'Z'
        )
        val abcLat = arrayOf(
            "a",
            "b",
            "v",
            "g",
            "d",
            "e",
            "e",
            "zh",
            "z",
            "i",
            "i",
            "k",
            "l",
            "m",
            "n",
            "o",
            "p",
            "r",
            "s",
            "t",
            "u",
            "f",
            "h",
            "c",
            "ch",
            "sh",
            "sh",
            "",
            "i",
            "",
            "e",
            "yu",
            "ya",
            "A",
            "B",
            "V",
            "G",
            "D",
            "E",
            "E",
            "Zh",
            "Z",
            "I",
            "I",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "R",
            "S",
            "T",
            "U",
            "F",
            "H",
            "C",
            "Ch",
            "Sh",
            "Sh",
            "",
            "I",
            "",
            "E",
            "Yu",
            "Ya",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "i",
            "j",
            "k",
            "l",
            "m",
            "n",
            "o",
            "p",
            "q",
            "r",
            "s",
            "t",
            "u",
            "v",
            "w",
            "x",
            "y",
            "z",
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z"
        )

        var firstname=payload?.split(" ")?.getOrNull(0)
        var lastname=payload?.split(" ")?.getOrNull(1)

        val builder1 = StringBuilder()
        if (firstname != null) {
            for (i in 0 until firstname.length) {
                for (x in abcCyr.indices) {
                    if (firstname?.get(i)==abcCyr[x]) {
                        builder1.append(abcLat[x])
                    }
                }
            }
        }
        firstname=builder1.toString()

        val builder2 = StringBuilder()
        if (lastname != null) {
            for (i in 0 until lastname.length) {
                for (x in abcCyr.indices) {
                    if (lastname?.get(i)==abcCyr[x]) {
                        builder2.append(abcLat[x])
                    }
                }
            }
        }
        lastname=builder2.toString()


        return firstname+devider+lastname
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
