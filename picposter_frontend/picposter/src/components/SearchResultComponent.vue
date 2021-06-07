<template>
    <div @click="redirectToProfile" class="search-result-container">
        <div id="search-user-img">
            <image-component :filename="user.profileImagePath"/>
        </div>
        <div>
            <span>{{this.user.login}}</span>
        </div>
    </div>
</template>

<script>
import ImageComponent from './ImageComponent.vue'
export default {
    name: 'search-result-component',
    props:{
        user: Object
    },
    components:{
        ImageComponent
    },
    methods:{
        redirectToProfile(){
            this.$router.push(this.$route.query.redirect || '/profile/' + this.user.id)
        }
    }
}
</script>

<style>
    .search-result-container{
        display: flex;
        flex-direction: row;
        padding: 1%;
    }

    #search-user-img{
        width: 100px;
        height: 100px;
        margin-right: 0;
    }

    #search-user-img img{
        width: 100%;
        height: 100%;
        border-radius: 50%;
        border: 0px;
    }
    
</style>