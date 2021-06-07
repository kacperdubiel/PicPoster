<template>
    <div>
        <search-result-component 
          v-for="user in resultUsers" 
          :key="user.id"
          :user="user"/>
    </div>
</template>

<script>
import axios from 'axios';
import SearchResultComponent from "./SearchResultComponent.vue"
export default {
    name: 'list-Of-search-results-component',
    components:{
        SearchResultComponent
    },
    props:{
        prefix: String
    },
    data(){
        return{
            resultUsers: []
        }
    },
    methods: {
        getResultUsers(){
            axios({
                method: 'GET',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')},
                url: "http://localhost:8090/users/search/" + this.prefix
            })
            .catch(e => {console.log(e);})
            .then((response) => this.resultUsers = response.data)
        }
    },
    created(){
        this.getResultUsers();
    }
}
</script>