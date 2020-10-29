import org.junit.Test

class LWWSetTest {

    /*
    *Add all element with sequence
    * */
    @Test
    fun case1() {
        var set = LWWSet()
        set.add("cmd1", 1)
        set.add("cmd2", 2)
        set.add("cmd3", 3)
        set.add("cmd4", 4)

        assert(set.addMap == mutableMapOf<String, Long>(Pair("cmd4", 4), Pair("cmd3", 3), Pair("cmd2", 2),Pair("cmd1", 1)))
        assert(set.removeMap == mutableMapOf<String, Long>()) //should be empty map

        assert(set.get() == mutableSetOf("cmd4", "cmd1", "cmd2", "cmd3"))
    }

    /*
    * Remove all element
    * */
    @Test
    fun case2() {
        var set = LWWSet()
        set.remove("cmd1", 1)
        set.remove("cmd2", 2)
        set.remove("cmd3", 3)
        set.remove("cmd4", 4)

        assert(set.addMap == mutableMapOf<String, Long>()) //should be empty map
        assert(set.removeMap == mutableMapOf<String, Long>(Pair("cmd4", 4), Pair("cmd3", 3), Pair("cmd2", 2),Pair("cmd1", 1)))

        assert(set.get() == mutableSetOf<String>())
    }

    /*
    * add and remove all element
    * */
    @Test
    fun case3() {
        var set = LWWSet()
        set.add("cmd1", 1)
        set.add("cmd2", 2)
        set.add("cmd3", 3)
        set.add("cmd4", 4)
        set.remove("cmd1", 1)
        set.remove("cmd2", 2)
        set.remove("cmd3", 3)
        set.remove("cmd4", 4)

        assert(set.addMap == mutableMapOf<String, Long>(Pair("cmd4", 4), Pair("cmd3", 3), Pair("cmd2", 2),Pair("cmd1", 1)))
        assert(set.removeMap == mutableMapOf<String, Long>(Pair("cmd4", 4), Pair("cmd3", 3), Pair("cmd2", 2),Pair("cmd1", 1)))

        assert(set.get() == mutableSetOf<String>())
    }

    /*
    * add and remove element 1 ,2 and 3
    * */
    @Test
    fun case4() {
        var set = LWWSet()
        set.add("cmd1", 1)
        set.add("cmd2", 2)
        set.add("cmd3", 3)
        set.add("cmd4", 4)
        set.remove("cmd1", 1)
        set.remove("cmd2", 2)
        set.remove("cmd3", 3)



        assert(set.addMap == mutableMapOf<String, Long>(Pair("cmd4", 4), Pair("cmd3", 3), Pair("cmd2", 2),Pair("cmd1", 1)))
        assert(set.removeMap == mutableMapOf<String, Long>(Pair("cmd3", 3), Pair("cmd2", 2),Pair("cmd1", 1)))
        assert(set.get() == mutableSetOf<String>("cmd4"))
    }


    /*
    * add 1,2,3 and remove 1,2,3,4
    * */
    @Test
    fun case5() {
        var set = LWWSet()
        set.add("cmd1", 1)
        set.add("cmd2", 2)
        set.add("cmd3", 3)
        set.remove("cmd1", 1)
        set.remove("cmd2", 2)
        set.remove("cmd3", 3)
        set.remove("cmd4", 4)


        assert(set.addMap == mutableMapOf<String, Long>(Pair("cmd3", 3), Pair("cmd2", 2),Pair("cmd1", 1)))
        assert(set.removeMap == mutableMapOf<String, Long>(Pair("cmd4", 4), Pair("cmd3", 3), Pair("cmd2", 2),Pair("cmd1", 1)))
        assert(set.get() == mutableSetOf<String>())
    }

    /*
    * Repeat adding into Set with sequence
    * */
    @Test
    fun case6() {
        var set = LWWSet()
        set.add("cmd1", 1)
        set.add("cmd1", 2)
        set.add("cmd1", 3)
        set.add("cmd1", 4)


        assert(set.addMap == mutableMapOf<String, Long>(Pair("cmd1", 4)))
        assert(set.removeMap == mutableMapOf<String, Long>())
        assert(set.get() == mutableSetOf<String>("cmd1"))
    }


