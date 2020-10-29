class LWWSet {
    var addMap = mutableMapOf<String, Long>()
    var removeMap = mutableMapOf<String, Long>()

    private fun addToMap(dataMap: MutableMap<String, Long>, data: String, timestamp: Long) {
        // if element does not exist, it will retuen -1 as it default timestamp
        // if element exists, put into map when timestamp is larger than current
        if (dataMap.getOrDefault(data, -1) < timestamp) {
            dataMap[data] = timestamp
        }
    }

    @Synchronized fun add(data: String, timestamp: Long) {
        addToMap(addMap, data, timestamp)
    }


    @Synchronized fun remove(data: String, timestamp: Long) {
        addToMap(removeMap, data, timestamp)
    }

    fun get(): Set<String> {
        // merge 2 data map to get a result set
        var result = mutableSetOf<String>()
        addMap.forEach {
            if (removeMap.getOrDefault(it.key, -1) < it.value) { // check if exist in remove map
                // add to result when add timestamp larger than remove timestamp
                result.add(it.key)
            }
        }
        return result
    }

}