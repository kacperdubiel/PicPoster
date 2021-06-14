<template>
    <div class="results">
    <div @click="redirectToProfile" id="search-result-container" class="shadow-box">
        <div id="search-user-img">
            <image-component :filename="user.profileImagePath"/>
        </div>
        <div id="pr-user-info">
            <div id="pr-user-info-top">
                <div id="pr-user-name">
                    {{ user.login }}
                </div>
            </div>
            <div id="pr-user-info-bottom">
                <div id="pr-user-stats">
                    <div>
                        Posty: <posts-counter-component :userId="user.id" />
                    </div>
                    <div>
                        Obserwujących: <followers-counter-component :userId="user.id" />
                    </div>
                    <div>
                        Obserwowani: <followings-counter-component :userId="user.id" />
                    </div>
                </div>
                <div id="pr-user-description">
                    {{ user.description }}
                </div>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
import ImageComponent from './ImageComponent.vue'
import FollowersCounterComponent from './FollowersCounterComponent.vue'
import FollowingsCounterComponent from './FollowingsCounterComponent.vue'
import PostsCounterComponent from './PostsCounterComponent.vue'
export default {
    name: 'search-result-component',
    props:{
        user: Object
    },
    components:{
        ImageComponent,
        FollowersCounterComponent,
        FollowingsCounterComponent,
        PostsCounterComponent,
    },
    methods:{
        redirectToProfile(){
            this.$router.push(this.$route.query.redirect || '/profile/' + this.user.id)
        }
    }
}
</script>

<style>
    #search-result-container{
        display: flex;
        width: 60%;
        justify-content: center;
        flex-direction: row;
        border-radius: 10px;
        margin-bottom: 20px;
        background-color: white;
        padding: 15px;
    }

    #search-result-container:hover {
        cursor: pointer;
    }

    .results{
        display: flex;
        justify-content: center;
    }

    #search-user-img {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-right: 10px;
        width: 200px;
        height: 180px;
    }

    #search-user-img img {
        width: 150px;
        height: 150px;
        object-fit: cover;
        border-radius: 50%;
        border: 3px solid #ffffff;
        -webkit-box-shadow: 1px 1px 4px #888888;
        -moz-box-shadow:    1px 1px 4px #888888;
        box-shadow:         1px 1px 4px #888888; 
    }
</style>