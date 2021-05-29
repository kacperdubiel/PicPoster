import Vue from 'vue'
import Vuex from 'vuex' 

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        count: 0  // Example
    },
    mutations: {
        increment (state) {  // Example
            state.count++
        }
    }
})

export default store