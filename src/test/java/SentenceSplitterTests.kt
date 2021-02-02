fun main(args: Array<String>) {
    splitChinese()
    println("-".padEnd(20,'-'))
    splitEnglish()
}

fun splitChinese() {
    val texts = listOf(
            "测试；分号。",
            "我有一个梦想。你还好吗？不用谢！",
            "最基本的分句形式。"
    )
    split("zh", texts)
}

fun splitEnglish() {
    val texts = listOf(
            "Read the discussions posted on the users group, or the FAQ page.",
            "Mr.Shi Are you OK?",
            "Open source projects can exist only because of the resources provided by some people and companies:"
    )
    split("en", texts, " ")
}

private fun split(lang: String, texts: List<String>, separator: String = "") {
    val list = SentenceSplitter.segment(lang, texts.joinToString(separator))
    for (l in list) {
        println(l)
    }
}
