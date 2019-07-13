package ru.skillbranch.devintensive.models

class Bender(var status: Status=Status.NORMAL, var question: Question=Question.NAME) {

    var count=0

    fun askQuestion():String=when(question){
                Question.NAME->Question.NAME.question
                Question.PROFESSION->Question.PROFESSION.question
                Question.MATERIAL->Question.MATERIAL.question
                Question.BDAY->Question.BDAY.question
                Question.SERIAL->Question.SERIAL.question
                Question.IDLE ->Question.IDLE.question
    }

    fun listenAnswer(answer:String):Pair<String,Triple<Int,Int,Int>>{
       if(question == Question.NAME &&answer[0].isLowerCase()) return "Имя должно начинаться с заглавной буквы" to this.status.color
        if(question == Question.PROFESSION&&answer[0].isUpperCase()) return "Профессия должна начинаться со строчной буквы" to this.status.color
        if(question == Question.MATERIAL&&answer.contains("\\d".toRegex())) return "Материал не должен содержать цифр" to this.status.color
        if(question == Question.BDAY&&!answer.matches("\\d+".toRegex())) return "Год моего рождения должен содержать только цифры" to this.status.color
        if(question == Question.SERIAL&&(!answer.matches("\\d+".toRegex())||(answer.length in 0..6||answer.length>7))) return "Серийный номер содержит только цифры, и их 7" to this.status.color


        return if(question.answer.contains(answer)){
           question=question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            ++count
            if(count>=3) {
                question=Question.NAME
                status=Status.NORMAL
                count=0
                return "Это неправльный ответ. Давай все по новой\n${question.question}" to status.color}
            status=status.nextStatus()
           "Это неправльный ответ\n${question.question}" to status.color
       }
    }

    enum class Status(val color:Triple<Int,Int,Int>){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,0,0));

        fun nextStatus():Status{
            return if(this.ordinal< values().lastIndex){
                values()[this.ordinal+1]
            } else{
                values()[0]
            }
        }
    }

    enum class Question(val question: String,val answer:List<String>){
        NAME("Как меня зовут?", listOf("Бендер", "Bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion():Question

    }
}