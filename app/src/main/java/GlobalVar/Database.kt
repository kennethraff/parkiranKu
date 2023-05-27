package GlobalVar

import Model.User

class Database {
    companion object{
        val list: MutableList<User> = ArrayList()
    }
}