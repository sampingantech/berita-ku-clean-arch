package beritaku.features.news.mapper

interface Mapper<INTENT, T> {
    fun mapToIntent(type: T): INTENT
}