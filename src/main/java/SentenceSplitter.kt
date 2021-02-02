import net.sf.okapi.common.LocaleId
import net.sf.okapi.common.resource.TextContainer
import net.sf.okapi.lib.segmentation.SRXDocument

object SentenceSplitter {
    /**
     * 规则文件*/
    private val srxDocument by lazy { SRXDocument() }

    init {
        srxDocument.loadRules(javaClass.getResourceAsStream("default.srx"))
    }

    /***
     * 句子分段
     * @param lang - 语言简称 en/zh/ja
     * @param text - 文本
     * @return 拆分后的句子集合
     */
    fun segment(lang: LocaleId, text: String): List<String> {
        requireNotNull(text.isEmpty()) { "请输入待拆分的文本" }

        val segmenter = srxDocument.compileLanguageRules(lang, null)
        val container = TextContainer(text)
        val segments = container.segments

        segmenter.computeSegments(container)
        segments.create(segmenter.ranges)

        return container.segments.map { it.text.text.trim() }
    }
}