    /*
    * Repeat removing from Set with sequence
    * */
    @Test
    fun case7() {
        var set = LWWSet()
        set.remove("cmd1", 1)
        set.remove("cmd1", 2)
        set.remove("cmd1", 3)
        set.remove("cmd1", 4)


        assert(set.addMap == mutableMapOf<String, Long>())
        assert(set.removeMap == mutableMapOf<String, Long>(Pair("cmd1", 4)))
        assert(set.get() == mutableSetOf<String>())
    }


    /*
    * Repeat adding into Set without sequence
    * */
    @Test
    fun case8() {
        var set = LWWSet()
        set.add("cmd1", 4)
        set.add("cmd1", 2)
        set.add("cmd1", 3)
        set.add("cmd1", 1)


        assert(set.addMap == mutableMapOf<String, Long>(Pair("cmd1", 4)))
        assert(set.removeMap == mutableMapOf<String, Long>())
        assert(set.get() == mutableSetOf<String>("cmd1"))
    }


    /*
    * Repeat removing from Set without sequence
    * */
    @Test
    fun case9() {
        var set = LWWSet()
        set.remove("cmd1", 4)
        set.remove("cmd1", 2)
        set.remove("cmd1", 3)
        set.remove("cmd1", 1)


        assert(set.addMap == mutableMapOf<String, Long>())
        assert(set.removeMap == mutableMapOf<String, Long>(Pair("cmd1", 4)))
        assert(set.get() == mutableSetOf<String>())
    }

    /*
    * Repeat add and removing from Set without sequence
    * */
    @Test
    fun case10() {
        var set = LWWSet()
        set.remove("cmd1", 4)
        set.remove("cmd1", 2)
        set.remove("cmd1", 3)
        set.remove("cmd1", 1)


        set.add("cmd1", 4)
        set.add("cmd1", 2)
        set.add("cmd1", 3)
        set.add("cmd1", 1)


        assert(set.addMap == mutableMapOf<String, Long>(Pair("cmd1", 4)))
        assert(set.removeMap == mutableMapOf<String, Long>(Pair("cmd1", 4)))
        assert(set.get() == mutableSetOf<String>())
    }


    /*
   * add after remove
   * */
    @Test
    fun case11() {
        var set = LWWSet()
        set.add("cmd1", 1)
        set.add("cmd2", 3)
        set.add("cmd3", 5)
        set.add("cmd4", 7)
        set.remove("cmd1", 2)
        set.remove("cmd2", 4)
        set.remove("cmd3", 6)
        set.remove("cmd4", 8)
        set.add("cmd4", 9) // <-- this is the target

        assert(set.addMap == mutableMapOf<String, Long>(Pair("cmd4", 9), Pair("cmd3", 5), Pair("cmd2", 3),Pair("cmd1", 1)))
        assert(set.removeMap == mutableMapOf<String, Long>(Pair("cmd4", 8), Pair("cmd3", 6), Pair("cmd2", 4),Pair("cmd1", 2)))

        assert(set.get() == mutableSetOf<String>("cmd4"))
    }

    /*
   * remove first then add
   * */
    @Test
    fun case12() {
        var set = LWWSet()
        set.remove("cmd1", 1)
        set.add("cmd1", 2)
        set.remove("cmd1", 3)
        set.add("cmd1", 4)

        assert(set.addMap == mutableMapOf<String, Long>(Pair("cmd1", 4)))
        assert(set.removeMap == mutableMapOf<String, Long>(Pair("cmd1", 3)))

        assert(set.get() == mutableSetOf<String>("cmd1"))
    }

    /*
    * remove after add, one element will add once more
    * */
    @Test
    fun case13() {
        var set = LWWSet()
        set.remove("cmd1", 1)
        set.remove("cmd2", 3)
        set.remove("cmd3", 5)
        set.remove("cmd4", 7)
        set.add("cmd1", 2)
        set.add("cmd2", 4)
        set.add("cmd3", 6)
        set.add("cmd4", 8)
        set.add("cmd4", 9)

        assert(set.removeMap == mutableMapOf<String, Long>(Pair("cmd4", 7), Pair("cmd3", 5), Pair("cmd2", 3),Pair("cmd1", 1)))
        assert(set.addMap == mutableMapOf<String, Long>(Pair("cmd4", 9), Pair("cmd3", 6), Pair("cmd2", 4),Pair("cmd1", 2)))

        assert(set.get() == mutableSetOf("cmd4", "cmd1", "cmd2", "cmd3"))
    }

}