<template>
    <div id="profile-page" class="">
        <div id="profile-loading" v-if="userFetchingStatus === 'fetching'">
            ŁADOWANIE
        </div>

        <div id="profile-not-found" v-else-if="userFetchingStatus === 'not-found'">
            <h1>Błąd 404</h1>
            <h3>Taki profil nie istnieje.</h3>
        </div>

        <div id="profile-container" class="shadow-box" v-else-if="userFetchingStatus === 'found'"> 
            <div id="pr-top">
                <div id="pr-user-img">
                    <image-component :filename="user.profileImagePath"/>
                </div>
                <div id="pr-user-info">
                    <div id="pr-user-info-top">
                        <div id="pr-user-name">
                            {{ user.login }}
                        </div>
                        <div id="pr-user-follow-btn">
                            <button class="btn btn-primary">Obserwuj</button>
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
            <div id="pr-bottom" class="shadow-box-inset">
                <div id="pr-posts-loading" v-if="postsFetchingStatus === 'fetching'">
                    ŁADOWANIE POSTÓW
                </div>
                <div id="pr-posts-not-found" v-if="postsFetchingStatus === 'not-found'">
                    Ten użytkownik nie dodał jeszcze żadnych postów.
                </div>
                <div id="pr-posts-found" v-else-if="postsFetchingStatus === 'found'">
                    <user-post-component 
                        v-for="post in posts" 
                        :key="post.id"
                        :post="post"
                    />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ImageComponent from '../components/ImageComponent.vue'
import FollowersCounterComponent from '../components/FollowersCounterComponent.vue'
import FollowingsCounterComponent from '../components/FollowingsCounterComponent.vue'
import PostsCounterComponent from '../components/PostsCounterComponent.vue'
import UserPostComponent from '../components/UserPostComponent.vue'
import axios from "axios"

export default {
  name: 'ProfileView',
  components: {
    ImageComponent,
    FollowersCounterComponent,
    FollowingsCounterComponent,
    PostsCounterComponent,
    UserPostComponent
  },
  data(){
    return {
        userId: this.$route.params.userId,
        userFetchingStatus: 'fetching',
        postsFetchingStatus: 'fetching',
        user: { },
        posts: []
    }
  },
  methods:{
    getUser(){
        axios.get('http://localhost:8090/users/' + localStorage.getItem('userId'), {
            headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
        .then(data => {
            this.user = data.data;
            this.userFetchingStatus = 'found';
            this.getUserPosts();
        })
        .catch(e => {
            console.log(e);
            this.userFetchingStatus = 'not-found';
        })
    },

    getUserPosts(){
        axios.get('http://localhost:8090/posts/user/' + localStorage.getItem('userId'), {
            headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
        .then(data => {
            this.posts = data.data;
            this.postsFetchingStatus = 'found';
        })
        .catch(e => {
            console.log(e);
            this.postsFetchingStatus = 'not-found';
        })
    },

    goBack(){
        if(window.history.length > 1)
            this.$router.go(-1);
        else
            this.$router.push({ name: 'WallView' })
    },
  },
  created(){
    this.getUser();
  }
}


</script>

<style>

#profile-page {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 4%;
}

#profile-loading {
    font-size: 16px;
    text-align: center;
}

#profile-not-found {
    font-size: 20px;
    text-align: center;
}

#profile-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    border-radius: 10px;
    padding: 20px;

    width: 1000px;
    background-color: white;
}

#pr-top {
    display: flex;
    flex-direction: row;
    margin-bottom: 30px;
}

#pr-user-img {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 10px;
    width: 200px;
    height: 180px;
}

#pr-user-img img {
    width: 150px;
    height: 150px;
    object-fit: cover;
    border-radius: 50%;
    border: 3px solid #ffffff;
    -webkit-box-shadow: 1px 1px 4px #888888;
    -moz-box-shadow:    1px 1px 4px #888888;
    box-shadow:         1px 1px 4px #888888; 
}

#pr-user-info {
    display: flex;
    flex-direction: column;
    width: 100%;
    flex: 1;
}

#pr-user-info-top {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-between;
    align-items: flex-start;
}

#pr-user-name {
    font-weight: bold;
    font-size: 22px;
}

#pr-user-info-bottom {
    display: flex;
    flex-direction: column;
}

#pr-user-stats {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-between;
    border-bottom: 1px solid #b6b6b6;
    padding-bottom: 5px;
    margin-top: 15px;
    margin-bottom: 10px;
}

#pr-user-stats span {
    font-weight: bold;
}

#pr-bottom {
    border-radius: 5px;
    padding: 10px;
}

#pr-posts-loading, #pr-posts-not-found {
    text-align: center;
    padding-top: 10px;
    padding-bottom: 10px;
}

#pr-posts-found {
    display: flex;
    flex-wrap: wrap;
}

#pr-posts-found #grid-img {
    position: relative;
    flex: 1 0 15rem;
    height: 310px;
    min-width: 250px;
    padding: 5px;
}

#pr-posts-found #grid-img img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 5px;
    -webkit-box-shadow: 2px 2px 4px #888888;
    -moz-box-shadow:    2px 2px 4px #888888;
    box-shadow:         2px 2px 4px #888888; 

}

#pr-posts-found #grid-img:hover {
    opacity: .95;
    padding: 1px;
}



@media (max-width: 1000px) { 
    #profile-page {
        margin-top: 0;
        width: 100%;
    }
    
    #profile-container {
        width: 100%;
        border-radius: 0;
    }

    #pr-top {
        margin-bottom: 15px;
    }

    #pr-user-stats {
        flex-direction: column;
        margin-top: 5px;
    }
}

@media (max-width: 425px) { 
    #profile-container {
        padding: 0;
    }

    #pr-top {
        flex-direction: column;
    }

    #pr-user-info {
        padding: 10px;
    }    

    #pr-user-img{
        width: 100%;
        height: 200px;
        margin-right: 0;
    }

    #pr-user-img img{
        width: 100%;
        height: 200px;
        border-radius: 0;
        border: 0px;
    }
}

</style>