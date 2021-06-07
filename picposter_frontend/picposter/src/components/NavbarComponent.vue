<template>
    <nav class="navbar fixed-top navbar-dark bg-info navbar-expand-md">
        <router-link :to="{ name: 'WallView' }">
            <span class="navbar-brand">PicPoster</span>
        </router-link>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" 
                aria-controls="navbarToggler" aria-expanded="true" aria-label="Toggle navigation"
                v-if="user.id">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarToggler" v-if="user.id">
            <div id="navbarButtons">
                <div id="navbarLeftButtons">
                    <form class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="search" placeholder="Szukaj" aria-label="Search">
                        <button class="btn btn-light my-2 my-sm-0" type="submit"><i class="fas fa-search"></i></button>
                    </form>
                </div>
                <div id="navbarRightButtons">
                    <router-link :to="{ name: 'AddPostView' }">
                        <button class="btn btn-light px-4 mr-3">Dodaj zdjęcie</button>
                    </router-link>
                    <form @submit.prevent="handleLogout">
                        <button type="submit" class="btn btn-light px-4 mr-3">Wyloguj</button>
                    </form>
                </div>
                
            </div>
            
        </div>
        <div v-if="user.id">
            <router-link :to="{ name: 'ProfileView', params: { userId: user.id } }">
                <div id="navbar-user-img">
                    <image-component :filename="user.profileImagePath"/>
                </div>
            </router-link>
        </div>
    </nav>
</template>

<script>
import ImageComponent from '../components/ImageComponent.vue'
import axios from "axios"

export default {
    name: 'navbar-component',
    components: {
        ImageComponent
    },
    data(){
        return {
            user: {}
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
            })
            .catch(e => {
                console.log(e);
            })
        },

        handleLogout(){
            localStorage.removeItem('userId');
            localStorage.removeItem('token');
            this.$router.push(this.$route.query.redirect || '/')
        },

    },
    mounted(){
        if(localStorage.getItem('token'))
            this.getUser()
    }
}
</script>

<style>

#navbarButtons {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
}

#navbarLeftButtons {
    display: flex;
    flex-direction: row;
}

#navbarRightButtons {
    display: flex;
}

#navbar-user-img {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 70px;
    height: 70px;
}

#navbar-user-img img {
    width: 70px;
    height: 70px;
    object-fit: cover;
    border-radius: 50%;
    border: 2px solid #ffffff;
    -webkit-box-shadow: 1px 1px 4px #888888;
    -moz-box-shadow:    1px 1px 4px #888888;
    box-shadow:         1px 1px 4px #888888; 
}

@media (max-width: 630px) { 
    #navbarButtons {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: flex-start;
    }

    #navbarRightButtons {
        width: 100%;
        margin-bottom: 10px;
    }
}

</style>