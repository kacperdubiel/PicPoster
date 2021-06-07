<template>
    <div>
        <navbar-component/>
        <list-of-search-results-component :prefix="prefix"/>
    </div>
</template>
<script>
import ListOfSearchResultsComponent from "../components/ListOfSearchResultsComponent.vue"
import NavbarComponent from "../components/NavbarComponent.vue"
export default {
    name: "search-view",
    components:{
        ListOfSearchResultsComponent,
        NavbarComponent
    },
    data(){
        return{
            prefix: this.$route.params.prefix,
        }
    }
}
</script>